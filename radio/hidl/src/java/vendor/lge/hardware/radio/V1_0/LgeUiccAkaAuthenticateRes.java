package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeUiccAkaAuthenticateRes {
    public String auts = new String();
    public String ck = new String();
    public String ik = new String();
    public String kc = new String();
    public String res = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeUiccAkaAuthenticateRes.class) {
            return false;
        }
        LgeUiccAkaAuthenticateRes other = (LgeUiccAkaAuthenticateRes) otherObject;
        return HidlSupport.deepEquals(this.res, other.res) && HidlSupport.deepEquals(this.ck, other.ck) && HidlSupport.deepEquals(this.ik, other.ik) && HidlSupport.deepEquals(this.kc, other.kc) && HidlSupport.deepEquals(this.auts, other.auts);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(this.res)), Integer.valueOf(HidlSupport.deepHashCode(this.ck)), Integer.valueOf(HidlSupport.deepHashCode(this.ik)), Integer.valueOf(HidlSupport.deepHashCode(this.kc)), Integer.valueOf(HidlSupport.deepHashCode(this.auts))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".res = ");
        builder.append(this.res);
        builder.append(", .ck = ");
        builder.append(this.ck);
        builder.append(", .ik = ");
        builder.append(this.ik);
        builder.append(", .kc = ");
        builder.append(this.kc);
        builder.append(", .auts = ");
        builder.append(this.auts);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(80), 0);
    }

    public static final ArrayList<LgeUiccAkaAuthenticateRes> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeUiccAkaAuthenticateRes> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 80), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeUiccAkaAuthenticateRes _hidl_vec_element = new LgeUiccAkaAuthenticateRes();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 80));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.res = _hidl_blob.getString(0 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.res.getBytes().length + 1), _hidl_blob.handle(), 0 + (0 + _hidl_offset), false);
        this.ck = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.ck.getBytes().length + 1), _hidl_blob.handle(), 0 + (16 + _hidl_offset), false);
        this.ik = _hidl_blob.getString(32 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.ik.getBytes().length + 1), _hidl_blob.handle(), 0 + (32 + _hidl_offset), false);
        this.kc = _hidl_blob.getString(48 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.kc.getBytes().length + 1), _hidl_blob.handle(), 0 + (48 + _hidl_offset), false);
        this.auts = _hidl_blob.getString(64 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.auts.getBytes().length + 1), _hidl_blob.handle(), 0 + (64 + _hidl_offset), false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(80);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeUiccAkaAuthenticateRes> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 80);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeUiccAkaAuthenticateRes) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 80));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.res);
        _hidl_blob.putString(16 + _hidl_offset, this.ck);
        _hidl_blob.putString(32 + _hidl_offset, this.ik);
        _hidl_blob.putString(48 + _hidl_offset, this.kc);
        _hidl_blob.putString(64 + _hidl_offset, this.auts);
    }
}
