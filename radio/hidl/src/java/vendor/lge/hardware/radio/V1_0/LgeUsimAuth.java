package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeUsimAuth {
    public String aid = new String();
    public String autn = new String();
    public int autn_length;
    public String rand = new String();
    public int rand_length;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeUsimAuth.class) {
            return false;
        }
        LgeUsimAuth other = (LgeUsimAuth) otherObject;
        return HidlSupport.deepEquals(this.rand, other.rand) && this.rand_length == other.rand_length && HidlSupport.deepEquals(this.autn, other.autn) && this.autn_length == other.autn_length && HidlSupport.deepEquals(this.aid, other.aid);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(this.rand)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rand_length))), Integer.valueOf(HidlSupport.deepHashCode(this.autn)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.autn_length))), Integer.valueOf(HidlSupport.deepHashCode(this.aid))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".rand = ");
        builder.append(this.rand);
        builder.append(", .rand_length = ");
        builder.append(this.rand_length);
        builder.append(", .autn = ");
        builder.append(this.autn);
        builder.append(", .autn_length = ");
        builder.append(this.autn_length);
        builder.append(", .aid = ");
        builder.append(this.aid);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(64), 0);
    }

    public static final ArrayList<LgeUsimAuth> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeUsimAuth> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 64), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeUsimAuth _hidl_vec_element = new LgeUsimAuth();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 64));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.rand = _hidl_blob.getString(0 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.rand.getBytes().length + 1), _hidl_blob.handle(), 0 + (0 + _hidl_offset), false);
        this.rand_length = _hidl_blob.getInt32(16 + _hidl_offset);
        this.autn = _hidl_blob.getString(24 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.autn.getBytes().length + 1), _hidl_blob.handle(), 0 + (24 + _hidl_offset), false);
        this.autn_length = _hidl_blob.getInt32(40 + _hidl_offset);
        this.aid = _hidl_blob.getString(48 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.aid.getBytes().length + 1), _hidl_blob.handle(), 0 + (48 + _hidl_offset), false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeUsimAuth> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 64);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeUsimAuth) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 64));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.rand);
        _hidl_blob.putInt32(16 + _hidl_offset, this.rand_length);
        _hidl_blob.putString(24 + _hidl_offset, this.autn);
        _hidl_blob.putInt32(40 + _hidl_offset, this.autn_length);
        _hidl_blob.putString(48 + _hidl_offset, this.aid);
    }
}
