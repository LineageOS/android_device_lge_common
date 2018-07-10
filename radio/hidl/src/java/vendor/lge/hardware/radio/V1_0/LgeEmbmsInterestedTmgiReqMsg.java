package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeEmbmsInterestedTmgiReqMsg {
    public final LgeEmbmsTmgiInfoType tmgi_list = new LgeEmbmsTmgiInfoType();
    public int trans_id;
    public int trans_id_valid;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsInterestedTmgiReqMsg.class) {
            return false;
        }
        LgeEmbmsInterestedTmgiReqMsg other = (LgeEmbmsInterestedTmgiReqMsg) otherObject;
        return this.trans_id == other.trans_id && this.trans_id_valid == other.trans_id_valid && HidlSupport.deepEquals(this.tmgi_list, other.tmgi_list);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id_valid))), Integer.valueOf(HidlSupport.deepHashCode(this.tmgi_list))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .trans_id_valid = ");
        builder.append(this.trans_id_valid);
        builder.append(", .tmgi_list = ");
        builder.append(this.tmgi_list);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(20), 0);
    }

    public static final ArrayList<LgeEmbmsInterestedTmgiReqMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsInterestedTmgiReqMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 20), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsInterestedTmgiReqMsg _hidl_vec_element = new LgeEmbmsInterestedTmgiReqMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 20));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.trans_id_valid = _hidl_blob.getInt32(4 + _hidl_offset);
        this.tmgi_list.readEmbeddedFromParcel(parcel, _hidl_blob, 8 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(20);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsInterestedTmgiReqMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 20);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsInterestedTmgiReqMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 20));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.trans_id);
        _hidl_blob.putInt32(4 + _hidl_offset, this.trans_id_valid);
        this.tmgi_list.writeEmbeddedToBlob(_hidl_blob, 8 + _hidl_offset);
    }
}
