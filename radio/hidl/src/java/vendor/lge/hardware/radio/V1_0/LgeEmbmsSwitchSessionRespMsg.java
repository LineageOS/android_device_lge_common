package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeEmbmsSwitchSessionRespMsg {
    public int act_response;
    public final LgeEmbmsTmgiInfoType act_tmgi_info = new LgeEmbmsTmgiInfoType();
    public byte act_tmgi_info_valid;
    public int deact_response;
    public final LgeEmbmsTmgiInfoType deact_tmgi_info = new LgeEmbmsTmgiInfoType();
    public byte deact_tmgi_info_valid;
    public int trans_id;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeEmbmsSwitchSessionRespMsg.class) {
            return false;
        }
        LgeEmbmsSwitchSessionRespMsg other = (LgeEmbmsSwitchSessionRespMsg) otherObject;
        return this.trans_id == other.trans_id && this.act_response == other.act_response && this.deact_response == other.deact_response && this.act_tmgi_info_valid == other.act_tmgi_info_valid && HidlSupport.deepEquals(this.act_tmgi_info, other.act_tmgi_info) && this.deact_tmgi_info_valid == other.deact_tmgi_info_valid && HidlSupport.deepEquals(this.deact_tmgi_info, other.deact_tmgi_info);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.trans_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.act_response))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.deact_response))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.act_tmgi_info_valid))), Integer.valueOf(HidlSupport.deepHashCode(this.act_tmgi_info)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.deact_tmgi_info_valid))), Integer.valueOf(HidlSupport.deepHashCode(this.deact_tmgi_info))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".trans_id = ");
        builder.append(this.trans_id);
        builder.append(", .act_response = ");
        builder.append(this.act_response);
        builder.append(", .deact_response = ");
        builder.append(this.deact_response);
        builder.append(", .act_tmgi_info_valid = ");
        builder.append(this.act_tmgi_info_valid);
        builder.append(", .act_tmgi_info = ");
        builder.append(this.act_tmgi_info);
        builder.append(", .deact_tmgi_info_valid = ");
        builder.append(this.deact_tmgi_info_valid);
        builder.append(", .deact_tmgi_info = ");
        builder.append(this.deact_tmgi_info);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(44), 0);
    }

    public static final ArrayList<LgeEmbmsSwitchSessionRespMsg> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeEmbmsSwitchSessionRespMsg> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 44), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeEmbmsSwitchSessionRespMsg _hidl_vec_element = new LgeEmbmsSwitchSessionRespMsg();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 44));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.trans_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.act_response = _hidl_blob.getInt32(4 + _hidl_offset);
        this.deact_response = _hidl_blob.getInt32(8 + _hidl_offset);
        this.act_tmgi_info_valid = _hidl_blob.getInt8(12 + _hidl_offset);
        this.act_tmgi_info.readEmbeddedFromParcel(parcel, _hidl_blob, 16 + _hidl_offset);
        this.deact_tmgi_info_valid = _hidl_blob.getInt8(28 + _hidl_offset);
        this.deact_tmgi_info.readEmbeddedFromParcel(parcel, _hidl_blob, 32 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(44);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeEmbmsSwitchSessionRespMsg> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 44);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeEmbmsSwitchSessionRespMsg) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 44));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.trans_id);
        _hidl_blob.putInt32(4 + _hidl_offset, this.act_response);
        _hidl_blob.putInt32(8 + _hidl_offset, this.deact_response);
        _hidl_blob.putInt8(12 + _hidl_offset, this.act_tmgi_info_valid);
        this.act_tmgi_info.writeEmbeddedToBlob(_hidl_blob, 16 + _hidl_offset);
        _hidl_blob.putInt8(28 + _hidl_offset, this.deact_tmgi_info_valid);
        this.deact_tmgi_info.writeEmbeddedToBlob(_hidl_blob, 32 + _hidl_offset);
    }
}
