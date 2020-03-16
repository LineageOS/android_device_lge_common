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

#ifndef __HWADDRS_H__
#define __HWADDRS_H__

#include <stdint.h>


static const char HWADDRS_TAG[]="hwaddrs";

/* structures are used in typedefs so assignment works normally */
/* this MUST NOT be padded, so ensure the compiler blows up if so */
#pragma GCC diagnostic push
#pragma GCC diagnostic error "-Wpadded"
typedef struct {
	uint8_t macaddr[6];
} macaddr_t;
#pragma GCC diagnostic pop

#endif
