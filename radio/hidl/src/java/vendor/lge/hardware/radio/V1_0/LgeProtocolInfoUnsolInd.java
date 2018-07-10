package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeProtocolInfoUnsolInd {
    public final ArrayList<Byte> data = new ArrayList();
    public int data_len;
    public int data_valid;
    public int param;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeProtocolInfoUnsolInd.class) {
            return false;
        }
        LgeProtocolInfoUnsolInd other = (LgeProtocolInfoUnsolInd) otherObject;
        return this.param == other.param && this.data_len == other.data_len && this.data_valid == other.data_valid && HidlSupport.deepEquals(this.data, other.data);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.param))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.data_len))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.data_valid))), Integer.valueOf(HidlSupport.deepHashCode(this.data))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".param = ");
        builder.append(this.param);
        builder.append(", .data_len = ");
        builder.append(this.data_len);
        builder.append(", .data_valid = ");
        builder.append(this.data_valid);
        builder.append(", .data = ");
        builder.append(this.data);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(32), 0);
    }

    public static final ArrayList<LgeProtocolInfoUnsolInd> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeProtocolInfoUnsolInd> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 32), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeProtocolInfoUnsolInd _hidl_vec_element = new LgeProtocolInfoUnsolInd();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 32));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.param = _hidl_blob.getInt32(0 + _hidl_offset);
        this.data_len = _hidl_blob.getInt32(4 + _hidl_offset);
        this.data_valid = _hidl_blob.getInt32(8 + _hidl_offset);
        int _hidl_vec_size = _hidl_blob.getInt32((16 + _hidl_offset) + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 1), _hidl_blob.handle(), (16 + _hidl_offset) + 0, true);
        this.data.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.data.add(Byte.valueOf(childBlob.getInt8((long) (_hidl_index_0 * 1))));
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(32);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeProtocolInfoUnsolInd> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeProtocolInfoUnsolInd) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 32));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.param);
        _hidl_blob.putInt32(4 + _hidl_offset, this.data_len);
        _hidl_blob.putInt32(_hidl_offset + 8, this.data_valid);
        int _hidl_vec_size = this.data.size();
        _hidl_blob.putInt32((_hidl_offset + 16) + 8, _hidl_vec_size);
        _hidl_blob.putBool((_hidl_offset + 16) + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 1);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            childBlob.putInt8((long) (_hidl_index_0 * 1), ((Byte) this.data.get(_hidl_index_0)).byteValue());
        }
        _hidl_blob.putBlob((_hidl_offset + 16) + 0, childBlob);
    }
}
