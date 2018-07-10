package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgePbmRecordInfo {
    public int device;
    public int file_type;
    public int max_num_length;
    public int max_text_length;
    public int num_of_free_ext;
    public int num_of_tot_rec;
    public int num_of_used_rec;
    public final int[] records_in_pb_set = new int[8];
    public int status;
    public final int[] used_records_in_pb_set = new int[8];

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgePbmRecordInfo.class) {
            return false;
        }
        LgePbmRecordInfo other = (LgePbmRecordInfo) otherObject;
        return this.device == other.device && this.status == other.status && this.max_num_length == other.max_num_length && this.max_text_length == other.max_text_length && this.num_of_used_rec == other.num_of_used_rec && this.num_of_free_ext == other.num_of_free_ext && this.num_of_tot_rec == other.num_of_tot_rec && this.file_type == other.file_type && HidlSupport.deepEquals(this.records_in_pb_set, other.records_in_pb_set) && HidlSupport.deepEquals(this.used_records_in_pb_set, other.used_records_in_pb_set);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.device))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.max_num_length))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.max_text_length))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.num_of_used_rec))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.num_of_free_ext))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.num_of_tot_rec))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.file_type))), Integer.valueOf(HidlSupport.deepHashCode(this.records_in_pb_set)), Integer.valueOf(HidlSupport.deepHashCode(this.used_records_in_pb_set))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".device = ");
        builder.append(this.device);
        builder.append(", .status = ");
        builder.append(this.status);
        builder.append(", .max_num_length = ");
        builder.append(this.max_num_length);
        builder.append(", .max_text_length = ");
        builder.append(this.max_text_length);
        builder.append(", .num_of_used_rec = ");
        builder.append(this.num_of_used_rec);
        builder.append(", .num_of_free_ext = ");
        builder.append(this.num_of_free_ext);
        builder.append(", .num_of_tot_rec = ");
        builder.append(this.num_of_tot_rec);
        builder.append(", .file_type = ");
        builder.append(this.file_type);
        builder.append(", .records_in_pb_set = ");
        builder.append(Arrays.toString(this.records_in_pb_set));
        builder.append(", .used_records_in_pb_set = ");
        builder.append(Arrays.toString(this.used_records_in_pb_set));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(96), 0);
    }

    public static final ArrayList<LgePbmRecordInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgePbmRecordInfo> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 96), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgePbmRecordInfo _hidl_vec_element = new LgePbmRecordInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 96));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        this.device = _hidl_blob.getInt32(0 + _hidl_offset);
        this.status = _hidl_blob.getInt32(_hidl_offset + 4);
        this.max_num_length = _hidl_blob.getInt32(8 + _hidl_offset);
        this.max_text_length = _hidl_blob.getInt32(12 + _hidl_offset);
        this.num_of_used_rec = _hidl_blob.getInt32(16 + _hidl_offset);
        this.num_of_free_ext = _hidl_blob.getInt32(20 + _hidl_offset);
        this.num_of_tot_rec = _hidl_blob.getInt32(24 + _hidl_offset);
        this.file_type = _hidl_blob.getInt32(28 + _hidl_offset);
        long _hidl_array_offset_0 = _hidl_offset + 32;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 8; _hidl_index_0_0++) {
            this.records_in_pb_set[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        _hidl_array_offset_0 = _hidl_offset + 64;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 8; _hidl_index_0_0++) {
            this.used_records_in_pb_set[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(96);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgePbmRecordInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 96);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgePbmRecordInfo) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 96));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        _hidl_blob.putInt32(0 + _hidl_offset, this.device);
        _hidl_blob.putInt32(_hidl_offset + 4, this.status);
        _hidl_blob.putInt32(8 + _hidl_offset, this.max_num_length);
        _hidl_blob.putInt32(12 + _hidl_offset, this.max_text_length);
        _hidl_blob.putInt32(16 + _hidl_offset, this.num_of_used_rec);
        _hidl_blob.putInt32(20 + _hidl_offset, this.num_of_free_ext);
        _hidl_blob.putInt32(24 + _hidl_offset, this.num_of_tot_rec);
        _hidl_blob.putInt32(28 + _hidl_offset, this.file_type);
        long _hidl_array_offset_0 = _hidl_offset + 32;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 8; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.records_in_pb_set[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_array_offset_0 = _hidl_offset + 64;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 8; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.used_records_in_pb_set[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
    }
}
