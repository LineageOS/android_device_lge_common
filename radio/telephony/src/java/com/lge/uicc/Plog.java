package com.lge.uicc;

import android.telephony.Rlog;

public final class Plog {
    public static native String encipher(String str);

    static {
        System.loadLibrary("uicc");
    }

    public static int d(String tag, String msg) {
        return Rlog.d(tag, encipher(msg));
    }

    public static int e(String tag, String msg) {
        return Rlog.e(tag, encipher(msg));
    }
}
