package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeEmbmsUnsolOosNotiMsg {
    public int reason;
    public final ArrayList<LgeEmbmsTmgiInfoType> tmgi_info = new ArrayList();
    public int tmgi_info_count;
    public int trans_id;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsUnsolOosNotiMsg.class) {
            return false;
        }
        LgeEmbmsUnsolOosNotiMsg other = (LgeEmbmsUnsolOosNotiMsg) otherObject;
        return this.trans_id == other.trans_id && this.reason == other.reason && this.tmgi_info_count == other.tmgi_info_count && HidlSupport.deepEquals(this.tmgi_info, other.tmgi_info);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.reason))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.tmgi_info_count))), Integer.valueOf(HidlSupport.deepHashCode(this.tmgi_info))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .reason = ");
        builder.append(this.reason);
        builder.append(", .tmgi_info_count = ");
        builder.append(this.tmgi_info_count);
        builder.append(", .tmgi_info = ");
        builder.append(this.tmgi_info);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(32), 0);
    }

    public static final ArrayList<LgeEmbmsUnsolOosNotiMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsUnsolOosNotiMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 32), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsUnsolOosNotiMsg _hidl_vec_element = new LgeEmbmsUnsolOosNotiMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 32));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.reason = _hidl_blob.getInt32(4 + _hidl_offset);
        this.tmgi_info_count = _hidl_blob.getInt32(8 + _hidl_offset);
        int _hidl_vec_size = _hidl_blob.getInt32((16 + _hidl_offset) + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 12), _hidl_blob.handle(), (16 + _hidl_offset) + 0, true);
        this.tmgi_info.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsTmgiInfoType _hidl_vec_element = new LgeEmbmsTmgiInfoType();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 12));
            this.tmgi_info.add(_hidl_vec_element);
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(32);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsUnsolOosNotiMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsUnsolOosNotiMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 32));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.trans_id);
        _hidl_blob.putInt32(4 + _hidl_offset, this.reason);
        _hidl_blob.putInt32(_hidl_offset + 8, this.tmgi_info_count);
        int _hidl_vec_size = this.tmgi_info.size();
        _hidl_blob.putInt32((_hidl_offset + 16) + 8, _hidl_vec_size);
        _hidl_blob.putBool((_hidl_offset + 16) + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 12);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsTmgiInfoType) this.tmgi_info.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 12));
        }
        _hidl_blob.putBlob((_hidl_offset + 16) + 0, childBlob);
    }
}
