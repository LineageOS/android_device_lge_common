/*
 * Copyright (C) 2020 The LineageOS Project
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
#include <string.h>
#include <stdlib.h>

#include "hwaddrs.h"


/* the C preprocessor can be a **** to work with */
#ifdef HWADDRS_MISC_PATH
#define HWADDRS_MISC_PATH_STR	_EXPAND(HWADDRS_MISC_PATH)
#endif

#define _EXPAND(str) __EXPAND(str)
#define __EXPAND(str) #str

/* sorry about this section */
#ifndef HWADDRS_OFFSET_MAX
#  ifdef HWADDRS_OFFSET_WIFI
#    ifdef HWADDRS_OFFSET_BLUETOOTH
#      if HWADDRS_OFFSET_WIFI <= HWADDRS_OFFSET_BLUETOOTH
#        define HWADDRS_OFFSET_MAX HWADDRS_OFFSET_BLUETOOTH
#      else
#        define HWADDRS_OFFSET_MAX HWADDRS_OFFSET_WIFI
#      endif
#    else
#      define HWADDRS_OFFSET_MAX HWADDRS_OFFSET_WIFI
#    endif
#  else
#    define HWADDRS_OFFSET_MAX HWADDRS_OFFSET_BLUETOOTH
#  endif
#endif

#ifndef HWADDRS_OFFSET_MIN
#  ifdef HWADDRS_OFFSET_WIFI
#    ifdef HWADDRS_OFFSET_BLUETOOTH
#      if HWADDRS_OFFSET_WIFI <= HWADDRS_OFFSET_BLUETOOTH
#        define HWADDRS_OFFSET_MIN HWADDRS_OFFSET_WIFI
#      else
#        define HWADDRS_OFFSET_MIN HWADDRS_OFFSET_BLUETOOTH
#      endif
#    else
#      define HWADDRS_OFFSET_MIN HWADDRS_OFFSET_WIFI
#    endif
#  else
#    define HWADDRS_OFFSET_MIN HWADDRS_OFFSET_BLUETOOTH
#  endif
#endif

#ifndef HWADDRS_OFFSET_MASK
#define HWADDRS_OFFSET_MASK ((HWADDRS_OFFSET_MIN-1)&~(HWADDRS_OFFSET_MIN|HWADDRS_OFFSET_MAX))
#endif


int main(int argc, char **argv)
{
	const char *errmsg;
	unsigned long offset;
	int miscfd=-1;
	macaddr_t buf;
	char *end;

	if(argc!=2) {
		errmsg="Incorrect number of arguments";
		goto fail;
	}

	offset=strtoul(argv[1], &end, 16);
	if(!argv[1]||*end||offset<HWADDRS_OFFSET_MIN||offset>HWADDRS_OFFSET_MAX||offset&HWADDRS_OFFSET_MASK) {
		errmsg="Offset invalid or suspicious";
		goto fail;
	}


	if((miscfd=open(HWADDRS_MISC_PATH_STR, O_RDONLY))<0) {
		errmsg="open() of misc failed: %s";
		goto fail;
	}

	if(pread(miscfd, buf.macaddr, sizeof(macaddr_t), offset)!=sizeof(macaddr_t)) {
		errmsg="pread() of misc failed: %s";
		goto fail;
	}

	/* close is a bit silly given how this process is exiting */
	close(miscfd);

	if(write(1, buf.macaddr, sizeof(macaddr_t))!=sizeof(macaddr_t)) {
		errmsg="write() to return fd failed: %s";
		goto fail;
	}

	/* closing this one though is important */
	if(close(1)<0) {
		errmsg="close() of return fd failed: %s";
		goto fail;
	}

	return 0;


fail:
	__android_log_print(ANDROID_LOG_ERROR, TAG, errmsg, strerror(errno));

	/* close() if never opened or failed is suboptimal, but harmless **
	** this is also kind of silly given how our process is exiting */
	close(miscfd);

	return 1;
}
