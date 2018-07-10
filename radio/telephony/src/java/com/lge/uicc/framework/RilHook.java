package com.lge.uicc.framework;

import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.CommandException.Error;
import com.android.internal.telephony.CommandsInterface;
import com.android.internal.telephony.IccProvider;
import com.android.internal.telephony.uicc.IccCardApplicationStatus.AppType;
import com.android.internal.telephony.uicc.IccConstants;
import com.android.internal.telephony.uicc.IccIoResult;
import com.android.internal.telephony.uicc.SIMRecords;
import com.google.android.mms.pdu.PduHeaders;
import com.lge.uicc.EfUtils;

public class RilHook extends Handler {
    private static final int HOOK_MSG = 1;
    private static final String LOGHEAD = "[RilHook] ";
    private static RilHook[] mInstances = null;
    final CommandException RESPONSE_GENERIC_FAILURE = new CommandException(Error.GENERIC_FAILURE);
    private int mSlotId = 0;

    protected abstract class Hook {
        Message msg;

        Hook(Message m) {
            this.msg = m;
        }

        Message handleResult(AsyncResult ar) {
            if (ar == null || this.msg == null) {
                return null;
            }
            AsyncResult.forMessage(this.msg, ar.result, ar.exception);
            return this.msg;
        }
    }

    private class HookIccPin extends Hook {
        String mAid;
        boolean mBlocked = false;
        String mPin;
        String mType;

        HookIccPin(String type, boolean blocked, String pin, String aid, Message msg) {
            super(msg);
            this.mType = type;
            this.mBlocked = blocked;
            this.mPin = pin;
            this.mAid = aid;
        }

        Message handleResult(AsyncResult ar) {
            if (ar.exception == null) {
                if (this.mType.equals("pin1") && !this.mBlocked) {
                    RilHook.this.logd("save hiddenpin");
                    LGUICC.setPreference("hiddenpin" + RilHook.this.mSlotId, this.mAid + "/" + this.mPin);
                }
                if (LGUICC.isMtkBoard) {
                    if (ar.result != null && (ar.result instanceof int[])) {
                        RilHook.this.updateRemainingCount(this.mType, this.mBlocked, ((int []) ar.result)[0]);
                    } else if (ar.result != null && (ar.result instanceof Integer)) {
                        RilHook.this.updateRemainingCount(this.mType, this.mBlocked, ((Integer) ar.result).intValue());
                    }
                }
            } else if (ar.result != null && (ar.result instanceof int[])) {
                RilHook.this.updateRemainingCount(this.mType, this.mBlocked, ((int[]) ar.result)[0]);
            } else if (ar.result != null && (ar.result instanceof Integer)) {
                RilHook.this.updateRemainingCount(this.mType, this.mBlocked, ((Integer) ar.result).intValue());
            }
            return super.handleResult(ar);
        }
    }

    protected static void setup() {
        if (mInstances == null) {
            int slots = IccTools.getSlots();
            mInstances = new RilHook[slots];
            for (int i = 0; i < slots; i++) {
                mInstances[i] = new RilHook(i);
            }
        }
    }

    public static RilHook getInstance(int slot) {
        return mInstances[slot];
    }

    private RilHook(int slot) {
        this.mSlotId = slot;
    }

    protected Message obtainHookMessage(Hook h) {
        return obtainMessage(1, h);
    }

    public void handleMessage(Message hookmsg) {
        switch (hookmsg.what) {
            case 1:
                try {
                    AsyncResult ar = (AsyncResult) hookmsg.obj;
                    Hook h = (Hook) ar.userObj;
                    if (h == null) {
                        loge("unknown message!!");
                        return;
                    }
                    logd(h.getClass().getSimpleName() + ".handleResult()");
                    Message msg = h.handleResult(ar);
                    if (msg != null) {
                        msg.sendToTarget();
                        return;
                    }
                    return;
                } catch (RuntimeException exc) {
                    loge("Exception handling Result: " + exc);
                    return;
                }
            default:
                return;
        }
    }

    public Message handleGetIccCardStatus(Message result) {
        return IccStateHandler.handleGetIccCardStatus(this.mSlotId, result);
    }

    public Message handleGetIMSIForApp(String aid, Message result) {
        if (IccTools.isMultiSimEnabled() || this.mSlotId != 0) {
            return result;
        }
        return ImsiHandler.handleGetImsiForApp(aid, result);
    }

    public Message handleIccIOForApp(int command, int fileid, String path, int p1, int p2, int p3, String data, String pin2, String aid, Message result) {
        if (result != null && command == 192) {
            switch (fileid) {
                case IccConstants.EF_VOICE_MAIL_INDICATOR_CPHS /*28433*/:
                case IccConstants.EF_MWIS /*28618*/:
                    if (LGUICC.targetOperator("SKT", "KT") && isCaller(SIMRecords.class)) {
                        logd("discard command: ef=" + String.format("%x", new Object[]{Integer.valueOf(fileid)}));
                        return discardRilCommand(result);
                    }
                case IccConstants.EF_CFIS /*28619*/:
                    if (LGUICC.targetOperator("SKT") && isCaller(SIMRecords.class)) {
                        logd("discard command: ef=" + String.format("%x", new Object[]{Integer.valueOf(fileid)}));
                        return discardRilCommand(result);
                    }
            }
        }
        if (result != null && command == PduHeaders.ADDITIONAL_HEADERS) {
            switch (fileid) {
                case IccConstants.EF_ICCID /*12258*/:
                    return obtainHookMessage(new Hook(result) {
                        Message handleResult(AsyncResult ar) {
                            if (ar.exception == null) {
                                String iccid;
                                IccIoResult ioresult = (IccIoResult) ar.result;
                                if (LGUICC.targetCountry("CN")) {
                                    iccid = RilHook.CNIccidToString(ioresult.payload, 0, ioresult.payload.length);
                                } else {
                                    iccid = EfUtils.bcdToString(ioresult.payload);
                                }
                                if (IccidHandler.handleGetIccidDone(iccid, mSlotId) == null) {
                                    logd("report fail to load iccid");
                                    ar.exception = RESPONSE_GENERIC_FAILURE;
                                }
                            }
                            return super.handleResult(ar);
                        }
                    });
            }
        }
        return result;
    }

    private void updateRemainingCount(String pin_type, boolean blocked, int remain) {
        String remainCount = Integer.toString(remain);
        logd("updateRemainingCount : " + (blocked ? "PUK" : "PIN") + " of " + pin_type + " : remain=" + remainCount);
        for (AppType app_type : AppType.values()) {
            String key = app_type.toString().substring("APPTYPE_".length()).toLowerCase() + "." + pin_type;
            String[] pinData = LGUICC.getConfig(key, this.mSlotId).split("/");
            if (pinData.length == 3) {
                if (blocked) {
                    pinData[2] = remainCount;
                } else {
                    pinData[1] = remainCount;
                }
                LGUICC.setConfig(key, this.mSlotId, pinData[0] + "/" + pinData[1] + "/" + pinData[2]);
            }
        }
    }

    public Message handleSupplyIccPinForApp(String pin, String aid, Message result) {
        logd("handleSupplyIccPinForApp");
        return obtainHookMessage(new HookIccPin("pin1", false, pin, aid, result));
    }

    public Message handleSupplyIccPukForApp(String puk, String newPin, String aid, Message result) {
        logd("handleSupplyIccPukForApp");
        return obtainHookMessage(new HookIccPin("pin1", true, newPin, aid, result));
    }

    public Message handleChangeIccPinForApp(String oldPin, String newPin, String aid, Message result) {
        logd("handleChangeIccPinForApp");
        return obtainHookMessage(new HookIccPin("pin1", false, newPin, aid, result));
    }

    public Message handleSupplyIccPin2ForApp(String pin, String aid, Message result) {
        logd("handleSupplyIccPin2ForApp");
        return obtainHookMessage(new HookIccPin(IccProvider.STR_PIN2, false, pin, aid, result));
    }

    public Message handleSupplyIccPuk2ForApp(String puk, String newPin2, String aid, Message result) {
        logd("handleSupplyIccPuk2ForApp");
        return obtainHookMessage(new HookIccPin(IccProvider.STR_PIN2, true, newPin2, aid, result));
    }

    public Message handleChangeIccPin2ForApp(String oldPin2, String newPin2, String aid, Message result) {
        logd("handleChangeIccPin2ForApp");
        return obtainHookMessage(new HookIccPin(IccProvider.STR_PIN2, false, newPin2, aid, result));
    }

    public Message handleSetFacilityLockForApp(String facility, boolean lockState, String password, int serviceClass, String appId, Message response) {
        logd("handleSetFacilityLockForApp");
        if (facility.equals(CommandsInterface.CB_FACILITY_BA_SIM)) {
            return obtainHookMessage(new HookIccPin("pin1", LGUICC.getConfig("usim.pin1", this.mSlotId).split("/")[0].equals("ENABLED_BLOCKED"), password, appId, response));
        } else if (!facility.equals(CommandsInterface.CB_FACILITY_BA_FD)) {
            return response;
        } else {
            return obtainHookMessage(new HookIccPin(IccProvider.STR_PIN2, LGUICC.getConfig("usim.pin2", this.mSlotId).split("/")[0].equals("ENABLED_BLOCKED"), password, appId, response));
        }
    }

    private boolean isCaller(Class target) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int s = 4; s < stack.length; s++) {
            String cls = stack[s].getClassName();
            if (cls.startsWith("com.lge.uicc") || cls.startsWith("java.lang")) {
                return false;
            }
            if (cls.equals(target.getName())) {
                return true;
            }
        }
        return false;
    }

    private Message discardRilCommand(Message msg) {
        AsyncResult.forMessage(msg, null, this.RESPONSE_GENERIC_FAILURE);
        msg.sendToTarget();
        return Message.obtain(null, 0);
    }

    public static boolean isDiscarded(Message msg) {
        if (msg == null || msg.getTarget() != null) {
            return false;
        }
        return true;
    }

    public static String CNIccidToString(byte[] data, int offset, int length) {
        StringBuilder ret = new StringBuilder(length * 2);
        for (int i = offset; i < offset + length; i++) {
            int v = data[i] & 15;
            if (v > 9) {
                ret.append((char) ((v - 10) + 65));
            } else {
                ret.append((char) (v + 48));
            }
            v = (data[i] >> 4) & 15;
            if (v != 15) {
                if (v > 9) {
                    ret.append((char) ((v - 10) + 65));
                } else {
                    ret.append((char) (v + 48));
                }
            }
        }
        return ret.toString();
    }

    protected void loge(String s) {
        LGUICC.loge(LOGHEAD + s);
    }

    protected void logd(String s) {
        LGUICC.logd(LOGHEAD + s);
    }

    protected void logp(String s) {
        LGUICC.logp(LOGHEAD + s);
    }
}
