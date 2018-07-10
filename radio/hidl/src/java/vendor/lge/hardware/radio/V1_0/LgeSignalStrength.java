package vendor.lge.hardware.radio.V1_0;

import android.hardware.radio.V1_0.CdmaSignalStrength;
import android.hardware.radio.V1_0.EvdoSignalStrength;
import android.hardware.radio.V1_0.LteSignalStrength;
import android.hardware.radio.V1_0.TdScdmaSignalStrength;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeSignalStrength {
    public final CdmaSignalStrength cdma = new CdmaSignalStrength();
    public final EvdoSignalStrength evdo = new EvdoSignalStrength();
    public final LgeGsmSignalStrength gw = new LgeGsmSignalStrength();
    public final LteSignalStrength lte = new LteSignalStrength();
    public final TdScdmaSignalStrength tdScdma = new TdScdmaSignalStrength();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeSignalStrength.class) {
            return false;
        }
        LgeSignalStrength other = (LgeSignalStrength) otherObject;
        return HidlSupport.deepEquals(this.gw, other.gw) && HidlSupport.deepEquals(this.cdma, other.cdma) && HidlSupport.deepEquals(this.evdo, other.evdo) && HidlSupport.deepEquals(this.lte, other.lte) && HidlSupport.deepEquals(this.tdScdma, other.tdScdma);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(this.gw)), Integer.valueOf(HidlSupport.deepHashCode(this.cdma)), Integer.valueOf(HidlSupport.deepHashCode(this.evdo)), Integer.valueOf(HidlSupport.deepHashCode(this.lte)), Integer.valueOf(HidlSupport.deepHashCode(this.tdScdma))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".gw = ");
        builder.append(this.gw);
        builder.append(", .cdma = ");
        builder.append(this.cdma);
        builder.append(", .evdo = ");
        builder.append(this.evdo);
        builder.append(", .lte = ");
        builder.append(this.lte);
        builder.append(", .tdScdma = ");
        builder.append(this.tdScdma);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(68), 0);
    }

    public static final ArrayList<LgeSignalStrength> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeSignalStrength> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 68), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeSignalStrength _hidl_vec_element = new LgeSignalStrength();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 68));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.gw.readEmbeddedFromParcel(parcel, _hidl_blob, 0 + _hidl_offset);
        this.cdma.readEmbeddedFromParcel(parcel, _hidl_blob, 20 + _hidl_offset);
        this.evdo.readEmbeddedFromParcel(parcel, _hidl_blob, 28 + _hidl_offset);
        this.lte.readEmbeddedFromParcel(parcel, _hidl_blob, 40 + _hidl_offset);
        this.tdScdma.readEmbeddedFromParcel(parcel, _hidl_blob, 64 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(68);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeSignalStrength> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 68);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeSignalStrength) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 68));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        this.gw.writeEmbeddedToBlob(_hidl_blob, 0 + _hidl_offset);
        this.cdma.writeEmbeddedToBlob(_hidl_blob, 20 + _hidl_offset);
        this.evdo.writeEmbeddedToBlob(_hidl_blob, 28 + _hidl_offset);
        this.lte.writeEmbeddedToBlob(_hidl_blob, 40 + _hidl_offset);
        this.tdScdma.writeEmbeddedToBlob(_hidl_blob, 64 + _hidl_offset);
    }
}
