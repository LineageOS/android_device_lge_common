package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeNsriNotice {
    public byte bsend;
    public final byte[] nsri_phonenum = new byte[11];
    public int phoneNumLen;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeNsriNotice.class) {
            return false;
        }
        LgeNsriNotice other = (LgeNsriNotice) otherObject;
        return HidlSupport.deepEquals(this.nsri_phonenum, other.nsri_phonenum) && this.phoneNumLen == other.phoneNumLen && this.bsend == other.bsend;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(this.nsri_phonenum)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.phoneNumLen))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.bsend)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".nsri_phonenum = ");
        builder.append(Arrays.toString(this.nsri_phonenum));
        builder.append(", .phoneNumLen = ");
        builder.append(this.phoneNumLen);
        builder.append(", .bsend = ");
        builder.append(this.bsend);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(20), 0);
    }

    public static final ArrayList<LgeNsriNotice> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeNsriNotice> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 20), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeNsriNotice _hidl_vec_element = new LgeNsriNotice();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 20));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        long _hidl_array_offset_0 = _hidl_offset + 0;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 11; _hidl_index_0_0++) {
            this.nsri_phonenum[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
        this.phoneNumLen = _hidl_blob.getInt32(12 + _hidl_offset);
        this.bsend = _hidl_blob.getInt8(16 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(20);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeNsriNotice> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 20);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeNsriNotice) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 20));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        long _hidl_array_offset_0 = _hidl_offset + 0;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 11; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.nsri_phonenum[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
        _hidl_blob.putInt32(12 + _hidl_offset, this.phoneNumLen);
        _hidl_blob.putInt8(16 + _hidl_offset, this.bsend);
    }
}
