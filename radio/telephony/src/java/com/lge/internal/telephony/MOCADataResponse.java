package com.lge.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MOCADataResponse implements Parcelable {
    public static final Creator<MOCADataResponse> CREATOR = new Creator<MOCADataResponse>() {
        public MOCADataResponse createFromParcel(Parcel in) {
            int send_buf_num = in.readInt();
            int data_len = in.readInt();
            byte[] data = new byte[data_len];
            in.readByteArray(data);
            return new MOCADataResponse(send_buf_num, data_len, data);
        }

        public MOCADataResponse[] newArray(int size) {
            return new MOCADataResponse[size];
        }
    };
    public byte[] data;
    public int data_len;
    public int send_buf_num;

    public MOCADataResponse(int send_buf_num, int data_len, byte[] data) {
        this.send_buf_num = send_buf_num;
        this.data_len = data_len;
        this.data = data;
    }

    public String toString() {
        return super.toString() + " send_buf_num: " + this.send_buf_num + " data_len: " + this.data_len + (this.data != null ? " data.length: " + this.data.length : " data is NULL");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.send_buf_num);
        dest.writeInt(this.data_len);
        dest.writeByteArray(this.data);
    }
}
