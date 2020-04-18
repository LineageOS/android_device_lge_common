/*
 * Copyright (C) 2011-2015 The CyanogenMod Project
 * Copyright (C) 2017-2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <sys/wait.h>
#include <errno.h>
#include <log/log.h>
#include <ctype.h>
#include <cutils/properties.h>
#include <fcntl.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

#include "hwaddrs.h"


/* the C preprocessor can be a **** to work with */
#ifdef HWADDRS_MAC_PREFIX
#define HWADDRS_MAC_PREFIX_STR	_EXPAND(HWADDRS_MAC_PREFIX)
#endif

#define _EXPAND(str) __EXPAND(str)
#define __EXPAND(str) #str


struct misc_entry {
	char *const datamiscname;
	char *const persistname;
	uint64_t offset;
	char *const prefix;
};


// Validates the contents of the given file
int checkAddr(const char *const filepath, const char *const prefix,
const unsigned mode)
{
	int notallzeroes = 0;
	char charbuf[20]; /* needs to be more than 18 characters */
	int i;
	struct stat stat;

	int checkfd = open(filepath, O_RDONLY);

	if (checkfd < 0) {
		if (errno != ENOENT) goto corrupt;

		__android_log_print(ANDROID_LOG_INFO, TAG,
"File \"%s\" doesn't exist", filepath);
		return 0;
	}

	if (lstat(filepath, &stat) < 0 || !S_ISREG(stat.st_mode)) goto corrupt;
	if (mode != (stat.st_mode&mode)) goto corrupt;

	if (prefix) {
		if (strlen(prefix) > sizeof(charbuf)) {
			__android_log_print(ANDROID_LOG_ERROR, TAG,
"Internal error, prefix \"%s\" exceeds buffer length of %lu.", prefix,
sizeof(charbuf));
			close(checkfd);
			return 0;
		}

		if (read(checkfd, charbuf, strlen(prefix)) != (ssize_t)strlen(prefix)) goto corrupt;
		if (memcmp(charbuf, prefix, strlen(prefix))) goto corrupt;
	}

	/* there should be 18 characters, more indicates junk at end */
	if (read(checkfd, charbuf, sizeof(charbuf)) != 18) goto corrupt;
	if (!isspace(charbuf[17])) goto corrupt;
	for (i = 0; i < 17; i++) {
		if (i % 3 != 2) {
			if (!isxdigit(charbuf[i])) goto corrupt;
			notallzeroes |= charbuf[i] - '0';
		} else if (charbuf[i] != ':') goto corrupt;
	}

	if (!notallzeroes) goto corrupt;

	__android_log_print(ANDROID_LOG_INFO, TAG, "File \"%s\" validated",
filepath);

	close(checkfd);

	return 1;

corrupt:
	__android_log_print(ANDROID_LOG_INFO, TAG,
"Removing corrupt \"%s\" file", filepath);
	if (unlink(filepath) < 0) __android_log_print(ANDROID_LOG_ERROR,
TAG, "unlink() failed: %s", strerror(errno));

	if (checkfd >= 0) close(checkfd);

	return 0;
}


static int readAddr(const struct misc_entry *const entry, macaddr_t *buf)
{
	const char *errmsg;
	pid_t child;
	int status;
	int comm[2]={-1, -1};

	if(pipe(comm)<0)
		errmsg="pipe";
	else if((child=fork())<0)
		errmsg="fork";
	else if(!child) { /* child process */
		char offstr[16];
		char *const args[]={"hwaddrs", offstr, NULL};

		/* this closes old stdout */
		if(dup2(comm[1], 1)<0) {
			__android_log_print(ANDROID_LOG_ERROR, TAG,
"%s() failed: %s; unable to retrieve true MAC address", "dup2",
strerror(errno));
			exit(1);
		}

		/* Linux's behavior is close() invalidates the FD as we're not
		** writing to these, errors are irrelevant */
		close(comm[0]);
		close(comm[1]);

		snprintf(offstr, sizeof(offstr), "%lX", entry->offset);

		execve("/system/bin/hwaddrs.readmisc", args, environ);

		/* child process, so must do this ourselves and *exit*! */
		/* hopefully compiler reuses string */
		__android_log_print(ANDROID_LOG_ERROR, TAG,
"%s() failed: %s; unable to retrieve true MAC address", "execve",
strerror(errno));

		exit(1);
	} else if(close(comm[1]), comm[1]=-1, waitpid(child, &status, 0)<0)
		errmsg="waitpid";
	else if(!WIFEXITED(status)) {
		__android_log_print(ANDROID_LOG_ERROR, TAG,
"child process failed to exit; retrieving true MAC address failed");
		close(comm[0]);
		return 0;
	} else if(WEXITSTATUS(status)) { /* non-zero process exit is false */
		close(comm[0]);
		return 0; /* child provides log message in this case */
	} else if(read(comm[0], buf, sizeof(macaddr_t))!=sizeof(macaddr_t))
		errmsg="read";
	else {
		close(comm[0]);
		return 1; /* zero process exit status is true */
	}

	__android_log_print(ANDROID_LOG_ERROR, TAG,
"%s() failed: %s; unable to retrieve true MAC address",
errmsg, strerror(errno));

	if(comm[0]>=0)
		close(comm[0]);
	if(comm[1]>=0)
		close(comm[1]);

	return 0;
}


// Writes a file using an address from the misc partition
// Generates a random address if the one read contains only zeroes
void writeAddr(const struct misc_entry *const entry)
{
	const char *const filepath = entry->persistname;
	const char *const prefix = entry->prefix;

	macaddr_t macbytes;
	char macbuf[19];
	unsigned int i, macnums = 0;
	int miscfd = -1;
	int writefd;
	const char *errmsg = NULL;

	if (readAddr(entry, &macbytes))
		for (i = 0; i < sizeof(macaddr_t); ++i) macnums |= macbytes.macaddr[i];

	if ((writefd = open(filepath, O_WRONLY|O_CREAT|O_EXCL, S_IRUSR)) < 0) {
		errmsg = "open() of \"%s\" failed: %s";
		goto abort;
	}


	__android_log_print(ANDROID_LOG_INFO, TAG, "Using %s for \"%s\"",
macnums?"data from misc":"random data", filepath);

	if (macnums == 0) {
		const char rerr[] = "read() of /dev/urandom failed: %2$s";
		char product_name[PROPERTY_VALUE_MAX];
		property_get("ro.product.name", product_name, "");

		if ((miscfd = open("/dev/urandom", O_RDONLY)) < 0) {
			errmsg = "open() of /dev/urandom failed: %2$s";
			goto abort;
		}

#ifdef HWADDRS_MAC_PREFIX
		memcpy(&macbytes, HWADDRS_MAC_PREFIX_STR, HWADDRS_MAC_PREFIX_LEN);

#define TMP (sizeof(macbytes)-(HWADDRS_MAC_PREFIX_LEN))
		if (read(miscfd, macbytes.macaddr+HWADDRS_MAC_PREFIX_LEN, TMP) != TMP) {
#undef TMP
			errmsg = rerr;
			goto abort;
		}
#else
		if (read(miscfd, &macbytes, sizeof(macbytes)) != sizeof(macbytes)) {
			errmsg = rerr;
			goto abort;
		}

		// Last two bits of the first octet are special
		macbytes.macaddr[0] = macbytes.macaddr[0] << 2 | 0b10;
#endif

		close(miscfd);
		miscfd = -1;
	}

	if (prefix && write(writefd, prefix, strlen(prefix)) != (ssize_t)strlen(prefix)) {
		errmsg = "write() of \"%s\" failed: %s";
		goto abort;
	}
	snprintf(macbuf, sizeof(macbuf), "%02x:%02x:%02x:%02x:%02x:%02x\n",
			macbytes.macaddr[0], macbytes.macaddr[1], macbytes.macaddr[2], macbytes.macaddr[3], macbytes.macaddr[4], macbytes.macaddr[5]);
	if (write(writefd, &macbuf, 18) != 18) {
		errmsg = "write() of \"%s\" failed: %s";
		goto abort;
	}
	if (close(writefd) < 0) {
		errmsg = "close() of \"%s\" failed: %s";
		goto abort;
	}
	return;

abort:
	__android_log_print(ANDROID_LOG_ERROR, TAG, errmsg, filepath,
strerror(errno));

	if (miscfd >= 0) close(miscfd);
	if (writefd >= 0) {
		__android_log_print(ANDROID_LOG_INFO, TAG,
"Removing failed \"%s\" file", filepath);
		/* unlink() first so file contents may never exit FS journal */
		if (unlink(filepath) < 0) __android_log_print(ANDROID_LOG_ERROR,
TAG, "unlink() failed: %s", strerror(errno));
		close(writefd);
	}
}

// Simple file copy
void copyAddr(const char *const source, const char *const dest)
{
	char buffer[128];
	ssize_t bufcnt;
	int sourcefd = open(source, O_RDONLY);
	int destfd = open(dest, O_WRONLY|O_CREAT|O_EXCL, S_IRUSR|S_IRGRP|S_IROTH);
	const char *errmsg;

	if (sourcefd < 0) {
		errmsg = "open() of \"%3$s\" failed: %2$s";
		goto abort;
	}
	if (destfd < 0) {
		errmsg = "open() of \"%s\" failed: %s";
		goto abort;
	}

	while((bufcnt = read(sourcefd, buffer, sizeof(buffer))) > 0) {
		if (write(destfd, buffer, bufcnt) != bufcnt) {
			errmsg = "write() of \"%s\" failed: %s";
			goto abort;
		}
	}

	if (bufcnt < 0) {
		errmsg = "read() of \"%3$s\" failed: %2$s";
		goto abort;
	}

	close(sourcefd);
	sourcefd = -1;
	if (close(destfd) < 0) {
		errmsg = "close() of \"%s\" failed: %s";
		goto abort;
	}
	return;

abort:
	__android_log_print(ANDROID_LOG_ERROR, TAG, errmsg,
dest, strerror(errno), source);

	if (sourcefd >= 0) close(sourcefd);
	if (destfd >= 0) {
		__android_log_print(ANDROID_LOG_INFO, TAG,
"Removing failed \"%s\" file", dest);
		/* unlink() first so file contents may never exit FS journal */
		if (unlink(dest) < 0) __android_log_print(ANDROID_LOG_ERROR,
TAG, "unlink() failed: %s", strerror(errno));
		close(destfd);
	}
}


void handlemac(const struct misc_entry *const entry)
{
	unsigned cond = 0;

	if (checkAddr(entry->datamiscname, entry->prefix, S_IRUSR|S_IRGRP|S_IROTH))
		cond |= 0b10;

	if (checkAddr(entry->persistname, entry->prefix, 0))
		cond |= 0b01;

	switch(cond) {
	case 0b00:
		writeAddr(entry);
	case 0b01:
		copyAddr(entry->persistname, entry->datamiscname);
		break;
	case 0b10:
		copyAddr(entry->datamiscname, entry->persistname);
/*	case 0b11: ; ** no action */
	}
}


int main()
{
	const struct misc_entry entries[]={
#ifdef HWADDRS_OFFSET_WIFI
		{
			"/data/misc/wifi/config",
			"/persist/.macaddr",
			HWADDRS_OFFSET_WIFI,
			"cur_etheraddr=",
		},
#endif
#ifdef HWADDRS_OFFSET_BLUETOOTH
		{
			"/data/misc/bluetooth/bdaddr",
			"/persist/.baddr",
			HWADDRS_OFFSET_BLUETOOTH,
			NULL,
		},
#endif
	};

	unsigned i;


	/* we are apparently invoked with a restrictive umask */
	umask(S_IWUSR|S_IWGRP|S_IWOTH);

	for(i=0; i<sizeof(entries)/sizeof(0[entries]); ++i) handlemac(entries+i);

	return 0;
}
