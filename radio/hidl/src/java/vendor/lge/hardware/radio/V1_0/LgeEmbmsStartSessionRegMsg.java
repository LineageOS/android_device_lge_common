package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeEmbmsStartSessionRegMsg {
    public final int[] earfcnlist = new int[32];
    public int earfcnlist_count;
    public final int[] saiList = new int[64];
    public int saiList_count;
    public byte saiList_valid;
    public final LgeEmbmsTmgiInfoType tmgi_info = new LgeEmbmsTmgiInfoType();
    public int trans_id;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsStartSessionRegMsg.class) {
            return false;
        }
        LgeEmbmsStartSessionRegMsg other = (LgeEmbmsStartSessionRegMsg) otherObject;
        return this.trans_id == other.trans_id && HidlSupport.deepEquals(this.tmgi_info, other.tmgi_info) && this.earfcnlist_count == other.earfcnlist_count && HidlSupport.deepEquals(this.earfcnlist, other.earfcnlist) && this.saiList_valid == other.saiList_valid && this.saiList_count == other.saiList_count && HidlSupport.deepEquals(this.saiList, other.saiList);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(this.tmgi_info)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.earfcnlist_count))), Integer.valueOf(HidlSupport.deepHashCode(this.earfcnlist)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.saiList_valid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.saiList_count))), Integer.valueOf(HidlSupport.deepHashCode(this.saiList))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .tmgi_info = ");
        builder.append(this.tmgi_info);
        builder.append(", .earfcnlist_count = ");
        builder.append(this.earfcnlist_count);
        builder.append(", .earfcnlist = ");
        builder.append(Arrays.toString(this.earfcnlist));
        builder.append(", .saiList_valid = ");
        builder.append(this.saiList_valid);
        builder.append(", .saiList_count = ");
        builder.append(this.saiList_count);
        builder.append(", .saiList = ");
        builder.append(Arrays.toString(this.saiList));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(412), 0);
    }

    public static final ArrayList<LgeEmbmsStartSessionRegMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsStartSessionRegMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 412), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsStartSessionRegMsg _hidl_vec_element = new LgeEmbmsStartSessionRegMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 412));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.tmgi_info.readEmbeddedFromParcel(parcel, _hidl_blob, _hidl_offset + 4);
        this.earfcnlist_count = _hidl_blob.getInt32(16 + _hidl_offset);
        long _hidl_array_offset_0 = _hidl_offset + 20;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 32; _hidl_index_0_0++) {
            this.earfcnlist[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
        this.saiList_valid = _hidl_blob.getInt8(148 + _hidl_offset);
        this.saiList_count = _hidl_blob.getInt32(152 + _hidl_offset);
        _hidl_array_offset_0 = _hidl_offset + 156;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 64; _hidl_index_0_0++) {
            this.saiList[_hidl_index_0_0] = _hidl_blob.getInt32(_hidl_array_offset_0);
            _hidl_array_offset_0 += 4;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(412);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsStartSessionRegMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 412);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsStartSessionRegMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 412));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_index_0_0;
        _hidl_blob.putInt32(0 + _hidl_offset, this.trans_id);
        this.tmgi_info.writeEmbeddedToBlob(_hidl_blob, _hidl_offset + 4);
        _hidl_blob.putInt32(16 + _hidl_offset, this.earfcnlist_count);
        long _hidl_array_offset_0 = _hidl_offset + 20;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 32; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.earfcnlist[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
        _hidl_blob.putInt8(148 + _hidl_offset, this.saiList_valid);
        _hidl_blob.putInt32(152 + _hidl_offset, this.saiList_count);
        _hidl_array_offset_0 = _hidl_offset + 156;
        for (_hidl_index_0_0 = 0; _hidl_index_0_0 < 64; _hidl_index_0_0++) {
            _hidl_blob.putInt32(_hidl_array_offset_0, this.saiList[_hidl_index_0_0]);
            _hidl_array_offset_0 += 4;
        }
    }
}
