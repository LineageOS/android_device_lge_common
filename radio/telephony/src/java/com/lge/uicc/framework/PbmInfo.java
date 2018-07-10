package com.lge.uicc.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PbmInfo implements Parcelable {
    public static final Creator<PbmInfo> CREATOR = new Creator<PbmInfo>() {
        public PbmInfo createFromParcel(Parcel source) {
            return new PbmInfo(source);
        }

        public PbmInfo[] newArray(int size) {
            return new PbmInfo[size];
        }
    };
    public final int MAX_PB_SETS;
    public int device;
    public int file_type;
    public int max_num_length;
    public int max_text_length;
    public int num_of_free_ext;
    public int num_of_tot_rec;
    public int num_of_used_rec;
    public int[] records_in_pb_set;
    public int status;
    public int[] used_records_in_pb_set;

    public PbmInfo() {
        this.MAX_PB_SETS = 8;
        this.records_in_pb_set = new int[8];
        this.used_records_in_pb_set = new int[8];
    }

    private PbmInfo(Parcel source) {
        this.MAX_PB_SETS = 8;
        this.records_in_pb_set = new int[8];
        this.used_records_in_pb_set = new int[8];
        readFromParcel(source);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeInt(this.device);
        dest.writeInt(this.status);
        dest.writeInt(this.max_num_length);
        dest.writeInt(this.max_text_length);
        dest.writeInt(this.num_of_used_rec);
        dest.writeInt(this.num_of_free_ext);
        dest.writeInt(this.num_of_tot_rec);
        dest.writeInt(this.file_type);
        for (i = 0; i < 8; i++) {
            dest.writeInt(this.records_in_pb_set[i]);
        }
        for (i = 0; i < 8; i++) {
            dest.writeInt(this.used_records_in_pb_set[i]);
        }
    }

    public void readFromParcel(Parcel source) {
        int i;
        this.device = source.readInt();
        this.status = source.readInt();
        this.max_num_length = source.readInt();
        this.max_text_length = source.readInt();
        this.num_of_used_rec = source.readInt();
        this.num_of_free_ext = source.readInt();
        this.num_of_tot_rec = source.readInt();
        this.file_type = source.readInt();
        for (i = 0; i < 8; i++) {
            this.records_in_pb_set[i] = source.readInt();
        }
        for (i = 0; i < 8; i++) {
            this.used_records_in_pb_set[i] = source.readInt();
        }
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return super.toString() + "Device  " + this.device + " status: " + this.status + " max_num_length: " + this.max_num_length + " max_text_length: " + this.max_text_length + " num_of_used_rec: " + this.num_of_used_rec + " num_of_free_ext: " + this.num_of_free_ext + " num_of_tot_rec: " + this.num_of_tot_rec + " file_type: " + this.file_type + " records_in_pb_set: " + this.records_in_pb_set[0] + " " + this.records_in_pb_set[1] + " " + this.records_in_pb_set[2] + " " + this.records_in_pb_set[3] + this.records_in_pb_set[4] + " " + this.records_in_pb_set[5] + " " + this.records_in_pb_set[6] + " " + this.records_in_pb_set[7] + " used_records_in_pb_set: " + this.used_records_in_pb_set[0] + " " + this.used_records_in_pb_set[1] + " " + this.used_records_in_pb_set[2] + " " + this.used_records_in_pb_set[3] + " " + this.used_records_in_pb_set[4] + " " + this.used_records_in_pb_set[5] + " " + this.used_records_in_pb_set[6] + " " + this.used_records_in_pb_set[7];
    }
}
