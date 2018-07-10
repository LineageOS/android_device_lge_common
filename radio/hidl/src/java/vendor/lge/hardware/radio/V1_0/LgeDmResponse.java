package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeDmResponse {
    public int cmd;
    public int result;
    public final byte[] rsp = new byte[1028];
    public int rsp_len;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeDmResponse.class) {
            return false;
        }
        LgeDmResponse other = (LgeDmResponse) otherObject;
        return this.result == other.result && this.cmd == other.cmd && this.rsp_len == other.rsp_len && HidlSupport.deepEquals(this.rsp, other.rsp);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.result))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cmd))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rsp_len))), Integer.valueOf(HidlSupport.deepHashCode(this.rsp))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".result = ");
        builder.append(this.result);
        builder.append(", .cmd = ");
        builder.append(this.cmd);
        builder.append(", .rsp_len = ");
        builder.append(this.rsp_len);
        builder.append(", .rsp = ");
        builder.append(Arrays.toString(this.rsp));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(1040), 0);
    }

    public static final ArrayList<LgeDmResponse> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeDmResponse> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 1040), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeDmResponse _hidl_vec_element = new LgeDmResponse();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 1040));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.result = _hidl_blob.getInt32(0 + _hidl_offset);
        this.cmd = _hidl_blob.getInt32(4 + _hidl_offset);
        this.rsp_len = _hidl_blob.getInt32(8 + _hidl_offset);
        long _hidl_array_offset_0 = _hidl_offset + 12;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 1028; _hidl_index_0_0++) {
            this.rsp[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(1040);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeDmResponse> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 1040);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeDmResponse) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 1040));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.result);
        _hidl_blob.putInt32(4 + _hidl_offset, this.cmd);
        _hidl_blob.putInt32(8 + _hidl_offset, this.rsp_len);
        long _hidl_array_offset_0 = _hidl_offset + 12;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 1028; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.rsp[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
    }
}
