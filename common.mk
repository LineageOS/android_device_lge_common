#
# Copyright (C) 2016 The CyanogenMod Project
# Copyright (C) 2017,2020 The LineageOS Project
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

# WiFi/Bluetooth MAC addresses
PRODUCT_PACKAGES += \
    hwaddrs2 \
    hwaddrs.readmisc

ifeq (,$(HWADDRS_DISABLE_BLUETOOTH))
# Bluetooth
PRODUCT_PROPERTY_OVERRIDES += \
    ro.bt.bdaddr_path="/data/misc/bluetooth/bdaddr"
endif
