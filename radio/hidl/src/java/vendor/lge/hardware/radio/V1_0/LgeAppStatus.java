package vendor.lge.hardware.radio.V1_0;

import android.hardware.radio.V1_0.AppState;
import android.hardware.radio.V1_0.AppType;
import android.hardware.radio.V1_0.PersoSubstate;
import android.hardware.radio.V1_0.PinState;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeAppStatus {
    public String aidPtr = new String();
    public String appLabelPtr = new String();
    public int appState;
    public int appType;
    public int persoSubstate;
    public int pin1;
    public int pin1Replaced;
    public int pin1_remaining_cnt;
    public int pin2;
    public int pin2_remaining_cnt;
    public int puk1_remaining_cnt;
    public int puk2_remaining_cnt;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeAppStatus.class) {
            return false;
        }
        LgeAppStatus other = (LgeAppStatus) otherObject;
        return this.appType == other.appType && this.appState == other.appState && this.persoSubstate == other.persoSubstate && HidlSupport.deepEquals(this.aidPtr, other.aidPtr) && HidlSupport.deepEquals(this.appLabelPtr, other.appLabelPtr) && this.pin1Replaced == other.pin1Replaced && this.pin1 == other.pin1 && this.pin2 == other.pin2 && this.pin1_remaining_cnt == other.pin1_remaining_cnt && this.puk1_remaining_cnt == other.puk1_remaining_cnt && this.pin2_remaining_cnt == other.pin2_remaining_cnt && this.puk2_remaining_cnt == other.puk2_remaining_cnt;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.appType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.appState))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.persoSubstate))), Integer.valueOf(HidlSupport.deepHashCode(this.aidPtr)), Integer.valueOf(HidlSupport.deepHashCode(this.appLabelPtr)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin1Replaced))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin1))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin2))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin1_remaining_cnt))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.puk1_remaining_cnt))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin2_remaining_cnt))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.puk2_remaining_cnt)))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".appType = ");
        builder.append(AppType.toString(this.appType));
        builder.append(", .appState = ");
        builder.append(AppState.toString(this.appState));
        builder.append(", .persoSubstate = ");
        builder.append(PersoSubstate.toString(this.persoSubstate));
        builder.append(", .aidPtr = ");
        builder.append(this.aidPtr);
        builder.append(", .appLabelPtr = ");
        builder.append(this.appLabelPtr);
        builder.append(", .pin1Replaced = ");
        builder.append(this.pin1Replaced);
        builder.append(", .pin1 = ");
        builder.append(PinState.toString(this.pin1));
        builder.append(", .pin2 = ");
        builder.append(PinState.toString(this.pin2));
        builder.append(", .pin1_remaining_cnt = ");
        builder.append(this.pin1_remaining_cnt);
        builder.append(", .puk1_remaining_cnt = ");
        builder.append(this.puk1_remaining_cnt);
        builder.append(", .pin2_remaining_cnt = ");
        builder.append(this.pin2_remaining_cnt);
        builder.append(", .puk2_remaining_cnt = ");
        builder.append(this.puk2_remaining_cnt);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(80), 0);
    }

    public static final ArrayList<LgeAppStatus> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeAppStatus> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 80), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeAppStatus _hidl_vec_element = new LgeAppStatus();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 80));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.appType = _hidl_blob.getInt32(0 + _hidl_offset);
        this.appState = _hidl_blob.getInt32(4 + _hidl_offset);
        this.persoSubstate = _hidl_blob.getInt32(8 + _hidl_offset);
        this.aidPtr = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.aidPtr.getBytes().length + 1), _hidl_blob.handle(), 0 + (16 + _hidl_offset), false);
        this.appLabelPtr = _hidl_blob.getString(32 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.appLabelPtr.getBytes().length + 1), _hidl_blob.handle(), 0 + (32 + _hidl_offset), false);
        this.pin1Replaced = _hidl_blob.getInt32(48 + _hidl_offset);
        this.pin1 = _hidl_blob.getInt32(52 + _hidl_offset);
        this.pin2 = _hidl_blob.getInt32(56 + _hidl_offset);
        this.pin1_remaining_cnt = _hidl_blob.getInt32(60 + _hidl_offset);
        this.puk1_remaining_cnt = _hidl_blob.getInt32(64 + _hidl_offset);
        this.pin2_remaining_cnt = _hidl_blob.getInt32(68 + _hidl_offset);
        this.puk2_remaining_cnt = _hidl_blob.getInt32(72 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(80);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeAppStatus> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 80);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeAppStatus) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 80));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.appType);
        _hidl_blob.putInt32(4 + _hidl_offset, this.appState);
        _hidl_blob.putInt32(8 + _hidl_offset, this.persoSubstate);
        _hidl_blob.putString(16 + _hidl_offset, this.aidPtr);
        _hidl_blob.putString(32 + _hidl_offset, this.appLabelPtr);
        _hidl_blob.putInt32(48 + _hidl_offset, this.pin1Replaced);
        _hidl_blob.putInt32(52 + _hidl_offset, this.pin1);
        _hidl_blob.putInt32(56 + _hidl_offset, this.pin2);
        _hidl_blob.putInt32(60 + _hidl_offset, this.pin1_remaining_cnt);
        _hidl_blob.putInt32(64 + _hidl_offset, this.puk1_remaining_cnt);
        _hidl_blob.putInt32(68 + _hidl_offset, this.pin2_remaining_cnt);
        _hidl_blob.putInt32(72 + _hidl_offset, this.puk2_remaining_cnt);
    }
}
