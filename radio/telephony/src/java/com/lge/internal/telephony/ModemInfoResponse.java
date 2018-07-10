package com.lge.internal.telephony;

import android.os.Parcel;
import android.telephony.Rlog;
import com.lge.internal.telephony.ModemItem.C_DATA;
import com.lge.internal.telephony.ModemItem.C_DBSCR;
import com.lge.internal.telephony.ModemItem.C_MLT;
import com.lge.internal.telephony.ModemItem.C_NV;
import com.lge.internal.telephony.ModemItem.C_PH;
import com.lge.internal.telephony.ModemItem.C_RP;
import com.lge.internal.telephony.ModemItem.C_SS;
import com.lge.internal.telephony.ModemItem.W_BASE;
import java.util.StringTokenizer;

public final class ModemInfoResponse {
    private static final String TAG = ModemInfoResponse.class.getSimpleName();
    static String addrString;
    static int i;
    static String[] parts;
    static StringTokenizer st;

    public static Object createFromParcel(Parcel p) {
        int id = p.readInt();
        switch (16711680 & id) {
            case 0:
                return getCdmaCallInfo(id, p);
            case 65536:
                return getCdmaSsInfo(id, p);
            case 131072:
                return getCdmaPhInfo(id, p);
            case 196608:
                return getCdmaNvInfo(id, p);
            case 262144:
                return getDbgScrInfo(id, p);
            case 393216:
                return getWBaseInfo(id, p);
            case 720896:
                return getDsInfo(id, p);
            case 786432:
                return getModemMltInfo(id, p);
            case 851968:
                return getModemInfoUEMode(id, p);
            default:
                throw new RuntimeException("RIL_REQUEST_GET_MODEM_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static String idToString(int id) {
        switch (id) {
            case 0:
                return "RIL_C_CALL_INFO";
            case 65536:
                return "RIL_C_SS_INFO";
            case 131072:
                return "RIL_C_PH_INFO";
            case 196608:
                return "RIL_C_NV_INFO";
            case 262144:
                return "RIL_C_DBGSCR_INFO";
            default:
                return "<unknown record>";
        }
    }

    public static Object getCdmaCallInfo(int id, Parcel p) {
        switch (id) {
            case 0:
            case 1:
                return p.readString();
            case 4:
            case 5:
            case 7:
                return new int[]{Integer.parseInt(p.readString())};
            default:
                throw new RuntimeException("RIL_C_CALL_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getCdmaSsInfo(int id, Parcel p) {
        switch (id) {
            case 65536:
            case C_SS.SYS_IS_JCDMA_NET /*65537*/:
            case C_SS.SYS_IS_DOMESTIC_NET /*65538*/:
            case C_SS.SYS_LGT_ROAMINGAREA /*65539*/:
            case C_SS.SYS_CURRENT_SID /*65540*/:
            case C_SS.SYS_CURRENT_NID /*65541*/:
            case C_SS.SYS_VT_PHYSI_LINK_UP_GOING /*65551*/:
                return new int[]{Integer.parseInt(p.readString())};
            case C_SS.SYS_LGT_CURRENTCH /*65542*/:
            case C_SS.SYS_LGT_BESTPN /*65543*/:
            case C_SS.SYS_MISC_SYSTEM_INFO /*65558*/:
            case C_SS.SYS_TOTAL_RSSIBAR /*65559*/:
                return p.readString();
            case C_SS.SYS_LGT_EVDO_AVAILABLE_CHECK /*65548*/:
            case C_SS.SYS_IMS_NEED_ITEM /*65738*/:
                addrString = p.readString();
                parts = addrString.split(",");
                Object response = new int[parts.length];
                i = 0;
                while (i < parts.length) {
                    ((int[]) response)[i] = Integer.parseInt(parts[i]);
                    i++;
                }
                return response;
            default:
                throw new RuntimeException("RIL_C_SS_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getCdmaPhInfo(int id, Parcel p) {
        switch (id) {
            case 131072:
            case C_PH.SYS_MDN /*131084*/:
            case C_PH.SYS_MIN /*131085*/:
            case C_PH.SYS_IRMNUMBER /*131086*/:
            case C_PH.SYS_CDMA_MDN /*131087*/:
            case C_PH.SYS_CDMA_MSIN /*131088*/:
            case C_PH.SYS_CDMA_IMSI /*131089*/:
            case C_PH.SYS_FSC_CODE /*131104*/:
            case C_PH.SYS_DDMS_FILTER /*131124*/:
            case C_PH.SYS_NV_PHONE_SERIAL /*131125*/:
            case C_PH.SYS_NV_ALL_AUTO_TEST_RESULT /*131144*/:
            case C_PH.SYS_NV_ANOTHER_PHONE_NUMBER /*131145*/:
                return p.readString();
            case C_PH.SYS_STARTDAY /*131074*/:
            case C_PH.SYS_WPBX_INFO /*131114*/:
            case C_PH.SYS_PRL_ID /*131115*/:
            case C_PH.SYS_MISC_LGT_EI_INFO /*131123*/:
                addrString = p.readString();
                parts = addrString.split(",");
                Object response = new int[parts.length];
                i = 0;
                while (i < parts.length) {
                    ((int[]) response)[i] = Integer.parseInt(parts[i]);
                    i++;
                }
                return response;
            case C_PH.SYS_LGT_PRST_MODE_PREF /*131075*/:
            case C_PH.SYS_SS_RESET /*131076*/:
            case C_PH.SYS_HYBRID_PREF /*131077*/:
            case C_PH.SYS_DDTM /*131078*/:
            case C_PH.SYS_TAS /*131079*/:
            case C_PH.SYS_HDR_CH /*131080*/:
            case C_PH.SYS_HDR_FORCE_REL0_SET /*131081*/:
            case C_PH.SYS_PHONE_MODEL /*131082*/:
            case C_PH.SYS_SLOT_CYCLE_INDEX /*131083*/:
            case C_PH.SYS_CDMA_MCC /*131090*/:
            case C_PH.SYS_CDMA_MNC /*131091*/:
            case C_PH.SYS_SID1 /*131092*/:
            case C_PH.SYS_SID2 /*131093*/:
            case C_PH.SYS_SID3 /*131094*/:
            case C_PH.SYS_SID4 /*131095*/:
            case C_PH.SYS_NID1 /*131096*/:
            case C_PH.SYS_NID2 /*131097*/:
            case C_PH.SYS_NID3 /*131098*/:
            case C_PH.SYS_NID4 /*131099*/:
            case C_PH.SYS_PRIMARY_CH_A /*131100*/:
            case C_PH.SYS_PRIMARY_CH_B /*131101*/:
            case C_PH.SYS_SECONDARY_CH_A /*131102*/:
            case C_PH.SYS_SECONDARY_CH_B /*131103*/:
            case C_PH.SYS_NAM /*131105*/:
            case C_PH.SYS_AUTO_NAM /*131106*/:
            case C_PH.SYS_TEST_CALL /*131107*/:
            case C_PH.SYS_VOCODER_ID /*131108*/:
            case C_PH.SYS_TEST_MODE /*131109*/:
            case C_PH.SYS_QPCH /*131110*/:
            case C_PH.SYS_PRL_ENABLE /*131111*/:
            case C_PH.SYS_LGT_AUTO_PRL_I /*131112*/:
            case C_PH.SYS_WPBX_MATCHED /*131113*/:
            case C_PH.SYS_VALIDATE_A_KEY /*131116*/:
            case C_PH.SYS_A_KEY_VALUE /*131117*/:
            case C_PH.SYS_MISC_TESTMODE_LPM_TEST /*131119*/:
            case C_PH.SYS_CDMA_PREF_MODE /*131127*/:
            case C_PH.SYS_IS_TEST_SCR /*131134*/:
            case C_PH.SYS_HDR_SECOND_CH /*131141*/:
            case C_PH.SYS_HDR_THIRD_CH /*131142*/:
            case C_PH.SYS_NV_MANUAL_TEST_MODE /*131143*/:
                return new int[]{Integer.parseInt(p.readString())};
            case C_PH.SYS_NV_MANUFACTURE_SN /*131162*/:
            case C_PH.SYS_NV_MANUFACTURE_DATE /*131163*/:
                return p.readString();
            case C_PH.SYS_NV_ACC_CALIBRATION_RESULT /*131164*/:
                return p.readString();
            default:
                throw new RuntimeException("RIL_C_PH_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getCdmaNvInfo(int id, Parcel p) {
        switch (id) {
            case 0:
                return new Object();
            case 1:
                return new Object();
            case 2:
                return new Object();
            case 3:
                return new Object();
            case C_NV.SYS_NV_SOS_NUMBER_I /*199951*/:
                return p.readString();
            case C_NV.SYS_NV_PRODUCTION_TEST_MODE_I /*246628*/:
                return p.readString();
            default:
                throw new RuntimeException("RIL_C_NV_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getDbgScrInfo(int id, Parcel p) {
        switch (id) {
            case 262144:
            case C_DBSCR.SYS_DEBSCR_CDMA /*262145*/:
            case C_DBSCR.SYS_DEBSCR_REVA /*262146*/:
            case C_DBSCR.SYS_DEBSCR_CDMARF /*262147*/:
            case C_DBSCR.SYS_CAL_DISPLAY /*262149*/:
            case C_DBSCR.SYS_DEBSCR_USER /*262150*/:
            case C_DBSCR.SYS_DEBSCR_WCDMA /*262151*/:
            case C_DBSCR.SYS_DEBSCR_GSM /*262152*/:
            case C_DBSCR.SYS_DEBSCR_ENG_WCDMA /*262153*/:
            case C_DBSCR.SYS_DEBSCR_ENG_GSM /*262154*/:
            case C_DBSCR.SYS_DEBSCR_REJ_CAUSE /*262155*/:
            case C_DBSCR.SYS_DEBSCR_LTE /*262156*/:
            case C_DBSCR.SYS_DEBSCR_QSPIDER /*262157*/:
            case C_DBSCR.SYS_DEBSCR_KNIGHT /*262158*/:
            case C_DBSCR.SYS_DEBSCR_TDSCDMA /*262160*/:
                return p.readString();
            case C_DBSCR.SYS_RF_CAL_BACKUP_INFO /*262148*/:
                addrString = p.readString();
                parts = addrString.split(",");
                Object response = new int[parts.length];
                i = 0;
                while (i < parts.length) {
                    ((int[]) response)[i] = Integer.parseInt(parts[i]);
                    i++;
                }
                return response;
            case C_DBSCR.SYS_MOBILE_QUALITY_INFO /*262159*/:
                return p.readString();
            default:
                throw new RuntimeException("RIL_C_DBGSCR_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getDsInfo(int id, Parcel p) {
        switch (id) {
            case 720896:
            case C_DATA.SYS_DS_LGT_SIP_REGI /*720897*/:
            case C_DATA.SYS_DS_LGT_VT_IS_ACTIVE /*720898*/:
            case C_DATA.SYS_DS_LGT_DORMANT /*720899*/:
            case C_DATA.SYS_DS_LGT_DATA_MODE /*720900*/:
            case C_DATA.SYS_DS_LGT_DUN_ENABLE /*720901*/:
            case C_DATA.SYS_DS_LGT_PHY_LINK_DOWN /*720902*/:
            case C_DATA.SYS_DS_LGT_PHY_LINK_UP /*720903*/:
            case C_DATA.SYS_DS_LGT_PKT_DATA_SESS_STATE /*720904*/:
            case C_DATA.SYS_DS_BRING_DOWN_DUN /*720905*/:
            case C_DATA.SYS_DS_LGT_IM_IS_ACTIVE /*720906*/:
            case C_DATA.SYS_DS_MMS_UPLOAD_SPEED /*720907*/:
            case C_DATA.SYS_DS_RSCH_SET /*720908*/:
            case C_DATA.SYS_DS_EVDO_QOS_REQUEST /*720909*/:
                return new int[]{Integer.parseInt(p.readString())};
            case C_DATA.SYS_DS_LGT_PPP_REALM_ROAMING /*720911*/:
                return p.readString();
            default:
                throw new RuntimeException("RIL_C_DBGSCR_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getWBaseInfo(int id, Parcel p) {
        switch (id) {
            case 393216:
            case W_BASE.LGE_MODEM_INFO_IMEI /*393217*/:
            case W_BASE.LGE_MODEM_INFO_GCF_TEST_MODE /*393218*/:
            case W_BASE.LGE_MODEM_INFO_HSDPA_CATEGORY /*393219*/:
            case W_BASE.LGE_MODEM_INFO_HSUPA_CATEGORY /*393220*/:
            case W_BASE.LGE_MODEM_INFO_WCDMA_CHANNEL /*393221*/:
            case W_BASE.LGE_MODEM_INFO_WCDMA_USER_CHANNEL /*393222*/:
            case W_BASE.LGE_MODEM_INFO_INTEGRITY_MODE /*393223*/:
            case W_BASE.LGE_MODEM_INFO_CIPHERING_MODE /*393224*/:
            case W_BASE.LGE_MODEM_INFO_RAT_BAND /*393225*/:
            case W_BASE.LGE_MODEM_INFO_RRC_VERSION /*393226*/:
            case W_BASE.LGE_MODEM_INFO_SERVICE_DOMAIN /*393227*/:
            case W_BASE.LGE_MODEM_INFO_SERIAL_NUMBER /*393230*/:
            case W_BASE.LGE_MODEM_INFO_119ECC_CATEGORY /*393231*/:
            case W_BASE.LGE_MODEM_INFO_SKT_USIM_DOWNLOAD /*393232*/:
            case W_BASE.LGE_MODEM_INFO_OTA_OPEN_MODE /*393233*/:
            case W_BASE.LGE_MODEM_INFO_CAMPED_MCC /*393234*/:
            case W_BASE.LGE_MODEM_INFO_SMS_MO_DOMAIN /*393235*/:
            case W_BASE.LGE_MODEM_INFO_WCDMA_CHANNEL_FIX /*393236*/:
            case W_BASE.LGE_MODEM_INFO_AUTO_CALL_ANSWER /*393237*/:
            case W_BASE.LGE_MODEM_INFO_SET_COPS /*393238*/:
            case W_BASE.LGE_MODEM_INFO_REJECT /*393239*/:
            case W_BASE.LGE_MODEM_INFO_FAKE_MODE /*393241*/:
            case W_BASE.LGE_MODEM_INFO_SMS_MO_DOMAIN_ROAMING /*393247*/:
            case W_BASE.LGE_MODEM_INFO_CS_PROTECTION /*393252*/:
            case W_BASE.LGE_MODEM_INFO_ISR_SETTING /*393255*/:
            case W_BASE.LGE_MODEM_INFO_LTE_EARFCN_SETTING /*393259*/:
            case W_BASE.LGE_MODEM_INFO_LTE_EARFCN_USER_SETTING /*393260*/:
            case W_BASE.LGE_MODEM_INFO_SET_UIM_FIRST_INSTR_CLASS /*393262*/:
            case W_BASE.LGE_MODEM_INFO_GET_UIM_FIRST_INSTR_CLASS /*393263*/:
            case W_BASE.LGE_MODEM_INFO_LOG2ADB /*393265*/:
            case W_BASE.LGE_MODEM_INFO_BAND_SELECTION /*393268*/:
            case W_BASE.LGE_MODEM_INFO_AMR_WB /*393269*/:
            case W_BASE.LGE_MODEM_INFO_CA_SETTING /*393270*/:
            case W_BASE.LGE_MODEM_INFO_LTE_3GPP_REL_VER /*393271*/:
            case W_BASE.LGE_MODEM_INFO_3xCA_SETTING /*393274*/:
            case W_BASE.LGE_MODEM_INFO_QSPIDER_NETWORK_BAND /*393275*/:
            case W_BASE.LGE_MODEM_INFO_QSPIDER_DEBUG_SCREEN /*393276*/:
            case W_BASE.LGE_MODEM_INFO_IS_CALL_STATUS_SECURE_CALL /*393277*/:
            case W_BASE.LGE_MODEM_INFO_IMS_RF_QUALITY /*393278*/:
            case W_BASE.LGE_MODEM_INFO_DL_256QAM_UL_64QAM_SETTING /*393279*/:
            case W_BASE.LGE_MODEM_INFO_MC_PUSCH_SETTING /*393281*/:
            case W_BASE.LGE_MODEM_INFO_SCC_LAA_SETTING /*393282*/:
            case W_BASE.LGE_MODEM_INFO_ERR_FATAL /*450221*/:
                return p.readString();
            case W_BASE.LGE_MODEM_INFO_OTA_LOG /*393228*/:
            case W_BASE.LGE_MODEM_INFO_START_DAY /*393229*/:
            case W_BASE.LGE_MODEM_INFO_SERVICE_STATUS /*393244*/:
            case W_BASE.LGE_MODEM_INFO_TX_RX /*393256*/:
                Rlog.d(TAG, "id : " + id);
                return convert_String_To_IntArray(p.readString(), "!");
            case W_BASE.LGE_MODEM_INFO_LTE_MULTI_BAND_TEST_MODE /*393272*/:
            case W_BASE.LGE_MODEM_INFO_GET_KR_HIDDEN_MENU_LIST /*393284*/:
            case W_BASE.LGE_MODEM_INFO_RP_SSR /*393285*/:
                return p.readString();
            case W_BASE.LGE_MODEM_INFO_GET_RRC_STATE /*393280*/:
                Object response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getWBaseInfo LGE_MODEM_INFO_GET_RRC_STATE :" + response);
                return response;
            default:
                throw new RuntimeException("RIL_C_DBGSCR_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    public static Object getModemMltInfo(int id, Parcel p) {
        switch (id) {
            case C_MLT.LGE_MODEM_MLT_INFO_RADIO_SST /*786433*/:
            case C_MLT.LGE_MODEM_MLT_INFO_RADIO_CALL /*786434*/:
                Object result = new String[]{p.readString()};
                Object response = result;
                return result;
            default:
                throw new RuntimeException("C_MLT_INFO: unsupported record. Got " + idToString(id) + " ");
        }
    }

    private static Object convert_String_To_IntArray(Object object, String expr) {
        Rlog.d(TAG, "object : " + object);
        if (object == null) {
            return null;
        }
        String[] parts = ((String) object).split(expr);
        int[] retResponse = new int[parts.length];
        i = 0;
        while (i < parts.length) {
            retResponse[i] = Integer.parseInt(parts[i]);
            i++;
        }
        return retResponse;
    }

    public String toString() {
        return super.toString();
    }

    private static Object getModemInfoUEMode(int id, Parcel p) {
        Object response;
        switch (id) {
            case 851968:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfoUEMode :" + response);
                return response;
            case C_RP.LGE_MODEM_RP_AMRWB_CHECK /*852028*/:
                response = p.readString();
                Rlog.d(TAG, "AMRWB Check msg " + response);
                return response;
            case C_RP.LGE_MODEM_RP_MODEM_PROPERTY /*852033*/:
                response = p.readString();
                Rlog.d(TAG, "getModemInfoUEMode : Modem Property msg " + response);
                return response;
            case C_RP.LGE_MODEM_PROTOCOL_INTERNAL_LOGGING /*852041*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfoInternalLogging :" + response);
                return response;
            case C_RP.LGE_MODEM_PROTOCOL_PATCH_OPTION /*852043*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfoProtocolPatchOption :" + response);
                return response;
            case C_RP.LGE_MODEM_PROTOCOL_TEST_OPTION /*852044*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfoProtocolTestOption :" + response);
                return response;
            case C_RP.LGE_MODEM_PROTOCOL_TEST_CONDITION /*852045*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfoProtocolTestCondition :" + response);
                return response;
            case C_RP.LGE_MODEM_PROTOCOL_FAKE_LLF_SETTING /*852046*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfoFakeLLF :" + response);
                return response;
            case C_RP.LGE_MODEM_RP_GONS_GET_HISTORY_DB_LIST /*852050*/:
                response = p.readString();
                Rlog.d(TAG, "GONSgetHistoryDBList :" + response);
                return response;
            case C_RP.LGE_MODEM_RP_GONS_GET_GONS_VER /*852063*/:
            case C_RP.LGE_MODEM_RP_GONS_GET_BO_VER /*852064*/:
            case C_RP.LGE_MODEM_RP_GONS_GET_RP_VER /*852065*/:
                response = p.readString();
                Rlog.d(TAG, "GONS get xml version :" + response);
                return response;
            case C_RP.LGE_MODEM_RP_ECC_PRIORITY /*852066*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getModemInfo ECC priority :" + response);
                return response;
            case C_RP.LGE_MODEM_RP_MCFG_GET_VAL /*852072*/:
                response = p.readString();
                Rlog.d(TAG, "getModemInfo rp mcfg val : " + response);
                return response;
            case C_RP.LGE_MODEM_PROTOCOL_PCAS_DEBUGGING_SETTING /*852076*/:
                response = new int[]{Integer.parseInt(p.readString())};
                Rlog.d(TAG, "getPCASDebuggingSetting :" + response);
                return response;
            default:
                throw new RuntimeException("getModemInfoUEMode: unsupported record. Got " + idToString(id) + " ");
        }
    }
}
