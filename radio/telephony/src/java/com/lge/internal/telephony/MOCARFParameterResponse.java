package com.lge.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MOCARFParameterResponse implements Parcelable {
    public static final Creator<MOCARFParameterResponse> CREATOR = new Creator<MOCARFParameterResponse>() {
        public MOCARFParameterResponse createFromParcel(Parcel in) {
            int kind_of_data = in.readInt();
            int send_buf_num = in.readInt();
            int data_len = in.readInt();
            byte[] data = new byte[data_len];
            in.readByteArray(data);
            return new MOCARFParameterResponse(kind_of_data, send_buf_num, data_len, data);
        }

        public MOCARFParameterResponse[] newArray(int size) {
            return new MOCARFParameterResponse[size];
        }
    };
    public byte[] data;
    public int data_len;
    public int kind_of_data;
    public int send_buf_num;

    public MOCARFParameterResponse(int kind_of_data, int send_buf_num, int data_len, byte[] data) {
        this.kind_of_data = kind_of_data;
        this.send_buf_num = send_buf_num;
        this.data_len = data_len;
        this.data = data;
    }

    public String toString() {
        return super.toString() + " kind_of_data: " + this.kind_of_data + " send_buf_num: " + this.send_buf_num + " data_len: " + this.data_len + (this.data != null ? " data.length: " + this.data.length : " data is NULL");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.kind_of_data);
        dest.writeInt(this.send_buf_num);
        dest.writeInt(this.data_len);
        dest.writeByteArray(this.data);
    }
}
