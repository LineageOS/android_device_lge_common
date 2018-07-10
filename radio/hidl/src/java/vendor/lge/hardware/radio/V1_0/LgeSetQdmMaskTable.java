package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeSetQdmMaskTable {
    public int config_kind;
    public int config_num;
    public final byte[] config_set1 = new byte[1024];
    public final byte[] config_set2 = new byte[1024];
    public final byte[] config_set3 = new byte[2];

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeSetQdmMaskTable.class) {
            return false;
        }
        LgeSetQdmMaskTable other = (LgeSetQdmMaskTable) otherObject;
        return this.config_kind == other.config_kind && this.config_num == other.config_num && HidlSupport.deepEquals(this.config_set1, other.config_set1) && HidlSupport.deepEquals(this.config_set2, other.config_set2) && HidlSupport.deepEquals(this.config_set3, other.config_set3);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.config_kind))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.config_num))), Integer.valueOf(HidlSupport.deepHashCode(this.config_set1)), Integer.valueOf(HidlSupport.deepHashCode(this.config_set2)), Integer.valueOf(HidlSupport.deepHashCode(this.config_set3))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".config_kind = ");
        builder.append(this.config_kind);
        builder.append(", .config_num = ");
        builder.append(this.config_num);
        builder.append(", .config_set1 = ");
        builder.append(Arrays.toString(this.config_set1));
        builder.append(", .config_set2 = ");
        builder.append(Arrays.toString(this.config_set2));
        builder.append(", .config_set3 = ");
        builder.append(Arrays.toString(this.config_set3));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(2060), 0);
    }

    public static final ArrayList<LgeSetQdmMaskTable> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeSetQdmMaskTable> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 2060), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeSetQdmMaskTable _hidl_vec_element = new LgeSetQdmMaskTable();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 2060));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        this.config_kind = _hidl_blob.getInt32(0 + _hidl_offset);
        this.config_num = _hidl_blob.getInt32(4 + _hidl_offset);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            this.config_set1[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 1032;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            this.config_set2[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 2056;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 2; _hidl_index_0_0++) {
            this.config_set3[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(2060);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeSetQdmMaskTable> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 2060);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeSetQdmMaskTable) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 2060));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        _hidl_blob.putInt32(0 + _hidl_offset, this.config_kind);
        _hidl_blob.putInt32(4 + _hidl_offset, this.config_num);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config_set1[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 1032;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config_set2[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 2056;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 2; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config_set3[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
    }
}
