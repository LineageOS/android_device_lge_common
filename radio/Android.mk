# Copyright 2018 LGE Inc.
# Copyright 2018 The LineageOS Project
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

LOCAL_PATH := $(call my-dir)

# vendor.lge.hardware.radio-V1.0-java.jar
include $(CLEAR_VARS)
LOCAL_MODULE := vendor.lge.hardware.radio-V1.0-java
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_MODULE_TAGS := optional

LOCAL_JAVA_LIBRARIES := telephony-common ims-common

LOCAL_SRC_FILES := \
    $(call all-java-files-under, hidl/src/java)

include $(BUILD_JAVA_LIBRARY)

# vendor.lge.hardware.radio-V1.0-java-static.jar
include $(CLEAR_VARS)
LOCAL_MODULE := vendor.lge.hardware.radio-V1.0-java-static
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_MODULE_TAGS := optional

LOCAL_JAVA_LIBRARIES := telephony-common ims-common

LOCAL_SRC_FILES := \
    $(call all-java-files-under, hidl/src/java)

LOCAL_STATIC_JAVA_LIBRARIES := \
    android.hidl.base-V1.0-java-static \

include $(BUILD_STATIC_JAVA_LIBRARY)

# telephony-lge.jar
include $(CLEAR_VARS)
LOCAL_MODULE := telephony-lge
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_MODULE_TAGS := optional

LOCAL_JAVA_LIBRARIES := telephony-common ims-common

LOCAL_SRC_FILES := \
    $(call all-java-files-under, telephony/src/java)

LOCAL_STATIC_JAVA_LIBRARIES := \
    ims-ext-common \
    vendor.lge.hardware.radio-V1.0-java-static

include $(BUILD_JAVA_LIBRARY)

include $(call all-makefiles-under,$(LOCAL_PATH))
