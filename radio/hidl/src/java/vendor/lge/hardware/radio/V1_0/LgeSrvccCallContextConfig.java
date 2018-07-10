package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeSrvccCallContextConfig {
    public int alerting_type_list_len;
    public int num_calls;
    public final ArrayList<LgeSrvccCall> srvcc_calls = new ArrayList();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeSrvccCallContextConfig.class) {
            return false;
        }
        LgeSrvccCallContextConfig other = (LgeSrvccCallContextConfig) otherObject;
        return this.num_calls == other.num_calls && HidlSupport.deepEquals(this.srvcc_calls, other.srvcc_calls) && this.alerting_type_list_len == other.alerting_type_list_len;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.num_calls))), Integer.valueOf(HidlSupport.deepHashCode(this.srvcc_calls)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.alerting_type_list_len)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".num_calls = ");
        builder.append(this.num_calls);
        builder.append(", .srvcc_calls = ");
        builder.append(this.srvcc_calls);
        builder.append(", .alerting_type_list_len = ");
        builder.append(this.alerting_type_list_len);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(32), 0);
    }

    public static final ArrayList<LgeSrvccCallContextConfig> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeSrvccCallContextConfig> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 32), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeSrvccCallContextConfig _hidl_vec_element = new LgeSrvccCallContextConfig();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 32));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.num_calls = _hidl_blob.getInt32(0 + _hidl_offset);
        int _hidl_vec_size = _hidl_blob.getInt32((8 + _hidl_offset) + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 88), _hidl_blob.handle(), (8 + _hidl_offset) + 0, true);
        this.srvcc_calls.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeSrvccCall _hidl_vec_element = new LgeSrvccCall();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 88));
            this.srvcc_calls.add(_hidl_vec_element);
        }
        this.alerting_type_list_len = _hidl_blob.getInt32(24 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(32);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeSrvccCallContextConfig> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeSrvccCallContextConfig) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 32));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.num_calls);
        int _hidl_vec_size = this.srvcc_calls.size();
        _hidl_blob.putInt32((_hidl_offset + 8) + 8, _hidl_vec_size);
        _hidl_blob.putBool((_hidl_offset + 8) + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 88);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeSrvccCall) this.srvcc_calls.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 88));
        }
        _hidl_blob.putBlob((_hidl_offset + 8) + 0, childBlob);
        _hidl_blob.putInt32(24 + _hidl_offset, this.alerting_type_list_len);
    }
}
