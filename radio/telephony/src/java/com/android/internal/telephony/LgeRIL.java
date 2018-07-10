package com.android.internal.telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.radio.V1_0.LastCallFailCause;
import android.hardware.radio.V1_0.RadioAccessFamily;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.IHwBinder.DeathRecipient;
import android.os.Message;
import android.os.Registrant;
import android.os.RegistrantList;
import android.os.SystemProperties;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.telephony.Rlog;
import android.telephony.SignalStrength;
import android.text.TextUtils;
import android.util.Log;

import com.android.internal.telephony.DriverCall.State;
import com.android.internal.telephony.dataconnection.DataCallResponse;
import com.android.internal.telephony.dataconnection.DataProfile;
import com.android.internal.telephony.dataconnection.DcTracker;
import com.android.internal.telephony.lgeautoprofiling.LgeAutoProfiling;
import com.android.internal.telephony.metrics.TelephonyMetrics;
import com.android.internal.telephony.uicc.IccUtils;

//import com.lge.telephony.LGSignalStrength;
import com.lge.telephony.provider.TelephonyProxy.Carriers;
import com.lge.uicc.Plog;
import com.lge.uicc.framework.LGUICC;
import com.lge.uicc.framework.PbmRecord;
import com.lge.uicc.framework.RilHook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import vendor.lge.hardware.radio.V1_0.ILgeRadio;
import vendor.lge.hardware.radio.V1_0.ILgeRadioPropertyControl;
import vendor.lge.hardware.radio.V1_0.LgeCdmaSmsWriteArgs;
import vendor.lge.hardware.radio.V1_0.LgeDataProfileInfo;
import vendor.lge.hardware.radio.V1_0.LgeDmRequest;
import vendor.lge.hardware.radio.V1_0.LgeEmbmsInterestedTmgiReqMsg;
import vendor.lge.hardware.radio.V1_0.LgeEmbmsStartSessionRegMsg;
import vendor.lge.hardware.radio.V1_0.LgeEmbmsStopSessionReqMsg;
import vendor.lge.hardware.radio.V1_0.LgeEmbmsSwitchSessionReqMsg;
import vendor.lge.hardware.radio.V1_0.LgeIntString;
import vendor.lge.hardware.radio.V1_0.LgeMocaConfigInfo;
import vendor.lge.hardware.radio.V1_0.LgeMocaGetMisc;
import vendor.lge.hardware.radio.V1_0.LgePbmRecords;
import vendor.lge.hardware.radio.V1_0.LgeSetupDataCallResult;
import vendor.lge.hardware.radio.V1_0.LgeSignalStrength;
import vendor.lge.hardware.radio.V1_0.LgeSrvccCall;
import vendor.lge.hardware.radio.V1_0.LgeSrvccCallContextConfig;
import vendor.lge.hardware.radio.V1_0.LgeUiccAkaAuthenticate;
import vendor.lge.hardware.radio.V1_0.LgeUiccApplicationIo;
import vendor.lge.hardware.radio.V1_0.LgeUiccGbaAuthenticateNaf;
import vendor.lge.hardware.radio.V1_0.LgeUimInternal;
import vendor.lge.hardware.radio.V1_0.LgeUsimAuth;

public class LgeRIL extends RIL {
    static final int DM_CMD_EXTERNAL_CMD_MAX = 5999;
    static final int DM_CMD_EXTERNAL_CMD_MIN = 5000;
    static final int DM_CMD_EXTERNAL_MOCA_DISABLE = 5002;
    static final int DM_CMD_EXTERNAL_MOCA_ENABLE = 5001;
    static final int DM_CMD_EXTERNAL_SDM_DISABLE = 5202;
    static final int DM_CMD_EXTERNAL_SDM_ENABLE = 5201;
    static final int DM_CMD_EXTERNAL_VOQAS_DISABLE = 5102;
    static final int DM_CMD_EXTERNAL_VOQAS_ENABLE = 5101;
    static final int DM_CMD_INTERNAL_CMD_MAX = 4999;
    static final int DM_CMD_INTERNAL_CMD_MIN = 0;
    static final int DM_CMD_INTERNAL_SET_PROP = 1001;
    static final int EVENT_LGE_RADIO_PROXY_DEAD = 6;
    static final int ILGERADIO_GET_SERVICE_DELAY_MILLIS = 3000;
    static final String[] LGERIL_SERVICE_NAME = new String[]{"lge_radio", "lge_radio2", "lge_radio3"};
    static final String LGERIL_TAG = "RILJ_EX";
    private static final String LGE_VSS_MODEM_RESET = "com.lge.vss_modem_reset";
    private static final int RAADIO_PROP_DEATH_COOKIE = 7000;
    private static final int ROAMING_INFO_ALL = 255;
    private static final int ROAMING_INFO_DATA = 16;
    private static final int ROAMING_INFO_DATA_ROAMING = 1;
    private static final int ROAMING_INFO_HOMEONLY = 8;
    private static final int ROAMING_INFO_LTE_ROAMING = 2;
    private static final int ROAMING_INFO_VOLTE = 4;
    private State mImsCallstate = State.ACTIVE;
    BroadcastReceiver mIntentReceiverLgeRIL = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LgeRIL.LGE_VSS_MODEM_RESET)) {
                LgeRIL.this.riljLog("[MBSP] Send RIL Command - RIL_REQUEST_VSS_MODEM_RESET");
                LgeRIL.this.vssModemReset(null);
                return;
            }
            LgeRIL.this.riljLog("LGE RIL BroadCastReceiver unexpected Intent" + intent.getAction());
        }
    };
    private boolean mIsSrvccIncoming = true;
    public Object mLastSIB16TimeInfo;
    private TelephonyMetrics mLgeMetrics = TelephonyMetrics.getInstance();
    final Integer mLgePhoneId;
    LgeRadioFalcon mLgeRadioFalcon;
    LgeRadioIndication mLgeRadioIndication = null;
    volatile ILgeRadioPropertyControl mLgeRadioPropertyControlProxy = null;
    final LgeRadioPropertyControlProxyDeathRecipient mLgeRadioPropertyControlProxyDeathRecipient;
    volatile ILgeRadio mLgeRadioProxy = null;
    final AtomicLong mLgeRadioProxyCookie = new AtomicLong(0);
    final LgeRadioProxyDeathRecipient mLgeRadioProxyDeathRecipient;
    LgeRadioResponse mLgeRadioResponse = null;
    LgeRadioTest mLgeRadioTest;
    final LgeRilHandler mLgeRilHandler;
    private boolean mStateIncomingCall = false;
    private boolean mStateSRVCC = false;
    private String mStrSRVCCcnap = null;
    private String mStrSRVCCnumber = null;
    private int mStrSRVCCnumberPresentation = 3;
    private boolean mUseFrameworkCallContext = false;

    protected Registrant mSIB16TimeRegistrant;
    protected RegistrantList mStartQueryAvailableNetworkRegistrants = new RegistrantList();
    protected RegistrantList mEndQueryAvailableNetworkRegistrants = new RegistrantList();
    protected RegistrantList mSetPreferredNetworkTypeRegistrants = new RegistrantList();

    public void unSetOnSIB16Time(Handler h) {
        if (this.mSIB16TimeRegistrant != null && this.mSIB16TimeRegistrant.getHandler() == h) {
            this.mSIB16TimeRegistrant.clear();
            this.mSIB16TimeRegistrant = null;
        }
    }

    public void registerForStartQueryAvailableNetwork(Handler h, int what, Object obj) {
        this.mStartQueryAvailableNetworkRegistrants.addUnique(h, what, obj);
    }

    public void unregisterForStartQueryAvailableNetwork(Handler h) {
        this.mStartQueryAvailableNetworkRegistrants.remove(h);
    }

    public void registerForEndQueryAvailableNetwork(Handler h, int what, Object obj) {
        this.mEndQueryAvailableNetworkRegistrants.addUnique(h, what, obj);
    }

    public void unregisterForEndQueryAvailableNetwork(Handler h) {
        this.mEndQueryAvailableNetworkRegistrants.remove(h);
    }

    public void registerForSetPreferredNetworkType(Handler h, int what, Object obj) {
        this.mSetPreferredNetworkTypeRegistrants.addUnique(h, what, obj);
    }

    public void unregisterForSetPreferredNetworkType(Handler h) {
        this.mSetPreferredNetworkTypeRegistrants.remove(h);
    }

    final class LgeRadioPropertyControlProxyDeathRecipient implements DeathRecipient {
        LgeRadioPropertyControlProxyDeathRecipient() {
        }

        public void serviceDied(long cookie) {
            Rlog.e(LgeRIL.LGERIL_TAG, "LgeRadioProp serviceDied");
            if (cookie == 7000) {
                LgeRIL.this.mLgeRadioPropertyControlProxy = null;
            }
        }
    }

    final class LgeRadioProxyDeathRecipient implements DeathRecipient {
        LgeRadioProxyDeathRecipient() {
        }

        public void serviceDied(long cookie) {
            Rlog.e(LgeRIL.LGERIL_TAG, "Lge serviceDied");
            LgeRIL.this.mLgeRilHandler.sendMessageDelayed(LgeRIL.this.mLgeRilHandler.obtainMessage(6, Long.valueOf(cookie)), 3000);
        }
    }

    class LgeRilHandler extends Handler {
        LgeRilHandler() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 6:
                    LgeRIL.this.riljLog("handleMessage: EVENT_RADIO_PROXY_DEAD cookie = " + msg.obj + " mLgeRadioProxyCookie = " + LgeRIL.this.mLgeRadioProxyCookie.get());
                    if (((Long) msg.obj).longValue() == LgeRIL.this.mLgeRadioProxyCookie.get()) {
                        LgeRIL.this.mLgeRadioProxy = null;
                        LgeRIL.this.mLgeRadioProxyCookie.incrementAndGet();
                        LgeRIL.this.getLgeRadioProxy(null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public LgeRIL(Context context, int preferredNetworkType, int cdmaSubscription, Integer instanceId) {
        super(context, preferredNetworkType, cdmaSubscription, instanceId);
        this.mLgePhoneId = instanceId;
        this.mLgeRadioPropertyControlProxyDeathRecipient = new LgeRadioPropertyControlProxyDeathRecipient();
        this.mLgeRadioProxyDeathRecipient = new LgeRadioProxyDeathRecipient();
        this.mLgeRadioResponse = new LgeRadioResponse(this);
        this.mLgeRadioIndication = new LgeRadioIndication(this);
        try {
            this.mLgeRadioTest = new LgeRadioTest(this);
            this.mLgeRadioTest.registerAsService("lge_radio_test");
        } catch (Throwable e) {
            Log.e("RILJ", "Starting LgeRadioTest", e);
        }
        try {
            this.mLgeRadioFalcon = new LgeRadioFalcon(this);
            this.mLgeRadioFalcon.registerAsService("lge_radio_test");
        } catch (Throwable e2) {
            Log.e("RILJ", "Starting LgeRadioFalcon", e2);
        }
        this.mLgeRilHandler = new LgeRilHandler();
        IntentFilter filterLgeRIL = new IntentFilter();
        filterLgeRIL.addAction(LGE_VSS_MODEM_RESET);
        context.registerReceiver(this.mIntentReceiverLgeRIL, filterLgeRIL);
        Rlog.d(LGERIL_TAG, "LgeRIL");
        getLgeRadioProxy(null);
    }

    private ILgeRadio getLgeRadioProxy(Message result) {
        if (!this.mIsMobileNetworkSupported) {
            riljLog("getLgeRadioProxy: Not calling getService(): wifi-only");
            return null;
        } else if (this.mLgeRadioProxy != null) {
            return this.mLgeRadioProxy;
        } else {
            try {
                this.mLgeRadioProxy = ILgeRadio.getService(LGERIL_SERVICE_NAME[this.mLgePhoneId == null ? 0 : this.mLgePhoneId.intValue()]);
                if (this.mLgeRadioProxy != null) {
                    Rlog.d(LGERIL_TAG, "LgeRIL getService done");
                    this.mLgeRadioProxy.linkToDeath(this.mLgeRadioProxyDeathRecipient, this.mLgeRadioProxyCookie.incrementAndGet());
                    this.mLgeRadioProxy.setResponseFunctions(this.mLgeRadioResponse, this.mLgeRadioIndication);
                } else {
                    riljLoge("getLgeRadioProxy: mLgeRadioProxy == null");
                }
            } catch (Exception e) {
                this.mLgeRadioProxy = null;
                riljLoge("getLgeRadioProxy getService/setResponseFunctions: " + e);
            }
            if (this.mLgeRadioProxy == null) {
                riljLoge("getService fail try later");
                if (result != null) {
                    AsyncResult.forMessage(result, null, CommandException.fromRilErrno(1));
                    result.sendToTarget();
                }
                this.mLgeRilHandler.sendMessageDelayed(this.mLgeRilHandler.obtainMessage(6, Long.valueOf(this.mLgeRadioProxyCookie.get())), 3000);
            }
            return this.mLgeRadioProxy;
        }
    }

    private ILgeRadioPropertyControl getLgeRadioPropertyControlProxy() {
        if (this.mLgeRadioPropertyControlProxy != null) {
            return this.mLgeRadioPropertyControlProxy;
        }
        try {
            this.mLgeRadioPropertyControlProxy = ILgeRadioPropertyControl.getService("lge_radio_prop");
            if (this.mLgeRadioPropertyControlProxy != null) {
                Rlog.d(LGERIL_TAG, "LgeRadioPropertyControl getService done");
                this.mLgeRadioPropertyControlProxy.linkToDeath(this.mLgeRadioProxyDeathRecipient, 7000);
            } else {
                riljLoge("getgeRadioPropertyControlProxy: mLgeRadioPropertyControlProxy == null");
            }
        } catch (Exception e) {
            this.mLgeRadioPropertyControlProxy = null;
            riljLoge("getgeRadioPropertyControlProxy: " + e);
        }
        return this.mLgeRadioPropertyControlProxy;
    }

    private boolean isPrivacyModemItem(int item_index) {
        switch (item_index) {
            case 393275:
                return true;
            default:
                return false;
        }
    }

    public int radio_property_get_int(String key, int def) {
        ILgeRadioPropertyControl lgeRadioPropertyControl = getLgeRadioPropertyControlProxy();
        riljLog("radio_property_get_int key = " + key + " def = " + def);
        int ret_int = -1;
        if (lgeRadioPropertyControl != null) {
            try {
                ret_int = lgeRadioPropertyControl.radio_property_get_int(key, def);
            } catch (Exception e) {
                riljLoge("getgeRadioPropertyControlProxy: " + e);
            }
        }
        riljLog("radio_property_get_int return = " + ret_int);
        return ret_int;
    }

    public String radio_property_get_string(String key, String def) {
        ILgeRadioPropertyControl lgeRadioPropertyControl = getLgeRadioPropertyControlProxy();
        riljLog("radio_property_get_string key = " + key + " def = " + def);
        String ret_string = "";
        if (lgeRadioPropertyControl != null) {
            try {
                ret_string = lgeRadioPropertyControl.radio_property_get_string(key, def);
            } catch (Exception e) {
                riljLoge("getgeRadioPropertyControlProxy: " + e);
            }
        }
        riljLog("radio_property_get_string return = " + ret_string);
        return ret_string;
    }

    public void radio_property_set(String key, String def) {
        ILgeRadioPropertyControl lgeRadioPropertyControl = getLgeRadioPropertyControlProxy();
        riljLog("radio_property_set key = " + key + " def = " + def);
        if (lgeRadioPropertyControl != null) {
            try {
                lgeRadioPropertyControl.radio_property_set(key, def);
            } catch (Exception e) {
                riljLoge("getgeRadioPropertyControlProxy: " + e);
            }
        }
        riljLog("radio_property_set end");
    }

    public void testLgeRadioInterface(int serial, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(160, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " serial = " + serial);
            try {
                lgeRadioProxy.testLgeRadioInterface(rrSerial, serial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "testLgeRadioInterface", e);
            }
        }
    }

    public void PBMReadRecord(int EFdevice, int rec_index, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(201, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.PBMReadRecord(rrSerial, EFdevice, rec_index);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "PBMReadRecord", e);
            }
        }
    }

    public void PBMWriteRecord(PbmRecord RecordData, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(202, result, this.mRILDefaultWorkSource);
            LgePbmRecords lgePbmRecords = new LgePbmRecords();
            lgePbmRecords.device = RecordData.device;
            lgePbmRecords.index = RecordData.index;
            lgePbmRecords.type = RecordData.type;
            lgePbmRecords.ad_type = RecordData.ad_type;
            lgePbmRecords.number = RecordData.number;
            lgePbmRecords.name = RecordData.name;
            lgePbmRecords.additional_number[0] = RecordData.additional_number;
            lgePbmRecords.additional_number[1] = RecordData.additional_number_a;
            lgePbmRecords.additional_number[2] = RecordData.additional_number_b;
            lgePbmRecords.email_address = RecordData.email_address;
            lgePbmRecords.second_name = RecordData.second_name;
            lgePbmRecords.gas_id = RecordData.gas_id;
            lgePbmRecords.sync_cnt = RecordData.sync_cnt;
            //privacy_riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.PBMWriteRecord(rrSerial, lgePbmRecords);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "PBMWriteRecord", e);
            }
        }
    }

    public void PBMDeleteRecord(int EFdevice, int rec_index, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial  = obtainRequestSerial(203, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.PBMDeleteRecord(rrSerial, EFdevice, rec_index);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "PBMDeleteRecord", e);
            }
        }
    }

    public void PBMGetInitState(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(204, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.PBMGetInitState(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "PBMGetInitState", e);
            }
        }
    }

    public void getUsimAuthentication(String aid, String rand_s, int rand_length, String autn_s, int autn_length, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(205, result, this.mRILDefaultWorkSource);
            LgeUsimAuth lgeUsimAuthInfo = new LgeUsimAuth();
            lgeUsimAuthInfo.rand = convertNullToEmptyString(rand_s);
            lgeUsimAuthInfo.rand_length = rand_length;
            lgeUsimAuthInfo.autn = convertNullToEmptyString(autn_s);
            lgeUsimAuthInfo.autn_length = autn_length;
            lgeUsimAuthInfo.aid = convertNullToEmptyString(aid);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getUsimAuthentication(rrSerial, lgeUsimAuthInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getUsimAuthentication", e);
            }
        }
    }

    public void smartCardTransmit(byte[] command, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(206, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.smartCardTransmit(rrSerial, RIL.primitiveArrayToArrayList(command));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "smartCardTransmit", e);
            }
        }
    }

    public void getAtr(Message response) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(response);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(216, response, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.USIMSmartcardGetAtr(rrSerial, 0);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getAtr", e);
            }
        }
    }

    public void iccSetTransmitBehaviour(int channelNumber, boolean expectDataWithWarningSW, Message result) {
        if (channelNumber <= 0) {
            throw new RuntimeException("Invalid channel in iccSetTransmitBehaviour: " + channelNumber);
        }
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(217, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " channelNumber = " + channelNumber + " expectDataWithWarningSW = " + expectDataWithWarningSW);
            try {
                lgeRadioProxy.iccSetTransmitBehaviour(rrSerial, channelNumber, expectDataWithWarningSW);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "iccSetTransmitBehaviour", e);
            }
        }
    }

    public void PBMGetInfo(int EFdevice, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(207, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " EFdevice = " + EFdevice);
            try {
                lgeRadioProxy.PBMGetInfo(rrSerial, EFdevice);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "PBMGetInfo", e);
            }
        }
    }

    public void UIMInternalRequestCmd(int CmdID, byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(208, result, this.mRILDefaultWorkSource);
            LgeUimInternal lgeUimInfo = new LgeUimInternal();
            lgeUimInfo.command = CmdID;
            if (data == null) {
                lgeUimInfo.datalen = 0;
            } else {
                lgeUimInfo.datalen = data.length;
                lgeUimInfo.dataPtr = IccUtils.bytesToHexString(data);
            }
            LGUICC.logd("[RIL] UIMInternalRequestCmd  CmidID: " + lgeUimInfo.command + " length: " + lgeUimInfo.datalen + " String: " + lgeUimInfo.dataPtr);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.UIMInternalRequestCmd(rrSerial, lgeUimInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "UIMInternalRequestCmd", e);
            }
        }
    }

    public void uiccSelectApplication(String aid, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(209, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " aid = " + aid);
            try {
                lgeRadioProxy.uiccSelectApplication(rrSerial, convertNullToEmptyString(aid));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "uiccSelectApplication", e);
            }
        }
    }

    public void uiccDeactivateApplication(int sessionId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(210, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " sessionId = " + sessionId);
            try {
                lgeRadioProxy.uiccDeactivateApplication(rrSerial, sessionId);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "uiccDeactivateApplication", e);
            }
        }
    }

    public void uiccApplicationIO(int sessionId, int command, int fileid, String path, int p1, int p2, int p3, String data, String pin2, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(211, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            LgeUiccApplicationIo appInfo = new LgeUiccApplicationIo();
            appInfo.session_id = sessionId;
            appInfo.command = command;
            appInfo.fileid = fileid;
            appInfo.path = convertNullToEmptyString(path);
            appInfo.p1 = p1;
            appInfo.p2 = p2;
            appInfo.p3 = p3;
            appInfo.data = convertNullToEmptyString(data);
            appInfo.pin2 = convertNullToEmptyString(pin2);
            try {
                lgeRadioProxy.uiccApplicationIO(rrSerial, appInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "uiccApplicationIO", e);
            }
        }
    }

    public void uiccAkaAuthenticate(int sessionId, byte[] rand, byte[] autn, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(212, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                LgeUiccAkaAuthenticate uicc_aka = new LgeUiccAkaAuthenticate();
                uicc_aka.session_id = sessionId;
                uicc_aka.rand = IccUtils.bytesToHexString(rand);
                uicc_aka.autn = IccUtils.bytesToHexString(autn);
                lgeRadioProxy.uiccAkaAuthenticate(rrSerial, uicc_aka);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "uiccAkaAuthenticate", e);
            }
        }
    }

    public void uiccGbaAuthenticateBootstrap(int sessionId, byte[] rand, byte[] autn, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(213, result, this.mRILDefaultWorkSource);
            String randHex = IccUtils.bytesToHexString(rand);
            String autnHex = IccUtils.bytesToHexString(autn);
            LgeUiccAkaAuthenticate data = new LgeUiccAkaAuthenticate();
            data.session_id = sessionId;
            data.rand = randHex;
            data.autn = autnHex;
            //privacy_riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest) + "[" + sessionId + "," + randHex + "," + autnHex + "]");
            try {
                lgeRadioProxy.uiccGbaAuthenticateBootstrap(rrSerial, data);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "PBMGetInfo", e);
            }
        }
    }

    public void uiccGbaAuthenticateNaf(int sessionId, byte[] nafId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(214, result, this.mRILDefaultWorkSource);
            //privacy_riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            LgeUiccGbaAuthenticateNaf lgeUiccNafInfo = new LgeUiccGbaAuthenticateNaf();
            lgeUiccNafInfo.session_id = sessionId;
            lgeUiccNafInfo.naf_id = IccUtils.bytesToHexString(nafId);
            lgeUiccNafInfo.impi = convertNullToEmptyString(null);
            try {
                lgeRadioProxy.uiccGbaAuthenticateNaf(rrSerial, lgeUiccNafInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "uiccGbaAuthenticateNaf", e);
            }
        }
    }

    public void uimPowerDownRequest(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(215, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.uimPowerDownRequest(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "uimPowerDownRequest", e);
            }
        }
    }

    public void setCdmaEriVersion(int value, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(231, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " value = " + value);
            try {
                lgeRadioProxy.setCdmaEriVersion(rrSerial, value);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setCdmaEriVersion", e);
            }
        }
    }

    public void setCdmaFactoryReset(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(232, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setCdmaFactoryReset(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setCdmaFactoryReset", e);
            }
        }
    }

    public void getEhrpdInfoForIms(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(233, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getEhrpdInfoForIms(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getEhrpdInfoForIms", e);
            }
        }
    }

    public void getMipErrorCode(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(234, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getMipErrorCode(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getMipErrorCode", e);
            }
        }
    }

    public void cancelManualSearchingRequest(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(235, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.cancelManualSearchingRequest(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "cancelManualSearchingRequest", e);
            }
        }
    }

    public void setPreviousNetworkSelectionModeManual(String operatorNumeric, String operatorRat, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(236, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " operatorNumeric= " + operatorNumeric + " operatorRat = " + operatorRat);
            try {
                lgeRadioProxy.setPreviousNetworkSelectionModeManual(rrSerial, convertNullToEmptyString(operatorNumeric), convertNullToEmptyString(operatorRat));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setPreviousNetworkSelectionModeManual", e);
            }
        }
    }

    public void setRmnetAutoconnect(int param, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(237, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " param = " + param);
            try {
                lgeRadioProxy.setRmnetAutoconnect(rrSerial, param);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setRmnetAutoconnect", e);
            }
        }
    }

    public void getSearchStatus(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(238, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getSearchStatus(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getSearchStatus", e);
            }
        }
    }

    public void getEngineeringModeInfo(int param, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(239, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getEngineeringModeInfo(rrSerial, param);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getEngineeringModeInfo", e);
            }
        }
    }

    public void setCSGSelectionManual(int data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(240, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setCSGSelectionManual(rrSerial, data);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setCSGSelectionManual", e);
            }
        }
    }

    public void getLteEmmErrorCode(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(241, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getLteEmmErrorCode(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getLteEmmErrorCode", e);
            }
        }
    }

    public void sendApnDisableFlag(int profileId, boolean disable, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(242, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " profileId= " + profileId + " disable = " + disable);
            try {
                lgeRadioProxy.sendApnDisableFlag(rrSerial, profileId, disable);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "sendApnDisableFlag", e);
            }
        }
    }

    public void loadVolteE911ScanList(int airplaneModeState, int imsRegiState, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(243, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " airplaneModeState = " + airplaneModeState + " imsRegiState = " + imsRegiState);
            try {
                lgeRadioProxy.loadVolteE911ScanList(rrSerial, airplaneModeState, imsRegiState);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "loadVolteE911ScanList", e);
            }
        }
    }

    public void getVolteE911NetworkType(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(244, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getVolteE911NetworkType(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getVolteE911NetworkType", e);
            }
        }
    }

    public void exitVolteE911EmergencyMode(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(245, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            LGEcallMonitor.onExitEmergencyMode();
            try {
                lgeRadioProxy.exitVolteE911EmergencyMode(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "exitVolteE911EmergencyMode", e);
            }
        }
    }

    public void setImsRegistration(int state, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(246, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setImsRegistration(rrSerial, state);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsRegistration", e);
            }
        }
    }

    public void sendE911CallState(int state) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(null);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(247, null, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " state = " + state);
            try {
                lgeRadioProxy.sendE911CallState(rrSerial, state);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "sendE911CallState", e);
            }
        }
    }

    public void setImsRegistrationForHVoLTE(int systemMode, int stateLength, int[] type, int[] registered, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(248, result, this.mRILDefaultWorkSource);
            ArrayList<Integer> imsRegData = new ArrayList();
            imsRegData.add(Integer.valueOf(systemMode));
            imsRegData.add(Integer.valueOf(stateLength));
            for (int i = 0; i < stateLength; i++) {
                imsRegData.add(Integer.valueOf(type[i]));
                imsRegData.add(Integer.valueOf(registered[i]));
            }
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " systemMode= " + systemMode + " stateLength = " + stateLength);
            try {
                lgeRadioProxy.setImsRegistrationForHVoLTE(rrSerial, imsRegData);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsRegistrationForHVoLTE", e);
            }
        }
    }

    public void setVoiceDomainPref(int mode, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(249, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " mode = " + mode);
            try {
                lgeRadioProxy.setVoiceDomainPref(rrSerial, mode);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setVoiceDomainPref", e);
            }
        }
    }

    public void setVoLteCall(int state, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(250, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " state = " + state);
            try {
                lgeRadioProxy.setVoLteCall(rrSerial, state);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setVoLteCall", e);
            }
        }
    }

    public void closeImsPdn(int reason, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(251, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " reason = " + reason);
            try {
                lgeRadioProxy.closeImsPdn(rrSerial, reason);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "closeImsPdn", e);
            }
        }
    }

    public void detachLte(int reason, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(251, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " reason = " + reason);
            try {
                lgeRadioProxy.closeImsPdn(rrSerial, reason);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "detachLte", e);
            }
        }
    }

    public void getLteInfoForIms(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(252, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getLteInfoForIms(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getLteInfoForIms", e);
            }
        }
    }

    public void setSrvccCallContextTransfer(int numberOfCall, LGSrvccCallContext[] callList) {
        setSrvccCallContextTransfer(numberOfCall, callList, 0);
    }

    public void setSrvccCallContextTransfer(int numberOfCall, LGSrvccCallContext[] callList, int alertingTypeListLen) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(null);
        if (lgeRadioProxy != null && numberOfCall > 0 && callList != null) {
            this.mStrSRVCCnumber = callList[0].getAddress();
            this.mStrSRVCCnumberPresentation = callList[0].getNumberPresentation();
            this.mStrSRVCCcnap = callList[0].getName();
            this.mIsSrvccIncoming = callList[0].getDirection() == 2;
            switch (callList[0].getCallState()) {
                case 1:
                    this.mImsCallstate = State.ALERTING;
                    break;
                case 2:
                    this.mImsCallstate = State.INCOMING;
                    break;
                case 3:
                    this.mImsCallstate = State.ACTIVE;
                    break;
                case 5:
                    this.mImsCallstate = State.ALERTING;
                    break;
                case 6:
                    this.mImsCallstate = State.HOLDING;
                    break;
                case 7:
                    this.mImsCallstate = State.WAITING;
                    break;
            }
            setUseFrameworkCallContext(true);
            if (!LgeAutoProfiling.isCountry("KR") && !LgeAutoProfiling.isOperator("VZW") && LgeAutoProfiling.checkABSRVCC(this.mContext, this.mLgePhoneId.intValue())) {
                LgeSrvccCallContextConfig aSrvccCont = new LgeSrvccCallContextConfig();
                aSrvccCont.num_calls = numberOfCall;
                aSrvccCont.alerting_type_list_len = alertingTypeListLen;
                for (int i = 0; i < numberOfCall; i++) {
                    LgeSrvccCall c = new LgeSrvccCall();
                    c.instance_id = callList[i].getInstanceId();
                    c.call_type = callList[i].getCallType();
                    c.call_state = callList[i].getCallState();
                    c.call_substate = callList[i].getCallSubState();
                    int i2 = (callList[i].isMptyCall() && LgeAutoProfiling.supportMptyForSRVCC(this.mContext, this.mLgePhoneId.intValue())) ? 1 : 0;
                    c.is_mpty_call = i2;
                    c.direction = callList[i].getDirection();
                    c.address = convertNullToEmptyString(callList[i].getAddress());
                    c.is_alerting_type_valid = callList[i].getIsAlertingTypeValid();
                    c.alerting_type = callList[i].getAlertingType();
                    int numberPresentation = callList[i].getNumberPresentation();
                    if (numberPresentation == 1 || numberPresentation == 2 || numberPresentation == 3 || numberPresentation == 4) {
                        switch (numberPresentation) {
                            case 1:
                                numberPresentation = 0;
                                break;
                            case 2:
                                numberPresentation = 1;
                                break;
                            case 4:
                                numberPresentation = 4;
                                break;
                            default:
                                numberPresentation = 2;
                                break;
                        }
                        c.is_num_pi_valid = 1;
                        c.num_pi = numberPresentation;
                    } else {
                        c.is_num_pi_valid = 0;
                        c.num_pi = 3;
                    }
                    c.caller_name = callList[i].getName();
                    if (TextUtils.isEmpty(c.caller_name)) {
                        c.name_pi = 0;
                        c.name_len = 0;
                        c.is_caller_name_type_valid = 0;
                        c.caller_name = convertNullToEmptyString(c.caller_name);
                    } else {
                        int namePresentation;
                        switch (callList[i].getNamePresentation()) {
                            case 1:
                                namePresentation = 0;
                                break;
                            case 2:
                                namePresentation = 1;
                                break;
                            default:
                                namePresentation = 2;
                                break;
                        }
                        c.name_pi = namePresentation;
                        c.name_len = c.caller_name.length();
                        c.is_caller_name_type_valid = 1;
                        c.caller_name = convertNullToEmptyString(c.caller_name);
                    }
                    aSrvccCont.srvcc_calls.add(c);
                }
                int rrSerial = obtainRequestSerial(253, null, this.mRILDefaultWorkSource);
                //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
                try {
                    lgeRadioProxy.setSrvccCallContextTransfer(rrSerial, aSrvccCont);
                } catch (Exception e) {
                    //handleLgeRadioProxyExceptionForRR(rr, "setSrvccCallContextTransfer", e);
                }
            }
        }
    }

    protected boolean getUseFrameworkCallContext() {
        return this.mUseFrameworkCallContext;
    }

    protected void setUseFrameworkCallContext(boolean state) {
        this.mUseFrameworkCallContext = state;
    }

    protected void resetSrvccState() {
        this.mStateIncomingCall = false;
        this.mStateSRVCC = false;
        this.mUseFrameworkCallContext = false;
    }

    protected void updateDriverCallInCaseOfSRVCC(DriverCall dc) {
        if (dc.index == 1) {
            riljLog("SRVCC mStateIncomingCall: " + this.mStateIncomingCall + " , mStateSRVCC: " + this.mStateSRVCC);
            riljLog("SRVCC dc.isMT: " + dc.isMT + " ,dc.state: " + dc.state + " ,dc.index:" + dc.index);
            if (dc.state == State.INCOMING || dc.state == State.WAITING) {
                this.mStateSRVCC = false;
                this.mStateIncomingCall = true;
            } else if (dc.state == State.DIALING || dc.state == State.ALERTING) {
                this.mStateSRVCC = false;
                this.mStateIncomingCall = false;
            }
            if (!this.mStateIncomingCall) {
                if (this.mStateSRVCC) {
                    dc.number = this.mStrSRVCCnumber;
                    dc.numberPresentation = this.mStrSRVCCnumberPresentation;
                    dc.name = this.mStrSRVCCcnap;
                    dc.isMT = this.mIsSrvccIncoming;
                    if (this.mImsCallstate != State.ACTIVE) {
                        dc.state = this.mImsCallstate;
                    }
                } else if (dc.isMT && dc.state == State.ACTIVE && TextUtils.isEmpty(dc.number)) {
                    riljLog("phantom call appeared");
                    this.mStateSRVCC = true;
                    this.mStateIncomingCall = false;
                    dc.number = this.mStrSRVCCnumber;
                    dc.numberPresentation = this.mStrSRVCCnumberPresentation;
                    dc.name = this.mStrSRVCCcnap;
                    dc.isMT = this.mIsSrvccIncoming;
                    dc.state = this.mImsCallstate;
                    riljLog("SRVCC dc.numberPresent: " + dc.numberPresentation + ", dc.isMT=" + dc.isMT + ", dc.state=" + dc.state);
                    if (!LgeAutoProfiling.isLogBlocked(16)) {
                        riljLog("SRVCC dc.number:" + dc.number + ", dc.name: " + dc.name);
                    }
                }
            }
        }
    }

    public void sendIMSCallState(int callstate, int calltype, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(254, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " callState= " + callstate + " callType = " + calltype);
            try {
                lgeRadioProxy.sendIMSCallState(rrSerial, callstate, calltype);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "sendIMSCallState", e);
            }
        }
    }

    public void setRssiTestAntConf(int antType, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(255, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " antType = " + antType);
            try {
                lgeRadioProxy.setRssiTestAntConf(rrSerial, antType);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setRssiTestAntConf", e);
            }
        }
    }

    public void getRssiTest(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(256, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getRssiTest(rrSerial, 4);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getRssiTest", e);
            }
        }
    }

    public void setQcril(int set) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(null);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(257, null, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " set = " + set);
            riljLog("[MBSP][dj.seo]setQcrilLogAdbOn START");
            if (set == 1) {
                riljLog("[MBSP][dj.seo]setQcrilLogAdbOn set 1 :: " + set);
                SystemProperties.set("persist.vendor.adio.adb_log_on", "1");
            } else if (set == 0) {
                riljLog("[MBSP][dj.seo]setQcrilLogAdbOn set 0 :: " + set);
                SystemProperties.set("persist.vendor.radio.adb_log_on", Carriers.PPP_DIALING_NUMBER_DEFAULT);
            } else {
                riljLog("[MBSP][dj.seo]setQcril invalid cmd :: " + set);
            }
            if (set == 1 || set == 0) {
                try {
                    riljLog("[MBSP][dj.seo] SEND RIL COMMAND commmand_id_sending_to_qcril :: " + 0);
                    riljLog("[MBSP][dj.seo] try lge_qcril_log_update !!!");
                    lgeRadioProxy.setQcril(rrSerial, 0);
                } catch (Exception e) {
                    //handleLgeRadioProxyExceptionForRR(rr, "setQcril", e);
                }
            }
        }
    }

    public void setMiMoAntennaControlTest(Message result, int sys_mode, int mask) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(258, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setMiMoAntennaControlTest(rrSerial, sys_mode, mask);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setMiMoAntennaControlTest", e);
            }
        }
    }

    public void setModemIntegerItem(int item_index, int data, Message result) {
        riljLog("setModemIntegerItem item = " + item_index + " data = " + data);
        setModemInfo(item_index, Integer.toString(data), result);
    }

    public void setModemStringItem(int item_index, String data, Message result) {
        if (isPrivacyModemItem(item_index)) {
            riljLog("setModemStringItem item = " + item_index);
        } else {
            riljLog("setModemStringItem item = " + item_index + " data = " + data);
        }
        setModemInfo(item_index, data, result);
    }

    public void setModemInfo(int param, String data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(259, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " param = " + param + " data = " + data);
            LgeIntString modemInfo = new LgeIntString();
            modemInfo.num = param;
            modemInfo.text = convertNullToEmptyString(data);
            try {
                lgeRadioProxy.setModemInfo(rrSerial, modemInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setModemInfo", e);
            }
        }
    }

    public void getModemIntegerItem(int item_index, Message response) {
        riljLog("getModemIntegerItem item = " + item_index);
        getModemInfo(item_index, Integer.toString(55), response);
    }

    public void getModemStringItem(int item_index, Message response) {
        riljLog("getModemStringItem item = " + item_index);
        getModemInfo(item_index, "77", response);
    }

    public void getModemInfo(int param, String data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(260, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " param = " + param);
            try {
                lgeRadioProxy.getModemInfo(rrSerial, param, data);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getModemInfo", e);
            }
        }
    }

    public void getGPRIItem(int param, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(261, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " param = " + param);
            try {
                lgeRadioProxy.getGPRIItem(rrSerial, param);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getGPRIItem", e);
            }
        }
    }

    public void setLteBandMode(long bandMode, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(263, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " bandMode = " + bandMode);
            ArrayList<Long> data = new ArrayList();
            data.add(Long.valueOf(bandMode));
            try {
                lgeRadioProxy.setLteBandMode(rrSerial, data);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setLteBandMode", e);
            }
        }
    }

    public void setRatBandMode(long[] bandMode, long rat, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int i;
            int rrSerial = obtainRequestSerial(263, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " rat = " + rat);
            ArrayList<Long> data = new ArrayList();
            if (bandMode.length > 0) {
                for (long valueOf : bandMode) {
                    data.add(Long.valueOf(valueOf));
                }
                data.add(Long.valueOf(rat));
            } else {
                riljLog("setRatBandMode Length Error!!");
            }
            for (i = 0; i < bandMode.length; i++) {
                riljLog("setRatBandMode in RIL.java: bandmode[" + i + "] " + bandMode[i]);
            }
            try {
                lgeRadioProxy.setLteBandMode(rrSerial, data);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setRatBandMode", e);
            }
        }
    }

    public void setEmergency(int state, int mode, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(269, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setEmergency(rrSerial, state);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setEmergency", e);
            }
        }
    }

    public void getDefaultAPN(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(271, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getDefaultAPN(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getDefaultAPN", e);
            }
        }
    }

    public void getAdminAPN(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(272, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getAdminAPN(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "testLgeRadioInterface", e);
            }
        }
    }

    public void getTetheringAPN(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(273, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getTetheringAPN(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getTetheringAPN", e);
            }
        }
    }

    public void setScriptValue(boolean on) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(null);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(274, null, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " enable = " + on);
            try {
                lgeRadioProxy.setScriptValue(rrSerial, on ? 1 : 0);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setScriptValue", e);
            }
        }
    }

    public void mocaGetRFParameter(int kindOfData, int buf_num, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(275, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.mocaGetRFParameter(rrSerial, kindOfData, buf_num);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaGetRFParameter", e);
            }
        }
    }

    public void mocaGetMisc(int kindOfData, int buf_num, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(276, result, this.mRILDefaultWorkSource);
            /*if (buf_num == 0 || buf_num == 65535) {
                riljLoge(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " , buf_num:" + buf_num + " , kindOfData:" + kindOfData);
            }*/
            LgeMocaGetMisc args = new LgeMocaGetMisc();
            args.kind_of_data = kindOfData;
            args.send_buf_num = buf_num;
            try {
                lgeRadioProxy.mocaGetMisc(rrSerial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaGetMisc", e);
            }
        }
    }

    public void mocaAlarmEvent(byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(277, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            riljLog("MOCA_ALARM_Event length=" + data.length + ", Byte:Hex: " + IccUtils.bytesToHexString(data));
            try {
                lgeRadioProxy.mocaAlarmEvent(rrSerial, RIL.primitiveArrayToArrayList(data));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "testLgeRadioInterface", e);
            }
        }
    }

    public void mocaSetMem(int percent, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(280, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.mocaSetMem(rrSerial, 0, percent);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaSetMem", e);
            }
        }
    }

    public void mocaSetLog(byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(278, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            LgeMocaConfigInfo maskInfo = new LgeMocaConfigInfo();
            if (data != null) {
                byte[] startCode = new byte[2];
                System.arraycopy(data, 0, startCode, 0, startCode.length);
                maskInfo.conf_info = startCode[0] & 255;
                maskInfo.conf_info += (startCode[1] & 255) << 8;
                riljLog("[set mask] startcode = " + IccUtils.bytesToHexString(startCode) + ", startcode_int = " + maskInfo.conf_info);
                maskInfo.config_data_len = data.length - 2;
                System.arraycopy(data, 2, maskInfo.config, 0, maskInfo.config_data_len);
                riljLog("length =" + maskInfo.config.length + ", Byte:Hex: " + IccUtils.bytesToHexString(maskInfo.config));
            } else {
                maskInfo.config_data_len = 0;
                maskInfo.conf_info = 45056;
            }
            try {
                lgeRadioProxy.mocaSetLog(rrSerial, maskInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaSetLog", e);
            }
        }
    }

    public void mocaSetEvent(byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(278, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            LgeMocaConfigInfo maskInfo = new LgeMocaConfigInfo();
            maskInfo.conf_info = RadioAccessFamily.EVDO_B;
            maskInfo.config_data_len = data.length;
            System.arraycopy(data, 0, maskInfo.config, 0, maskInfo.config_data_len);
            riljLog("length =" + maskInfo.config.length + ", Byte:Hex: " + IccUtils.bytesToHexString(maskInfo.config));
            try {
                lgeRadioProxy.mocaSetLog(rrSerial, maskInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaSetEvent", e);
            }
        }
    }

    public void mocaGetData(int buf_num, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(279, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " buf_num = " + buf_num);
            try {
                lgeRadioProxy.mocaGetData(rrSerial, buf_num);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaGetData", e);
            }
        }
    }

    public void mocaCheckMem(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(280, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + ">  " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.mocaSetMem(rrSerial, 1, 0);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaCheckMem", e);
            }
        }
    }

    public void mocaAlarmEventReg(int event, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(281, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.mocaAlarmEventReg(rrSerial, event);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "mocaAlarmEventReg", e);
            }
        }
    }

    private int byteToInt(byte[] byteData, int offset) {
        return (((byteData[offset] & 255) | ((byteData[offset + 1] & 255) << 8)) | ((byteData[offset + 2] & 255) << 16)) | ((byteData[offset + 3] & 255) << 24);
    }

    public void DMRequest(byte[] req, Message response) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(response);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(282, response, this.mRILDefaultWorkSource);
            int result = 0;
            byte[] tempReq = new byte[(req.length + 4)];
            riljLog("[RIL.java] DMRequest");
            System.arraycopy(req, 0, tempReq, 4, req.length);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            if (req != null) {
                if (req.length >= 4) {
                    riljLog("[DMRequest] req length=" + req.length + ", Byte:Hex: " + IccUtils.bytesToHexString(req));
                    int cmdNum = byteToInt(req, 0);
                    riljLog("[DMRequest] cmdNum = " + cmdNum);
                    switch (cmdNum) {
                        case 1001:
                            byte[] temp;
                            int payloadLen = byteToInt(req, 4);
                            int propLen = 0;
                            String strProp = "";
                            int propValLen = 0;
                            String strPropVal = "";
                            byte[] reqSetProp = new byte[(req.length - 4)];
                            riljLog("[DMRequest] payloadLen = " + payloadLen);
                            System.arraycopy(req, 8, reqSetProp, 0, payloadLen);
                            if (payloadLen >= 4) {
                                propLen = byteToInt(reqSetProp, 0);
                            }
                            if (payloadLen >= propLen + 4) {
                                temp = new byte[propLen];
                                System.arraycopy(reqSetProp, 4, temp, 0, propLen);
                                String str = new String(temp);
                            }
                            if (payloadLen >= (propLen + 4) + 4) {
                                propValLen = byteToInt(reqSetProp, propLen + 4);
                            }
                            if (payloadLen >= ((propLen + 4) + 4) + propValLen) {
                                temp = new byte[propValLen];
                                System.arraycopy(reqSetProp, (propLen + 4) + 4, temp, 0, propValLen);
                                strPropVal = new String(temp);
                            } else {
                                riljLog("[DMRequest][setprop] payloadData is not valid. payloadLen = " + payloadLen + ", propLen = " + propLen + ", propValLen = " + propValLen);
                                result = 6;
                            }
                            riljLog("[DMRequest][setprop] strProp = " + strProp + ", strPropVal = " + strPropVal);
                            SystemProperties.set(strProp, strPropVal);
                            break;
                        case DM_CMD_EXTERNAL_MOCA_ENABLE /*5001*/:
                            riljLog("[DMRequest] MOCA enable!! ");
                            SystemProperties.set("persist.service.moca.enable", "1");
                            break;
                        case DM_CMD_EXTERNAL_MOCA_DISABLE /*5002*/:
                            riljLog("[DMRequest] MOCA disable!! ");
                            SystemProperties.set("persist.service.moca.enable", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                            break;
                        case DM_CMD_EXTERNAL_VOQAS_ENABLE /*5101*/:
                            riljLog("[DMRequest] VOQAS enable!! ");
                            SystemProperties.set("sys.voqas.service.enable", "1");
                            break;
                        case DM_CMD_EXTERNAL_VOQAS_DISABLE /*5102*/:
                            riljLog("[DMRequest] VOQAS disable!! ");
                            SystemProperties.set("sys.voqas.service.enable", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                            break;
                        case DM_CMD_EXTERNAL_SDM_ENABLE /*5201*/:
                            riljLog("[DMRequest] SDM enable!! ");
                            SystemProperties.set("persist.service.dm_app.enable", "true");
                            break;
                        case DM_CMD_EXTERNAL_SDM_DISABLE /*5202*/:
                            riljLog("[DMRequest] SDM disable!! ");
                            SystemProperties.set("persist.service.dm_app.enable", "false");
                            break;
                        default:
                            riljLog("[DMRequest] This DMRequest cmd(" + cmdNum + ") will be sent modem.");
                            break;
                    }
                }
                riljLog("[DMRequest] req size is short. req.length = " + req.length);
                result = 6;
            } else {
                riljLog("[DMRequest] req data is NULL");
                result = 3;
            }
            riljLog("[DMRequest]  result (in ril.java) = " + result);
            tempReq[0] = (byte) (result & 255);
            tempReq[1] = (byte) ((65280 & result) >> 8);
            tempReq[2] = (byte) ((16711680 & result) >> 16);
            tempReq[3] = (byte) ((-16777216 & result) >> 24);
            LgeDmRequest args = new LgeDmRequest();
            args.req_len = tempReq.length;
            System.arraycopy(args.req, 0, tempReq, 0, args.req_len);
            try {
                lgeRadioProxy.DMRequest(rrSerial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "DMRequest", e);
            }
        }
    }

    public void setImsDataFlushEnabled(boolean enable, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(283, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " enable = " + enable);
            try {
                lgeRadioProxy.setImsDataFlushEnabled(serial, enable ? 1 : 0);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsDataFlushEnabled", e);
            }
        }
    }

    public void NSRI_SetCaptureMode_requestProc(int index, byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(284, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " index = " + index);
            try {
                lgeRadioProxy.NSRI_SetCaptureMode_requestProc(serial, index, RIL.primitiveArrayToArrayList(data));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "NSRI_SetCaptureMode_requestProc", e);
            }
        }
    }

    public void NSRI_requestProc(int datalen, byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(285, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " datalen = " + datalen);
            try {
                lgeRadioProxy.NSRI_requestProc(serial, datalen, RIL.primitiveArrayToArrayList(data));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "NSRI_requestProc", e);
            }
        }
    }

    public void NSRI_Oem_requestProc(int index, byte[] data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(286, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " index = " + index);
            try {
                lgeRadioProxy.NSRI_Oem_requestProc(serial, index, RIL.primitiveArrayToArrayList(data));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "NSRI_Oem_requestProc", e);
            }
        }
    }

    public void setNSRICallInfoTransfer(int callstate, int UEType, String phonenumber, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(287, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " callstate = " + callstate + " UEType = " + UEType);
            try {
                lgeRadioProxy.setNSRICallInfoTransfer(serial, callstate, UEType, phonenumber);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "NSRI_SetCaptureMode_requestProc", e);
            }
        }
    }

    public void iwlanSetRegisterCellularQualityReport(int qualityRegister, int type, int[] values, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(288, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            ArrayList<Integer> data = new ArrayList();
            for (int valueOf : values) {
                data.add(Integer.valueOf(valueOf));
            }
            try {
                lgeRadioProxy.iwlanSetRegisterCellularQualityReport(serial, qualityRegister, type, data);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "iwlanSetRegisterCellularQualityReport", e);
            }
        }
    }

    public void iwlanSendImsPdnStatus(int imsPdnStatus, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(LGCallFailCause.MS_ACCESS_CLASS_BARRED, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " imsPdnStatus= " + imsPdnStatus);
            try {
                lgeRadioProxy.iwlanSendImsPdnStatus(serial, imsPdnStatus);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "iwlanSendImsPdnStatus", e);
            }
        }
    }

    public void setProximitySensorState(boolean near) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(null);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(290, null, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " near = " + near);
            try {
                lgeRadioProxy.setProximitySensorState(serial, near ? 1 : 0);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setProximitySensorState", e);
            }
        }
    }

    public void setProximitySensorState(int sarState) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(null);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(290, null, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " sarState = " + sarState);
            try {
                lgeRadioProxy.setProximitySensorState(serial, sarState);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setProximitySensorState", e);
            }
        }
    }

    public void setImsRegistrationStatus(int regState, int regServices, int detailState, int systemMode, int reason, int slotId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(291, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " regState: " + regState + " regServices: " + regServices + " detailState: " + detailState + " systemMode: " + systemMode + " reason: " + reason);
            ArrayList<Integer> regStatus = new ArrayList();
            regStatus.add(Integer.valueOf(regState));
            regStatus.add(Integer.valueOf(regServices));
            regStatus.add(Integer.valueOf(detailState));
            regStatus.add(Integer.valueOf(systemMode));
            regStatus.add(Integer.valueOf(reason));
            regStatus.add(Integer.valueOf(slotId));
            try {
                lgeRadioProxy.setImsRegistrationStatus(serial, regStatus);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsRegistrationStatus", e);
            }
        }
    }

    public void setImsCallStatus(int wholeCallState, int individualCallIdentification, int individualCallState, int individualCallReason, int individualCallType, int slot_id, int system_mode, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(292, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " wholeCallState: " + wholeCallState + " individualCallIdentification: " + individualCallIdentification + " individualCallState: " + individualCallState + " individualCallReason: " + individualCallReason + " individualCallType: " + individualCallType + " slot_id: " + slot_id + " system_mode: " + system_mode);
            ArrayList<Integer> callStatus = new ArrayList();
            callStatus.add(Integer.valueOf(wholeCallState));
            callStatus.add(Integer.valueOf(individualCallIdentification));
            callStatus.add(Integer.valueOf(individualCallState));
            callStatus.add(Integer.valueOf(individualCallReason));
            callStatus.add(Integer.valueOf(individualCallType));
            callStatus.add(Integer.valueOf(slot_id));
            callStatus.add(Integer.valueOf(system_mode));
            try {
                lgeRadioProxy.setImsCallStatus(rrSerial, callStatus);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsCallStatus", e);
            }
        }
    }

    public void setScmMode(int type, int mode, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(293, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " type: " + type + " mode:" + mode);
            try {
                lgeRadioProxy.setScmMode(serial, type, mode);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setScmMode", e);
            }
        }
    }

    public void setImsStatus(int type, int imsState, int reason, int slot_id, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(294, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " type: " + type + " imsState: " + imsState + " reason: " + reason + " slot_id: " + slot_id);
            ArrayList<Integer> imsStatus = new ArrayList();
            imsStatus.add(Integer.valueOf(type));
            imsStatus.add(Integer.valueOf(imsState));
            imsStatus.add(Integer.valueOf(reason));
            imsStatus.add(Integer.valueOf(slot_id));
            try {
                lgeRadioProxy.setImsStatus(serial, imsStatus);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsStatus", e);
            }
        }
    }

    public void enable(int transId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(180, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId: " + transId);
            try {
                lgeRadioProxy.embmsEnable(serial, transId);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsEnable", e);
            }
        }
    }

    public void disable(int transId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(181, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId: " + transId);
            try {
                lgeRadioProxy.embmsDisable(serial, transId);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsEnable", e);
            }
        }
    }

    public void startSession(int transId, byte[] tmgi, int[] earfcnlist, int[] saiList, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int i;
            int rrSerial = obtainRequestSerial(182, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId= " + transId + ", tmgi=" + Arrays.toString(tmgi) + ", earfcnlist=" + Arrays.toString(earfcnlist) + ", saiList=" + Arrays.toString(saiList));
            LgeEmbmsStartSessionRegMsg args = new LgeEmbmsStartSessionRegMsg();
            args.trans_id = transId;
            args.tmgi_info.tmgi_len = tmgi.length;
            for (i = 0; i < tmgi.length; i++) {
                args.tmgi_info.tmgi[i] = tmgi[i];
            }
            args.earfcnlist_count = earfcnlist.length;
            for (i = 0; i < earfcnlist.length; i++) {
                args.earfcnlist[i] = earfcnlist[i];
            }
            args.saiList_valid = (byte) 1;
            args.saiList_count = saiList.length;
            for (i = 0; i < saiList.length; i++) {
                args.saiList[i] = saiList[i];
            }
            try {
                lgeRadioProxy.embmsStartSession(rrSerial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsSwtichSession", e);
            }
        }
    }

    public void stopSession(int transId, byte[] tmgi, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(183, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId= " + transId + ", tmgi=" + Arrays.toString(tmgi));
            LgeEmbmsStopSessionReqMsg args = new LgeEmbmsStopSessionReqMsg();
            args.trans_id = transId;
            args.tmgi_info.tmgi_len = tmgi.length;
            for (int i = 0; i < tmgi.length; i++) {
                args.tmgi_info.tmgi[i] = tmgi[i];
            }
            try {
                lgeRadioProxy.embmsStopSession(rrSerial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsSwtichSession", e);
            }
        }
    }

    public void swtichSession(int transId, byte[] act_tmgi, byte[] deact_tmgi, int[] earfcnlist, int[] saiList, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int i;
            int rrSerial = obtainRequestSerial(184, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId: " + transId + "act_tmgi=" + Arrays.toString(act_tmgi) + "deact_tmgi" + Arrays.toString(deact_tmgi) + ", earfcnlist=" + Arrays.toString(earfcnlist) + ", saiList=" + Arrays.toString(saiList));
            LgeEmbmsSwitchSessionReqMsg args = new LgeEmbmsSwitchSessionReqMsg();
            args.trans_id = transId;
            args.act_tmgi_info.tmgi_len = act_tmgi.length;
            for (i = 0; i < act_tmgi.length; i++) {
                args.act_tmgi_info.tmgi[i] = act_tmgi[i];
            }
            args.deact_tmgi_info.tmgi_len = deact_tmgi.length;
            for (i = 0; i < deact_tmgi.length; i++) {
                args.deact_tmgi_info.tmgi[i] = deact_tmgi[i];
            }
            args.earfcnlist_count = earfcnlist.length;
            for (i = 0; i < earfcnlist.length; i++) {
                args.earfcnlist[i] = earfcnlist[i];
            }
            args.saiList_valid = (byte) 1;
            args.saiList_count = saiList.length;
            for (i = 0; i < saiList.length; i++) {
                args.saiList[i] = saiList[i];
            }
            try {
                lgeRadioProxy.embmsSwitchSession(rrSerial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsSwtichSession", e);
            }
        }
    }

    public void getTime(int transId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(185, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId: " + transId);
            try {
                lgeRadioProxy.embmsGetTime(rrSerial, transId);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsEnable", e);
            }
        }
    }

    public void getCoverageState(int transId, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(186, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId: " + transId);
            try {
                lgeRadioProxy.embmsGetCoverageState(rrSerial, transId);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsEnable", e);
            }
        }
    }

    public void interestedSession(int transId, int transIdValid, byte[] tmgiList, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(187, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " transId: " + transId);
            LgeEmbmsInterestedTmgiReqMsg args = new LgeEmbmsInterestedTmgiReqMsg();
            args.trans_id = transId;
            args.trans_id_valid = transIdValid;
            args.tmgi_list.tmgi_len = tmgiList.length;
            for (int i = 0; i < tmgiList.length; i++) {
                args.tmgi_list.tmgi[i] = tmgiList[i];
            }
            try {
                lgeRadioProxy.embmsInterestedSession(rrSerial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "embmsEnable", e);
            }
        }
    }

    public void getIMSNetworkInfo(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(295, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getIMSNetworkInfo(serial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getIMSNetworkInfo", e);
            }
        }
    }

    public void vssModemReset(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(270, result, this.mRILDefaultWorkSource);
            //riljLoge(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.vssModemReset(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "vssModemReset", e);
            }
        }
    }

    public void lgeSetNetworkSelectionModeManual(String operatorNumeric, String operatorRat, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(296, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " operatorNumeric = " + operatorNumeric + " operatorRat = " + operatorRat);
            try {
                lgeRadioProxy.lgeSetNetworkSelectionModeManual(rrSerial, convertNullToEmptyString(operatorNumeric), convertNullToEmptyString(operatorRat));
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "lgeSetNetworkSelectionModeManual", e);
            }
        }
    }

    public void setOnSIB16Time(Handler h, int what, Object obj) {
        this.mSIB16TimeRegistrant = new Registrant(h, what, obj);
        if (this.mLastSIB16TimeInfo != null) {
            this.mSIB16TimeRegistrant.notifyRegistrant(new AsyncResult(null, this.mLastSIB16TimeInfo, null));
            this.mLastSIB16TimeInfo = null;
        }
    }

    public void writeSmsToCsim(int status, byte[] pdu, Message response) {
        status = translateStatus(status);
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(response);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(300, response, this.mRILDefaultWorkSource);
            LgeCdmaSmsWriteArgs args = new LgeCdmaSmsWriteArgs();
            args.status = status;
            args.msglen = pdu.length;
            constructCdmaSendSmsRilRequest(args.message, pdu);
            try {
                lgeRadioProxy.lgeCdmaWriteSmsToRuim(serial, args);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "writeSmsToCsim", e);
            }
        }
    }

    public void getSignalStrength(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(299, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.lgeGetSignalStrength(serial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getSignalStrength", e);
            }
        }
    }

    public void getCurrentCalls(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(301, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.lgeGetCurrentCalls(serial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getCurrentCalls", e);
            }
        }
    }

    public void getAvailableNetworks(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(302, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getAvailableNetworks(serial);
                if (LgeAutoProfiling.isSupportedFeature(null, "vzw_gfit")) {
                    this.mStartQueryAvailableNetworkRegistrants.notifyRegistrants();
                }
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "lgeSetNetworkSelectionModeManual", e);
            }
        }
    }

    public void setupDataCallEx(int radioTechnology, DataProfile dataProfile, boolean isRoaming, boolean allowRoaming, String handoverMode, String v4addr, String v6addr, boolean needPcscf, boolean isEmergency, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int serial = obtainRequestSerial(297, result, this.mRILDefaultWorkSource);
            LgeDataProfileInfo dpi = convertToHalDataProfileEx(dataProfile);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + ",radioTechnology=" + radioTechnology + ",isRoaming=" + isRoaming + ",allowRoaming=" + allowRoaming + ",handoverMode=" + handoverMode + ",v4addr=" + v4addr + ",v6addr=" + v6addr + ",needPcscf=" + needPcscf + "," + isEmergency + "," + dataProfile);
            try {
                lgeRadioProxy.setupDataCallEx(serial, radioTechnology, dpi, dataProfile.modemCognitive, allowRoaming, isRoaming, handoverMode, v4addr, v6addr, needPcscf, isEmergency);
                this.mLgeMetrics.writeRilSetupDataCall(this.mPhoneId.intValue(), serial, radioTechnology, dpi.profileId, dpi.apn, dpi.authType, dpi.protocol);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setupDataCallEx", e);
            }
        }
    }

    public void setPreferredNetworkType(int networkType, Message result) {
        if (LgeAutoProfiling.isOperatorCountry("KR", "KT")) {
            String prevState;
            if (networkType == 2 || networkType == 0) {
                prevState = SystemProperties.get("gsm.lte_available", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                SystemProperties.set("gsm.lte_available", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                SystemProperties.set("gsm.lte_avail.bak", prevState);
            } else if (networkType == 9) {
                String backState = SystemProperties.get("gsm.lte_avail.bak", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                prevState = SystemProperties.get("gsm.lte_available", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                if (backState.equals("1") && prevState.equals(Carriers.PPP_DIALING_NUMBER_DEFAULT)) {
                    SystemProperties.set("gsm.lte_available", "1");
                    SystemProperties.set("gsm.lte_avail.bak", Carriers.PPP_DIALING_NUMBER_DEFAULT);
                }
            }
        }
        if (LgeAutoProfiling.isOperatorCountry("KR", "KT")) {
            SystemProperties.set("gsm.rat_changed", "true");
            SystemProperties.set("persist.radio.last_rat", String.valueOf(networkType));
        }
        super.setPreferredNetworkType(networkType, result);
    }

    public void getIccCardStatus(Message result) {
        result = RilHook.getInstance(this.mLgePhoneId.intValue()).handleGetIccCardStatus(result);
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(298, result, this.mRILDefaultWorkSource);
            //riljLoge(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.lgeGetIccCardStatus(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "lgeGetIccCardStatus", e);
            }
        }
    }

    public void setPcasInfo(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(310, result, this.mRILDefaultWorkSource);
            //riljLoge(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setPcasInfo(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setPcasInfo", e);
            }
        }
    }

    public void supplyIccPinForApp(String pin, String aid, Message result) {
        super.supplyIccPinForApp(pin, aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleSupplyIccPinForApp(pin, aid, result));
    }

    public void supplyIccPukForApp(String puk, String newPin, String aid, Message result) {
        super.supplyIccPukForApp(puk, newPin, aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleSupplyIccPukForApp(puk, newPin, aid, result));
    }

    public void supplyIccPin2ForApp(String pin, String aid, Message result) {
        super.supplyIccPin2ForApp(pin, aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleSupplyIccPin2ForApp(pin, aid, result));
    }

    public void supplyIccPuk2ForApp(String puk, String newPin2, String aid, Message result) {
        super.supplyIccPuk2ForApp(puk, newPin2, aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleSupplyIccPuk2ForApp(puk, newPin2, aid, result));
    }

    public void changeIccPinForApp(String oldPin, String newPin, String aid, Message result) {
        super.changeIccPinForApp(oldPin, newPin, aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleChangeIccPinForApp(oldPin, newPin, aid, result));
    }

    public void changeIccPin2ForApp(String oldPin2, String newPin2, String aid, Message result) {
        super.changeIccPin2ForApp(oldPin2, newPin2, aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleChangeIccPin2ForApp(oldPin2, newPin2, aid, result));
    }

    public void getIMSIForApp(String aid, Message result) {
        super.getIMSIForApp(aid, RilHook.getInstance(this.mLgePhoneId.intValue()).handleGetIMSIForApp(aid, result));
    }

    public void iccIOForApp(int command, int fileId, String path, int p1, int p2, int p3, String data, String pin2, String aid, Message result) {
        result = RilHook.getInstance(this.mLgePhoneId.intValue()).handleIccIOForApp(command, fileId, path, p1, p2, p3, data, pin2, aid, result);
        if (!RilHook.isDiscarded(result)) {
            super.iccIOForApp(command, fileId, path, p1, p2, p3, data, pin2, aid, result);
        }
    }

    public void setFacilityLockForApp(String facility, boolean lockState, String password, int serviceClass, String appId, Message result) {
        super.setFacilityLockForApp(facility, lockState, password, serviceClass, appId, RilHook.getInstance(this.mLgePhoneId.intValue()).handleSetFacilityLockForApp(facility, lockState, password, serviceClass, appId, result));
    }

    public void getDataRegistrationState(Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(303, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.getDataRegistrationState(rrSerial);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "getDataRegistrationState", e);
            }
        }
    }

    public void setImsNewRegistrationState(int sysMode, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(304, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setImsNewRegistrationState(rrSerial, sysMode);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsNewRegistrationState", e);
            }
        }
    }

    public void setImsRegistrationErrorStatus(int sysMode, int errorCause, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(305, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setImsRegistrationErrorStatus(rrSerial, sysMode, errorCause);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setImsRegistrationErrorStatus", e);
            }
        }
    }

    public void setLteProc(int procType, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(306, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setLteProc(rrSerial, procType);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setLteProc", e);
            }
        }
    }

    public void setOtasnPdnState(int otasnPdnState, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(307, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest));
            try {
                lgeRadioProxy.setOtasnPdnState(rrSerial, otasnPdnState);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setOtasnPdnState", e);
            }
        }
    }

    protected String requestToStringEx(int request) {
        switch (request) {
            case 160:
                return "TEST_LGE_RADIO_INTERFACE";
            case 180:
                return "RIL_REQUEST_EMBMS_ENABLE";
            case 181:
                return "RIL_REQUEST_EMBMS_DISABLE";
            case 182:
                return "RIL_REQUEST_EMBMS_START_SESSION";
            case 183:
                return "RIL_REQUEST_EMBMS_STOP_SESSION";
            case 184:
                return "RIL_REQUEST_EMBMS_SWITCH_SESSION";
            case 185:
                return "RIL_REQUEST_EMBMS_GET_TIME";
            case 186:
                return "RIL_REQUEST_EMBMS_GET_COVERAGE_STATE";
            case 187:
                return "RIL_REQUEST_EMBMS_INTERESTED_SESSION";
            case 201:
                return "RIL_REQUEST_PBM_READ_RECORD";
            case 202:
                return "RIL_REQUEST_PBM_WRITE_RECORD";
            case 203:
                return "RIL_REQUEST_PBM_DELETE_RECORD";
            case 204:
                return "RIL_REQUEST_PBM_GET_INIT_STATE";
            case 205:
                return "RIL_REQUEST_USIM_AUTH";
            case 206:
                return "RIL_REQUEST_USIM_SMARTCARD_TRANSMIT";
            case 207:
                return "RIL_REQUEST_PBM_GET_INFO";
            case 208:
                return "RIL_REQUEST_UIM_INTERNAL_REQUEST";
            case 209:
                return "RIL_REQUEST_UICC_SELECT_APPLICATION";
            case 210:
                return "RIL_REQUEST_UICC_DEACTIVATE_APPLICATION";
            case 211:
                return "RIL_REQUEST_UICC_APPLICATION_IO";
            case 212:
                return "RIL_REQUEST_UICC_AKA_AUTHENTICATE";
            case 213:
                return "RIL_REQUEST_UICC_GBA_AUTHENTICATE_BOOTSTRAP";
            case 214:
                return "RIL_REQUEST_UICC_GBA_AUTHENTICATE_NAF";
            case 215:
                return "RIL_REQUEST_UIM_POWER_DOWN";
            case 216:
                return "RIL_REQUEST_LGE_SIM_GET_ATR";
            case 217:
                return "RIL_REQUEST_SIM_SET_APDU_BEHAVIOR";
            case 231:
                return "RIL_REQUEST_CDMA_ERI_VERSION_WRITE";
            case 232:
                return "RIL_REQUEST_CDMA_FACTORY_RESET";
            case 233:
                return "RIL_REQUEST_GET_EHRPD_INFO_FOR_IMS";
            case 234:
                return "RIL_REQUEST_GET_MIP_ERRORCODE";
            case 235:
                return "RIL_REQUEST_CANCEL_MANUAL_SEARCHING";
            case 236:
                return "RIL_REQUEST_SET_PREVIOUS_NETWORK_SELECTION_MANUAL";
            case 237:
                return "RIL_REQUEST_SET_RMNET_AUTOCONNECT";
            case 238:
                return "RIL_REQUEST_GET_SEARCH_STATUS";
            case 239:
                return "RIL_REQUEST_GET_ENGINEERING_MODE_INFO";
            case 240:
                return "RIL_REQUEST_CSG_SELECTION_MANUAL";
            case 241:
                return "RIL_REQUEST_GET_EMM_REJECT_CAUSE";
            case 242:
                return "RIL_REQUEST_SET_APN_DISABLE_FLAG";
            case 243:
                return "RIL_REQUEST_SET_VOLTE_E911_SCAN_LIST";
            case 244:
                return "RIL_REQUEST_GET_VOLTE_E911_NETWORK_TYPE";
            case 245:
                return "RIL_REQUEST_EXIT_VOLTE_E911_EMERGENCY_MODE";
            case 246:
                return "RIL_REQUEST_LG_IMS_REGISTRATION_STATE";
            case 247:
                return "RIL_REQUEST_SEND_E911_CALL_STATE";
            case 248:
                return "RIL_REQUEST_UPDATE_IMS_STATUS_REQ";
            case 249:
                return "RIL_REQUEST_SET_VOICE_DOMAIN_PREFERENCE";
            case 250:
                return "RIL_REQUEST_HVOLTE_SET_VOLTE_CALL_STATUS";
            case 251:
                return "RIL_REQUEST_VSS_LGEIMS_LTE_DETACH";
            case 252:
                return "RIL_REQUEST_LTE_INFO_FOR_IMS";
            case 253:
                return "RIL_REQUEST_SET_SRVCC_CALL_CONFIG";
            case 254:
                return "RIL_REQUEST_IMS_CALL_STATE_NOTI_REQ";
            case 255:
                return "RIL_REQUEST_VSS_ANTENNA_CONF";
            case 256:
                return "RIL_REQUEST_VSS_ANTENNA_INFO";
            case 257:
                return "RIL_REQUEST_VSS_SET_QCRIL";
            case 258:
                return "RIL_REQUEST_PRX_DRX_ANT_CTRL";
            case 259:
                return "RIL_REQUEST_SET_MODEM_INFO";
            case 260:
                return "RIL_REQUEST_GET_MODEM_INFO";
            case 261:
                return "RIL_REQUEST_GET_GPRI_INFO";
            case 262:
                return "RIL_REQUEST_SET_GONS_INFO";
            case 263:
                return "RIL_REQUEST_SET_LTE_BAND_MODE";
            case 269:
                return "RIL_REQUEST_SET_E911_STATE";
            case 270:
                return "RIL_REQUEST_VSS_MODEM_RESET";
            case 271:
                return "RIL_REQUEST_GET_DEFAULT_APN";
            case 272:
                return "RIL_REQUEST_GET_ADMIN_APN";
            case 273:
                return "RIL_REQUEST_GET_TETHERING_APN";
            case 274:
                return "RIL_REQUEST_SET_SCRIPT_VALUE";
            case 275:
                return "RIL_REQUEST_VSS_MOCA_GET_RFPARAMETER";
            case 276:
                return "RIL_REQUEST_VSS_MOCA_GET_MISC";
            case 277:
                return "RIL_REQUEST_VSS_MOCA_ALARM_EVENT_SET";
            case 278:
                return "RIL_REQUEST_VSS_MOCA_CONFIG_SETUP";
            case 279:
                return "RIL_REQUEST_VSS_MOCA_GET_DATA";
            case 280:
                return "RIL_REQUEST_VSS_MOCA_MEM_CHECK";
            case 281:
                return "RIL_REQUEST_VSS_MOCA_ALARM_EVENT_REG";
            case 282:
                return "RIL_REQUEST_VSS_DM_REQUEST";
            case 283:
                return "RIL_REQUEST_VSS_VOLTE_CALL_FLUSH";
            case 284:
                return "RIL_REQUEST_VSS_NSRI_CAPTUREMODE_COMMAND";
            case 285:
                return "RIL_REQUEST_VSS_NSRI_COMMAND";
            case 286:
                return "RIL_REQUEST_VSS_NSRI_OEM_COMMAND";
            case 287:
                return "RIL_REQUEST_SET_NSRI_SECRET_VOLTE_CALL_INFO";
            case 288:
                return "RIL_REQUEST_IWLAN_REGISTER_CELLULAR_QUALITY_REPORT";
            case 289:
                return "RIL_REQUEST_IWLAN_SEND_IMS_PDN_STATUS";
            case 290:
                return "RIL_REQUEST_SET_PROXIMITY_SENSOR_STATE";
            case 291:
                return "RIL_REQUEST_SET_IMS_REGISTRATION_STATUS";
            case 292:
                return "RIL_REQUEST_SET_IMS_CALL_STATUS";
            case 293:
                return "RIL_REQUEST_SET_SCM_MODE";
            case 294:
                return "RIL_REQUEST_VSS_SET_IMS_STATUS";
            case 295:
                return "RIL_REQUEST_NETWORK_INFO_FOR_IMS";
            case 296:
                return "RIL_REQUEST_LGE_SET_NETWORK_SELECTION_MANUAL";
            case 297:
                return "RIL_REQUEST_LGE_SETUP_DATA_CALL";
            case 298:
                return "RIL_REQUEST_LGE_GET_SIM_STATUS";
            case 299:
                return "RIL_REQUEST_LGE_SIGNAL_STRENGTH";
            case 300:
                return "RIL_REQUEST_LGE_CDMA_WRITE_SMS_TO_RUIM";
            case 301:
                return "RIL_REQUEST_LGE_GET_CURRENT_CALLS";
            case 302:
                return "RIL_REQUEST_LGE_QUERY_AVAILABLE_NETWORKS";
            case 303:
                return "RIL_REQUEST_LGE_DATA_REGISTRATION_STATE";
            case 304:
                return "RIL_REQUEST_IMS_NEW_REGISTRATION";
            case 305:
                return "RIL_REQUEST_UPDATE_IMS_STATUS_W_ERROR";
            case 310:
                return "RIL_REQUEST_UPDATE_PCAS_INFO";
            default:
                return RIL.requestToString(request);
        }
    }

    protected String responseToStringEx(int request) {
        switch (request) {
            case 1099:
                return "UNSOL_TEST_LGE_RADIO_INTERFACE";
            case 1100:
                return "RIL_UNSOL_EMBMS_CELL_INFO_NOTIFICATION";
            case 1101:
                return "RIL_UNSOL_EMBMS_COVERAGE_STATE";
            case 1102:
                return "RIL_UNSOL_EMBMS_ACTIVE_SESSION";
            case 1103:
                return "RIL_UNSOL_EMBMS_AVAILABLE_SESSION";
            case 1104:
                return "RIL_UNSOL_EMBMS_SAI_LIST_NOTIFICATION";
            case 1105:
                return "RIL_UNSOL_EMBMS_OOS_NOTIFICATION";
            case 1106:
                return "RIL_UNSOL_EMBMS_RADIO_STATE_NOTIFICATION";
            case 1107:
                return "RIL_UNSOL_EMBMS_SVC_INTEREST_NOTIFICATION";
            case 1120:
                return "RIL_UNSOL_RESPONSE_PBM_INIT_DONE";
            case 1121:
                return "RIL_UNSOL_RESPONSE_BIP_PROCMD_STATUS";
            case 1122:
                return "RIL_UNSOL_GSTK_OTA_STATE";
            case 1123:
                return "RIL_UNSOL_GSTK_SIM_IMSI_STATE";
            case 1124:
                return "RIL_UNSOL_SIM_UART_STATUS";
            case 1125:
                return "RIL_UNSOL_SIM_STATUS_ToLDB";
            case 1140:
                return "RIL_UNSOL_LGE_RAC_IND";
            case 1141:
                return "RIL_UNSOL_SPRINT_LTE_EHRPD_FORCED";
            case 1142:
                return "RIL_UNSOL_WCDMA_NET_CHANGED";
            case 1143:
                return "RIL_UNSOL_WCDMA_NET_TO_KOREA_CHANGED";
            case 1146:
                return "RIL_UNSOL_PERIODIC_CSG_SEARCH";
            case 1147:
                return "RIL_UNSOL_LGE_CIPHERING_IND";
            case 1148:
                return "RIL_UNSOL_LTE_ACB_INFO_IND";
            case 1149:
                return "RIL_UNSOL_LOG_RF_BAND_INFO";
            case 1150:
                return "RIL_UNSOL_VSS_MOCA_MISC_NOTI";
            case 1151:
                return "RIL_UNSOL_VSS_MOCA_ALARM_EVENT";
            case 1152:
                return "RIL_UNSOL_VSS_MOCA_MEM_LIMIT";
            case 1153:
                return "RIL_UNSOL_VOLTE_E911_1x_CONNECTED";
            case 1154:
                return "RIL_UNSOL_VOLTE_EPS_NETWORK_FEATURE_SUPPORT";
            case 1155:
                return "RIL_UNSOL_VOLTE_NETWORK_SIB_INFO";
            case 1156:
                return "RIL_UNSOL_VOLTE_EMERGENCY_CALL_FAIL_CAUSE";
            case 1157:
                return "RIL_UNSOL_VOLTE_EMERGENCY_ATTACH_INFO";
            case 1158:
                return "RIL_UNSOL_VOLTE_LTE_CONNECTION_STATUS";
            case 1159:
                return "RIL_UNSOL_VOICE_CODEC_INDICATOR";
            case 1160:
                return "RIL_UNSOL_LGE_LTE_CA_IND";
            case 1161:
                return "RIL_UNSOL_PROTOCOL_INFO_IND";
            case 1162:
                return "RIL_UNSOL_DATA_QOS_CHANGED";
            case 1163:
                return "RIL_UNSOL_VOLTE_E911_NETWORK_TYPE";
            case 1164:
                return "RIL_UNSOL_DQSL_EVENT";
            case 1165:
                return "RIL_UNSOL_VZW_RESERVED_PCO_INFO";
            case 1166:
                return "RIL_UNSOL_LTE_REJECT_CAUSE";
            case 1167:
                return "RIL_UNSOL_SIB16_TIME_RECEIVED";
            case 1168:
                return "RIL_UNSOL_LTE_NETWORK_INFO";
            case 1169:
                return "RIL_UNSOL_LDB_MODEM_RESET";
            case 1170:
                return "RIL_UNSOL_WCDMA_REJECT_RECEIVED";
            case 1171:
                return "RIL_UNSOL_WCDMA_ACCEPT_RECEIVED";
            case 1172:
                return "RIL_UNSOL_LTE_EMM_REJECT";
            case 1173:
                return "RIL_UNSOL_SPRINT_LTE_ROAM_INDICATOR";
            case 1174:
                return "RIL_UNSOL_IMS_PREF_STATUS_IND";
            case 1175:
                return "RIL_UNSOL_SSAC_CHANGE_INFO_IND";
            case 1176:
                return "RIL_UNSOL_VSS_NSRI_NOTI_MSG";
            case 1177:
                return "RIL_UNSOL_RESIM_TIME_EXPIRED";
            case 1178:
                return "RIL_UNSOL_IWLAN_CELLULAR_QUALITY_CHANGED_IND";
            case 1180:
                return "RIL_UNSOL_LGE_CSFB_STATUS_INFO";
            case 1181:
                return "RIL_UNSOL_LGE_HO_STATUS_INFO";
            case 1182:
                return "RIL_UNSOL_LGE_NET_BAND_INFO";
            case 1183:
                return "RIL_UNSOL_LGE_GSM_ENCRYP_INFO";
            case 1184:
                return "RIL_UNSOL_CAPSENSOR_RRC_STATE";
            case 1185:
                return "RIL_UNSOL_LGE_UNSOL";
            case 1186:
                return "RIL_UNSOL_ECC_TASK_RESULT";
            case 1187:
                return "RIL_UNSOL_LGE_RIL_CONNECTED";
            case 1188:
                return "RIL_UNSOL_LGE_DATA_IMS_PCSCF_RESTORATION_IND";
            case 1189:
                return "RIL_UNSOL_LGE_ON_USSD";
            case 1200:
                return "RIL_UNSOL_LGE_SIGNAL_STRENGTH";
            default:
                return RIL.responseToString(request);
        }
    }

    public void reConnectLgeRil() {
        if (this.mLgeRadioIndication == null || this.mLgeRadioResponse == null) {
            riljLog("LgeRIL will connect to RILD after make finishing instance of LgeRadioResponse");
        } else {
            getLgeRadioProxy(null);
        }
    }

    protected String convertNullToEmptyString(String string) {
        return string != null ? string : "";
    }

    private static int convertToHalMvnoTypeEx(String mvnoType) {
        if (mvnoType.equals("imsi")) {
            return 1;
        }
        if (mvnoType.equals("gid")) {
            return 2;
        }
        if (mvnoType.equals("spn")) {
            return 3;
        }
        return 0;
    }

    static DataCallResponse convertDataCallExResult(LgeSetupDataCallResult dcResult) {
        return new DataCallResponse(dcResult.status, dcResult.suggestedRetryTime, dcResult.cid, dcResult.active, dcResult.type, dcResult.ifname, dcResult.addresses, dcResult.dnses, dcResult.gateways, dcResult.pcscf, dcResult.mtu);
    }

    private static LgeDataProfileInfo convertToHalDataProfileEx(DataProfile dp) {
        LgeDataProfileInfo dpi = new LgeDataProfileInfo();
        dpi.profileId = dp.profileId;
        dpi.apn = dp.apn;
        dpi.protocol = dp.protocol;
        dpi.roamingProtocol = dp.roamingProtocol;
        dpi.authType = dp.authType;
        dpi.user = dp.user;
        dpi.password = dp.password;
        dpi.type = dp.type;
        dpi.maxConnsTime = dp.maxConnsTime;
        dpi.maxConns = dp.maxConns;
        dpi.waitTime = dp.waitTime;
        dpi.enabled = dp.enabled;
        dpi.supportedApnTypesBitmap = dp.supportedApnTypesBitmap;
        dpi.bearerBitmap = dp.bearerBitmap;
        dpi.mtu = dp.mtu;
        dpi.mvnoType = convertToHalMvnoTypeEx(dp.mvnoType);
        dpi.mvnoMatchData = dp.mvnoMatchData;
        return dpi;
    }

    public static int[] integerArrayListToPrimitiveArray(ArrayList<Integer> aInts) {
        int[] ret = new int[aInts.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = ((Integer) aInts.get(i)).intValue();
        }
        return ret;
    }

    static SignalStrength convertHalSignalStrength(LgeSignalStrength signalStrength) {
        SignalStrength ss = new SignalStrength(signalStrength.gw.signalStrength, signalStrength.gw.bitErrorRate, signalStrength.cdma.dbm, signalStrength.cdma.ecio, signalStrength.evdo.dbm, signalStrength.evdo.ecio, signalStrength.evdo.signalNoiseRatio, signalStrength.lte.signalStrength, signalStrength.lte.rsrp, signalStrength.lte.rsrq, signalStrength.lte.rssnr, signalStrength.lte.cqi, signalStrength.tdScdma.rscp, false);
        //LGSignalStrength.setGsmDbmEcio(ss, signalStrength.gw.dbm, signalStrength.gw.ecio);
        return ss;
    }

    public void sendRoamingInfo_KR() {
        int dataRoaming = 0;
        int lteRoaming = 0;
        int volteRoaming = 0;
        if (Global.getInt(this.mContext.getContentResolver(), "data_roaming", 0) == 1) {
            dataRoaming = 1;
            riljLog("sendRoamingInfo_KR() DATA_ROAMING =  " + 1);
        }
        if (Secure.getInt(this.mContext.getContentResolver(), "data_lte_roaming", 0) == 1) {
            lteRoaming = 1;
            riljLog("sendRoamingInfo_KR() LTE_ROAMING =    " + 1);
        }
        if (LgeAutoProfiling.isSupportedFeature(null, "kt_skt_volte_roaming") && Global.getInt(this.mContext.getContentResolver(), "roaming_hdvoice_enabled", 0) == 1) {
            volteRoaming = 1;
            riljLog("sendRoamingInfo_KR() KT ROAMING_HDVOICE_ENABLED =  " + 1);
        }
        int iMask = (((((dataRoaming & 1) << 0) | 0) | 2) | ((lteRoaming & 1) << 2)) | 8;
        if (LgeAutoProfiling.isSupportedFeature(null, "kt_skt_volte_roaming")) {
            iMask = (iMask | ((volteRoaming & 1) << 4)) | 32;
        }
        riljLog("sendRoamingInfo_KR() iMask = " + iMask);
        setModemIntegerItem(131673, iMask, null);
    }

    public void setVoLTERoaming(int status, Message result) {
        if (status == 0) {
            setModemIntegerItem(131673, getRoamingInfoFromDB(8), result);
        } else if (status == 1) {
            setModemIntegerItem(131673, getRoamingInfoFromDB(4), result);
        }
    }

    private int getRoamingInfoFromDB(int item) {
        int iMask = 0;
        int dataRoaming = 0;
        int lteRoaming = 0;
        int volteRoaming = 0;
        int homeOnly = 0;
        int mobileData = 0;
        if ((item & 1) == 1 && Global.getInt(this.mContext.getContentResolver(), "data_roaming", 0) == 1) {
            dataRoaming = 1;
        }
        if ((item & 2) == 2 && Secure.getInt(this.mContext.getContentResolver(), "data_lte_roaming", 0) == 1) {
            lteRoaming = 1;
        }
        if ((item & 4) == 4) {
            if (LgeAutoProfiling.isOperator("DCM")) {
                if (Global.getInt(this.mContext.getContentResolver(), "volte_vt_enabled", 0) == 1) {
                    volteRoaming = 1;
                }
            } else if (Global.getInt(this.mContext.getContentResolver(), "volte_roaming_enabled", 0) == 1) {
                volteRoaming = 1;
            }
        }
        if ((item & 8) == 8) {
            int networkMode = LGNetworkModeController.getDefault().getNetworkModeforTB();
            if (SystemProperties.getInt("ro.telephony.default_network", -1) == 10) {
                if (!(networkMode == 4 || networkMode == 5 || networkMode == 6)) {
                    if (networkMode == 8) {
                    }
                }
                homeOnly = 1;
            } else if (networkMode == 11) {
                homeOnly = 1;
            }
        }
        if ((item & 16) == 16 && Global.getInt(this.mContext.getContentResolver(), "mobile_data", 0) == 1) {
            mobileData = 1;
        }
        if ((item & 1) == 1) {
            iMask = (((dataRoaming & 1) << 0) | 0) | 2;
        }
        if ((item & 2) == 2) {
            iMask = (iMask | ((lteRoaming & 1) << 2)) | 8;
        }
        if ((item & 4) == 4) {
            iMask = (iMask | ((volteRoaming & 1) << 4)) | 32;
        }
        if ((item & 8) == 8) {
            iMask = (iMask | ((homeOnly & 1) << 6)) | 128;
        }
        if ((item & 16) == 16) {
            return (iMask | ((mobileData & 1) << 8)) | 512;
        }
        return iMask;
    }

    public void sendRoamingInfo(Message result) {
        int valueMask = 0;
        if (LgeAutoProfiling.checkOperators(new String[]{"KDDI", "JCM", "UQ"})) {
            valueMask = 31;
        } else if (LgeAutoProfiling.isOperator("DCM")) {
            valueMask = 21;
        }
        setModemIntegerItem(131673, getRoamingInfoFromDB(valueMask), result);
    }

    public void getImsRegistrationState(Message result) {
        /*if (LgeAutoProfiling.isSupportedFeature(null, "sms_over_ims_on_mtk")) {
            SoiManagerMTK.getDefault().notifyUpdateImsService(result, this.mPhoneId.intValue());
        } else {*/
            super.getImsRegistrationState(result);
        //}
    }

    public void sendImsGsmSms(String smscPdu, String pdu, int retry, int messageRef, Message result) {
        /*if (LgeAutoProfiling.isSupportedFeature(null, "sms_over_ims_on_mtk") && SoiTestMTK.getDefault().getTestMode()) {
            SoiTestMTK.getDefault().setCurTC(2);
        }*/
        super.sendImsGsmSms(smscPdu, pdu, retry, messageRef, result);
    }

    public void sendImsCdmaSms(byte[] pdu, int retry, int messageRef, Message result) {
        /*if (LgeAutoProfiling.isSupportedFeature(this.mContext, "sms_over_ims_on_mtk_3gpp2") && SoiTestMTK.getDefault().getTestMode()) {
            SoiTestMTK.getDefault().setCurTC(7);
        }*/
        super.sendImsCdmaSms(pdu, retry, messageRef, result);
    }

    public void sendSMSOtaRequest(String smscPDU, String pdu, Message result) {
        //if (getSmsDispatcherEx() == null || !getSmsDispatcherEx().isIms()) {
            sendSMS(smscPDU, pdu, result);
        //} else {
        //    sendImsGsmSms(smscPDU, pdu, 0, 0, result);
        //}
    }

    public void setGNOSInfo(int param, String data, Message result) {
        ILgeRadio lgeRadioProxy = getLgeRadioProxy(result);
        if (lgeRadioProxy != null) {
            int rrSerial = obtainRequestSerial(262, result, this.mRILDefaultWorkSource);
            //riljLog(rr.serialString() + "> " + requestToStringEx(rr.mRequest) + " param = " + param + " data = " + data);
            LgeIntString modemInfo = new LgeIntString();
            modemInfo.num = param;
            modemInfo.text = convertNullToEmptyString(data);
            try {
                lgeRadioProxy.setGNOSInfo(rrSerial, modemInfo);
            } catch (Exception e) {
                //handleLgeRadioProxyExceptionForRR(rr, "setPcasInfo", e);
            }
        }
    }

    private void privacy_riljLog(String msg) {
        Plog.d("RILJ", msg + (this.mPhoneId != null ? " [SUB" + this.mPhoneId + "]" : ""));
    }

    private void privacy_unsljLogRet(int response, Object ret) {
        privacy_riljLog("[UNSL]< " + RIL.responseToString(response) + " " + RIL.retToString(response, ret));
    }
}
