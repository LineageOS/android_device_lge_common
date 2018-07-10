package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeGsmSignalStrength {
    public int bitErrorRate;
    public int dbm;
    public int ecio;
    public int signalStrength;
    public int timingAdvance;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeGsmSignalStrength.class) {
            return false;
        }
        LgeGsmSignalStrength other = (LgeGsmSignalStrength) otherObject;
        return this.signalStrength == other.signalStrength && this.bitErrorRate == other.bitErrorRate && this.timingAdvance == other.timingAdvance && this.dbm == other.dbm && this.ecio == other.ecio;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.signalStrength))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.bitErrorRate))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timingAdvance))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.dbm))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ecio)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".signalStrength = ");
        builder.append(this.signalStrength);
        builder.append(", .bitErrorRate = ");
        builder.append(this.bitErrorRate);
        builder.append(", .timingAdvance = ");
        builder.append(this.timingAdvance);
        builder.append(", .dbm = ");
        builder.append(this.dbm);
        builder.append(", .ecio = ");
        builder.append(this.ecio);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(20), 0);
    }

    public static final ArrayList<LgeGsmSignalStrength> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeGsmSignalStrength> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 20), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeGsmSignalStrength _hidl_vec_element = new LgeGsmSignalStrength();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 20));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.signalStrength = _hidl_blob.getInt32(0 + _hidl_offset);
        this.bitErrorRate = _hidl_blob.getInt32(4 + _hidl_offset);
        this.timingAdvance = _hidl_blob.getInt32(8 + _hidl_offset);
        this.dbm = _hidl_blob.getInt32(12 + _hidl_offset);
        this.ecio = _hidl_blob.getInt32(16 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(20);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeGsmSignalStrength> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 20);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeGsmSignalStrength) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 20));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.signalStrength);
        _hidl_blob.putInt32(4 + _hidl_offset, this.bitErrorRate);
        _hidl_blob.putInt32(8 + _hidl_offset, this.timingAdvance);
        _hidl_blob.putInt32(12 + _hidl_offset, this.dbm);
        _hidl_blob.putInt32(16 + _hidl_offset, this.ecio);
    }
}
