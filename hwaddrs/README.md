# Converting to the lge/common version of `hwaddrs`:

## init.qcom.rc

A section along the following is often inserted into rootdir/etc/init.qcom.rc.
This was left out of the lge/common area since the high degree of variability
makes maintenance look problematic.

```
service vendor.conn_init /vendor/bin/hwaddrs**2**
    class core
    user root
    group system wifi **bluetooth**
    oneshot
```
"vendor.conn_init" is used for LineageOS 16.0 and later, earlier devices may
have an existing "conn_init" section.  If updating an earlier build, the
"group" line is likely to need modification.  The program is `hwaddrs2` in
order to avoid conflicts with existing devices.

Observations seem to hint "vendor.conn_init" is triggered **before** userdata
is decrypted on devices which support Full Device Encryption.  There _may_ be a
need for `on property:vold.decrypt=trigger_restart_framework`,
`start vendor.conn_init`.

On earlier builds the following segment is often included.

```
on property:init.svc.conn_init=stopped
    chmod 0644 /data/misc/wifi/config
    chown system wifi /data/misc/wifi/config
    chmod 0644 /data/misc/bluetooth/bdaddr
    chown bluetooth bluetooth /data/misc/bluetooth/bdaddr
```
With the version of `hwaddrs` in common/lge this segment (or equivalent) is
unnecessary and **should be removed**.  The underlying issue was `init` runs
with a umask() of 0077, which resulted in the public read bits being masked
off.  The original author of `hwaddrs` likely never tried to confirm whether
the `chown` was absolutely necessary.  This version of `hwaddrs` polices the
permissions of the files itself and will correct them if needed.  As such
leaving the section in place is mildly **detrimental to devices**.

## <device-common>.mk

The initial commit of android_device_lge_common included a common.mk file.
Most devices haven't added
"$(call inherit-product, device/lge/common/common.mk)" in their device.mk or
<device_family>.mk file.  This is required for common to add `hwaddrs2` and its
helper to $(PRODUCT_PACKAGES).

Once this is done, lge/common will takeover the value of "ro.bt.bdaddr_path".
If the device or family is handles Bluetooth MAC addresses itself, it needs to
set the variable $(HWADDRS_DISABLE_BLUETOOTH) to true in device.mk.

## Configuration

The following values control the behavior of `hwaddrs` and can be set in
BoardConfigCommon.mk at build time.

* **HWADDRS_OFFSET_WIFI**
This specifies the offset into _misc_ where the 802.11 MAC address can be
found.  For devices with a block size of 2KB (raw flash or eMMC) this is often
0x3000.  For devices with a block size of 4KB (UFS) this is often 0x6000.
_This value is **required** to enable retrieval of 802.11 MAC address._
* **HWADDRS_OFFSET_BLUETOOTH**
This specifies the offset into _misc_ where the Bluetooth MAC address can be
found.  For devices with a block size of 2KB (raw flash or eMMC) this is often
0x3000.  For devices with a block size of 4KB (UFS) this is often 0x8000.
_This value is **required** to enable retrieval of Bluetooth MAC address._
* **HWADDRS_MISC_PATH**
Specifies the path to the _misc_ area.  Generally this is of the form
"/dev/block/<something>/misc".  If left unset the value
"/dev/block/bootdevice/by-name/misc" will be used, which is appropriate for
GPT-using devices (eMMC or UFS); direct-mapped flash devices will need this to
be set.
* **HWADDRS_MAC_PREFIX**
If the _misc_ area has been wiped a substitute value will be generated.  This
variable is used to set the MAC prefix (first 3 bytes).  Due to this being a
binary value, setting it is interesting.  If the prefix bytes are 0xDEADBE then
the line `HWADDRS_MAC_PREFIX := \\xDE\\xAD\\xBE` should be used in
BoardConfigCommon.mk.  **Important**: This _must_ be at least 3 bytes, less
than 3 bytes will result in undesirable behavior by `hwaddrs`; additional bytes
can be used to make the value findable in the executable (using
`\\xDE\\xAD\\xBEprefix-macaddr-search` will allow searching for
"prefix-macaddr-search" in a binary editor and checking the prefix).
* **HWADDRS_DISABLE_BLUETOOTH**
This is used to disable the setting of "ro.bt.bdaddr_path".  This **shouldn't**
ever be needed, but is provided to cover the unusual case where common.mk is
brought in, but the Bluetooth MAC functionality is undesired.  Note this
must be set in _<device-common>.mk_ or _device.mk_ instead of
_BoardConfigCommon.mk_.
* **HWADDRS_OFFSET_MIN**
_Generally this is left unset_.  Effects behavior of the `hwaddrs.readmisc`
helper program.  If an offset into _misc_ before this is specified then
`hwaddrs.readmisc` will log the error "Offset invalid or suspicious" and fail.
As a _security feature_ a good default is used if left unset.
* **HWADDRS_OFFSET_MAX**
_Generally this is left unset_.  Effects behavior of the `hwaddrs.readmisc`
helper program. If an offset into _misc_ after this is specified then
`hwaddrs.readmisc` will log the error "Offset invalid or suspicious" and fail.
As a _security feature_ a good default is used if left unset.
* **HWADDRS_OFFSET_MASK**
_Generally this is left unset_.  Effects behavior of the `hwaddrs.readmisc`
helper program.  If an offset into _misc_ which has corresponding bits set is
specified then `hwaddrs.readmisc` will log the error
"Offset invalid or suspicious" and fail.  As a _security feature_ a good
default is used if left unset.

These are roughly in order of importance to set.  If neither
$(HWADDRS_OFFSET_WIFI) nor $(HWADDRS_OFFSET_BLUETOOTH) is set, then `hwaddrs`
won't even be built from common/lge.  If $(HWADDRS_MISC_PATH) is left unset
there is a default, but this is appropriate for eMMC/UFS devices not
direct-mapped flash devices.  The other values can safely be left unset, though
leaving $(HWADDRS_MAC_PREFIX) unset will result in a completely random MAC
address being used if _misc_ is wiped.
