package com.lge.uicc;

import android.os.Handler;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.Rlog;
import android.util.TimedRemoteCaller;
import com.lge.uicc.ISimCallback.Stub;
import java.lang.ref.WeakReference;

public class SimStateListener {
    public static final int LISTEN_CARD_STATE = 2;
    public static final int LISTEN_KR_PERSO = 16;
    public static final int LISTEN_NONE = 0;
    public static final int LISTEN_SIM_STATE = 1;
    public static final int SERVICE_CONNECTED = 268435456;
    public static final int SERVICE_DISCONNECTED = 536870912;
    ISimCallback mCallback;
    Runnable mConnector;
    DeathRecipient mDeath;
    final Handler mHandler;

    public SimStateListener() {
        this(Looper.myLooper());
    }

    public SimStateListener(Looper looper) {
        this.mCallback = new Stub() {
            private WeakReference<SimStateListener> listenerRef = new WeakReference(SimStateListener.this);

            public void sendEvent(int event, int arg1, int arg2, byte[] data) throws RemoteException {
                SimStateListener listener = (SimStateListener) this.listenerRef.get();
                if (listener != null) {
                    Message.obtain(listener.mHandler, event, arg1, arg2, data).sendToTarget();
                }
            }
        };
        this.mDeath = new DeathRecipient() {
            private WeakReference<SimStateListener> listenerRef = new WeakReference(SimStateListener.this);

            public void binderDied() {
                SimStateListener listener = (SimStateListener) this.listenerRef.get();
                if (listener != null) {
                    listener.loge("binderDied");
                    listener.resetConnection(null);
                }
            }
        };
        this.mConnector = null;
        this.mHandler = new Handler(looper) {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        SimStateListener.this.onSimStateChanged(msg.arg1, new String((byte[]) msg.obj));
                        return;
                    case 2:
                        SimStateListener.this.onCardStateChanged(msg.arg1, new String((byte[]) msg.obj));
                        return;
                    case 16:
                        SimStateListener.this.onKrPersoUnlocked();
                        return;
                    case 268435456:
                        SimStateListener.this.logd("SERVICE_CONNECTED");
                        return;
                    case 536870912:
                        SimStateListener.this.logd("SERVICE_DISCONNECTED");
                        return;
                    default:
                        SimStateListener.this.loge("unhandled message: " + msg.what);
                        return;
                }
            }
        };
    }

    public void onSimStateChanged(int slot, String state) {
        logd("onSimStateChanged: slot=" + slot + ", state=" + state);
    }

    public void onCardStateChanged(int slot, String state) {
        logd("onCardStateChanged: slot=" + slot + ", state=" + state);
    }

    public void onKrPersoUnlocked() {
        logd("onKrPersoUnlocked");
    }

    void resetConnection(Runnable connector) {
        if (this.mConnector != null) {
            this.mHandler.removeCallbacks(this.mConnector);
        }
        if (connector != null) {
            this.mConnector = connector;
            this.mConnector.run();
        } else if (this.mConnector != null) {
            this.mHandler.postDelayed(this.mConnector, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
        } else {
            loge("no connector created!");
        }
    }

    void logd(String s) {
        Rlog.d("LGUICC", "[SimStateListener] " + s);
    }

    void loge(String s) {
        Rlog.e("LGUICC", "[SimStateListener] " + s);
    }
}
