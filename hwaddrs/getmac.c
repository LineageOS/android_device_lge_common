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

#include <errno.h>
#include <log/log.h>
#include <ctype.h>
#include <cutils/properties.h>
#include <fcntl.h>
#include <stdint.h>
#include <openssl/sha.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>


#undef LOG_TAG
static const char LOG_TAG[]="hwaddrs";

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
	int notallzeroes=0;
	char charbuf[17];
	int i;
	struct stat stat;
	int checkfd=open(filepath, O_RDONLY);

	if(checkfd<0) { // doesn't exist/error
		if(errno!=ENOENT) goto corrupt;

		ALOGI("File \"%s\" doesn't exist", filepath);
		return 0;
	}

	if(lstat(filepath, &stat)<0||!S_ISREG(stat.st_mode)) goto corrupt;
	if(mode!=(stat.st_mode&mode)) goto corrupt;

	if(prefix) {
		if(strlen(prefix)>sizeof(charbuf)) {
			ALOGE(
"Internal error, prefix \"%s\" exceeds buffer length of %zu.", prefix,
sizeof(charbuf));
			close(checkfd);
			return 0;
		}

		if(read(checkfd, charbuf, strlen(prefix))!=
(ssize_t)strlen(prefix)) goto corrupt;
		if(memcmp(charbuf, prefix, strlen(prefix))) goto corrupt;
	}

	if(read(checkfd, charbuf, 17)!=17) goto corrupt;
	for(i=0; i<17; i++) {
		if(i%3!=2) {
			if(!isxdigit(charbuf[i])) goto corrupt;
			notallzeroes|=charbuf[i]-'0';
		} else if(charbuf[i]!=':') goto corrupt;
	}

	if(!notallzeroes) goto corrupt;

	ALOGI("File \"%s\" validated", filepath);

	close(checkfd);

	return 1;

corrupt:
	ALOGI("File \"%s\" is corrupt", filepath);

	if(checkfd>=0) close(checkfd);

	return 0;
}


// Writes a file using an address from the misc partition
// Generates a random address if the one read contains only zeroes
void writeAddr(const struct misc_entry *const entry)
{
	const char *const filepath=entry->persistname;
	const char *const prefix=entry->prefix;

	uint8_t macbytes[6];
	char macbuf[19];
	unsigned int i, macnums=0;
	int miscfd=open("/dev/block/bootdevice/by-name/misc", O_RDONLY);
	int writefd=-1;
	const char *errmsg=NULL;

	if(miscfd<0) errmsg="open";
	else if(pread(miscfd, macbytes, sizeof(macbytes), entry->offset)!=
sizeof(macbytes)) errmsg="pread";
	else for(i=0; i<sizeof(macbytes); ++i) macnums|=macbytes[i];

	if(errmsg) ALOGE("%s() of misc failed: %s", errmsg, strerror(errno));

	/* close()ing if open() failed is suboptimal, but harmless */
	close(miscfd);
	miscfd=-1;

	ALOGI("Using %s for \"%s\"", macnums?"data from misc":"random data",
filepath);

	if(macnums==0) {
		char propval[PROPERTY_VALUE_MAX];
		unsigned char sum[SHA256_DIGEST_LENGTH];
		SHA256_CTX ctx;

		SHA256_Init(&ctx);

		property_get("ro.build.fingerprint", propval, "");
		SHA256_Update(&ctx, propval, strlen(propval)+1);

		property_get("ro.serialno", propval, "");
		SHA256_Update(&ctx, propval, strlen(propval)+1);

		/* Ensure different pseudo-MACs everywhere */
		SHA256_Update(&ctx, entry->datamiscname,
strlen(entry->datamiscname)+1);

		SHA256_Final(sum, &ctx);
		memcpy(&macbytes, sum, sizeof(macbytes));

		property_get("ro.product.name", propval, "");

		if(strstr(propval, "chuckwagon")) {
			macbytes[0]=0xDEu;
			macbytes[1]=0xADu;
			macbytes[2]=0xBEu;
		} else {
			// Last two bits of the first octet are special
			macbytes[0]=macbytes[0]<<2|0b10;
		}
	}

	if(unlink(filepath)<0&&errno!=ENOENT) {
		errmsg="unlink() of \"%s\" failed: %s";
		goto abort;
	}
	if((writefd=open(filepath, O_WRONLY|O_CREAT|O_EXCL, S_IRUSR))<0) {
		errmsg="open() of \"%s\" failed: %s";
		goto abort;
	}

	if(prefix&&write(writefd, prefix, strlen(prefix))!=(ssize_t)strlen(prefix)) {
		errmsg="write() of \"%s\" failed: %s";
		goto abort;
	}
	snprintf(macbuf, sizeof(macbuf), "%02x:%02x:%02x:%02x:%02x:%02x\n",
macbytes[0], macbytes[1], macbytes[2], macbytes[3], macbytes[4], macbytes[5]);
	if(write(writefd, &macbuf, 18)!=18) {
		errmsg="write() of \"%s\" failed: %s";
		goto abort;
	}
	if(close(writefd)<0) {
		errmsg="close() of \"%s\" failed: %s";
		goto abort;
	}
	return;

abort:
	ALOGE(errmsg, filepath, strerror(errno));

	if(miscfd>=0) close(miscfd);
	if(writefd>=0) {
		ALOGI("Removing failed \"%s\" file", filepath);
		/* unlink() first so file contents may never exit FS journal */
		if(unlink(filepath)<0) ALOGE("unlink() failed: %s",
strerror(errno));
		close(writefd);
	}
}


// Simple file copy
void copyAddr(const char *const source, const char *const dest)
{
	char buffer[128];
	ssize_t bufcnt;
	int sourcefd=open(source, O_RDONLY);
	int destfd=-1;
	const char *errmsg;

#define ERRMSG(call, file) #call "() of \"" file##NAME "\" failed: " file##ERROR
#define sourceNAME "%3$s"
#define sourceERROR "%2$s"
#define destNAME "%s"
#define destERROR "%s"

	if(sourcefd<0) { // doesn't exist/error
		errmsg=ERRMSG(open, source);
		goto abort;
	}

	if(unlink(dest)<0&&errno!=ENOENT) {
		errmsg=ERRMSG(unlink, dest);
		goto abort;
	}
	if((destfd=open(dest, O_WRONLY|O_CREAT|O_EXCL, S_IRUSR|S_IRGRP|S_IROTH))<0) {
		errmsg=ERRMSG(open, dest);
		goto abort;
	}

	while((bufcnt=read(sourcefd, buffer, sizeof(buffer)))>0) {
		if(write(destfd, buffer, bufcnt)!=bufcnt) {
			errmsg=ERRMSG(write, dest);
			goto abort;
		}
	}

	if(bufcnt<0) {
		errmsg=ERRMSG(read, source);
		goto abort;
	}

	close(sourcefd);
	sourcefd=-1;
	if(close(destfd)<0) {
		errmsg=ERRMSG(close, dest);
		goto abort;
	}
	return;

#undef ERRMSG
#undef sourceNAME
#undef sourceERROR
#undef destNAME
#undef destERROR

abort:
	ALOGE(errmsg, dest, strerror(errno), source);

	if(sourcefd>=0) close(sourcefd);
	if(destfd>=0) {
		ALOGI("Removing failed \"%s\" file", dest);
		/* unlink() first so file contents may never exit FS journal */
		if(unlink(dest)<0) ALOGE("unlink() failed: %s",
strerror(errno));
		close(destfd);
	}
}


void handlemac(const struct misc_entry *const entry)
{
	if(!checkAddr(entry->datamiscname, entry->prefix, S_IRUSR|S_IRGRP|S_IROTH)) {
		if(!checkAddr(entry->persistname, entry->prefix, 0))
			writeAddr(entry);
		copyAddr(entry->persistname, entry->datamiscname);
	}
}


int main()
{
	const struct misc_entry entries[]={
		{
			"/data/misc/wifi/config",
			"/persist/.macaddr",
			0xBEEF,
			"cur_etheraddr=",
		}, {
			"/data/misc/bluetooth/bdaddr",
			"/persist/.baddr",
			0xDEAD,
			NULL,
		},
	};

	unsigned i;


	/* we are apparently invoked with a restrictive umask */
	umask(S_IWUSR|S_IWGRP|S_IWOTH);

	for(i=0; i<sizeof(entries)/sizeof(0[entries]); ++i) handlemac(entries+i);

	return 0;
}
