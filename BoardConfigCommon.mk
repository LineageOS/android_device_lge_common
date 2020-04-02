#
# Copyright (C) 2016 The CyanogenMod Project
# Copyright (C) 2020 The LineageOS Project
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
#

BOARD_VENDOR := lge

ifneq (,$(HWADDRS_OFFSET_WIFI)$(HWADDRS_OFFSET_BLUETOOTH))
# SELinux policies
BOARD_SEPOLICY_DIRS += device/$(BOARD_VENDOR)/common/hwaddrs/sepolicy
endif
