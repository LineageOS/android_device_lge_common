package vendor.lge.hardware.radio.V1_0;

import android.hardware.radio.V1_0.RadioError;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class LgeMocaConfigInfo {
    public int conf_info;
    public final byte[] config = new byte[512];
    public int config_data_len;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeMocaConfigInfo.class) {
            return false;
        }
        LgeMocaConfigInfo other = (LgeMocaConfigInfo) otherObject;
        return this.conf_info == other.conf_info && this.config_data_len == other.config_data_len && HidlSupport.deepEquals(this.config, other.config);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.conf_info))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.config_data_len))), Integer.valueOf(HidlSupport.deepHashCode(this.config))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".conf_info = ");
        builder.append(this.conf_info);
        builder.append(", .config_data_len = ");
        builder.append(this.config_data_len);
        builder.append(", .config = ");
        builder.append(Arrays.toString(this.config));
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(520), 0);
    }

    public static final ArrayList<LgeMocaConfigInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeMocaConfigInfo> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * RadioError.OEM_ERROR_20), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeMocaConfigInfo _hidl_vec_element = new LgeMocaConfigInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * RadioError.OEM_ERROR_20));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.conf_info = _hidl_blob.getInt32(0 + _hidl_offset);
        this.config_data_len = _hidl_blob.getInt32(4 + _hidl_offset);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 512; _hidl_index_0_0++) {
            this.config[_hidl_index_0_0] = _hidl_blob.getInt8(_hidl_array_offset_0);
            _hidl_array_offset_0++;
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(RadioError.OEM_ERROR_20);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeMocaConfigInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * RadioError.OEM_ERROR_20);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeMocaConfigInfo) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * RadioError.OEM_ERROR_20));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.conf_info);
        _hidl_blob.putInt32(4 + _hidl_offset, this.config_data_len);
        long _hidl_array_offset_0 = _hidl_offset + 8;
        for (int _hidl_index_0_0 = 0; _hidl_index_0_0 < 512; _hidl_index_0_0++) {
            _hidl_blob.putInt8(_hidl_array_offset_0, this.config[_hidl_index_0_0]);
            _hidl_array_offset_0++;
        }
    }
}
