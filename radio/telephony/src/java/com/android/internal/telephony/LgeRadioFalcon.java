package com.android.internal.telephony;

import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.telephony.Rlog;
import android.telephony.SignalStrength;
import com.android.internal.telephony.CommandException.Error;
import com.android.internal.telephony.dataconnection.ApnSetting;
import com.android.internal.telephony.dataconnection.DataCallResponse;
import com.android.internal.telephony.dataconnection.DataProfile;
import com.android.internal.telephony.gsm.LgeNetworkNameConstants;
import com.android.internal.telephony.uicc.IccCardStatus;
import com.android.internal.telephony.uicc.IccIoResult;
import com.android.internal.telephony.uicc.IccUtils;
import com.lge.internal.telephony.MOCADataResponse;
import com.lge.internal.telephony.MOCAMiscResponse;
import com.lge.internal.telephony.MOCARFParameterResponse;
import com.lge.uicc.framework.PbmInfo;
import com.lge.uicc.framework.PbmRecord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import vendor.lge.hardware.radio.V1_0.DataRegStateResult;
import vendor.lge.hardware.radio.V1_0.ILgeRadioFalconResponse;
import vendor.lge.hardware.radio.V1_0.ILgeRadioPcfalcon.Stub;
import vendor.lge.hardware.radio.V1_0.LgeIntString;

public class LgeRadioFalcon extends Stub {
    static final int EVENT_FALCON_CANCEL_MANUAL_SEARCHING_REQUEST = 28;
    static final int EVENT_FALCON_DM_REQUEST = 85;
    static final int EVENT_FALCON_EMBMS_ENABLE = 1;
    static final int EVENT_FALCON_EXIT_VOLTE_E911_EMERGENCY_MODE = 38;
    static final int EVENT_FALCON_GET_ADMIN_APN = 58;
    static final int EVENT_FALCON_GET_DEFAULT_APN = 57;
    static final int EVENT_FALCON_GET_EHRPD_INFO_IMS = 26;
    static final int EVENT_FALCON_GET_ENGINEERING_MODE_INFO = 32;
    static final int EVENT_FALCON_GET_GPRI_INFO = 53;
    static final int EVENT_FALCON_GET_IMS_NETWORK_INFO = 69;
    static final int EVENT_FALCON_GET_LTE_EMM_ERRORCODE = 34;
    static final int EVENT_FALCON_GET_LTE_INFO_FOR_IMS = 45;
    static final int EVENT_FALCON_GET_MIP_ERROR_CODE = 27;
    static final int EVENT_FALCON_GET_MODEM_INFO = 52;
    static final int EVENT_FALCON_GET_RSSI_TEST = 49;
    static final int EVENT_FALCON_GET_SEARCH_STATUS = 31;
    static final int EVENT_FALCON_GET_TETHERING_APN = 59;
    static final int EVENT_FALCON_GET_VOLTE_E911_NEWTORK_TYPE = 37;
    static final int EVENT_FALCON_IWLAN_SEND_IMS_PDN_STATUS = 63;
    static final int EVENT_FALCON_IWLAN_SET_REGISTER_CELLULAR_QUALITY_REPORT = 62;
    static final int EVENT_FALCON_LGE_CDMA_WRITE_SMS_TO_RUIM = 71;
    static final int EVENT_FALCON_LGE_GET_AVAILABLE_NETWORKS = 75;
    static final int EVENT_FALCON_LGE_GET_CURRENT_CALLS = 74;
    static final int EVENT_FALCON_LGE_GET_DATA_REGISTRATION_STATE = 77;
    static final int EVENT_FALCON_LGE_GET_ICC_CARD_STATUS = 73;
    static final int EVENT_FALCON_LGE_GET_SIGNAL_STRENGTH = 72;
    static final int EVENT_FALCON_LGE_SETUP_DATA_CALL = 76;
    static final int EVENT_FALCON_LGE_SET_NETWORK_SELECTION_MODE_MANUAL = 70;
    static final int EVENT_FALCON_LOAD_VOLTE_E911_SCANLIST = 36;
    static final int EVENT_FALCON_MOCA_ALARM_EVENT = 80;
    static final int EVENT_FALCON_MOCA_ALARM_EVENT_REG = 84;
    static final int EVENT_FALCON_MOCA_GET_DATA = 82;
    static final int EVENT_FALCON_MOCA_GET_MISC = 79;
    static final int EVENT_FALCON_MOCA_GET_RF_PARAMETER = 78;
    static final int EVENT_FALCON_MOCA_SET_LOG = 81;
    static final int EVENT_FALCON_MOCA_SET_MEM = 83;
    static final int EVENT_FALCON_NSRI_CAPTUREMODE_COMMAND = 89;
    static final int EVENT_FALCON_NSRI_COMMAND = 90;
    static final int EVENT_FALCON_NSRI_OEM_COMMAND = 91;
    static final int EVENT_FALCON_PBM_DELETE_RECORD = 10;
    static final int EVENT_FALCON_PBM_GET_INFO = 14;
    static final int EVENT_FALCON_PBM_GET_INIT_STATE = 11;
    static final int EVENT_FALCON_PBM_READ_RECORD = 8;
    static final int EVENT_FALCON_SEND_APN_DISABLE_FLAG = 35;
    static final int EVENT_FALCON_SEND_E911_CALL_STATE = 40;
    static final int EVENT_FALCON_SEND_IMC_CALL_STATE = 47;
    static final int EVENT_FALCON_SET_CDMA_ERI_VERSION = 24;
    static final int EVENT_FALCON_SET_CDMA_FACTORY_RESET = 25;
    static final int EVENT_FALCON_SET_CSG_SELECTION_MANUAL = 33;
    static final int EVENT_FALCON_SET_EMERGENCY = 55;
    static final int EVENT_FALCON_SET_GONS_INFO = 87;
    static final int EVENT_FALCON_SET_IMS_CALL_STATUS = 66;
    static final int EVENT_FALCON_SET_IMS_DATA_FLUSH = 61;
    static final int EVENT_FALCON_SET_IMS_REGI = 39;
    static final int EVENT_FALCON_SET_IMS_REGISTRATION_HVOLTE = 41;
    static final int EVENT_FALCON_SET_IMS_REGISTRATION_STATUS = 65;
    static final int EVENT_FALCON_SET_IMS_STATUS = 68;
    static final int EVENT_FALCON_SET_LTE_BAND_MODE = 54;
    static final int EVENT_FALCON_SET_MIMO_ANT_CONTROL_TEST = 50;
    static final int EVENT_FALCON_SET_MODEM_INFO = 51;
    static final int EVENT_FALCON_SET_NSRI_SECRET_VOLTE_CALL_INFO = 92;
    static final int EVENT_FALCON_SET_OTASN_PDN_STATE = 93;
    static final int EVENT_FALCON_SET_PREVIOUS_NETWORK_SELECTION = 29;
    static final int EVENT_FALCON_SET_PROXIMITY_SENSOR_STATE = 64;
    static final int EVENT_FALCON_SET_QCRIL = 86;
    static final int EVENT_FALCON_SET_RMNET_AUTO_CONNECT = 30;
    static final int EVENT_FALCON_SET_RSSI_ANT_CONF = 48;
    static final int EVENT_FALCON_SET_SCM_MODE = 67;
    static final int EVENT_FALCON_SET_SCRIPT_VALUE = 60;
    static final int EVENT_FALCON_SET_SRVCC_CALL_CONTEXT_TRANSFER = 46;
    static final int EVENT_FALCON_SET_VOICE_CALL = 43;
    static final int EVENT_FALCON_SET_VOICE_DOMAIN_PREF = 42;
    static final int EVENT_FALCON_SMRAT_CARD_TRANSMIT = 13;
    static final int EVENT_FALCON_UICC_AKA_AUTHENTICATE = 19;
    static final int EVENT_FALCON_UICC_APPLICATION_IO = 18;
    static final int EVENT_FALCON_UICC_DEACTIVE_APPLICATION = 17;
    static final int EVENT_FALCON_UICC_GBA_AUTH_BOOTSTRAP = 20;
    static final int EVENT_FALCON_UICC_GBA_AUTH_NAF = 21;
    static final int EVENT_FALCON_UICC_SELECT_APPLICATION = 16;
    static final int EVENT_FALCON_UIM_INTERNAL_REQUEST_CMD = 15;
    static final int EVENT_FALCON_UIM_POWER_DOWN = 22;
    static final int EVENT_FALCON_UIM_SMARTCARD_GET_ATR = 23;
    static final int EVENT_FALCON_UPDATE_PCAS_INFO = 88;
    static final int EVENT_FALCON_USIM_AUTHENTICATION = 12;
    static final int EVENT_FALCON_VSS_LGEIMS_LTE_DETACH = 44;
    static final int EVENT_FALCON_VSS_MODEM_RESET = 56;
    static final int EVENT_FALCON_WRITE_READ_RECORD = 9;
    static final int EVENT_SET_CDMA_ERI_VERSION = 6;
    static final int EVENT_SET_PREVIOUS_NETWORK_SELECTION = 7;
    static final int EVENT_UICC_DEACTIVE_APPLICATION = 4;
    static final int EVENT_UICC_SELECT_APPLICATION = 3;
    static final int EVENT_UIM_POWER_DOWN = 5;
    static final String TESTRILJ_LOG_TAG = "TESTRILJ";
    private ApnSetting mApn1 = new ApnSetting(2163, "44010", "sp-mode", "fake_apn", "", "", "", "", "", "user", "passwd", -1, new String[]{"default", "supl"}, "IPV6", "IP", true, 0, 0, 1234, false, 321, 456, 789, 0, "", "");
    ILgeRadioFalconResponse mLgeRadioFalconResponse;
    LgeRIL mLgeRil;
    private Map<Integer, Integer> mRequestTokenList = new HashMap();
    RilFalcontHandler mRilFalcontHandler = new RilFalcontHandler();

    class RilFalcontHandler extends Handler {
        RilFalcontHandler() {
        }

        public void handleMessage(Message msg) {
            AsyncResult ar;
            int token;
            int error;
            ArrayList<String> arrayList;
            int i;
            ArrayList<Integer> arrayList2;
            int data_len;
            byte[] resData;
            switch (msg.what) {
                case 1:
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOTEST: testLgeRadioInterface Response serial= " + msg.arg1);
                    ar = (AsyncResult) msg.obj;
                    int trans_id = ((int[]) ar.result)[0];
                    int resp_code = ((int[]) ar.result)[1];
                    int interface_index_valid = ((int[]) ar.result)[2];
                    int interface_index = ((int[]) ar.result)[3];
                    return;
                case 8:
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: PBMReadRecord Response");
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(8);
                    if (ar.exception == null) {
                        PbmRecord pbmRecord = (PbmRecord) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.PBMReadRecordResponse(token, 0, pbmRecord.device, pbmRecord.index, pbmRecord.type, pbmRecord.ad_type, pbmRecord.number, pbmRecord.name, pbmRecord.additional_number, pbmRecord.additional_number_a, pbmRecord.additional_number_b, pbmRecord.email_address, pbmRecord.second_name, pbmRecord.gas_id, pbmRecord.sync_cnt);
                            return;
                        } catch (Exception e) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMReadRecord", e);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: PBMReadRecord error: invalid");
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMReadRecord error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMReadRecord", e2);
                        return;
                    }
                case 9:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(9);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: PBMWriteRecord token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.PBMWriteRecordResponse(token, 0, ((int[]) ar.result)[0], ((int[]) ar.result)[1], ((int[]) ar.result)[2]);
                            return;
                        } catch (Exception e22) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMWriteRecord", e22);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMWriteRecord error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMWriteRecord", e222);
                        return;
                    }
                case 10:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(10);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: PBMDeleteRecord token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.PBMDeleteRecordResponse(token, 0, ((int[]) ar.result)[0], ((int[]) ar.result)[1], ((int[]) ar.result)[2]);
                            return;
                        } catch (Exception e2222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMDeleteRecord", e2222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMDeleteRecord error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBDeleteRecord", e22222);
                        return;
                    }
                case 11:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(11);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: PBMGetInitState token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.PBMGetInitStateResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMGetInitState", e222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMGetInitState error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMGetInitState", e2222222);
                        return;
                    }
                case 12:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(12);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getUsimAuthentication token:" + token);
                    if (ar.exception == null) {
                        Parcel authResult = (Parcel) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getUsimAuthenticationResponse(token, 0, authResult.readInt(), IccUtils.bytesToHexString(authResult.createByteArray()));
                            return;
                        } catch (Exception e22222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getUsimAuthentication", e22222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getUsimAuthentication error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getUsimAuthentication", e222222222);
                        return;
                    }
                case 13:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(13);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: smartCardTransmit token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.smartCardTransmitResponse(token, 0, IccUtils.bytesToHexString((byte[]) ar.result));
                            return;
                        } catch (Exception e2222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: smartCardTransmit", e2222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: smartCardTransmit error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: smartCardTransmit", e22222222222);
                        return;
                    }
                case 14:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(14);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: PBMGetInfo token:" + token);
                    if (ar.exception == null) {
                        PbmInfo pbmInfo = (PbmInfo) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.PBMGetInfoResponse(token, 0, pbmInfo.device, pbmInfo.status, pbmInfo.max_num_length, pbmInfo.max_text_length, pbmInfo.num_of_used_rec, pbmInfo.num_of_free_ext, pbmInfo.num_of_tot_rec, pbmInfo.file_type);
                            return;
                        } catch (Exception e222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMGetInfo", e222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMGetInfo error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: PBMGetInfo", e2222222222222);
                        return;
                    }
                case 15:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(15);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: UIMInternalRequestCmd token:" + token);
                    if (ar.exception != null) {
                        try {
                            error = ((CommandException) ar.exception).getCommandError().ordinal();
                            if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                                LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: UIMInternalRequestCmd error: invalid");
                                error = 66;
                            }
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: UIMInternalRequestCmd error:" + error);
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                            return;
                        } catch (Exception e22222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: UIMInternalRequestCmd", e22222222222222);
                            return;
                        }
                    } else if (ar.result != null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.UIMInternalRequestCmdResponse(token, 0, ((String[]) ar.result)[0], ((String[]) ar.result)[1]);
                            return;
                        } catch (Exception e222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: UIMInternalRequestCmd", e222222222222222);
                            return;
                        }
                    } else {
                        LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: UIMInternalRequestCmd data is null => send invalid_response");
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, 66);
                            return;
                        } catch (Exception e2222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: UIMInternalRequestCmd", e2222222222222222);
                            return;
                        }
                    }
                case 16:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(16);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: uiccSelectApplication token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uiccSelectApplicationResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e22222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccSelectApplication", e22222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccSelectApplication error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccSelectApplication", e222222222222222222);
                        return;
                    }
                case 17:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(17);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: uiccDeactivateApplication token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uiccDeactivateApplicationResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccDeactivateApplication", e2222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccDeactivateApplication error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccDeactivateApplication", e22222222222222222222);
                        return;
                    }
                case 18:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(18);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: uiccApplication token:" + token);
                    if (ar.exception == null) {
                        IccIoResult result = (IccIoResult) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uiccApplicationResponse(token, 0, result.sw1, result.sw2, IccUtils.bytesToHexString(result.payload));
                            return;
                        } catch (Exception e222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccApplication", e222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccApplication error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccApplication", e2222222222222222222222);
                        return;
                    }
                case 19:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(19);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: uiccAkaAuthenticate token:" + token);
                    if (ar.exception == null) {
                        Bundle aka_rsp = (Bundle) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uiccAkaAuthenticateResponse(token, 0, IccUtils.bytesToHexString(aka_rsp.getByteArray("res")), IccUtils.bytesToHexString(aka_rsp.getByteArray("Ck")), IccUtils.bytesToHexString(aka_rsp.getByteArray("Ik")), IccUtils.bytesToHexString(aka_rsp.getByteArray("kc")), IccUtils.bytesToHexString(aka_rsp.getByteArray("auts")));
                            return;
                        } catch (Exception e22222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccAkaAuthenticate", e22222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccAkaAuthenticate error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uiccAkaAuthenticate", e222222222222222222222222);
                        return;
                    }
                case 20:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(20);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: GbaAuthenticateBootstrap token:" + token);
                    if (ar.exception == null) {
                        Bundle gba_rsp = (Bundle) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uiccGbaAuthenticateBootstrapResponse(token, 0, IccUtils.bytesToHexString(gba_rsp.getByteArray("res")), IccUtils.bytesToHexString(gba_rsp.getByteArray("auts")));
                            return;
                        } catch (Exception e2222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: GbaAuthenticateBootstrap", e2222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: GbaAuthenticateBootstrap error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: GbaAuthenticateBootstrap", e22222222222222222222222222);
                        return;
                    }
                case 21:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(21);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: GbaAuthenticateNaf token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uiccGbaAuthenticateNafResponse(token, 0, IccUtils.bytesToHexString((byte[]) ar.result));
                            return;
                        } catch (Exception e222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: GbaAuthenticateNaf", e222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: GbaAuthenticateNaf error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: GbaAuthenticateNaf", e2222222222222222222222222222);
                        return;
                    }
                case 22:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(22);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: uimPowerDownRequest token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.uimPowerDownRequestResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uimPowerDownRequest", e22222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uimPowerDownRequest error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: uimPowerDownRequest", e222222222222222222222222222222);
                        return;
                    }
                case 23:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(23);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: USIMSmartcardGetAtr token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.USIMSmartcardGetAtrResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e2222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: USIMSmartcardGetAtr", e2222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: USIMSmartcardGetAtr error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: USIMSmartcardGetAtr", e22222222222222222222222222222222);
                        return;
                    }
                case 24:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(24);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setCdmaEriVersion token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setCdmaEriVersionResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCdmaEriVersion", e222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCdmaEriVersion error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCdmaEriVersion", e2222222222222222222222222222222222);
                        return;
                    }
                case 25:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(25);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setCdmaFactoryReset token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setCdmaFactoryResetResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e22222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCdmaFactoryReset", e22222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCdmaFactoryReset error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCdmaFactoryReset", e222222222222222222222222222222222222);
                        return;
                    }
                case 26:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(26);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getEhrpdInfoForIms token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getEhrpdInfoForImsResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getEhrpdInfoForIms", e2222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getEhrpdInfoForIms error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getEhrpdInfoForIms", e22222222222222222222222222222222222222);
                        return;
                    }
                case 27:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(27);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getMipErrorCode token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getMipErrorCodeResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getMipErrorCode", e222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getMipErrorCode error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getMipErrorCode", e2222222222222222222222222222222222222222);
                        return;
                    }
                case 28:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(28);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: cancelManualSearchingRequest token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.cancelManualSearchingRequestResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: cancelManualSearchingRequest", e22222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: cancelManualSearchingRequest error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: cancelManualSearchingRequest", e222222222222222222222222222222222222222222);
                        return;
                    }
                case 29:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(29);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setPreviousNetworkSelectionModeManual token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setPreviousNetworkSelectionModeManualResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setPreviousNetworkSelectionModeManual", e2222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setPreviousNetworkSelectionModeManual error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setPreviousNetworkSelectionModeManual", e22222222222222222222222222222222222222222222);
                        return;
                    }
                case 30:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(30);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setRmnetAutoconnect token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setRmnetAutoconnectResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setRmnetAutoconnect", e222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setRmnetAutoconnect error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setRmnetAutoconnect", e2222222222222222222222222222222222222222222222);
                        return;
                    }
                case 31:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(31);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getSearchStatus token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getSearchStatusResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getSearchStatus", e22222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getSearchStatus error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getSearchStatus", e222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 32:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(32);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getEngineeringModeInfo token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getEngineeringModeInfoResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getEngineeringModeInfo", e2222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getEngineeringModeInfo error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getEngineeringModeInfo", e22222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 33:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(33);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setCSGSelectionManual token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setCSGSelectionManualResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCSGSelectionManual", e222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCSGSelectionManual error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setCSGSelectionManual", e2222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 34:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(34);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getLteEmmErrorCode token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getLteEmmErrorCodeResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getLteEmmErrorCode", e22222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getLteEmmErrorCode error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getLteEmmErrorCode", e222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 35:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(35);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: sendApnDisableFlag token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.sendApnDisableFlagResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: sendApnDisableFlag", e2222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: sendApnDisableFlag error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: sendApnDisableFlag", e22222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 36:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(36);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: loadVolteE911ScanList token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.loadVolteE911ScanListResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: loadVolteE911ScanList", e222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: loadVolteE911ScanList error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: loadVolteE911ScanList", e2222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 37:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(37);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getVolteE911NetworkType token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getVolteE911NetworkTypeResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getVolteE911NetworkType", e22222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getVolteE911NetworkType error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getVolteE911NetworkType", e222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 38:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(38);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: exitVolteE911EmergencyMode token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.exitVolteE911EmergencyModeResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: exitVolteE911EmergencyMode", e2222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: exitVolteE911EmergencyMode error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: exitVolteE911EmergencyMode", e22222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 39:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(39);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsRegistration token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setImsRegistrationResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistration", e222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistration error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistration", e2222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 40:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(40);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsRegistration token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setImsRegistrationResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistration", e22222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistration error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistration", e222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 41:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(41);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsRegistrationForHVoLTE token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setImsRegistrationForHVoLTEResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistrationForHVoLTE", e2222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistrationForHVoLTE error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistrationForHVoLTE", e22222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 42:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(42);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setVoiceDomainPref token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setVoiceDomainPrefResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setVoiceDomainPref", e222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setVoiceDomainPref error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setVoiceDomainPref", e2222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 43:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(43);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setVoLteCall token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setVoLteCallResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setVoLteCall", e22222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setVoLteCall error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setVoLteCall", e222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 44:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(44);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: closeImsPdn token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.closeImsPdnResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: closeImsPdn", e2222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: closeImsPdn error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: closeImsPdn", e22222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 45:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(45);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getLteInfoForIms token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getLteInfoForImsResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getLteInfoForIms", e222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getLteInfoForIms error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getLteInfoForIms", e2222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 46:
                    return;
                case 47:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(47);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: sendImsCallState token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.sendIMSCallStateResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: sendImsCallState", e22222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: sendImsCallState error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: sendImsCallState", e222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 48:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(48);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setRssiTestAntConf token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setRssiTestAntConfResponse(token, 0, ((int[]) ar.result)[0], ((int[]) ar.result)[1]);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setRssiTestAntConf", e2222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setRssiTestAntConf error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setRssiTestAntConf", e22222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 49:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(49);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getRssiTest token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getRssiTestResponse(token, 0, ((int[]) ar.result)[0], ((int[]) ar.result)[1], ((int[]) ar.result)[2], ((int[]) ar.result)[3], ((int[]) ar.result)[4], ((int[]) ar.result)[5]);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getRssiTest", e222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getRssiTest error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getRssiTest", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 50:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(50);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setMiMoAntennaControlTest token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setMiMoAntennaControlTestResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setMiMoAntennaControlTest", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setMiMoAntennaControlTest error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setMiMoAntennaControlTest", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 51:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(51);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setModemInfo token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setModemInfoResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setModemInfo", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setModemInfo error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setModemInfo", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 52:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(52);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getModemInfoResponse(token, 0, ((String[]) ar.result)[0]);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getModemInfo", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getModemInfo error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getModemInfo", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 53:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(53);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getGPRIItem token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getGPRIItemResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getGPRIItem", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getGPRIItem error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getGPRIItem", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 54:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(54);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setLteBandMode token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setLteBandModeResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setLteBandMode", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setLteBandMode error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setLteBandMode", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 55:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(55);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setEmergency token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setEmergencyResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setEmergency", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setEmergency error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setEmergency", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 56:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(56);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: vssModemReset token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.vssModemResetResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: vssModemReset", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: vssModemReset error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: vssModemReset", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 57:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(57);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getDefaultAPN token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getDefaultAPNResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getDefaultAPN", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getDefaultAPN error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getDefaultAPN", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 58:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(58);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getAdminAPN token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getAdminAPNResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getAdminAPN", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getAdminAPN error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getAdminAPN", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 59:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(59);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getTetheringAPN token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getTetheringAPNResponse(token, 0, (String) ar.result);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getTetheringAPN", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getTetheringAPN error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getTetheringAPN", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 60:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(60);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setScriptValue token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setScriptValueResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setScriptValue", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setScriptValue error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setScriptValue", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 61:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(61);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsDataFlushEnabled token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setImsDataFlushEnabledResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsDataFlushEnabled", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsDataFlushEnabled error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsDataFlushEnabled", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 62:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(62);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: iwlanSetRegisterCellularQualityReport token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.iwlanSetRegisterCellularQualityReportResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: iwlanSetRegisterCellularQualityReport", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: iwlanSetRegisterCellularQualityReport error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: iwlanSetRegisterCellularQualityReport", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 63:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(63);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: iwlanSendImsPdnStatus token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.iwlanSendImsPdnStatusResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: iwlanSendImsPdnStatus", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: iwlanSendImsPdnStatus error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: iwlanSendImsPdnStatus", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 64:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(64);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setProximitySensorState token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setProximitySensorStateResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setProximitySensorState", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setProximitySensorState error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setProximitySensorState", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 65:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(65);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsRegistrationStatus token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setProximitySensorStateResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistrationStatus", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistrationStatus error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsRegistrationStatus", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 66:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(66);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsCallStatus token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setImsCallStatusResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsCallStatus", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsCallStatus error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsCallStatus", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_SET_SCM_MODE /*67*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_SET_SCM_MODE);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setScmMode token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setScmModeResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setScmMode", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setScmMode error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setScmMode", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 68:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(68);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setImsStatus token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setImsStatusResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsStatus", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsStatus error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setImsStatus", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 69:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(69);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getIMSNetworkInfo token:" + token);
                    if (ar.exception == null) {
                        String[] aNetworkInfoRet = (String[]) ar.result;
                        LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getIMSNetworkInfo length:" + aNetworkInfoRet.length);
                        arrayList = new ArrayList(aNetworkInfoRet.length);
                        for (i = 0; i < aNetworkInfoRet.length; i++) {
                            arrayList.add(aNetworkInfoRet[i]);
                            LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getIMSNetworkInfo data:" + aNetworkInfoRet[i]);
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getIMSNetworkInfoResponse(token, 0, arrayList);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getIMSNetworkInfo", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getIMSNetworkInfo error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getIMSNetworkInfo", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 70:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(70);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: lgeSetNetworkSelectionModeManual token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.lgeSetNetworkSelectionModeManualResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeSetNetworkSelectionModeManual", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeSetNetworkSelectionModeManual error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeSetNetworkSelectionModeManual", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 71:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(71);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: lgeCdmaWriteSmsToRuim token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.lgeCdmaWriteSmsToRuimResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeCdmaWriteSmsToRuim", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeCdmaWriteSmsToRuim error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeCdmaWriteSmsToRuim", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 72:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(72);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: lgeGetSignalStrength token:" + token);
                    if (ar.exception == null) {
                        SignalStrength sig_str = (SignalStrength) ar.result;
                        arrayList2 = new ArrayList(14);
                        arrayList2.add(Integer.valueOf(sig_str.getGsmSignalStrength()));
                        arrayList2.add(Integer.valueOf(sig_str.getGsmBitErrorRate()));
                        arrayList2.add(Integer.valueOf(sig_str.getCdmaDbm()));
                        arrayList2.add(Integer.valueOf(sig_str.getCdmaEcio()));
                        arrayList2.add(Integer.valueOf(sig_str.getEvdoDbm()));
                        arrayList2.add(Integer.valueOf(sig_str.getEvdoEcio()));
                        arrayList2.add(Integer.valueOf(sig_str.getEvdoSnr()));
                        arrayList2.add(Integer.valueOf(sig_str.getLteSignalStrength()));
                        arrayList2.add(Integer.valueOf(sig_str.getLteRsrp()));
                        arrayList2.add(Integer.valueOf(sig_str.getLteRsrq()));
                        arrayList2.add(Integer.valueOf(sig_str.getLteRssnr()));
                        arrayList2.add(Integer.valueOf(sig_str.getLteCqi()));
                        arrayList2.add(Integer.valueOf(sig_str.getTdScdmaDbm()));
                        arrayList2.add(Integer.valueOf(sig_str.isGsm() ? 1 : 0));
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.lgeGetSignalStrengthResponse(token, 0, arrayList2);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetSignalStrength", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetSignalStrength error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetSignalStrength", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 73:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(73);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: lgeGetIccCardStatusResponse token:" + token);
                    if (ar.exception == null) {
                        IccCardStatus icc_status = (IccCardStatus) ar.result;
                        String aCardState = String.valueOf(icc_status.mCardState);
                        String aUniversalPinState = String.valueOf(icc_status.mUniversalPinState);
                        int aGsmUmtsSubscriptionAppIndex = icc_status.mGsmUmtsSubscriptionAppIndex;
                        int aCdmaSubscriptionAppIndex = icc_status.mCdmaSubscriptionAppIndex;
                        int aImsSubscriptionAppIndex = icc_status.mImsSubscriptionAppIndex;
                        int numApplications = icc_status.mApplications.length;
                        arrayList = new ArrayList(numApplications);
                        arrayList = new ArrayList(numApplications);
                        arrayList = new ArrayList(numApplications);
                        arrayList = new ArrayList(numApplications);
                        arrayList = new ArrayList(numApplications);
                        arrayList2 = new ArrayList(numApplications);
                        arrayList = new ArrayList(numApplications);
                        arrayList = new ArrayList(numApplications);
                        arrayList2 = new ArrayList(numApplications);
                        arrayList2 = new ArrayList(numApplications);
                        arrayList2 = new ArrayList(numApplications);
                        arrayList2 = new ArrayList(numApplications);
                        for (i = 0; i < numApplications; i++) {
                            arrayList.add(String.valueOf(icc_status.mApplications[i].app_type));
                            arrayList.add(String.valueOf(icc_status.mApplications[i].app_state));
                            arrayList.add(String.valueOf(icc_status.mApplications[i].perso_substate));
                            arrayList.add(icc_status.mApplications[i].aid);
                            arrayList.add(icc_status.mApplications[i].app_label);
                            arrayList2.add(Integer.valueOf(icc_status.mApplications[i].pin1_replaced));
                            arrayList.add(String.valueOf(icc_status.mApplications[i].pin1));
                            arrayList.add(String.valueOf(icc_status.mApplications[i].pin2));
                            arrayList2.add(Integer.valueOf(icc_status.mApplications[i].remaining_count_pin1));
                            arrayList2.add(Integer.valueOf(icc_status.mApplications[i].remaining_count_puk1));
                            arrayList2.add(Integer.valueOf(icc_status.mApplications[i].remaining_count_pin2));
                            arrayList2.add(Integer.valueOf(icc_status.mApplications[i].remaining_count_puk2));
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.lgeGetIccCardStatusResponse(token, 0, aCardState, aUniversalPinState, aGsmUmtsSubscriptionAppIndex, aCdmaSubscriptionAppIndex, aImsSubscriptionAppIndex, arrayList, arrayList, arrayList, arrayList, arrayList, arrayList2, arrayList, arrayList, arrayList2, arrayList2, arrayList2, arrayList2);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetIccCardStatusResponse", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetIccCardStatusResponse error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetIccCardStatusResponse", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 74:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(74);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: lgeGetCurrentCalls token:" + token);
                    if (ar.exception == null) {
                        ArrayList<DriverCall> DC = (ArrayList<DriverCall>)ar.result;
                        int dc_num = DC.size();
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList = new ArrayList(dc_num);
                        arrayList = new ArrayList(dc_num);
                        arrayList2 = new ArrayList(dc_num);
                        arrayList = new ArrayList(dc_num);
                        for (i = 0; i < dc_num; i++) {
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).index));
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).isMT ? 1 : 0));
                            arrayList.add(String.valueOf(((DriverCall) DC.get(i)).state));
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).isMpty ? 1 : 0));
                            arrayList.add(((DriverCall) DC.get(i)).number);
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).TOA));
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).isVoice ? 1 : 0));
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).isVoicePrivacy ? 1 : 0));
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).als));
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).numberPresentation));
                            arrayList.add(((DriverCall) DC.get(i)).name);
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).namePresentation));
                            if (((DriverCall) DC.get(i)).uusInfo == null) {
                                arrayList2.add(Integer.valueOf(0));
                                arrayList2.add(Integer.valueOf(0));
                                arrayList.add(LgeNetworkNameConstants.ITEM_VALUE_NULL);
                            } else {
                                arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).uusInfo.getType()));
                                arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).uusInfo.getDcs()));
                                arrayList.add(IccUtils.bytesToHexString(((DriverCall) DC.get(i)).uusInfo.getUserData()));
                            }
                            arrayList.add(((DriverCall) DC.get(i)).cdnipNumber);
                            arrayList2.add(Integer.valueOf(((DriverCall) DC.get(i)).signal));
                            if (((DriverCall) DC.get(i)).redirectNumber == null) {
                                arrayList.add(LgeNetworkNameConstants.ITEM_VALUE_NULL);
                            } else {
                                arrayList.add(((DriverCall) DC.get(i)).redirectNumber);
                            }
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.lgeGetCurrentCallsResponse(token, 0, arrayList2, arrayList2, arrayList, arrayList2, arrayList, arrayList2, arrayList2, arrayList2, arrayList2, arrayList2, arrayList, arrayList2, arrayList2, arrayList2, arrayList, arrayList, arrayList2, arrayList);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetCurrentCalls", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetCurrentCalls error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: lgeGetCurrentCalls", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 75:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(75);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getAvailableNetworks token:" + token);
                    if (ar.exception == null) {
                        ArrayList<OperatorInfo> operatorInfo = (ArrayList<OperatorInfo>) ar.result;
                        int aOpInfoNum = operatorInfo.size();
                        arrayList = new ArrayList(aOpInfoNum);
                        for (i = 0; i < aOpInfoNum; i++) {
                            arrayList.add(((OperatorInfo) operatorInfo.get(i)).getOperatorAlphaLong());
                            arrayList.add(((OperatorInfo) operatorInfo.get(i)).getOperatorAlphaShort());
                            arrayList.add(((OperatorInfo) operatorInfo.get(i)).getOperatorNumeric());
                            arrayList.add(String.valueOf(((OperatorInfo) operatorInfo.get(i)).getState()));
                            arrayList.add(((OperatorInfo) operatorInfo.get(i)).operatorRAT);
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getAvailableNetworksResponse(token, 0, aOpInfoNum, arrayList);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getAvailableNetworks", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getAvailableNetworks error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getAvailableNetworks", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 76:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(76);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setupDataCallEx token:" + token);
                    if (ar.exception == null) {
                        DataCallResponse DC_res = (DataCallResponse) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setupDataCallExResponse(token, 0, DC_res.status, DC_res.suggestedRetryTime, DC_res.cid, DC_res.active, DC_res.type, DC_res.ifname, Arrays.toString(DC_res.addresses), Arrays.toString(DC_res.dnses), Arrays.toString(DC_res.gateways), Arrays.toString(DC_res.pcscf), DC_res.mtu);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setupDataCallEx", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setupDataCallEx error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setupDataCallEx", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 77:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(77);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: getDataRegistrationState token:" + token);
                    if (ar.exception == null) {
                        DataRegStateResult dataRegStateResult = (DataRegStateResult) ar.result;
                        arrayList2 = new ArrayList(4);
                        arrayList2.add(Integer.valueOf(dataRegStateResult.regState));
                        arrayList2.add(Integer.valueOf(dataRegStateResult.rat));
                        arrayList2.add(Integer.valueOf(dataRegStateResult.reasonDataDenied));
                        arrayList2.add(Integer.valueOf(dataRegStateResult.maxDataCalls));
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.getDataRegistrationStateResponse(token, 0, arrayList2);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getDataRegistrationState", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getDataRegistrationState error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: getDataRegistrationState", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 78:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(78);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaGetRFParameter token:" + token);
                    if (ar.exception == null) {
                        MOCARFParameterResponse moca_rf = (MOCARFParameterResponse) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaGetRFParameterResponse(token, 0, moca_rf.kind_of_data, moca_rf.send_buf_num, moca_rf.data_len, LgeRadioFalcon.this.bytetoString(moca_rf.data));
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetRFParameter", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetRFParameter error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetRFParameter", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 79:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(79);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaGetMisc token:" + token);
                    if (ar.exception == null) {
                        MOCAMiscResponse moca_misc = (MOCAMiscResponse) ar.result;
                        int i2 = moca_misc.kind_of_data;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaGetMiscResponse(token, 0, moca_misc.send_buf_num, moca_misc.data_len, LgeRadioFalcon.this.bytetoString(moca_misc.data));
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetMisc", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetMisc error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetMisc", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 80:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(80);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaAlarmEvent token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaAlarmEventResponse(token, 0, ((byte[]) ar.result)[0]);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaAlarmEvent", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaAlarmEvent error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaAlarmEvent", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 81:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(81);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaSetLog token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaSetLogResponse(token, 0, ((byte[]) ar.result)[0]);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaSetLog", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaSetLog error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaSetLog", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 82:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(82);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaGetData token:" + token);
                    if (ar.exception == null) {
                        MOCADataResponse moca_data = (MOCADataResponse) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaGetDataResponse(token, 0, moca_data.send_buf_num, moca_data.data_len, LgeRadioFalcon.this.bytetoString(moca_data.data));
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetData", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetData error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaGetData", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_MOCA_SET_MEM /*83*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_MOCA_SET_MEM);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaSetMem token:" + token);
                    if (ar.exception == null) {
                        int aData_1;
                        int aData_2;
                        int[] data = (int[]) ar.result;
                        if (data.length == 2) {
                            aData_1 = data[0];
                            aData_2 = data[1];
                        } else {
                            aData_1 = data[0];
                            aData_2 = 0;
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaSetMemResponse(token, 0, aData_1, aData_2);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaSetMem", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaSetMem error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaSetMem", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_MOCA_ALARM_EVENT_REG /*84*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_MOCA_ALARM_EVENT_REG);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: mocaAlarmEventReg token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.mocaAlarmEventRegResponse(token, 0, ((int[]) ar.result)[0]);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaAlarmEventReg", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaAlarmEventReg error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: mocaAlarmEventReg", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_DM_REQUEST /*85*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_DM_REQUEST);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: DMRequest token:" + token);
                    if (ar.exception == null) {
                        byte[] data2 = (byte[]) ar.result;
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.DMRequestResponse(token, 0, data2[0], data2[1]);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: DMRequest", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: DMRequest error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: DMRequest", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_SET_QCRIL /*86*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_SET_QCRIL);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setQcril token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setQcrilResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setQcril", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setQcril error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setQcril", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 88:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(88);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setPcasInfo token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setPcasInfofaceResponse(token, 0);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setPcasInfo", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setPcasInfo error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setPcasInfo", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_NSRI_CAPTUREMODE_COMMAND /*89*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_NSRI_CAPTUREMODE_COMMAND);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_SetCaptureMode_requestProc token:" + token);
                    if (ar.exception == null) {
                        byte[] modeData = (byte[]) ar.result;
                        data_len = modeData.length;
                        LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_SetCaptureMode_requestProc data_len:" + data_len);
                        arrayList = new ArrayList(data_len);
                        for (i = 0; i < data_len; i++) {
                            arrayList.add(Integer.toHexString(modeData[i] & 255));
                            LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_SetCaptureMode_requestProc modeData[i] " + i + "=" + Integer.toHexString(modeData[i] & 255));
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.NSRI_SetCaptureMode_requestProcResponse(token, 0, arrayList);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_SetCaptureMode_requestProc", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_SetCaptureMode_requestProc error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_SetCaptureMode_requestProc", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_NSRI_COMMAND /*90*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_NSRI_COMMAND);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_requestProc token:" + token);
                    if (ar.exception == null) {
                        resData = (byte[]) ar.result;
                        data_len = resData.length;
                        LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_requestProc data_len:" + data_len);
                        arrayList = new ArrayList(data_len);
                        for (i = 0; i < data_len; i++) {
                            arrayList.add(Integer.toHexString(resData[i] & 255));
                            LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_requestProc resData[i] " + i + "=" + Integer.toHexString(resData[i] & 255));
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.NSRI_requestProcResponse(token, 0, arrayList);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_requestProc", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_requestProc error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_requestProc", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 91:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(91);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_Oem_requestProc token:" + token);
                    if (ar.exception == null) {
                        resData = (byte[]) ar.result;
                        data_len = resData.length;
                        LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_Oem_requestProc data_len:" + data_len);
                        arrayList = new ArrayList(data_len);
                        for (i = 0; i < data_len; i++) {
                            arrayList.add(Integer.toHexString(resData[i] & 255));
                            LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: NSRI_Oem_requestProc resData[i] " + i + "=" + Integer.toHexString(resData[i] & 255));
                        }
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.NSRI_Oem_requestProcResponse(token, 0, arrayList);
                            return;
                        } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_Oem_requestProc", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_Oem_requestProc error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: NSRI_Oem_requestProc", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case 92:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(92);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setNSRICallInfoTransfer token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setNSRICallInfoTransferResponse(token, 0);
                            return;
                        } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setNSRICallInfoTransfer", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setNSRICallInfoTransfer error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setNSRICallInfoTransfer", e2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                case LgeRadioFalcon.EVENT_FALCON_SET_OTASN_PDN_STATE /*93*/:
                    ar = (AsyncResult) msg.obj;
                    token = LgeRadioFalcon.this.removeRequestToken(LgeRadioFalcon.EVENT_FALCON_SET_OTASN_PDN_STATE);
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOFALCON: setOtasnPdnState token:" + token);
                    if (ar.exception == null) {
                        try {
                            LgeRadioFalcon.this.mLgeRadioFalconResponse.setOtasnPdnStateResponse(token, 0);
                            return;
                        } catch (Exception e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                            LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setOtasnPdnState", e22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                            return;
                        }
                    }
                    try {
                        error = ((CommandException) ar.exception).getCommandError().ordinal();
                        if (((CommandException) ar.exception).getCommandError() == Error.INVALID_RESPONSE) {
                            error = 66;
                        }
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setOtasnPdnState error:" + error);
                        LgeRadioFalcon.this.mLgeRadioFalconResponse.ResponseError(token, error);
                        return;
                    } catch (Exception e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222) {
                        LgeRadioFalcon.this.mLgeRil.riljLoge("LGRADIOFALCON: setOtasnPdnState", e222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222);
                        return;
                    }
                default:
                    LgeRadioFalcon.this.mLgeRil.riljLog("LGRADIOTEST: EVENT is not registered");
                    return;
            }
        }
    }

    public LgeRadioFalcon(LgeRIL ril) {
        this.mLgeRil = ril;
    }

    public void setResponseFunctions(ILgeRadioFalconResponse respnose) {
        this.mLgeRadioFalconResponse = respnose;
    }

    public void embmsEnable(int aSerial, int aTransId) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: embmsEnable enter transid=%d" + aTransId);
        this.mLgeRil.enable(aTransId, this.mRilFalcontHandler.obtainMessage(1));
    }

    public void embmsDisable(int aSerial, int aTransId) {
    }

    public void embmsStartSession(int aSerial) {
    }

    public void embmsStopSession(int aSerial) {
    }

    public void embmsSwitchSession(int aSerial) {
    }

    public void embmsGetTime(int aSerial, int aTransId) {
    }

    public void embmsGetCoverageState(int aSerial, int aTransId) {
    }

    public void PBMReadRecord(int aSerial, int aEfDevice, int aRecIndex) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: PBMReadRecord enter");
        addRequestToken(8, aSerial);
        this.mLgeRil.PBMReadRecord(aEfDevice, aRecIndex, this.mRilFalcontHandler.obtainMessage(8));
    }

    public void PBMWriteRecord(int aSerial, int aDevice, int aIndex, int aType, int aAdtype, String number, String name, String aAdditionalNumber, String aAdditionalNumberA, String aAdditionalNumberB, String aEmailAddress, String aSecondName, int aGasId, int aSyncCnt) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: PBMWriteRecord enter");
        addRequestToken(9, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(9);
        PbmRecord records = new PbmRecord();
        String null_check = LgeNetworkNameConstants.ITEM_VALUE_NULL;
        records.device = aDevice;
        records.index = aIndex;
        records.type = aType;
        records.ad_type = aAdtype;
        if (number.equals(null_check)) {
            records.number = "";
        } else {
            records.number = number;
        }
        records.name = name;
        if (aAdditionalNumber.equals(null_check)) {
            records.additional_number = "";
        } else {
            records.additional_number = aAdditionalNumber;
        }
        if (aAdditionalNumberA.equals(null_check)) {
            records.additional_number_a = "";
        } else {
            records.additional_number_a = aAdditionalNumberA;
        }
        if (aAdditionalNumberB.equals(null_check)) {
            records.additional_number_b = "";
        } else {
            records.additional_number_b = aAdditionalNumberB;
        }
        if (aEmailAddress.equals(null_check)) {
            records.email_address = "";
        } else {
            records.email_address = aEmailAddress;
        }
        if (aSecondName.equals(null_check)) {
            records.second_name = "";
        } else {
            records.second_name = aSecondName;
        }
        records.gas_id = aGasId;
        records.sync_cnt = aSyncCnt;
        this.mLgeRil.PBMWriteRecord(records, msg);
    }

    public void PBMDeleteRecord(int aSerial, int aEfDevice, int aRecIndex) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: PBMDeleteRecord enter");
        addRequestToken(10, aSerial);
        this.mLgeRil.PBMDeleteRecord(aEfDevice, aRecIndex, this.mRilFalcontHandler.obtainMessage(10));
    }

    public void PBMGetInitState(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: PBMGetInitState enter");
        addRequestToken(11, aSerial);
        this.mLgeRil.PBMGetInitState(this.mRilFalcontHandler.obtainMessage(11));
    }

    public void getUsimAuthentication(int aSerial, String aAid, String aRandS, int aRandLength, String aAutnS, int AautnLength) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getUsimAuthentication enter");
        addRequestToken(12, aSerial);
        this.mLgeRil.getUsimAuthentication(aAid, aRandS, aRandLength, aAutnS, AautnLength, this.mRilFalcontHandler.obtainMessage(12));
    }

    public void smartCardTransmit(int aSerial, String aCmd) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: smartCardTransmit enter");
        addRequestToken(13, aSerial);
        this.mLgeRil.smartCardTransmit(IccUtils.hexStringToBytes(aCmd), this.mRilFalcontHandler.obtainMessage(13));
    }

    public void PBMGetInfo(int aSerial, int aEFDevice) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: PBMGetInfo enter");
        addRequestToken(14, aSerial);
        this.mLgeRil.PBMGetInfo(aEFDevice, this.mRilFalcontHandler.obtainMessage(14));
    }

    public void UIMInternalRequestCmd(int aSerial, int aCmd, int aSlotid, int aIsvolte) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: UIMInternalRequestCmd enter");
        addRequestToken(15, aSerial);
        byte[] data = new byte[]{(byte) aSlotid, (byte) aIsvolte};
        this.mLgeRil.UIMInternalRequestCmd(aCmd, data, this.mRilFalcontHandler.obtainMessage(15));
    }

    public void uiccSelectApplication(int aSerial, String aAid) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uiccSelectApplication enter");
        addRequestToken(16, aSerial);
        this.mLgeRil.uiccSelectApplication(aAid, this.mRilFalcontHandler.obtainMessage(16));
    }

    public void uiccDeactivateApplication(int aSerial, int aSessionId) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uiccDeactivateApplication enter");
        addRequestToken(17, aSerial);
        this.mLgeRil.uiccDeactivateApplication(aSessionId, this.mRilFalcontHandler.obtainMessage(17));
    }

    public void uiccApplication(int aSerial, int aSessionId, int aCommand, int aField, String aPath, int aP1, int aP2, int aP3, String aData, String aPin2) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uiccApplication enter");
        addRequestToken(18, aSerial);
        this.mLgeRil.uiccApplicationIO(aSessionId, aCommand, aField, aPath, aP1, aP2, aP3, aData, aPin2, this.mRilFalcontHandler.obtainMessage(18));
    }

    public void uiccAkaAuthenticate(int aSerial, int aSessionId, String aRand, String aAutn) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uiccAkaAuthenticate enter");
        addRequestToken(19, aSerial);
        this.mLgeRil.uiccAkaAuthenticate(aSessionId, IccUtils.hexStringToBytes(aRand), IccUtils.hexStringToBytes(aAutn), this.mRilFalcontHandler.obtainMessage(19));
    }

    public void uiccGbaAuthenticateBootstrap(int aSerial, int aSessionId, String aRand, String aAutn) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uiccGbaAuthenticateBootstrap enter");
        addRequestToken(20, aSerial);
        this.mLgeRil.uiccGbaAuthenticateBootstrap(aSessionId, IccUtils.hexStringToBytes(aRand), IccUtils.hexStringToBytes(aAutn), this.mRilFalcontHandler.obtainMessage(20));
    }

    public void uiccGbaAuthenticateNaf(int aSerial, int aSessionId, String aNafld) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uiccGbaAuthenticateNaf enter");
        addRequestToken(21, aSerial);
        this.mLgeRil.uiccGbaAuthenticateNaf(aSessionId, IccUtils.hexStringToBytes(aNafld), this.mRilFalcontHandler.obtainMessage(21));
    }

    public void uimPowerDownRequest(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: uimPowerDownRequest enter");
        addRequestToken(22, aSerial);
        this.mLgeRil.uimPowerDownRequest(this.mRilFalcontHandler.obtainMessage(22));
    }

    public void USIMSmartcardGetAtr(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: USIMSmartcardGetAtr enter");
        addRequestToken(23, aSerial);
        this.mLgeRil.getAtr(this.mRilFalcontHandler.obtainMessage(23));
    }

    public void setCdmaEriVersion(int aSerial, int aVersion) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setCdmaEriVersion enter");
        addRequestToken(24, aSerial);
        this.mLgeRil.setCdmaEriVersion(aVersion, this.mRilFalcontHandler.obtainMessage(24));
    }

    public void setCdmaFactoryReset(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setCdmaFactoryReset enter");
        addRequestToken(25, aSerial);
        this.mLgeRil.setCdmaFactoryReset(this.mRilFalcontHandler.obtainMessage(25));
    }

    public void getEhrpdInfoForIms(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getEhrpdInfoForIms enter");
        addRequestToken(26, aSerial);
        this.mLgeRil.getEhrpdInfoForIms(this.mRilFalcontHandler.obtainMessage(26));
    }

    public void getMipErrorCode(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getMipErrorCode enter");
        addRequestToken(27, aSerial);
        this.mLgeRil.getMipErrorCode(this.mRilFalcontHandler.obtainMessage(27));
    }

    public void cancelManualSearchingRequest(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: cancelManualSearchingRequest enter");
        addRequestToken(28, aSerial);
        this.mLgeRil.cancelManualSearchingRequest(this.mRilFalcontHandler.obtainMessage(28));
    }

    public void setPreviousNetworkSelectionModeManual(int aSerial, String aOperatorNumeric, String aOperatorRat) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setPreviousNetworkSelectionModeManual enter");
        addRequestToken(29, aSerial);
        this.mLgeRil.setPreviousNetworkSelectionModeManual(aOperatorNumeric, aOperatorRat, this.mRilFalcontHandler.obtainMessage(29));
    }

    public void setRmnetAutoconnect(int aSerial, int aParam) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setRmnetAutoconnect enter");
        addRequestToken(30, aSerial);
        this.mLgeRil.setRmnetAutoconnect(aParam, this.mRilFalcontHandler.obtainMessage(30));
    }

    public void getSearchStatus(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getSearchStatus enter");
        addRequestToken(31, aSerial);
        this.mLgeRil.getSearchStatus(this.mRilFalcontHandler.obtainMessage(31));
    }

    public void getEngineeringModeInfo(int aSerial, int aParam) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getEngineeringModeInfo enter");
        addRequestToken(32, aSerial);
        this.mLgeRil.getEngineeringModeInfo(aParam, this.mRilFalcontHandler.obtainMessage(32));
    }

    public void setCSGSelectionManual(int aSerial, int aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setCSGSelectionManual enter");
        addRequestToken(33, aSerial);
        this.mLgeRil.setCSGSelectionManual(aData, this.mRilFalcontHandler.obtainMessage(33));
    }

    public void getLteEmmErrorCode(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getLteEmmErrorCode enter");
        addRequestToken(34, aSerial);
        this.mLgeRil.getLteEmmErrorCode(this.mRilFalcontHandler.obtainMessage(34));
    }

    public void sendApnDisableFlag(int aSerial, int aProfileId, int aDisable) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: sendApnDisableFlag enter");
        addRequestToken(35, aSerial);
        this.mLgeRil.sendApnDisableFlag(aProfileId, aDisable == 1, this.mRilFalcontHandler.obtainMessage(35));
    }

    public void loadVolteE911ScanList(int aSerial, int aAirplaneModeState, int aImsRegiState) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: loadVolteE911ScanList enter");
        addRequestToken(36, aSerial);
        this.mLgeRil.loadVolteE911ScanList(aAirplaneModeState, aImsRegiState, this.mRilFalcontHandler.obtainMessage(36));
    }

    public void getVolteE911NetworkType(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getVolteE911NetworkType enter");
        addRequestToken(37, aSerial);
        this.mLgeRil.getVolteE911NetworkType(this.mRilFalcontHandler.obtainMessage(37));
    }

    public void exitVolteE911EmergencyMode(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: exitVolteE911EmergencyMode enter");
        addRequestToken(38, aSerial);
        this.mLgeRil.exitVolteE911EmergencyMode(this.mRilFalcontHandler.obtainMessage(38));
    }

    public void setImsRegistration(int aSerial, int aState) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setImsRegistration enter");
        addRequestToken(39, aSerial);
        this.mLgeRil.setImsRegistration(aState, this.mRilFalcontHandler.obtainMessage(39));
    }

    public void sendE911CallState(int aSerial, int aState) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: sendE911CallState enter");
        addRequestToken(40, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(40);
        this.mLgeRil.sendE911CallState(aState);
    }

    public void setImsRegistrationForHVoLTE(int aSerial, int aSystemMode, int aStateLength, int aType1, int aType2, int aRegistered1, int aRegistered2) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setImsRegistrationForHVoLTE enter");
        addRequestToken(41, aSerial);
        int[] type = new int[]{aType1, aType2};
        int[] registered = new int[]{aRegistered1, aRegistered2};
        this.mLgeRil.setImsRegistrationForHVoLTE(aSystemMode, aStateLength, type, registered, this.mRilFalcontHandler.obtainMessage(41));
    }

    public void setVoiceDomainPref(int aSerial, int aMode) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setVoiceDomainPref enter");
        addRequestToken(42, aSerial);
        this.mLgeRil.setVoiceDomainPref(aMode, this.mRilFalcontHandler.obtainMessage(42));
    }

    public void setVoLteCall(int aSerial, int aState) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setVoLteCall enter");
        addRequestToken(43, aSerial);
        this.mLgeRil.setVoLteCall(aState, this.mRilFalcontHandler.obtainMessage(43));
    }

    public void closeImsPdn(int aSerial, int aReason) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: closeImsPdn enter");
        addRequestToken(44, aSerial);
        this.mLgeRil.closeImsPdn(aReason, this.mRilFalcontHandler.obtainMessage(44));
    }

    public void getLteInfoForIms(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getLteInfoForIms enter");
        addRequestToken(45, aSerial);
        this.mLgeRil.getLteInfoForIms(this.mRilFalcontHandler.obtainMessage(45));
    }

    public void setSrvccCallContextTransfer(int aSerial, int mInstanceId, int mCallType, int mCallState, int mCallSubState, int mIsMptyCall, int mDirection, String mAddress, int mIsAlertingTypeValid, int mAlertingType, String mName, int mNamePresent, int mNumberPresent) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setSrvccCallContextTransfer enter");
        LGSrvccCallContext[] list = new LGSrvccCallContext[2];
        list[0] = new LGSrvccCallContext(mInstanceId, mCallType, mCallState, mCallSubState, mIsMptyCall == 1, mDirection, mAddress, mNumberPresent, mName, mNamePresent, mIsAlertingTypeValid, mAlertingType);
        this.mLgeRil.setSrvccCallContextTransfer(1, list);
    }

    public void sendImsCallState(int aSerial, int aState, int aType) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: sendImsCallState enter");
        addRequestToken(47, aSerial);
        this.mLgeRil.sendIMSCallState(aState, aType, this.mRilFalcontHandler.obtainMessage(47));
    }

    public void setRssiTestAntConf(int aSerial, int aAntType) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setRssiTestAntConf enter");
        addRequestToken(48, aSerial);
        this.mLgeRil.setRssiTestAntConf(aAntType, this.mRilFalcontHandler.obtainMessage(48));
    }

    public void getRssiTest(int aSerial, int aSysMode) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getRssiTest enter");
        addRequestToken(49, aSerial);
        this.mLgeRil.getRssiTest(this.mRilFalcontHandler.obtainMessage(49));
    }

    public void setQcril(int aSerial, int aSet) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOTEST: setQcril enter");
        addRequestToken(EVENT_FALCON_SET_QCRIL, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_SET_QCRIL);
        this.mLgeRil.setQcril(aSet);
    }

    public void setMiMoAntennaControlTest(int aSerial, int sys_mode, int mask) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setMiMoAntennaControlTest enter");
        addRequestToken(50, aSerial);
        this.mLgeRil.setMiMoAntennaControlTest(this.mRilFalcontHandler.obtainMessage(50), sys_mode, mask);
    }

    public void setModemInfo(int aSerial, int aParam, String data) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setModemInfo enter");
        addRequestToken(51, aSerial);
        this.mLgeRil.setModemInfo(aParam, data, this.mRilFalcontHandler.obtainMessage(51));
    }

    public void getModemInfo(int aSerial, int aParam, String data) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getModemInfo enter");
        addRequestToken(52, aSerial);
        this.mLgeRil.getModemInfo(aParam, data, this.mRilFalcontHandler.obtainMessage(52));
    }

    public void getGPRIItem(int aSerial, int aParam) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getGPRIItem enter");
        addRequestToken(53, aSerial);
        this.mLgeRil.getGPRIItem(aParam, this.mRilFalcontHandler.obtainMessage(53));
    }

    public void setGNOSInfo(int aSerial, LgeIntString aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setGNOSInfo enter");
        addRequestToken(87, aSerial);
        this.mLgeRil.setGNOSInfo(aData.num, aData.text, this.mRilFalcontHandler.obtainMessage(87));
    }

    public void setLteBandMode(int aSerial, int aBandMode) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setLteBandMode enter");
        addRequestToken(54, aSerial);
        this.mLgeRil.setLteBandMode((long) aBandMode, this.mRilFalcontHandler.obtainMessage(54));
    }

    public void setEmergency(int aSerial, int aState, int aMode) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setEmergency enter");
        addRequestToken(55, aSerial);
        this.mLgeRil.setEmergency(aState, aMode, this.mRilFalcontHandler.obtainMessage(55));
    }

    public void vssModemReset(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: vssModemReset enter");
        addRequestToken(56, aSerial);
        this.mLgeRil.vssModemReset(this.mRilFalcontHandler.obtainMessage(56));
    }

    public void getDefaultAPN(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getDefaultAPN enter");
        addRequestToken(57, aSerial);
        this.mLgeRil.getDefaultAPN(this.mRilFalcontHandler.obtainMessage(57));
    }

    public void getAdminAPN(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getAdminAPN enter");
        addRequestToken(58, aSerial);
        this.mLgeRil.getAdminAPN(this.mRilFalcontHandler.obtainMessage(58));
    }

    public void getTetheringAPN(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getTetheringAPN enter");
        addRequestToken(59, aSerial);
        this.mLgeRil.getTetheringAPN(this.mRilFalcontHandler.obtainMessage(59));
    }

    public void setScriptValue(int aSerial, int aOnOff) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setScriptValue enter");
        addRequestToken(60, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(60);
        this.mLgeRil.setScriptValue(aOnOff == 1);
    }

    public void mocaGetRFParameter(int aSerial, int aKindOfData, int aBufNum) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaGetRFParameter enter");
        addRequestToken(78, aSerial);
        this.mLgeRil.mocaGetRFParameter(aKindOfData, aBufNum, this.mRilFalcontHandler.obtainMessage(78));
    }

    public void mocaGetMisc(int aSerial, int aKindOfData, int aBufNum) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaGetMisc enter");
        addRequestToken(79, aSerial);
        this.mLgeRil.mocaGetMisc(aKindOfData, aBufNum, this.mRilFalcontHandler.obtainMessage(79));
    }

    public void mocaAlarmEvent(int aSerial, String aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaAlarmEvent enter");
        addRequestToken(80, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(80);
        this.mLgeRil.mocaAlarmEvent(aData.getBytes(), msg);
    }

    public void mocaSetLog(int aSerial, String aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaSetLog enter");
        addRequestToken(81, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(81);
        this.mLgeRil.mocaSetLog(aData.getBytes(), msg);
    }

    public void mocaGetData(int aSerial, int aBufNum) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaGetData enter");
        addRequestToken(82, aSerial);
        this.mLgeRil.mocaGetData(aBufNum, this.mRilFalcontHandler.obtainMessage(82));
    }

    public void mocaSetMem(int aSerial, int aPercent) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaSetMem enter");
        addRequestToken(EVENT_FALCON_MOCA_SET_MEM, aSerial);
        this.mLgeRil.mocaSetMem(aPercent, this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_MOCA_SET_MEM));
    }

    public void mocaAlarmEventReg(int aSerial, int aEvent) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: mocaAlarmEventReg enter");
        addRequestToken(EVENT_FALCON_MOCA_ALARM_EVENT_REG, aSerial);
        this.mLgeRil.mocaAlarmEventReg(aEvent, this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_MOCA_ALARM_EVENT_REG));
    }

    public void DMRequest(int aSerial, String aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: DMRequest enter");
        addRequestToken(EVENT_FALCON_DM_REQUEST, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_DM_REQUEST);
        this.mLgeRil.DMRequest(aData.getBytes(), msg);
    }

    public void setImsDataFlushEnabled(int aSerial, int aEnable) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setImsDataFlushEnabled enter");
        addRequestToken(61, aSerial);
        this.mLgeRil.setImsDataFlushEnabled(aEnable == 1, this.mRilFalcontHandler.obtainMessage(61));
    }

    public void NSRI_SetCaptureMode_requestProc(int aSerial, int aIndex, String aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: NSRI_SetCaptureMode_requestProc enter");
        addRequestToken(EVENT_FALCON_NSRI_CAPTUREMODE_COMMAND, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_NSRI_CAPTUREMODE_COMMAND);
        this.mLgeRil.NSRI_SetCaptureMode_requestProc(aIndex, aData.getBytes(), msg);
    }

    public void NSRI_requestProc(int aSerial, int aDataLen, String aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: NSRI_requestProc enter");
        addRequestToken(EVENT_FALCON_NSRI_COMMAND, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_NSRI_COMMAND);
        this.mLgeRil.NSRI_SetCaptureMode_requestProc(aDataLen, aData.getBytes(), msg);
    }

    public void NSRI_Oem_requestProc(int aSerial, int aIndex, String aData) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: NSRI_Oem_requestProc enter");
        addRequestToken(91, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(91);
        this.mLgeRil.NSRI_Oem_requestProc(aIndex, aData.getBytes(), msg);
    }

    public void setNSRICallInfoTransfer(int aSerial, int aCallState, int aUEType, String aPhoneNumber) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setNSRICallInfoTransfer enter");
        addRequestToken(92, aSerial);
        this.mLgeRil.setNSRICallInfoTransfer(aCallState, aUEType, aPhoneNumber, this.mRilFalcontHandler.obtainMessage(92));
    }

    public void iwlanSetRegisterCellularQualityReport(int aSerial, int aNumOfValues, int qualityRegister, int aType, ArrayList<Integer> aValues) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: iwlanSetRegisterCellularQualityReport enter");
        addRequestToken(62, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(62);
        int[] ret = new int[aNumOfValues];
        for (int i = 0; i < aNumOfValues; i++) {
            ret[i] = ((Integer) aValues.get(i)).intValue();
            Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: iwlanSetRegisterCellularQualityReport aValues" + ret[i]);
        }
        this.mLgeRil.iwlanSetRegisterCellularQualityReport(qualityRegister, aType, ret, msg);
    }

    public void iwlanSendImsPdnStatus(int aSerial, int aImsPdnStatus) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: iwlanSendImsPdnStatus enter");
        addRequestToken(63, aSerial);
        this.mLgeRil.iwlanSendImsPdnStatus(aImsPdnStatus, this.mRilFalcontHandler.obtainMessage(63));
    }

    public void setProximitySensorState(int aSerial, int aNear) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setProximitySensorState enter");
        addRequestToken(64, aSerial);
        Message msg = this.mRilFalcontHandler.obtainMessage(64);
        this.mLgeRil.setProximitySensorState(aNear == 1);
    }

    public void setImsRegistrationStatus(int aSerial, int aRegState, int aRegServices, int aDetailState, int aSystemMode, int aReason, int aSlotId) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setImsRegistrationStatus enter");
        addRequestToken(65, aSerial);
        this.mLgeRil.setImsRegistrationStatus(aRegState, aRegServices, aDetailState, aSystemMode, aReason, aSlotId, this.mRilFalcontHandler.obtainMessage(65));
    }

    public void setImsCallStatus(int aSerial, int aWholeCallState, int aIndividualCallIdentification, int aIndividualCallState, int aIndividualReason, int aIndividualCallType, int aSlotId, int aSystemMode) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setImsCallStatus enter");
        addRequestToken(66, aSerial);
        this.mLgeRil.setImsCallStatus(aWholeCallState, aIndividualCallIdentification, aIndividualCallState, aIndividualReason, aIndividualCallType, aSlotId, aSystemMode, this.mRilFalcontHandler.obtainMessage(66));
    }

    public void setScmMode(int aSerial, int aType, int aMode) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setScmMode enter");
        addRequestToken(EVENT_FALCON_SET_SCM_MODE, aSerial);
        this.mLgeRil.setScmMode(aType, aMode, this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_SET_SCM_MODE));
    }

    public void setImsStatus(int aSerial, int aType, int aImsState, int aReason, int aSlotId) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setImsStatus enter");
        addRequestToken(68, aSerial);
        this.mLgeRil.setImsStatus(aType, aImsState, aReason, aSlotId, this.mRilFalcontHandler.obtainMessage(68));
    }

    public void getIMSNetworkInfo(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getIMSNetworkInfo enter");
        addRequestToken(69, aSerial);
        this.mLgeRil.getIMSNetworkInfo(this.mRilFalcontHandler.obtainMessage(69));
    }

    public void lgeSetNetworkSelectionModeManual(int aSerial, String aOperatorNumeric, String aOperatorRat) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: lgeSetNetworkSelectionModeManual enter");
        addRequestToken(70, aSerial);
        this.mLgeRil.lgeSetNetworkSelectionModeManual(aOperatorNumeric, aOperatorRat, this.mRilFalcontHandler.obtainMessage(70));
    }

    public void lgeCdmaWriteSmsToRuim(int aSerial, int aStatus, String aPdu) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: lgeCdmaWriteSmsToRuim enter");
        addRequestToken(71, aSerial);
        this.mLgeRil.writeSmsToCsim(aStatus, IccUtils.hexStringToBytes(aPdu), this.mRilFalcontHandler.obtainMessage(71));
    }

    public void lgeGetSignalStrength(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: lgeGetSignalStrength enter");
        addRequestToken(72, aSerial);
        this.mLgeRil.getSignalStrength(this.mRilFalcontHandler.obtainMessage(72));
    }

    public void lgeGetCurrentCalls(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: lgeGetCurrentCalls enter");
        addRequestToken(74, aSerial);
        this.mLgeRil.getCurrentCalls(this.mRilFalcontHandler.obtainMessage(74));
    }

    public void getAvailableNetworks(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getAvailableNetworks enter");
        addRequestToken(75, aSerial);
        this.mLgeRil.getAvailableNetworks(this.mRilFalcontHandler.obtainMessage(75));
    }

    public void setupDataCallEx(int aSerial, int aRadioTechnology, int aIsRoaming, int aAllowRoaming, String aHandoverMode, String aV6addr, String aV4addr, int aNeedPcscf, int aIsEmergency) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setupDataCallEx enter");
        addRequestToken(76, aSerial);
        this.mLgeRil.setupDataCallEx(aRadioTechnology, new DataProfile(this.mApn1), aIsRoaming == 1, aAllowRoaming == 1, aHandoverMode, aV6addr, aV4addr, aNeedPcscf == 1, aIsEmergency == 1, this.mRilFalcontHandler.obtainMessage(76));
    }

    public void lgeGetIccCardStatus(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: lgeGetIccCardStatus enter");
        addRequestToken(73, aSerial);
        this.mLgeRil.getIccCardStatus(this.mRilFalcontHandler.obtainMessage(73));
    }

    public void getDataRegistrationState(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getDataRegistrationState enter");
        addRequestToken(77, aSerial);
        this.mLgeRil.getDataRegistrationState(this.mRilFalcontHandler.obtainMessage(77));
    }

    public void setPcasInfo(int aSerial) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setPcasInfo enter");
        addRequestToken(88, aSerial);
        this.mLgeRil.setPcasInfo(this.mRilFalcontHandler.obtainMessage(88));
    }

    public void setOtasnPdnState(int aSerial, int aOtasnPdnState) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: setOtasnPdnState enter");
        addRequestToken(EVENT_FALCON_SET_OTASN_PDN_STATE, aSerial);
        this.mLgeRil.setOtasnPdnState(aOtasnPdnState, this.mRilFalcontHandler.obtainMessage(EVENT_FALCON_SET_OTASN_PDN_STATE));
    }

    public void addRequestToken(int aRequest, int aToken) {
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: addRequestToken aRequest:" + aRequest);
        Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: addRequestToken aToken:" + aToken);
        this.mRequestTokenList.put(Integer.valueOf(aRequest), Integer.valueOf(aToken));
    }

    public int getRequestToken(int aReqeust) {
        if (this.mRequestTokenList == null) {
            return 0;
        }
        int token = 0;
        if (this.mRequestTokenList.containsKey(Integer.valueOf(aReqeust))) {
            token = ((Integer) this.mRequestTokenList.get(Integer.valueOf(aReqeust))).intValue();
            Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: getRequestToken token:" + token);
        }
        return token;
    }

    public int removeRequestToken(int aRequest) {
        if (this.mRequestTokenList == null) {
            return 0;
        }
        int token = 0;
        if (this.mRequestTokenList.containsKey(Integer.valueOf(aRequest))) {
            token = ((Integer) this.mRequestTokenList.remove(Integer.valueOf(aRequest))).intValue();
            Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOFALCON: removeRequestToken token:" + token);
        }
        return token;
    }

    public void removeAll() {
        if (this.mRequestTokenList != null) {
            Rlog.d(TESTRILJ_LOG_TAG, "LGRADIOTEST: removeAll");
            this.mRequestTokenList.clear();
        }
    }

    public String bytetoString(byte[] data) {
        String result = "";
        if (data == null || data.length == 0) {
            return "";
        }
        for (int input : data) {
            result = result + (("" + "0123456789abcdef".charAt((input >> 4) & 15)) + "0123456789abcdef".charAt(input & 15));
        }
        return result;
    }
}
