package com.android.internal.telephony.lgeautoprofiling;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemProperties;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
//import android.telephony.TelephonyManagerEx;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.lge.lgdata.Country;
import java.util.HashMap;

public class LgeSimInfo implements LgeAutoProfilingConstants {
    private static final boolean DBG = true;
    private static final boolean EDBG = true;
    private static final HashMap<String, Integer> SIMLOCK_MCC_TABLE = new HashMap<String, Integer>() {
        {
            put("44", Integer.valueOf(234));
            put("353", Integer.valueOf(272));
            put("65", Integer.valueOf(525));
            put("852", Integer.valueOf(454));
            put("853", Integer.valueOf(455));
            put("86", Integer.valueOf(MetricsEvent.ACTION_DELETION_SELECTION_PHOTOS));
            put("886", Integer.valueOf(466));
        }
    };
    private static final String TAG = "LgeSimInfo";
    private static final boolean VDBG = true;
    private static final HashMap<String, String> VDF_MCC_MNC_TABLE = new HashMap<String, String>() {
        {
            put("202", "05");
            put("204", "04");
            put("208", "10");
            put("214", "01");
            put("216", "70");
            put("219", "10");
            put("222", "10");
            put("226", "01");
            put("230", "03");
            put("234", "15");
            put("262", "02");
            put("268", "01");
            put("272", "01");
            put("274", "02");
            put("280", "01");
        }
    };
    private String mGid;
    private String mImsi;
    private String mMcc;
    private String mMnc;
    private int mPhoneId;
    private String mSpn;

    public LgeSimInfo(String mcc, String mnc, String gid, String spn, String imsi, int phoneId) {
        this.mMcc = mcc;
        this.mMnc = mnc;
        this.mGid = gid;
        this.mSpn = spn;
        this.mImsi = imsi;
        this.mPhoneId = phoneId;
    }

    static LgeSimInfo getSimInfo(Context mContext) {
        return getSimInfo(mContext, getDefaultPhoneId());
    }

    static LgeSimInfo getSimInfo(Context mContext, int phoneId) {
        String str = null;
        String mnc = null;
        String gid = null;
        String imsi = "";
        int simState = 0;
        int subId = getSubIdUsingPhoneId(phoneId);
        int slotId = SubscriptionManager.getSlotIndex(subId);
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService("phone");
        if (tm != null) {
            gid = tm.getGroupIdLevel1(subId);
        }
        String numeric = TelephonyManager.getDefault().getSimOperatorNumericForPhone(phoneId);
        String spn = TelephonyManager.getDefault().getSimOperatorNameForPhone(phoneId);
/*
        if (!(TelephonyManager.getDefault() == null || TelephonyManagerEx.getDefault() == null)) {
            simState = TelephonyManager.getDefault().getSimState(slotId);
            imsi = numeric + TelephonyManagerEx.getDefault().getMSIN(subId);
            Log.d(TAG, "TelephonyManagerEx : " + TelephonyManagerEx.getDefault() + " TelephonyManager : " + TelephonyManager.getDefault() + " getIMSI: " + LgeAutoProfiling.privateLogHandler(imsi, 256) + " getGroupIdLevel1: " + LgeAutoProfiling.privateLogHandler(gid, 256));
        }
*/
        if (!(TelephonyManager.getDefault() == null)) {
            simState = TelephonyManager.getDefault().getSimState(slotId);
            Log.d(TAG, "TelephonyManager : " + TelephonyManager.getDefault() + " getIMSI: " + LgeAutoProfiling.privateLogHandler(imsi, 256) + " getGroupIdLevel1: " + LgeAutoProfiling.privateLogHandler(gid, 256));
        }
        if (2 == simState || 3 == simState || TextUtils.isEmpty(numeric) || numeric.length() < 5) {
            Log.d(TAG, "[getSimInfo] numeric is invalid, numeric : " + LgeAutoProfiling.privateLogHandler(numeric, 256) + ", subId : " + subId + ", phoneId : " + phoneId + ", slotId : " + slotId);
        } else {
            str = numeric.substring(0, 3);
            mnc = numeric.substring(3);
        }
        if (LgeAutoProfiling.checkOperatorCountries("VDF", Country.EU, "COM") && !TextUtils.isEmpty(str) && SystemProperties.getBoolean("ro.build.regional", false)) {
            String[] vdfMccmnc = getMCCMNC_VdfGlobalProfiling(str, mnc);
            str = vdfMccmnc[0];
            mnc = vdfMccmnc[1];
        }
        Log.v(TAG, "[getSimInfo] *** SIM Info, MCC : " + LgeAutoProfiling.privateLogHandler(str, 256) + ", MNC : " + LgeAutoProfiling.privateLogHandler(mnc, 256) + ", subId : " + subId + ", phoneId : " + phoneId + ", slotId : " + slotId + ", IMSI : " + LgeAutoProfiling.privateLogHandler(imsi, 256));
        return new LgeSimInfo(str, mnc, gid, spn, imsi, phoneId);
    }

    private static String[] getMCCMNC_VdfGlobalProfiling(String mcc, String mnc) {
        Log.v(TAG, "[getSimInfo] VDF Regional Feature");
        String mccList = SystemProperties.get("persist.radio.mcc-list");
        if (!mccList.isEmpty()) {
            Boolean regionalVersion = Boolean.valueOf(mccList.contains(","));
            Log.v(TAG, "[getSimInfo] mccList exists, mccList : " + mccList + ", regionalVersion : " + regionalVersion);
            if (!regionalVersion.booleanValue()) {
                mcc = mccList;
                mnc = (String) VDF_MCC_MNC_TABLE.get(mccList);
                if (mnc == null) {
                    Log.e(TAG, "[getSimInfo] error.. cannot find matched mnc");
                    mcc = "";
                    mnc = "";
                }
            } else if (!mccList.contains(mcc)) {
                Log.e(TAG, "[getSimInfo] MCC from SIM doesn't exist in the list");
                mcc = "";
                mnc = "";
            }
        }
        return new String[]{mcc, mnc};
    }

    public static String getSimChangedInfo(int phoneId) {
        String simChanged = "";
        String iccidStatusprop = "persist.radio.iccid-changed";
        if (phoneId != 0) {
            iccidStatusprop = iccidStatusprop + (phoneId + 1);
        }
        return SystemProperties.get(iccidStatusprop, "F");
    }

    public String toString() {
        return "SimInfo - MCC : " + this.mMcc + ", MNC : " + this.mMnc + ", GID : " + this.mGid + ", SPN : " + this.mSpn + ", IMSI : " + LgeAutoProfiling.privateLogHandler(this.mImsi, 256);
    }

    public boolean equals(LgeSimInfo simInfo) {
        if (this == simInfo) {
            Log.d(TAG, "[equals] return true");
            return true;
        } else if (simInfo == null) {
            Log.d(TAG, "[equals] return false");
            return false;
        } else if (!TextUtils.isEmpty(this.mMcc) && !TextUtils.isEmpty(this.mMnc) && !TextUtils.isEmpty(this.mImsi) && TextUtils.equals(this.mMcc, simInfo.getMcc()) && TextUtils.equals(this.mMnc, simInfo.getMnc()) && TextUtils.equals(this.mGid, simInfo.getGid()) && TextUtils.equals(this.mSpn, simInfo.getSpn()) && TextUtils.equals(this.mImsi, simInfo.getImsi())) {
            Log.d(TAG, "[equals] return true");
            return true;
        } else {
            Log.d(TAG, "[equals] return false");
            return false;
        }
    }

    public boolean isNull() {
        return TextUtils.isEmpty(this.mMcc) ? TextUtils.isEmpty(this.mMnc) : false;
    }

    public static LgeSimInfo createFromPreference(Context context, int phoneId) {
        SharedPreferences preference = context.getSharedPreferences(LgeAutoProfilingConstants.PREF_NAME_SIM_INFO + phoneId, 0);
        return new LgeSimInfo(preference.getString("mcc", null), preference.getString("mnc", null), preference.getString("gid", null), preference.getString("spn", null), preference.getString("imsi", null), phoneId);
    }

    public static void writeToPreference(Context context, LgeSimInfo simInfo, int phoneId) {
        Editor editor = context.getSharedPreferences(LgeAutoProfilingConstants.PREF_NAME_SIM_INFO + phoneId, 0).edit();
        editor.clear();
        editor.putString("mcc", simInfo.getMcc());
        editor.putString("mnc", simInfo.getMnc());
        editor.putString("gid", simInfo.getGid());
        editor.putString("spn", simInfo.getSpn());
        editor.putString("imsi", simInfo.getImsi());
        editor.apply();
    }

    public void setMcc(String mcc) {
        this.mMcc = mcc;
    }

    public String getMcc() {
        return this.mMcc;
    }

    public void setMnc(String mnc) {
        this.mMnc = mnc;
    }

    public String getMnc() {
        return this.mMnc;
    }

    public void setGid(String gid) {
        this.mGid = gid;
    }

    public String getGid() {
        return this.mGid;
    }

    public void setSpn(String spn) {
        this.mSpn = spn;
    }

    public String getSpn() {
        return this.mSpn;
    }

    public void setImsi(String imsi) {
        this.mImsi = imsi;
    }

    public String getImsi() {
        return this.mImsi;
    }

    public int getPhoneId() {
        return this.mPhoneId;
    }

    static int getDefaultPhoneId() {
        return getPhoneId(SubscriptionManager.getDefaultSubscriptionId());
    }

    static int getPhoneId(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        if (phoneId < 0 || phoneId >= TelephonyManager.getDefault().getPhoneCount()) {
            return 0;
        }
        return phoneId;
    }

    static int getSubIdUsingPhoneId(int phoneId) {
        int[] subIds = SubscriptionManager.getSubId(phoneId);
        if (subIds == null || subIds.length <= 0) {
            return -1;
        }
        return subIds[0];
    }

    static int getMccFromIccid(Context context, int slotId) {
        SubscriptionInfo subInfo = SubscriptionManager.from(context).getActiveSubscriptionInfoForSimSlotIndex(slotId);
        if (subInfo == null) {
            return 0;
        }
        String iccId = subInfo.getIccId();
        if (TextUtils.isEmpty(iccId) || iccId.length() <= 5) {
            return 0;
        }
        String countryCallingCode = iccId.substring(2, 5);
        if (!SIMLOCK_MCC_TABLE.containsKey(countryCallingCode)) {
            countryCallingCode = iccId.substring(2, 4);
        }
        int mcc = ((Integer) SIMLOCK_MCC_TABLE.getOrDefault(countryCallingCode, Integer.valueOf(0))).intValue();
        Log.d(TAG, "getMccFromIccid() mcc = " + mcc + ", iccId = " + iccId + ", countryCallingCode = " + countryCallingCode);
        return mcc;
    }
}
