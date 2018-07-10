package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeDataQosResponse {
    public int cid;
    public int qid;
    public String rx_flow_desc = new String();
    public String rx_tft = new String();
    public int status;
    public String tx_flow_desc = new String();
    public String tx_tft = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeDataQosResponse.class) {
            return false;
        }
        LgeDataQosResponse other = (LgeDataQosResponse) otherObject;
        return this.cid == other.cid && this.qid == other.qid && this.status == other.status && HidlSupport.deepEquals(this.tx_flow_desc, other.tx_flow_desc) && HidlSupport.deepEquals(this.rx_flow_desc, other.rx_flow_desc) && HidlSupport.deepEquals(this.tx_tft, other.tx_tft) && HidlSupport.deepEquals(this.rx_tft, other.rx_tft);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.qid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(this.tx_flow_desc)), Integer.valueOf(HidlSupport.deepHashCode(this.rx_flow_desc)), Integer.valueOf(HidlSupport.deepHashCode(this.tx_tft)), Integer.valueOf(HidlSupport.deepHashCode(this.rx_tft))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".cid = ");
        builder.append(this.cid);
        builder.append(", .qid = ");
        builder.append(this.qid);
        builder.append(", .status = ");
        builder.append(this.status);
        builder.append(", .tx_flow_desc = ");
        builder.append(this.tx_flow_desc);
        builder.append(", .rx_flow_desc = ");
        builder.append(this.rx_flow_desc);
        builder.append(", .tx_tft = ");
        builder.append(this.tx_tft);
        builder.append(", .rx_tft = ");
        builder.append(this.rx_tft);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(80), 0);
    }

    public static final ArrayList<LgeDataQosResponse> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeDataQosResponse> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 80), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeDataQosResponse _hidl_vec_element = new LgeDataQosResponse();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 80));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.cid = _hidl_blob.getInt32(0 + _hidl_offset);
        this.qid = _hidl_blob.getInt32(4 + _hidl_offset);
        this.status = _hidl_blob.getInt32(8 + _hidl_offset);
        this.tx_flow_desc = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.tx_flow_desc.getBytes().length + 1), _hidl_blob.handle(), 0 + (16 + _hidl_offset), false);
        this.rx_flow_desc = _hidl_blob.getString(32 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.rx_flow_desc.getBytes().length + 1), _hidl_blob.handle(), 0 + (32 + _hidl_offset), false);
        this.tx_tft = _hidl_blob.getString(48 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.tx_tft.getBytes().length + 1), _hidl_blob.handle(), 0 + (48 + _hidl_offset), false);
        this.rx_tft = _hidl_blob.getString(64 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.rx_tft.getBytes().length + 1), _hidl_blob.handle(), 0 + (64 + _hidl_offset), false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(80);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeDataQosResponse> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 80);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeDataQosResponse) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 80));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.cid);
        _hidl_blob.putInt32(4 + _hidl_offset, this.qid);
        _hidl_blob.putInt32(8 + _hidl_offset, this.status);
        _hidl_blob.putString(16 + _hidl_offset, this.tx_flow_desc);
        _hidl_blob.putString(32 + _hidl_offset, this.rx_flow_desc);
        _hidl_blob.putString(48 + _hidl_offset, this.tx_tft);
        _hidl_blob.putString(64 + _hidl_offset, this.rx_tft);
    }
}
