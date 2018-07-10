package com.lge.uicc.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PbmRecord implements Parcelable {
    public static final Creator<PbmRecord> CREATOR = new Creator<PbmRecord>() {
        public PbmRecord createFromParcel(Parcel source) {
            return new PbmRecord(source);
        }

        public PbmRecord[] newArray(int size) {
            return new PbmRecord[size];
        }
    };
    public int ad_type;
    public String additional_number;
    public String additional_number_a;
    public String additional_number_b;
    public int device;
    public String email_address;
    public int gas_id;
    public int index;
    public String name;
    public String number;
    public String second_name;
    public int sync_cnt;
    public int type;

    private PbmRecord(Parcel source) {
        readFromParcel(source);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.device);
        dest.writeInt(this.index);
        dest.writeInt(this.type);
        dest.writeInt(this.ad_type);
        dest.writeString(this.number);
        dest.writeString(this.name);
        dest.writeString(this.additional_number);
        dest.writeString(this.additional_number_a);
        dest.writeString(this.additional_number_b);
        dest.writeString(this.email_address);
        dest.writeString(this.second_name);
        dest.writeInt(this.gas_id);
        dest.writeInt(this.sync_cnt);
    }

    public void readFromParcel(Parcel source) {
        this.device = source.readInt();
        this.index = source.readInt();
        this.type = source.readInt();
        this.ad_type = source.readInt();
        this.number = source.readString();
        this.name = source.readString();
        this.additional_number = source.readString();
        this.additional_number_a = source.readString();
        this.additional_number_b = source.readString();
        this.email_address = source.readString();
        this.second_name = source.readString();
        this.gas_id = source.readInt();
        this.sync_cnt = source.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "device: " + this.device + " index: " + this.index + " type: " + this.type + " ad_type: " + this.ad_type + " number: " + this.number + " name: " + this.name + " additional_number: " + this.additional_number + " additional_number_a: " + this.additional_number_a + " additional_number_b: " + this.additional_number_b + " email_address: " + this.email_address + " second_name: " + this.second_name + " gas_id: " + this.gas_id + " sync_cnt: " + this.sync_cnt;
    }
}
