package com.android.internal.telephony;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.telephony.Rlog;
import com.android.internal.telephony.lgeautoprofiling.LgeAutoProfiling;

public class LGEcallMonitor extends Handler {
    private static final long DELAY_MILLIS = 4000;
    private static final int EVENT_CHECK_ECBM = 2;
    private static final int EVENT_RADIO_OFF_OR_NOT_AVAILABLE = 1;
    private static final String LOG_TAG = "EcallMon";
    private static LGEcallMonitor sInstance = null;
    private static Object sSync = new Object();
    private CommandsInterface mCi;
    private Phone mEmcPhone;
    private boolean mEmgssStarted;
    private Handler mHandler;
    private boolean mInEcbm;

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        public void handleMessage(Message msg) {
            boolean found = false;
            switch (msg.what) {
                case 1:
                    if (LGEcallMonitor.this.mCi == null) {
                        return;
                    }
                    if (LGEcallMonitor.this.mCi.getRadioState().isAvailable()) {
                        Rlog.d(LGEcallMonitor.LOG_TAG, "handleMessage available");
                        return;
                    }
                    Rlog.d(LGEcallMonitor.LOG_TAG, "handleMessage off or not avail");
                    synchronized (LGEcallMonitor.sSync) {
                        LGEcallMonitor.this.onExitEmergencyModeInternal();
                    }
                    return;
                case 2:
                    boolean emgss;
                    boolean inEcm;
                    synchronized (LGEcallMonitor.sSync) {
                        emgss = LGEcallMonitor.this.mEmgssStarted;
                        inEcm = LGEcallMonitor.this.mInEcbm;
/*
                        if (!(!LGEcallMonitor.this.mEmgssStarted || LGEcallMonitor.this.mInEcbm || LGEcallMonitor.this.mEmcPhone == null)) {
                            LGEcallMonitor.this.mEmcPhone.exitVolteE911EmergencyMode();
                            found = true;
                        }
*/
                    }
                    Rlog.d(LGEcallMonitor.LOG_TAG, "handleMessage EVENT_CHECK_ECBM " + emgss + ", " + inEcm + ", " + found);
                    return;
                default:
                    return;
            }
        }
    }

    public static void prepare(Phone phone, Context context, CommandsInterface ci) {
        if (phone == null) {
            Rlog.e(LOG_TAG, "prepare phone null");
        } else if (!LgeAutoProfiling.IS_SUPPORT_NAOP && !TelephonyCapabilities.supportsEcm(phone)) {
            Rlog.e(LOG_TAG, "prepare not supports ecm");
        } else if (!SystemProperties.getBoolean("ro.telephony.emgss", false)) {
            Rlog.e(LOG_TAG, "prepare no emgss");
        } else if (LgeAutoProfiling.isOperator("SPR")) {
            Rlog.e(LOG_TAG, "prepare not supported operator");
        } else {
            Rlog.e(LOG_TAG, "prepare");
            synchronized (sSync) {
                if (sInstance != null) {
                    sInstance.dispose();
                }
                sInstance = new LGEcallMonitor(context, ci);
            }
        }
    }

    private LGEcallMonitor(Context context, CommandsInterface ci) {
        this.mHandler = null;
        this.mCi = null;
        this.mEmgssStarted = false;
        this.mEmcPhone = null;
        this.mInEcbm = false;
        this.mHandler = new MyHandler();
        this.mCi = ci;
        if (ci != null) {
            ci.registerForOffOrNotAvailable(this.mHandler, 1, null);
        }
    }

    private void dispose() {
        if (this.mCi != null) {
            this.mCi.unregisterForOffOrNotAvailable(this.mHandler);
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        this.mCi = null;
        this.mHandler = null;
        this.mEmgssStarted = false;
        this.mEmcPhone = null;
        this.mInEcbm = false;
    }

    public static void onEnterEmergencyMode(Phone p) {
        if (sInstance != null) {
            synchronized (sSync) {
                sInstance.onEnterEmergencyModeInternal(p);
            }
        }
    }

    public static void onExitEmergencyMode() {
        if (sInstance != null) {
            synchronized (sSync) {
                sInstance.onExitEmergencyModeInternal();
            }
        }
    }

    public static void onEcbmStateChanged(boolean on) {
        if (sInstance != null) {
            Rlog.d(LOG_TAG, "onEcbmStateChanged " + on);
            synchronized (sSync) {
                sInstance.onEcbmStateChangedInternal(on);
            }
        }
    }

    public static void onTelephonyConnectionTerminated(Connection originalConnection) {
        //if (sInstance != null && originalConnection != null && originalConnection.isLgeEmergencyNumber()) {
        if (sInstance != null && originalConnection != null) {
            Rlog.d(LOG_TAG, "onConnectionTerminated");
            synchronized (sSync) {
                sInstance.onTelephonyConnectionTerminatedInternal(originalConnection);
            }
        }
    }

    private void onEnterEmergencyModeInternal(Phone p) {
        if (this.mHandler != null && this.mCi != null) {
            reset();
            if (this.mCi.getRadioState().isAvailable()) {
                this.mHandler.removeMessages(1);
                this.mEmgssStarted = true;
                this.mEmcPhone = p;
            }
        }
    }

    private void onExitEmergencyModeInternal() {
        if (this.mHandler != null) {
            reset();
        }
    }

    private void onEcbmStateChangedInternal(boolean on) {
        if (this.mHandler != null) {
            removeEvents();
            this.mInEcbm = on;
        }
    }

    public void onTelephonyConnectionTerminatedInternal(Connection originalConnection) {
        if (this.mHandler != null && this.mEmgssStarted && !this.mInEcbm) {
            Rlog.d(LOG_TAG, "onConnectionTerminated found");
            sendMessage(2, DELAY_MILLIS);
        }
    }

    private void removeEvents() {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(2);
        }
    }

    private void reset() {
        removeEvents();
        this.mEmgssStarted = false;
        this.mEmcPhone = null;
    }

    private void sendMessage(int what, long delayMillis) {
        if (this.mHandler != null) {
            if (delayMillis > 0) {
                this.mHandler.sendEmptyMessageDelayed(what, delayMillis);
            } else {
                this.mHandler.sendEmptyMessage(what);
            }
        }
    }
}
