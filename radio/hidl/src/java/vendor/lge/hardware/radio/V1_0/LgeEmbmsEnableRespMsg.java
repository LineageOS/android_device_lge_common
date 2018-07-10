package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeEmbmsEnableRespMsg {
    public int interface_index;
    public byte interface_index_valid;
    public int response;
    public int trans_id;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsEnableRespMsg.class) {
            return false;
        }
        LgeEmbmsEnableRespMsg other = (LgeEmbmsEnableRespMsg) otherObject;
        return this.trans_id == other.trans_id && this.response == other.response && this.interface_index_valid == other.interface_index_valid && this.interface_index == other.interface_index;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.response))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.interface_index_valid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.interface_index)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .response = ");
        builder.append(this.response);
        builder.append(", .interface_index_valid = ");
        builder.append(this.interface_index_valid);
        builder.append(", .interface_index = ");
        builder.append(this.interface_index);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(16), 0);
    }

    public static final ArrayList<LgeEmbmsEnableRespMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsEnableRespMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 16), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsEnableRespMsg _hidl_vec_element = new LgeEmbmsEnableRespMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 16));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.response = _hidl_blob.getInt32(4 + _hidl_offset);
        this.interface_index_valid = _hidl_blob.getInt8(8 + _hidl_offset);
        this.interface_index = _hidl_blob.getInt32(12 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(16);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsEnableRespMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 16);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsEnableRespMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 16));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.trans_id);
        _hidl_blob.putInt32(4 + _hidl_offset, this.response);
        _hidl_blob.putInt8(8 + _hidl_offset, this.interface_index_valid);
        _hidl_blob.putInt32(12 + _hidl_offset, this.interface_index);
    }
}
