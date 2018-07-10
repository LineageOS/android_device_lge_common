package vendor.lge.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LgeSrvccCall {
    public String address = new String();
    public int alerting_type;
    public int call_state;
    public int call_substate;
    public int call_type;
    public String caller_name = new String();
    public int direction;
    public int instance_id;
    public int is_alerting_type_valid;
    public int is_caller_name_type_valid;
    public int is_mpty_call;
    public int is_num_pi_valid;
    public int name_len;
    public int name_pi;
    public int num_pi;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != LgeSrvccCall.class) {
            return false;
        }
        LgeSrvccCall other = (LgeSrvccCall) otherObject;
        return this.instance_id == other.instance_id && this.call_type == other.call_type && this.call_state == other.call_state && this.call_substate == other.call_substate && this.is_mpty_call == other.is_mpty_call && this.direction == other.direction && HidlSupport.deepEquals(this.address, other.address) && this.is_alerting_type_valid == other.is_alerting_type_valid && this.alerting_type == other.alerting_type && this.is_num_pi_valid == other.is_num_pi_valid && this.num_pi == other.num_pi && this.is_caller_name_type_valid == other.is_caller_name_type_valid && this.name_pi == other.name_pi && this.name_len == other.name_len && HidlSupport.deepEquals(this.caller_name, other.caller_name);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.instance_id))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.call_type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.call_state))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.call_substate))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.is_mpty_call))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.direction))), Integer.valueOf(HidlSupport.deepHashCode(this.address)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.is_alerting_type_valid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.alerting_type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.is_num_pi_valid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.num_pi))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.is_caller_name_type_valid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.name_pi))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.name_len))), Integer.valueOf(HidlSupport.deepHashCode(this.caller_name))});
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(".instance_id = ");
        builder.append(this.instance_id);
        builder.append(", .call_type = ");
        builder.append(this.call_type);
        builder.append(", .call_state = ");
        builder.append(this.call_state);
        builder.append(", .call_substate = ");
        builder.append(this.call_substate);
        builder.append(", .is_mpty_call = ");
        builder.append(this.is_mpty_call);
        builder.append(", .direction = ");
        builder.append(this.direction);
        builder.append(", .address = ");
        builder.append(this.address);
        builder.append(", .is_alerting_type_valid = ");
        builder.append(this.is_alerting_type_valid);
        builder.append(", .alerting_type = ");
        builder.append(this.alerting_type);
        builder.append(", .is_num_pi_valid = ");
        builder.append(this.is_num_pi_valid);
        builder.append(", .num_pi = ");
        builder.append(this.num_pi);
        builder.append(", .is_caller_name_type_valid = ");
        builder.append(this.is_caller_name_type_valid);
        builder.append(", .name_pi = ");
        builder.append(this.name_pi);
        builder.append(", .name_len = ");
        builder.append(this.name_len);
        builder.append(", .caller_name = ");
        builder.append(this.caller_name);
        builder.append("}");
        return builder.toString();
    }

    public final void readFromParcel(HwParcel parcel) {
        readEmbeddedFromParcel(parcel, parcel.readBuffer(88), 0);
    }

    public static final ArrayList<LgeSrvccCall> readVectorFromParcel(HwParcel parcel) {
        ArrayList<LgeSrvccCall> _hidl_vec = new ArrayList();
        HwBlob _hidl_blob = parcel.readBuffer(16);
        int _hidl_vec_size = _hidl_blob.getInt32(8);
        HwBlob childBlob = parcel.readEmbeddedBuffer((long) (_hidl_vec_size * 88), _hidl_blob.handle(), 0, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            LgeSrvccCall _hidl_vec_element = new LgeSrvccCall();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, (long) (_hidl_index_0 * 88));
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.instance_id = _hidl_blob.getInt32(0 + _hidl_offset);
        this.call_type = _hidl_blob.getInt32(4 + _hidl_offset);
        this.call_state = _hidl_blob.getInt32(8 + _hidl_offset);
        this.call_substate = _hidl_blob.getInt32(12 + _hidl_offset);
        this.is_mpty_call = _hidl_blob.getInt32(16 + _hidl_offset);
        this.direction = _hidl_blob.getInt32(20 + _hidl_offset);
        this.address = _hidl_blob.getString(24 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.address.getBytes().length + 1), _hidl_blob.handle(), 0 + (24 + _hidl_offset), false);
        this.is_alerting_type_valid = _hidl_blob.getInt32(40 + _hidl_offset);
        this.alerting_type = _hidl_blob.getInt32(44 + _hidl_offset);
        this.is_num_pi_valid = _hidl_blob.getInt32(48 + _hidl_offset);
        this.num_pi = _hidl_blob.getInt32(52 + _hidl_offset);
        this.is_caller_name_type_valid = _hidl_blob.getInt32(56 + _hidl_offset);
        this.name_pi = _hidl_blob.getInt32(60 + _hidl_offset);
        this.name_len = _hidl_blob.getInt32(64 + _hidl_offset);
        this.caller_name = _hidl_blob.getString(72 + _hidl_offset);
        parcel.readEmbeddedBuffer((long) (this.caller_name.getBytes().length + 1), _hidl_blob.handle(), 0 + (72 + _hidl_offset), false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(88);
        writeEmbeddedToBlob(_hidl_blob, 0);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<LgeSrvccCall> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8, _hidl_vec_size);
        _hidl_blob.putBool(12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 88);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ((LgeSrvccCall) _hidl_vec.get(_hidl_index_0)).writeEmbeddedToBlob(childBlob, (long) (_hidl_index_0 * 88));
        }
        _hidl_blob.putBlob(0, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.instance_id);
        _hidl_blob.putInt32(4 + _hidl_offset, this.call_type);
        _hidl_blob.putInt32(8 + _hidl_offset, this.call_state);
        _hidl_blob.putInt32(12 + _hidl_offset, this.call_substate);
        _hidl_blob.putInt32(16 + _hidl_offset, this.is_mpty_call);
        _hidl_blob.putInt32(20 + _hidl_offset, this.direction);
        _hidl_blob.putString(24 + _hidl_offset, this.address);
        _hidl_blob.putInt32(40 + _hidl_offset, this.is_alerting_type_valid);
        _hidl_blob.putInt32(44 + _hidl_offset, this.alerting_type);
        _hidl_blob.putInt32(48 + _hidl_offset, this.is_num_pi_valid);
        _hidl_blob.putInt32(52 + _hidl_offset, this.num_pi);
        _hidl_blob.putInt32(56 + _hidl_offset, this.is_caller_name_type_valid);
        _hidl_blob.putInt32(60 + _hidl_offset, this.name_pi);
        _hidl_blob.putInt32(64 + _hidl_offset, this.name_len);
        _hidl_blob.putString(72 + _hidl_offset, this.caller_name);
    }
}
