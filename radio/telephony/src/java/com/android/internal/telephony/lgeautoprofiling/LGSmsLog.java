package com.android.internal.telephony.lgeautoprofiling;

import android.os.SystemProperties;
import android.util.Log;

public class LGSmsLog {
    public static final int DEBUG = 2;
    public static final int ERROR = 16;
    public static final int FLOW = 32;
    public static final int INFO = 4;
    public static final int PRIVACY = 64;
    private static final String PROPERTY_DISABLELOG = "persist.gsm.sms.disablelog";
    private static final String TAG_DEBUG = "[SMS_LD]";
    private static final String TAG_ERROR = "[SMS_LE]";
    private static final String TAG_FLOW = "[SMS_LF]";
    private static final String TAG_INFO = "[SMS_LI]";
    private static final String TAG_PRIVACY = "[SMS_LP]";
    private static final String TAG_VERBOSE = "[SMS_LV]";
    private static final String TAG_WARN = "[SMS_LW]";
    public static final int VERBOSE = 1;
    public static final int WARN = 8;
    private static int sDisableLog = SystemProperties.getInt(PROPERTY_DISABLELOG, 0);

    private LGSmsLog() {
    }

    public static boolean isLoggable(int priority) {
        if ((sDisableLog & priority) == 0) {
            return true;
        }
        return false;
    }

    public static int v(String msg) {
        if (msg != null && isLoggable(1)) {
            return Log.v(TAG_VERBOSE, msg);
        }
        return 0;
    }

    public static int v(String msg, Throwable tr) {
        if (msg != null && isLoggable(1)) {
            return Log.v(TAG_VERBOSE, msg, tr);
        }
        return 0;
    }

    public static int d(String msg) {
        if (msg != null && isLoggable(2)) {
            return Log.d(TAG_DEBUG, msg);
        }
        return 0;
    }

    public static int d(String msg, Throwable tr) {
        if (msg != null && isLoggable(2)) {
            return Log.d(TAG_DEBUG, msg, tr);
        }
        return 0;
    }

    public static int i(String msg) {
        if (msg != null && isLoggable(4)) {
            return Log.i(TAG_INFO, msg);
        }
        return 0;
    }

    public static int i(String msg, Throwable tr) {
        if (msg != null && isLoggable(4)) {
            return Log.i(TAG_INFO, msg, tr);
        }
        return 0;
    }

    public static int w(String msg) {
        if (msg != null && isLoggable(8)) {
            return Log.w(TAG_WARN, msg);
        }
        return 0;
    }

    public static int w(String msg, Throwable tr) {
        if (msg != null && isLoggable(8)) {
            return Log.w(TAG_WARN, msg, tr);
        }
        return 0;
    }

    public static int e(String msg) {
        if (msg != null && isLoggable(16)) {
            return Log.e(TAG_WARN, msg);
        }
        return 0;
    }

    public static int e(String msg, Throwable tr) {
        if (msg != null && isLoggable(16)) {
            return Log.e(TAG_ERROR, msg, tr);
        }
        return 0;
    }

    public static int f(String msg) {
        if (msg != null && isLoggable(32)) {
            return Log.i(TAG_FLOW, msg);
        }
        return 0;
    }

    public static int p(String msg) {
        if (msg != null && isLoggable(64)) {
            return Log.i(TAG_PRIVACY, msg);
        }
        return 0;
    }
}
