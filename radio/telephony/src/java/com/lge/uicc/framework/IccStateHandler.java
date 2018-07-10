package com.lge.uicc.framework;

import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import com.android.internal.telephony.CommandsInterface.RadioState;
import com.android.internal.telephony.RIL;
import com.android.internal.telephony.gsm.LgeNetworkNameConstants;
import com.android.internal.telephony.uicc.IccCardApplicationStatus;
import com.android.internal.telephony.uicc.IccCardApplicationStatus.AppState;
import com.android.internal.telephony.uicc.IccCardApplicationStatus.AppType;
import com.android.internal.telephony.uicc.IccCardStatus;
import com.lge.telephony.provider.TelephonyProxy.Carriers;
import com.lge.uicc.LGUiccConstants;
import com.lge.uicc.framework.LGUICC.ConfigListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IccStateHandler extends Handler {
    private static final int EVENT_HIDDEN_PIN_DONE = 2;
    private static final int EVENT_RADIO_STATE_CHANGED = 3;
    private static final int EVENT_RIL_GET_SIM_STATUS_DONE = 1;
    private static final int EVENT_SHOW_SIM_PIN_VIEW = 4;
    private static IccStateHandler[] mInstances;
    private boolean hidden_reset_checked = false;
    private int last_modem_reboot_count = 0;
    private boolean mHiddenSupplyPinRunning = false;
    private ArrayList<Message> mMessageQ = new ArrayList();
    private RadioState mRadioState = null;
    private int mSlotId = 0;

    protected static void setup() {
        if (mInstances == null) {
            int slots = IccTools.getSlots();
            mInstances = new IccStateHandler[slots];
            for (int i = 0; i < slots; i++) {
                mInstances[i] = new IccStateHandler(i);
            }
        }
    }

    private IccStateHandler(int slotId) {
        this.mSlotId = slotId;
        RIL ril = IccTools.getRIL(this.mSlotId);
        if (ril != null) {
            ril.registerForRadioStateChanged(this, 3, null);
            if (IccTools.isMultiSimEnabled() && slotId == 0) {
                LGUICC.registerForConfig("show_sim_pin_view", (Handler) this, 4, null);
            }
            LGUICC.registerForConfig("card_event", slotId, (Handler) this, new ConfigListener() {
                public void onConfigChanged(String key, int slot, String value) {
                    if ("REMOVED".equals(value)) {
                        IccTools.broadcastIccStateChangedIntent(LGUiccConstants.INTENT_VALUE_ICC_REMOVED, null, slot);
                    }
                }
            });
        }
    }

    public static Message handleGetIccCardStatus(int slotId, Message msg) {
        if (mInstances == null) {
            return msg;
        }
        IccStateHandler ish = mInstances[slotId];
        ish.logd("handleGetIccCardStatus");
        return ish.obtainMessage(1, msg);
    }

    private void returnGetIccCardStatus(AsyncResult ar) {
        Message msg = (Message) ar.userObj;
        if (msg == null || this.mHiddenSupplyPinRunning) {
            logd("returnGetIccCardStatus " + this.mMessageQ.size());
            for (Message m : this.mMessageQ) {
                AsyncResult.forMessage(m, ar.result, ar.exception);
                m.sendToTarget();
            }
            this.mMessageQ.clear();
            this.mHiddenSupplyPinRunning = false;
            return;
        }
        logd("returnGetIccCardStatus");
        AsyncResult.forMessage(msg, ar.result, ar.exception);
        msg.sendToTarget();
    }

    public void handleMessage(Message msg) {
        AsyncResult ar = (AsyncResult) msg.obj;
        RIL ril;
        switch (msg.what) {
            case 1:
                logd("EVENT_RIL_GET_SIM_STATUS_DONE");
                String shutdownAction = SystemProperties.get("sys.shutdown.requested");
                if (shutdownAction != null && shutdownAction.length() > 0) {
                    logd("ignore getIccStatus due to shut down by " + shutdownAction);
                    return;
                } else if (ar.exception != null || ar.result == null) {
                    logd("error in RIL_REQUEST_GET_SIM_STATUS : " + ar.exception);
                    returnGetIccCardStatus(ar);
                    return;
                } else {
                    int i;
                    IccCardStatus cardStatus = (IccCardStatus) ar.result;
                    int numApplications = cardStatus.mApplications.length;
                    if (!this.mHiddenSupplyPinRunning) {
                        i = 0;
                        while (i < numApplications && cardStatus.mApplications[i].app_state != AppState.APPSTATE_PIN) {
                            i++;
                        }
                        if (i < numApplications && isHiddenReset() && hiddenSupplyPin(cardStatus)) {
                            this.mMessageQ.add((Message) ar.userObj);
                            logd("hiddenSupplyPin start " + this.mMessageQ.size());
                            this.mHiddenSupplyPinRunning = true;
                            return;
                        }
                    } else if (ar.userObj != null) {
                        this.mMessageQ.add((Message) ar.userObj);
                        logd("hiddenSupplyPin Running " + this.mMessageQ.size());
                        return;
                    } else {
                        logd("hiddenSupplyPin complete.");
                        this.mHiddenSupplyPinRunning = false;
                    }
                    logd("load IccCardStatus...");
                    if (cardStatus.mCardError != null) {
                        LGUICC.notifyConfig("card_event", this.mSlotId, cardStatus.mCardError);
                    }
                    String card_state = cardStatus.mCardState.toString().substring("CARDSTATE_".length());
                    if (LGUICC.isMtkBoard && cardStatus.mCardError == null && !LGUICC.getConfig("card_state", this.mSlotId, "ABSENT").equals("ABSENT") && card_state.equals("ABSENT")) {
                        LGUICC.notifyConfig("card_event", this.mSlotId, "REMOVED");
                    }
                    LGUICC.setConfig("card_state", this.mSlotId, card_state);
                    clearAllCardAppConfig();
                    LGUICC.setConfig("card_app_count", this.mSlotId, Integer.toString(numApplications));
                    i = 0;
                    while (i < numApplications) {
                        if (i == cardStatus.mGsmUmtsSubscriptionAppIndex || i == cardStatus.mCdmaSubscriptionAppIndex || i == cardStatus.mImsSubscriptionAppIndex) {
                            updateCardAppConfig(cardStatus.mApplications[i]);
                        }
                        i++;
                    }
                    returnGetIccCardStatus(ar);
                    return;
                }
            case 2:
                logd("EVENT_HIDDEN_PIN_DONE");
                if (ar.exception == null) {
                    logd("Hidden PIN verify OK");
                } else {
                    logd("Hidden PIN verify Fails : " + ar.exception);
                }
                LGUICC.setConfig("hiddenpin_state", this.mSlotId, ar.exception == null ? "SUCCESS" : "FAIL");
                ril = IccTools.getRIL(this.mSlotId);
                if (ril != null) {
                    ril.getIccCardStatus(null);
                    return;
                }
                return;
            case 3:
                ril = IccTools.getRIL(this.mSlotId);
                if (ril != null) {
                    RadioState newRadioState = ril.getRadioState();
                    if (this.mRadioState != newRadioState) {
                        String radio_state = newRadioState.toString().substring("RADIO_".length());
                        logd("EVENT_RADIO_STATE_CHANGED : " + radio_state);
                        this.mRadioState = newRadioState;
                        LGUICC.setConfig("radio_state", this.mSlotId, radio_state);
                        return;
                    }
                    return;
                }
                return;
            case 4:
                logd("EVENT_SHOW_SIM_PIN_VIEW");
                if (ar.result != null) {
                    int slot = ((Integer) ar.result).intValue();
                    String app_state = LGUICC.getConfig("proxy.sim_state", slot);
                    if (app_state.equals("PIN_REQUIRED")) {
                        IccTools.broadcastIccStateChangedIntent("LOCKED", "PIN", slot);
                    } else if (app_state.equals("PUK_REQUIRED")) {
                        IccTools.broadcastIccStateChangedIntent("LOCKED", "PUK", slot);
                    }
                    LGUICC.removeConfig("show_sim_pin_view", slot);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean hiddenSupplyPin(IccCardStatus cardStatus) {
        RIL ril = IccTools.getRIL(this.mSlotId);
        if (ril == null) {
            return false;
        }
        String[] pinData = LGUICC.getPreference("hiddenpin" + this.mSlotId).split("/");
        if (pinData == null || pinData.length != 2) {
            return false;
        }
        String aid = pinData[0];
        String pin = pinData[1];
        for (IccCardApplicationStatus appStatus : cardStatus.mApplications) {
            if (appStatus.app_state == AppState.APPSTATE_PIN && (aid.equals(LgeNetworkNameConstants.ITEM_VALUE_NULL) || aid.equals(appStatus.aid))) {
                logp("hiddenSupplyPin: aid=" + aid);
                if (appStatus.remaining_count_pin1 < 3) {
                    loge("hiddenSupplyPin: fail: remaining_count_pin1=" + appStatus.remaining_count_pin1);
                } else {
                    if (aid.equals(LgeNetworkNameConstants.ITEM_VALUE_NULL)) {
                        aid = null;
                    }
                    ril.supplyIccPinForApp(pin, aid, obtainMessage(2));
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isHiddenReset() {
        Exception e;
        Throwable th;
        if (!this.hidden_reset_checked) {
            this.hidden_reset_checked = true;
            if (SystemProperties.get("ro.lge.hiddenreset", Carriers.PPP_DIALING_NUMBER_DEFAULT).equals("1")) {
                logd("hiddenreset occured ");
                return true;
            }
        }
        int modem_reboot_cnt = 0;
        BufferedReader bufferedReader = null;
        try {
            File f = new File("/sys/module/subsystem_restart/parameters/modem_reboot_cnt");
            if (f.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                try {
                    modem_reboot_cnt = Integer.parseInt(reader.readLine());
                    bufferedReader = reader;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = reader;
                    try {
                        loge("error " + e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (modem_reboot_cnt > 0) {
                        }
                        logd("hidden reset not occured ");
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = reader;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
            loge("can not check modem_reboot_cnt");
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                }
            }
        } catch (Exception e6) {
            e = e6;
            loge("error " + e);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (modem_reboot_cnt > 0) {
            }
            logd("hidden reset not occured ");
            return false;
        }
        if (modem_reboot_cnt > 0 || modem_reboot_cnt == this.last_modem_reboot_count) {
            logd("hidden reset not occured ");
            return false;
        }
        logd("modem crash arise! hidden reset flag on! ");
        this.last_modem_reboot_count = modem_reboot_cnt;
        return true;
    }

    private void clearAllCardAppConfig() {
        for (AppType app_type : AppType.values()) {
            String app = app_type.toString().substring("APPTYPE_".length()).toLowerCase();
            String pin1_state = LGUICC.getConfig(app + ".pin1", this.mSlotId).split("/")[0];
            String pin2_state = LGUICC.getConfig(app + ".pin2", this.mSlotId).split("/")[0];
            LGUICC.removeConfig(app + ".state", this.mSlotId);
            LGUICC.removeConfig(app + ".state.perso", this.mSlotId);
            LGUICC.removeConfig(app + ".aid", this.mSlotId);
            if (LGUICC.isMtkBoard) {
                if ("UNKNOWN".equals(pin1_state)) {
                    LGUICC.removeConfig(app + ".pin1", this.mSlotId);
                }
                if ("UNKNOWN".equals(pin2_state)) {
                    LGUICC.removeConfig(app + ".pin2", this.mSlotId);
                }
            } else {
                LGUICC.removeConfig(app + ".pin1", this.mSlotId);
                LGUICC.removeConfig(app + ".pin2", this.mSlotId);
            }
        }
    }

    private void updateCardAppConfig(IccCardApplicationStatus as) {
        if (as.app_type != AppType.APPTYPE_UNKNOWN) {
            String app = as.app_type.toString().substring("APPTYPE_".length()).toLowerCase();
            String state = as.app_state.toString().substring("APPSTATE_".length());
            String pin1_state = as.pin1.toString().substring("PINSTATE_".length());
            String pin2_state = as.pin2.toString().substring("PINSTATE_".length());
            if (LGUICC.isMtkBoard && "READY".equals(state) && "UNKNOWN".equals(pin1_state) && !LGUICC.getConfig(app + ".pin1", this.mSlotId).split("/")[0].equals("UNKNOWN")) {
                logp("updateCardAppConfig: not save pin1 unknown state!!!: pin1_state=" + pin1_state);
                pin1_state = LGUICC.getConfig(app + ".pin1", this.mSlotId).split("/")[0];
            }
            LGUICC.setConfig(app + ".state", this.mSlotId, state);
            LGUICC.setConfig(app + ".state.perso", this.mSlotId, as.app_state == AppState.APPSTATE_SUBSCRIPTION_PERSO ? as.perso_substate.toString().substring("PERSOSUBSTATE_".length()) : null);
            LGUICC.setConfig(app + ".aid", this.mSlotId, as.aid);
            LGUICC.setConfig(app + ".pin1", this.mSlotId, pin1_state + "/" + as.remaining_count_pin1 + "/" + as.remaining_count_puk1);
            LGUICC.setConfig(app + ".pin2", this.mSlotId, pin2_state + "/" + as.remaining_count_pin2 + "/" + as.remaining_count_puk2);
            logp(app + " : state=" + state + ", pin1=" + LGUICC.getConfig(app + ".pin1", this.mSlotId) + ", pin2=" + LGUICC.getConfig(app + ".pin2", this.mSlotId));
        }
    }

    private void loge(String s) {
        LGUICC.loge("[IccStateHandler:" + this.mSlotId + "] " + s);
    }

    private void logd(String s) {
        LGUICC.logd("[IccStateHandler:" + this.mSlotId + "] " + s);
    }

    private void logp(String s) {
        LGUICC.logp("[IccStateHandler:" + this.mSlotId + "] " + s);
    }
}
