# Copyright (C) 2011-2015 The CyanogenMod project
# Copyright (C) 2017-2020 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH:= $(call my-dir)


ifneq (,$(HWADDRS_OFFSET_WIFI)$(HWADDRS_OFFSET_BLUETOOTH))

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := handlemac.c
LOCAL_SHARED_LIBRARIES := libcutils liblog
LOCAL_PRELINK_MODULE := false
LOCAL_MODULE := hwaddrs2
LOCAL_VENDOR_MODULE := true
# Shared variables:
ifdef HWADDRS_OFFSET_WIFI
LOCAL_CFLAGS += -DHWADDRS_OFFSET_WIFI=$(HWADDRS_OFFSET_WIFI)
endif
ifdef HWADDRS_OFFSET_BLUETOOTH
LOCAL_CFLAGS += -DHWADDRS_OFFSET_BLUETOOTH=$(HWADDRS_OFFSET_BLUETOOTH)
endif
# Only the file writing portion needs these:
ifdef HWADDRS_MAC_PREFIX
LOCAL_CFLAGS += -DHWADDRS_MAC_PREFIX=$(HWADDRS_MAC_PREFIX)
ifdef HWADDR_MAC_PREFIX_LEN
LOCAL_CFLAGS += -DHWADDRS_MAC_PREFIX_LEN=$(HWADDRS_MAC_PREFIX_LEN)
else
LOCAL_CFLAGS += -DHWADDRS_MAC_PREFIX_LEN=3
endif
endif
include $(BUILD_EXECUTABLE)


include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := readmisc.c
LOCAL_SHARED_LIBRARIES := libcutils liblog
LOCAL_PRELINK_MODULE := false
LOCAL_MODULE := hwaddrs.readmisc
LOCAL_VENDOR_MODULE := true
# Shared variables:
ifdef HWADDRS_OFFSET_WIFI
LOCAL_CFLAGS += -DHWADDRS_OFFSET_WIFI=$(HWADDRS_OFFSET_WIFI)
endif
ifdef HWADDRS_OFFSET_BLUETOOTH
LOCAL_CFLAGS += -DHWADDRS_OFFSET_BLUETOOTH=$(HWADDRS_OFFSET_BLUETOOTH)
endif
# Only the misc reading portion needs these:
ifdef HWADDRS_MISC_PATH
LOCAL_CFLAGS += -DHWADDRS_MISC_PATH=$(HWADDRS_MISC_PATH)
else
# appropriate for GPT-using devices, as such a reasonable default
LOCAL_CFLAGS += -DHWADDRS_MISC_PATH=/dev/block/bootdevice/by-name/misc
endif
ifdef HWADDRS_OFFSET_MIN
LOCAL_CFLAGS += -DHWADDRS_OFFSET_MIN=$(HWADDRS_OFFSET_MIN)
endif
ifdef HWADDRS_OFFSET_MAX
LOCAL_CFLAGS += -DHWADDRS_OFFSET_MAX=$(HWADDRS_OFFSET_MAX)
endif
ifdef HWADDRS_OFFSET_MASK
LOCAL_CFLAGS += -DHWADDRS_OFFSET_MASK=$(HWADDRS_OFFSET_MASK)
endif
include $(BUILD_EXECUTABLE)

endif
