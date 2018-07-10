package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgePbmRecords {
    public int ad_type;
    public final String[] additional_number = new String[3];
    public int device;
    public String email_address = new String();
    public int gas_id;
    public int index;
    public String name = new String();
    public String number = new String();
    public String second_name = new String();
    public int sync_cnt;
    public int type;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgePbmRecords.class) {
            return false;
        }
        LgePbmRecords other = (LgePbmRecords) otherObject;
        return this.device == other.device && this.index == other.index && this.type == other.type && this.ad_type == other.ad_type && HidlSupport.deepEquals(this.number, other.number) && HidlSupport.deepEquals(this.name, other.name) && HidlSupport.deepEquals(this.additional_number, other.additional_number) && HidlSupport.deepEquals(this.email_address, other.email_address) && HidlSupport.deepEquals(this.second_name, other.second_name) && this.gas_id == other.gas_id && this.sync_cnt == other.sync_cnt;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.device))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.index))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ad_type))), Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(this.additional_number)), Integer.valueOf(HidlSupport.deepHashCode(this.email_address)), Integer.valueOf(HidlSupport.deepHashCode(this.second_name)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.gas_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sync_cnt)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".device = ");
        builder.append(this.device);
        builder.append(", .index = ");
        builder.append(this.index);
        builder.append(", .type = ");
        builder.append(this.type);
        builder.append(", .ad_type = ");
        builder.append(this.ad_type);
        builder.append(", .number = ");
        builder.append(this.number);
        builder.append(", .name = ");
        builder.append(this.name);
        builder.append(", .additional_number = ");
        builder.append(Arrays.toString(this.additional_number));
        builder.append(", .email_address = ");
        builder.append(this.email_address);
        builder.append(", .second_name = ");
        builder.append(this.second_name);
        builder.append(", .gas_id = ");
        builder.append(this.gas_id);
        builder.append(", .sync_cnt = ");
        builder.append(this.sync_cnt);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(136), 0);
    }

    public static final ArrayList<LgePbmRecords> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgePbmRecords> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 136), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgePbmRecords _hidl_vec_element = new LgePbmRecords();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 136));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.device = _hidl_blob.getInt32(0 + _hidl_offset);
        this.index = _hidl_blob.getInt32(4 + _hidl_offset);
        this.type = _hidl_blob.getInt32(8 + _hidl_offset);
        this.ad_type = _hidl_blob.getInt32(12 + _hidl_offset);
        this.number = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.number.getBytes().length + 1), _hidl_blob.handle(), (16 + _hidl_offset) + 0, false);
        this.name = _hidl_blob.getString(32 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.name.getBytes().length + 1), _hidl_blob.handle(), (32 + _hidl_offset) + 0, false);
        long _hidl_array_offset_0 = _hidl_offset + 48;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 3; _hidl_index_0_0++) {
            this.additional_number[_hidl_index_0_0] = _hidl_blob.getString(_hidl_array_offset_0);
            parcel.readEmbeddedBuffer((long) (this.additional_number[_hidl_index_0_0].getBytes().length + 1), _hidl_blob.handle(), 0 + _hidl_array_offset_0, false);
            _hidl_array_offset_0 += 16;
        }
        this.email_address = _hidl_blob.getString(96 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.email_address.getBytes().length + 1), _hidl_blob.handle(), (96 + _hidl_offset) + 0, false);
        this.second_name = _hidl_blob.getString(112 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.second_name.getBytes().length + 1), _hidl_blob.handle(), (112 + _hidl_offset) + 0, false);
        this.gas_id = _hidl_blob.getInt32(128 + _hidl_offset);
        this.sync_cnt = _hidl_blob.getInt32(132 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(136);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgePbmRecords> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 136);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgePbmRecords) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 136));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.device);
        _hidl_blob.putInt32(4 + _hidl_offset, this.index);
        _hidl_blob.putInt32(8 + _hidl_offset, this.type);
        _hidl_blob.putInt32(12 + _hidl_offset, this.ad_type);
        _hidl_blob.putString(_hidl_offset + 16, this.number);
        _hidl_blob.putString(32 + _hidl_offset, this.name);
        long _hidl_array_offset_0 = _hidl_offset + 48;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 3; _hidl_index_0_0++) {
            _hidl_blob.putString(_hidl_array_offset_0, this.additional_number[_hidl_index_0_0]);
            _hidl_array_offset_0 += 16;
        }
        _hidl_blob.putString(96 + _hidl_offset, this.email_address);
        _hidl_blob.putString(112 + _hidl_offset, this.second_name);
        _hidl_blob.putInt32(128 + _hidl_offset, this.gas_id);
        _hidl_blob.putInt32(132 + _hidl_offset, this.sync_cnt);
    }
}
