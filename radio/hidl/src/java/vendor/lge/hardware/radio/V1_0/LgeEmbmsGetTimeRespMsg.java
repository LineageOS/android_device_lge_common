package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeEmbmsGetTimeRespMsg {
    public byte day_light_saving;
    public byte day_light_saving_valid;
    public byte leap_seconds;
    public byte leap_seconds_valid;
    public byte local_time_offset;
    public byte local_time_offset_valid;
    public long milli_sec;
    public int response;
    public int trans_id;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsGetTimeRespMsg.class) {
            return false;
        }
        LgeEmbmsGetTimeRespMsg other = (LgeEmbmsGetTimeRespMsg) otherObject;
        return this.trans_id == other.trans_id && this.response == other.response && this.milli_sec == other.milli_sec && this.day_light_saving_valid == other.day_light_saving_valid && this.day_light_saving == other.day_light_saving && this.leap_seconds_valid == other.leap_seconds_valid && this.leap_seconds == other.leap_seconds && this.local_time_offset_valid == other.local_time_offset_valid && this.local_time_offset == other.local_time_offset;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.response))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.milli_sec))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.day_light_saving_valid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.day_light_saving))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.leap_seconds_valid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.leap_seconds))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.local_time_offset_valid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.local_time_offset)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .response = ");
        builder.append(this.response);
        builder.append(", .milli_sec = ");
        builder.append(this.milli_sec);
        builder.append(", .day_light_saving_valid = ");
        builder.append(this.day_light_saving_valid);
        builder.append(", .day_light_saving = ");
        builder.append(this.day_light_saving);
        builder.append(", .leap_seconds_valid = ");
        builder.append(this.leap_seconds_valid);
        builder.append(", .leap_seconds = ");
        builder.append(this.leap_seconds);
        builder.append(", .local_time_offset_valid = ");
        builder.append(this.local_time_offset_valid);
        builder.append(", .local_time_offset = ");
        builder.append(this.local_time_offset);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(24), 0);
    }

    public static final ArrayList<LgeEmbmsGetTimeRespMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsGetTimeRespMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 24), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsGetTimeRespMsg _hidl_vec_element = new LgeEmbmsGetTimeRespMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 24));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.response = _hidl_blob.getInt32(4 + _hidl_offset);
        this.milli_sec = _hidl_blob.getInt64(8 + _hidl_offset);
        this.day_light_saving_valid = _hidl_blob.getInt8(16 + _hidl_offset);
        this.day_light_saving = _hidl_blob.getInt8(17 + _hidl_offset);
        this.leap_seconds_valid = _hidl_blob.getInt8(18 + _hidl_offset);
        this.leap_seconds = _hidl_blob.getInt8(19 + _hidl_offset);
        this.local_time_offset_valid = _hidl_blob.getInt8(20 + _hidl_offset);
        this.local_time_offset = _hidl_blob.getInt8(21 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(24);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsGetTimeRespMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 24);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsGetTimeRespMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 24));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.trans_id);
        _hidl_blob.putInt32(4 + _hidl_offset, this.response);
        _hidl_blob.putInt64(8 + _hidl_offset, this.milli_sec);
        _hidl_blob.putInt8(16 + _hidl_offset, this.day_light_saving_valid);
        _hidl_blob.putInt8(17 + _hidl_offset, this.day_light_saving);
        _hidl_blob.putInt8(18 + _hidl_offset, this.leap_seconds_valid);
        _hidl_blob.putInt8(19 + _hidl_offset, this.leap_seconds);
        _hidl_blob.putInt8(20 + _hidl_offset, this.local_time_offset_valid);
        _hidl_blob.putInt8(21 + _hidl_offset, this.local_time_offset);
    }
}
