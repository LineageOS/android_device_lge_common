package com.lge.uicc.framework;

import android.os.Handler;
import android.os.Message;
import com.android.internal.telephony.RIL;
import com.lge.uicc.EfUtils;

public class LGUiccRIL extends Handler {
    public LGUiccRIL(RIL rilObj) {
    }

    static LGUiccRIL make(RIL rilObj) {
        try {
            return (LGUiccRIL) IccTools.getDerivedClass(LGUiccRIL.class).getConstructor(new Class[]{RIL.class}).newInstance(new Object[]{rilObj});
        } catch (Exception e) {
            LGUICC.loge("[LGUiccRIL] make: " + e.toString());
            return null;
        }
    }

    void getAtr(Message response) {
        logd("dummy getAtr");
        IccTools.postGenericFailure(response);
    }

    void transmitApdu(byte[] apdu, Message response) {
        logp("dummy transmitAPDU " + EfUtils.bytesToHexString(apdu));
        IccTools.postGenericFailure(response);
    }

    void requestUimPowerDown(Message response) {
        logd("dummy requestUimPowerDown");
        IccTools.postGenericFailure(response);
    }

    void requestUimInternalCommand(int cmd, byte[] data, Message response) {
        logd("dummy requestUimInternalCommand " + cmd);
        IccTools.postGenericFailure(response);
    }

    void loge(String s) {
        LGUICC.loge("[LGUiccRIL] " + s);
    }

    void logd(String s) {
        LGUICC.logd("[LGUiccRIL] " + s);
    }

    void logp(String s) {
        LGUICC.logp("[LGUiccRIL] " + s);
    }
}
