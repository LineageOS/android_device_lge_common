package com.android.internal.telephony.lgeautoprofiling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.internal.telephony.IccCardConstants;
import com.android.internal.telephony.lgeautoprofiling.LgeProfileParser.NameValueProfile;
import com.lge.internal.telephony.LGTelephonyIntents;
import com.lge.lgdata.Country;
import java.util.HashMap;
import java.util.Map.Entry;

public class LgeProfile implements LgeAutoProfilingConstants {
    private static final boolean DBG = true;
    private static final boolean EDBG = true;
    private static final boolean VDBG = true;
    private static LgeProfile instance;
    private Context mContext;
    private boolean mCotaUpdated;
    private boolean mForceResetXml = false;
    private boolean mIsMTKchip = "mtk".equalsIgnoreCase(SystemProperties.get("ro.lge.chip.vendor"));
    private HashMap<Integer, String> mLastSimState;
    private HashMap<Integer, HashMap<String, String>> mProfiles;

    private LgeProfile(Context context) {
        this.mContext = context;
        this.mProfiles = new HashMap();
        this.mLastSimState = new HashMap();
    }

    public static LgeProfile getInstance(Context context) {
        if (instance == null) {
            instance = new LgeProfile(context);
        } else {
            instance.setContext(context);
        }
        return instance;
    }

    private void setContext(Context context) {
        if (this.mContext == null) {
            this.mContext = context;
        }
    }

    public void loadProfile(int phoneId) {
        LgeSimInfo simInfo = LgeSimInfo.getSimInfo(this.mContext, phoneId);
        boolean loadSuccessFromPreferences = false;
        if (this.mContext != null) {
            if (simInfo.isNull()) {
                loadSuccessFromPreferences = loadProfileFromPreferences(phoneId, true);
            } else if (!simInfo.isNull() && simInfo.equals(LgeSimInfo.createFromPreference(this.mContext, phoneId))) {
                loadSuccessFromPreferences = loadProfileFromPreferences(phoneId, false);
            }
        }
        if (!loadSuccessFromPreferences) {
            loadProfileFromXml(simInfo, phoneId);
        }
        if ("1".equals(SystemProperties.get("persist.sys.cupss.changed", "0"))) {
            Log.d(LgeAutoProfilingConstants.TAG, "[LGE][SBP] Operator is changed. Need to reload!");
            loadProfileFromXml(simInfo, phoneId);
        }
    }

    private boolean loadProfileFromPreferences(int phoneId, boolean defaultProfile) {
        String prefName;
        Log.d(LgeAutoProfilingConstants.TAG, "[loadProfileFromPreferences] *** start profile loading from preferences - defaultProfile : " + defaultProfile);
        String currentSWBuildDate = SystemProperties.get(LgeAutoProfilingConstants.PROPERTY_BUILD_DATE);
        if (defaultProfile) {
            prefName = LgeAutoProfilingConstants.PREF_NAME_PROFILE_DEFAULT;
        } else {
            prefName = LgeAutoProfilingConstants.PREF_NAME_PROFILE + phoneId;
        }
        SharedPreferences preferences = this.mContext.getSharedPreferences(prefName, 0);
        if (!TextUtils.equals(currentSWBuildDate, preferences.getString(LgeAutoProfilingConstants.ITEM_NAME_BUILD_DATE, null))) {
            Log.e(LgeAutoProfilingConstants.TAG, "[loadProfileFromPreferences] preferences do not exist or BUILD_DATE is different - phoneId : " + phoneId);
            SystemProperties.set(LgeAutoProfilingConstants.PROPERTY_GSM_COTA, "0");
            return false;
        } else if (TextUtils.equals("updated", preferences.getString(LgeAutoProfilingConstants.ITEM_NAME_COTA, null))) {
            return false;
        } else {
            HashMap<String, String> profile = (HashMap) preferences.getAll();
            profile.remove(LgeAutoProfilingConstants.ITEM_NAME_BUILD_DATE);
            this.mProfiles.put(Integer.valueOf(phoneId), profile);
            Log.d(LgeAutoProfilingConstants.TAG, "[loadProfileFromPreferences] *** profile loading from preferences complete - defaultProfile : " + defaultProfile + ", phoneId : " + phoneId + " - " + profile);
            return true;
        }
    }

    private void loadProfileFromXml(LgeSimInfo simInfo, int phoneId) {
        Log.d(LgeAutoProfilingConstants.TAG, "[loadProfileFromXml] *** start profile loading from xml - phoneId : " + phoneId);
        NameValueProfile profileFromXml = (NameValueProfile) new LgeProfileParser().getMatchedProfile(getProfileType(), simInfo);
        if (profileFromXml == null) {
            Log.e(LgeAutoProfilingConstants.TAG, "[profileFromXml] load profile from xml failed");
            return;
        }
        HashMap<String, String> profileMapFromXml = LgeProfileParser.profileToMap(profileFromXml);
        if (profileMapFromXml == null) {
            Log.e(LgeAutoProfilingConstants.TAG, "[loadProfileFromXml] load profile Map from xml failed");
            return;
        }
        this.mProfiles.put(Integer.valueOf(phoneId), profileMapFromXml);
        if (this.mContext != null) {
            if (!simInfo.isNull()) {
                Log.d(LgeAutoProfilingConstants.TAG, "[loadProfileFromXml] save SimInfo to preferences");
                LgeSimInfo.writeToPreference(this.mContext, simInfo, phoneId);
            }
            saveProfileToPreferences(profileMapFromXml, phoneId, simInfo.isNull());
        }
        Log.d(LgeAutoProfilingConstants.TAG, "[loadProfileFromXml] *** end profile loading from xml - " + profileMapFromXml);
    }

    private int getProfileType() {
        int profileType = SystemProperties.get(LgeAutoProfilingConstants.PROPERTY_GSM_COTA).equals("1") ? 3 : 1;
        if (!this.mCotaUpdated) {
            return profileType;
        }
        this.mCotaUpdated = false;
        return 3;
    }

    private void saveProfileToPreferences(HashMap<String, String> profileMap, int phoneId, boolean simInfoIsNull) {
        String prefName;
        String swBuildDate = SystemProperties.get(LgeAutoProfilingConstants.PROPERTY_BUILD_DATE);
        if (simInfoIsNull) {
            prefName = LgeAutoProfilingConstants.PREF_NAME_PROFILE_DEFAULT;
        } else if (simInfoIsNull) {
            Log.d(LgeAutoProfilingConstants.TAG, "[saveProfileToPreferences] simInfo is null, do not save to preferences, phoneId : " + phoneId);
            return;
        } else {
            prefName = LgeAutoProfilingConstants.PREF_NAME_PROFILE + phoneId;
        }
        Editor editor = this.mContext.getSharedPreferences(prefName, 0).edit();
        Log.d(LgeAutoProfilingConstants.TAG, "[saveProfileToPreferences] save to " + prefName);
        editor.clear();
        if (!TextUtils.isEmpty(swBuildDate)) {
            editor.putString(LgeAutoProfilingConstants.ITEM_NAME_BUILD_DATE, swBuildDate);
        }
        for (Entry<String, String> entry : profileMap.entrySet()) {
            editor.putString((String) entry.getKey(), (String) entry.getValue());
        }
        editor.apply();
        if (this.mForceResetXml) {
            Log.d(LgeAutoProfilingConstants.TAG, "[saveProfileToPreferences] COTA is updated. Reset defaultProfile.xml");
            Editor defaultEditor = this.mContext.getSharedPreferences(LgeAutoProfilingConstants.PREF_NAME_PROFILE_DEFAULT, 0).edit();
            defaultEditor.putString(LgeAutoProfilingConstants.ITEM_NAME_COTA, "updated");
            defaultEditor.apply();
            this.mForceResetXml = false;
        }
    }

    public void updateProfile(Intent intent) {
        if (this.mContext == null) {
            Log.e(LgeAutoProfilingConstants.TAG, "[updateProfile] context is null, return");
            return;
        }
        String action = intent.getAction();
        int phoneId = intent.getIntExtra("phone", LgeSimInfo.getDefaultPhoneId());
        if ("android.intent.action.SIM_STATE_CHANGED".equals(action)) {
            String simState = intent.getStringExtra(IccCardConstants.INTENT_KEY_ICC_STATE);
            if (IccCardConstants.INTENT_VALUE_ICC_ABSENT.equals(simState) || IccCardConstants.INTENT_VALUE_ICC_LOCKED.equals(simState) || IccCardConstants.INTENT_VALUE_ICC_NOT_READY.equals(simState) || IccCardConstants.INTENT_VALUE_ICC_LOADED.equals(simState)) {
                StringBuilder buf = new StringBuilder("[updateProfile] ACTION_SIM_STATE_CHANGED -");
                Bundle extras = intent.getExtras();
                for (String key : extras.keySet()) {
                    buf.append(" [").append(key).append("=").append(extras.get(key)).append("]");
                }
                Log.d(LgeAutoProfilingConstants.TAG, buf.toString());
                if (getLastSimState(phoneId).equals(simState)) {
                    Log.d(LgeAutoProfilingConstants.TAG, "[updateProfile] repeated SIM_STATE, ignore");
                    return;
                }
                Log.d(LgeAutoProfilingConstants.TAG, "[updateProfile] new SIM_STATE, continue process");
                setLastSimState(phoneId, simState);
                Log.d(LgeAutoProfilingConstants.TAG, "[updateProfile] loadProfile");
                loadProfile(phoneId);
                setEccList(simState, phoneId);
            }
        }
        if (LGTelephonyIntents.ACTION_UPDATE_TELEPHONY_CONFIGS.equals(action)) {
            Log.d(LgeAutoProfilingConstants.TAG, "[updateProfile] COTA - ACTION_UPDATE_TELEPHONY_CONFIGS");
            SystemProperties.set(LgeAutoProfilingConstants.PROPERTY_GSM_COTA, "1");
            this.mForceResetXml = true;
            this.mCotaUpdated = true;
            loadProfileFromXml(LgeSimInfo.getSimInfo(this.mContext, phoneId), phoneId);
            setEccList(getLastSimState(phoneId), phoneId);
        }
    }

    public void setLastSimState(int phoneId, String simState) {
        this.mLastSimState.put(Integer.valueOf(phoneId), simState);
    }

    public String getLastSimState(int phoneId) {
        String lastSimState = (String) this.mLastSimState.get(Integer.valueOf(phoneId));
        return lastSimState == null ? "" : lastSimState;
    }

    public void setEccList(String simState, int phoneId) {
        if (!LgeAutoProfiling.isCountry(Country.KR)) {
            String eccList = null;
            boolean changed = false;
            if (IccCardConstants.INTENT_VALUE_ICC_ABSENT.equals(simState) || (IccCardConstants.INTENT_VALUE_ICC_NOT_READY.equals(simState) && !this.mIsMTKchip)) {
                eccList = getValue(LgeAutoProfilingConstants.KEY_NO_SIM_ECCLIST, phoneId);
                changed = true;
                SystemProperties.set(LgeAutoProfiling.getAutoProfileEcclistPropName(LgeAutoProfilingConstants.SYSTEM_PROP_AUTOPROFILE_ECC_IDLEMODE, phoneId), "");
                Log.v(LgeAutoProfilingConstants.TAG, "[setEccList] phoneId : " + phoneId + ", KEY_NO_SIM_ECCLIST : " + LgeAutoProfiling.privateLogHandler(eccList, 16));
            } else if (IccCardConstants.INTENT_VALUE_ICC_LOCKED.equals(simState) || ("trigger_restart_min_framework".equals(SystemProperties.get("vold.decrypt")) && IccCardConstants.INTENT_VALUE_ICC_NOT_READY.equals(simState))) {
                eccList = getEccListSimLock(phoneId);
                changed = true;
                Log.v(LgeAutoProfilingConstants.TAG, "[setEccList] phoneId : " + phoneId + ", KEY_SIM_LOCK_ECCLIST : " + LgeAutoProfiling.privateLogHandler(eccList, 16));
            } else if (IccCardConstants.INTENT_VALUE_ICC_LOADED.equals(simState)) {
                eccList = getValue(LgeAutoProfilingConstants.KEY_ECC_LIST, phoneId);
                changed = true;
                Log.v(LgeAutoProfilingConstants.TAG, "[setEccList] phoneId : " + phoneId + ", KEY_ECC_LIST : " + LgeAutoProfiling.privateLogHandler(eccList, 16));
                String eccIdleModeList = getValue(LgeAutoProfilingConstants.KEY_ECC_IDLE_MODE, phoneId);
                Log.v(LgeAutoProfilingConstants.TAG, "[setEccIdleList] phoneId : " + phoneId + ", KEY_ECC_IDLE_MODE : " + LgeAutoProfiling.privateLogHandler(eccIdleModeList, 16));
                SystemProperties.set(LgeAutoProfiling.getAutoProfileEcclistPropName(LgeAutoProfilingConstants.SYSTEM_PROP_AUTOPROFILE_ECC_IDLEMODE, phoneId), eccIdleModeList);
            }
            if (changed) {
                SystemProperties.set(LgeAutoProfiling.getAutoProfileEcclistPropName(LgeAutoProfilingConstants.SYSTEM_PROP_AUTOPROFILE_ECCLIST, phoneId), eccList);
            }
        }
    }

    private String getEccListSimLock(int phoneId) {
        String eccListSimLock = getValue(LgeAutoProfilingConstants.KEY_SIM_LOCK_ECCLIST, phoneId);
        int mcc = SystemProperties.getInt(LgeAutoProfilingConstants.SYSTEM_PROP_MCC_FOR_ONE_IMAGE, 0);
        if (LgeAutoProfiling.IS_GLOBAL_COUNTRY && mcc == 0) {
            mcc = LgeSimInfo.getMccFromIccid(this.mContext, phoneId);
        }
        if (mcc == 234 || mcc == 272 || mcc == 525 || mcc == 454 || mcc == 455 || (mcc == 222 && LgeAutoProfiling.isOperator("H3G"))) {
            eccListSimLock = "999";
        } else if (mcc == 466) {
            eccListSimLock = "110,119";
        } else if (mcc == MetricsEvent.ACTION_DELETION_SELECTION_PHOTOS) {
            eccListSimLock = "110,119,120,122";
        }
        if (LgeAutoProfiling.isOperator("CNO") || LgeAutoProfiling.isOperator(LgeAutoProfilingConstants.CMCC_OPERATOR)) {
            eccListSimLock = "110,119,120,122";
        }
        if (LgeAutoProfiling.isRegion(Country.SCA)) {
            return getValue(LgeAutoProfilingConstants.KEY_NO_SIM_ECCLIST, phoneId);
        }
        return eccListSimLock;
    }

    public String getValue(String key, int phoneId) {
        String str = null;
        if (this.mProfiles != null) {
            HashMap<String, String> profile = (HashMap) this.mProfiles.get(Integer.valueOf(phoneId));
            if (profile == null) {
                loadProfile(phoneId);
                profile = (HashMap) this.mProfiles.get(Integer.valueOf(phoneId));
            }
            if (profile != null) {
                str = (String) profile.get(key);
            }
        }
        Log.v(LgeAutoProfilingConstants.TAG, "[getValue] PROFILE key : " + key + ", value : " + str + ", phoneId : " + phoneId);
        return str == null ? "" : str;
    }

    public HashMap<String, String> getProfile(int phoneId) {
        return (HashMap) this.mProfiles.get(Integer.valueOf(phoneId));
    }
}
