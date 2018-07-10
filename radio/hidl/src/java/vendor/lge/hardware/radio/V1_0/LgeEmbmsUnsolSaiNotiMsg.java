package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeEmbmsUnsolSaiNotiMsg {
    public int camped_freq_count;
    public final int[] camped_freq_list = new int[9];
    public int camped_freq_sai_count;
    public final int[] camped_freq_sai_list = new int[576];
    public final int[] camped_sai_count_per_group = new int[9];
    public int neighbor_freq_count;
    public final int[] neighbor_freq_list = new int[9];
    public int neighbor_freq_sai_count;
    public final int[] neighbor_freq_sai_list = new int[576];
    public final int[] neighbor_sai_count_per_group = new int[9];
    public int trans_id;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsUnsolSaiNotiMsg.class) {
            return false;
        }
        LgeEmbmsUnsolSaiNotiMsg other = (LgeEmbmsUnsolSaiNotiMsg) otherObject;
        return this.trans_id == other.trans_id && this.camped_freq_count == other.camped_freq_count && HidlSupport.deepEquals(this.camped_freq_list, other.camped_freq_list) && HidlSupport.deepEquals(this.camped_sai_count_per_group, other.camped_sai_count_per_group) && this.camped_freq_sai_count == other.camped_freq_sai_count && HidlSupport.deepEquals(this.camped_freq_sai_list, other.camped_freq_sai_list) && this.neighbor_freq_count == other.neighbor_freq_count && HidlSupport.deepEquals(this.neighbor_freq_list, other.neighbor_freq_list) && HidlSupport.deepEquals(this.neighbor_sai_count_per_group, other.neighbor_sai_count_per_group) && this.neighbor_freq_sai_count == other.neighbor_freq_sai_count && HidlSupport.deepEquals(this.neighbor_freq_sai_list, other.neighbor_freq_sai_list);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.camped_freq_count))), Integer.valueOf(HidlSupport.deepHashCode(this.camped_freq_list)), Integer.valueOf(HidlSupport.deepHashCode(this.camped_sai_count_per_group)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.camped_freq_sai_count))), Integer.valueOf(HidlSupport.deepHashCode(this.camped_freq_sai_list)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.neighbor_freq_count))), Integer.valueOf(HidlSupport.deepHashCode(this.neighbor_freq_list)), Integer.valueOf(HidlSupport.deepHashCode(this.neighbor_sai_count_per_group)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.neighbor_freq_sai_count))), Integer.valueOf(HidlSupport.deepHashCode(this.neighbor_freq_sai_list))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .camped_freq_count = ");
        builder.append(this.camped_freq_count);
        builder.append(", .camped_freq_list = ");
        builder.append(Arrays.toString(this.camped_freq_list));
        builder.append(", .camped_sai_count_per_group = ");
        builder.append(Arrays.toString(this.camped_sai_count_per_group));
        builder.append(", .camped_freq_sai_count = ");
        builder.append(this.camped_freq_sai_count);
        builder.append(", .camped_freq_sai_list = ");
        builder.append(Arrays.toString(this.camped_freq_sai_list));
        builder.append(", .neighbor_freq_count = ");
        builder.append(this.neighbor_freq_count);
        builder.append(", .neighbor_freq_list = ");
        builder.append(Arrays.toString(this.neighbor_freq_list));
        builder.append(", .neighbor_sai_count_per_group = ");
        builder.append(Arrays.toString(this.neighbor_sai_count_per_group));
        builder.append(", .neighbor_freq_sai_count = ");
        builder.append(this.neighbor_freq_sai_count);
        builder.append(", .neighbor_freq_sai_list = ");
        builder.append(Arrays.toString(this.neighbor_freq_sai_list));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(4772), 0);
    }

    public static final ArrayList<LgeEmbmsUnsolSaiNotiMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsUnsolSaiNotiMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 4772), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsUnsolSaiNotiMsg _hidl_vec_element = new LgeEmbmsUnsolSaiNotiMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 4772));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.camped_freq_count = _hidl_blob.getInt32(_hidl_offset + 4);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            this.camped_freq_list[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        _hidl_array_offset_0 = _hidl_offset + 44;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            this.camped_sai_count_per_group[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        this.camped_freq_sai_count = _hidl_blob.getInt32(80 + _hidl_offset);
        _hidl_array_offset_0 = _hidl_offset + 84;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 576; _hidl_index_0_0++) {
            this.camped_freq_sai_list[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        this.neighbor_freq_count = _hidl_blob.getInt32(2388 + _hidl_offset);
        _hidl_array_offset_0 = _hidl_offset + 2392;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            this.neighbor_freq_list[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        _hidl_array_offset_0 = _hidl_offset + 2428;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            this.neighbor_sai_count_per_group[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        this.neighbor_freq_sai_count = _hidl_blob.getInt32(2464 + _hidl_offset);
        _hidl_array_offset_0 = _hidl_offset + 2468;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 576; _hidl_index_0_0++) {
            this.neighbor_freq_sai_list[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(4772);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsUnsolSaiNotiMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 4772);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsUnsolSaiNotiMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 4772));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        _hidl_blob.putInt32(0 + _hidl_offset, this.trans_id);
        _hidl_blob.putInt32(_hidl_offset + 4, this.camped_freq_count);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.camped_freq_list[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_array_offset_0 = _hidl_offset + 44;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.camped_sai_count_per_group[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_blob.putInt32(80 + _hidl_offset, this.camped_freq_sai_count);
        _hidl_array_offset_0 = _hidl_offset + 84;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 576; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.camped_freq_sai_list[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_blob.putInt32(2388 + _hidl_offset, this.neighbor_freq_count);
        _hidl_array_offset_0 = _hidl_offset + 2392;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.neighbor_freq_list[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_array_offset_0 = _hidl_offset + 2428;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 9; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.neighbor_sai_count_per_group[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_blob.putInt32(2464 + _hidl_offset, this.neighbor_freq_sai_count);
        _hidl_array_offset_0 = _hidl_offset + 2468;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 576; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.neighbor_freq_sai_list[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
    }
}
