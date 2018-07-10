package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeSetQdmMaskTableRsp {
    public final byte[] config_rsp1 = new byte[1024];
    public final byte[] config_rsp2 = new byte[1024];
    public final byte[] config_rsp3 = new byte[2];
    public int config_rsp_len;
    public byte result;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeSetQdmMaskTableRsp.class) {
            return false;
        }
        LgeSetQdmMaskTableRsp other = (LgeSetQdmMaskTableRsp) otherObject;
        return this.result == other.result && this.config_rsp_len == other.config_rsp_len && HidlSupport.deepEquals(this.config_rsp1, other.config_rsp1) && HidlSupport.deepEquals(this.config_rsp2, other.config_rsp2) && HidlSupport.deepEquals(this.config_rsp3, other.config_rsp3);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.result))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.config_rsp_len))), Integer.valueOf(HidlSupport.deepHashCode(this.config_rsp1)), Integer.valueOf(HidlSupport.deepHashCode(this.config_rsp2)), Integer.valueOf(HidlSupport.deepHashCode(this.config_rsp3))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".result = ");
        builder.append(this.result);
        builder.append(", .config_rsp_len = ");
        builder.append(this.config_rsp_len);
        builder.append(", .config_rsp1 = ");
        builder.append(Arrays.toString(this.config_rsp1));
        builder.append(", .config_rsp2 = ");
        builder.append(Arrays.toString(this.config_rsp2));
        builder.append(", .config_rsp3 = ");
        builder.append(Arrays.toString(this.config_rsp3));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(2060), 0);
    }

    public static final ArrayList<LgeSetQdmMaskTableRsp> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeSetQdmMaskTableRsp> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 2060), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeSetQdmMaskTableRsp _hidl_vec_element = new LgeSetQdmMaskTableRsp();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 2060));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        this.result = _hidl_blob.getInt8(0 + _hidl_offset);
        this.config_rsp_len = _hidl_blob.getInt32(4 + _hidl_offset);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            this.config_rsp1[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 1032;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            this.config_rsp2[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 2056;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 2; _hidl_index_0_0++) {
            this.config_rsp3[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(2060);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeSetQdmMaskTableRsp> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 2060);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeSetQdmMaskTableRsp) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 2060));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        _hidl_blob.putInt8(0 + _hidl_offset, this.result);
        _hidl_blob.putInt32(4 + _hidl_offset, this.config_rsp_len);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config_rsp1[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 1032;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 1024; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config_rsp2[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
        _hidl_array_offset_0 = _hidl_offset + 2056;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 2; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config_rsp3[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
    }
}
