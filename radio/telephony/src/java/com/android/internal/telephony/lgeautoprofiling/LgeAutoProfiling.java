package com.android.internal.telephony.lgeautoprofiling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.telephony.Rlog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.IccCardConstants;
import com.lge.lgdata.Country;
import com.lge.lgdata.Operator;
import com.lge.pcas.PCASInfo;
import com.lge.telephony.LGTelephonyProperties;

public class LgeAutoProfiling implements LgeKeyProfiling, LgeAutoProfilingConstants {
    public static final String ACTION_VOLTE_CHANGED_INFO = "com.lge.action.VOLTE_CHANGED_INFO";
    private static final boolean DBG = false;
    private static final boolean EDBG = true;
    private static final String[] PROP_LATE_SIM_PROFILING_COUNTRIES = new String[]{"persist.sys.sim.country", "persist.sys.sim2.country"};
    private static final String[] PROP_LATE_SIM_PROFILING_OPERATORS = new String[]{"persist.sys.sim.operator", "persist.sys.sim2.operator"};
    private static final String PROP_SIM_PROFILE_TYPE = "persist.sys.sim.profile";
    private static final int SERVICE_CLASS_VOICE = 1;
    private static final boolean VDBG = true;
    private static String sCardOperator = "";
    private static IntentFilter sIntentFilter = null;
    private static boolean sIsCMO = "CMO".equals(OPERATOR);
    private static boolean sIsCTO = "CTO".equals(OPERATOR);
    private static boolean sIsCardOperatorCached = false;
    private static boolean sIsDebugMode = "1".equals(SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_DEBUGGABLE, "0"));
    private static boolean sIsEnabled;
    private static boolean sIsPSCall_Slot1;
    private static boolean sIsPSCall_Slot2;
    private static boolean sIsSupportGII;
    private static int sLogDialstring = 0;
    private static boolean sLogFeatureLoaded = false;
    private static int sLogIdentity = 0;
    private static int sLogUssd = 0;
    private static BroadcastReceiver sReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            boolean z2 = false;
            String action = intent.getAction();
            Log.d(LgeAutoProfilingConstants.TAG, "Received intent with action" + action);
            if (action.equals(LgeAutoProfiling.ACTION_VOLTE_CHANGED_INFO)) {
                boolean z3 = !SystemProperties.getBoolean("ro.lge.supportvolte", false) ? SystemProperties.getBoolean("persist.lg.data.iwlan", false) ? SystemProperties.getBoolean("persist.lge.supportvowifi", false) : false : true;
                LgeAutoProfiling.sIsPSCall_Slot1 = z3;
                if (SystemProperties.getBoolean("ro.lge.supportvolte.sim2", false)) {
                    z2 = true;
                } else if (SystemProperties.getBoolean("persist.lg.data.iwlan", false)) {
                    z2 = SystemProperties.getBoolean("persist.lge.supportvowifi.sim2", false);
                }
                LgeAutoProfiling.sIsPSCall_Slot2 = z2;
                if (!LgeAutoProfiling.sIsPSCall_Slot1) {
                    z = LgeAutoProfiling.sIsPSCall_Slot2;
                }
                LgeAutoProfiling.sIsSupportGII = z;
            } else if (action.equals("android.intent.action.SIM_STATE_CHANGED") && IccCardConstants.INTENT_VALUE_ICC_LOADED.equals(intent.getStringExtra(IccCardConstants.INTENT_KEY_ICC_STATE))) {
                LgeAutoProfiling.processSimProfiling(context, intent);
            }
        }
    };
    private static String sSmartCACountry = SystemProperties.get("persist.sys.cota.country", IccCardConstants.INTENT_VALUE_ICC_UNKNOWN);
    private static String sSmartCAOperator = SystemProperties.get("persist.sys.cota.operator", Operator.OPEN);

    static {
        boolean z;
        boolean z2 = true;
        if (SystemProperties.getBoolean(LgeAutoProfilingConstants.PRIVATE_LOG_PROP, false) || "1".equals(SystemProperties.get(LgeAutoProfilingConstants.PRIVATE_LOG_TMUS_PROP, "0"))) {
            z = true;
        } else {
            z = sIsDebugMode;
        }
        sIsEnabled = z;
        z = !SystemProperties.getBoolean("ro.lge.supportvolte", false) ? SystemProperties.getBoolean("persist.lg.data.iwlan", false) ? SystemProperties.getBoolean("persist.lge.supportvowifi", false) : false : true;
        sIsPSCall_Slot1 = z;
        z = !SystemProperties.getBoolean("ro.lge.supportvolte.sim2", false) ? SystemProperties.getBoolean("persist.lg.data.iwlan", false) ? SystemProperties.getBoolean("persist.lge.supportvowifi.sim2", false) : false : true;
        sIsPSCall_Slot2 = z;
        if (!sIsPSCall_Slot1) {
            z2 = sIsPSCall_Slot2;
        }
        sIsSupportGII = z2;
    }

    private LgeAutoProfiling() {
    }

    public static void init(Context context, int phoneType) {
        Log.v(LgeAutoProfilingConstants.TAG, "[init] ******** Telephony Auto Profiling *******");
        if (context == null) {
            Log.e(LgeAutoProfilingConstants.TAG, "[init] context is null, return");
            return;
        }
        LgeFeature.getInstance(context).loadFeature();
        if (sIntentFilter == null) {
            sIntentFilter = new IntentFilter();
            sIntentFilter.addAction(ACTION_VOLTE_CHANGED_INFO);
            sIntentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            context.registerReceiver(sReceiver, sIntentFilter);
        }
    }

    private static void processSimProfiling(Context context, Intent intent) {
        String simProfilingType = SystemProperties.get(PROP_SIM_PROFILE_TYPE, "none");
        boolean z = (simProfilingType.equals("fast") || simProfilingType.equals("both")) ? true : IS_SUPPORT_NAOP;
        if (z) {
            LgeFeature.getInstance(context).loadFeature();
        }
        Log.d(LgeAutoProfilingConstants.TAG, "[processSimProfiling] simProfilingType : " + simProfilingType);
    }

    public static void updateProfile(Context context, Intent intent) {
        LgeProfile.getInstance(context).updateProfile(intent);
        syncClearCodes(context, intent);
    }

    public static void updateFeature(Context context) {
        if (isOperator("CNO")) {
            LgeFeature.getInstance(context).loadFeature();
        }
    }

    public static String getProfileInfo(Context context, String key, int phoneId) {
        return LgeProfile.getInstance(context).getValue(key, phoneId);
    }

    public static String getFeatureInfo(Context context, String key) {
        return LgeFeature.getInstance(context).getValue(key);
    }

    public static void syncClearCodes(Context context, Intent intent) {
        if ("android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
            if (IccCardConstants.INTENT_VALUE_ICC_LOADED.equals(intent.getStringExtra(IccCardConstants.INTENT_KEY_ICC_STATE))) {
                updateClearCodes();
            }
        } else if ("android.telephony.action.DEFAULT_SUBSCRIPTION_CHANGED".equals(intent.getAction())) {
            updateClearCodes();
        }
    }

    private static void updateClearCodes() {
        String clearCodes = getProfileInfo(null, LgeKeyProfiling.KEY_CLEAR_CODES, LgeSimInfo.getDefaultPhoneId());
        if (TextUtils.isEmpty(clearCodes)) {
            SystemProperties.set("gsm.call.clear_codes", "");
        } else {
            SystemProperties.set("gsm.call.clear_codes", clearCodes);
        }
    }

    public static int getClirSettingValue(Context context) {
        return getClirSettingValue(context, LgeSimInfo.getDefaultPhoneId());
    }

    public static int getClirSettingValue(Context context, int phoneId) {
        String clirSetting = getProfileInfo(context, LgeKeyProfiling.KEY_SEND_MY_NUMBER_DEFAULT_VALUE, phoneId);
        String simChangedInfo = LgeSimInfo.getSimChangedInfo(phoneId);
        String clirInit = SystemProperties.get("gsm.call.clir.init");
        if ("0".equals(simChangedInfo)) {
            SystemProperties.set("gsm.call.clir.init", "");
        }
        if ("0".equals(simChangedInfo) || TextUtils.isEmpty(clirSetting) || clirInit.contains(Integer.toString(phoneId + 1))) {
            return -1;
        }
        int clirSettingValue = Integer.parseInt(clirSetting);
        SystemProperties.set("gsm.call.clir.init", clirInit + (phoneId + 1));
        Log.d(LgeAutoProfilingConstants.TAG, "[getClirSettingValue] This is First time after SIM change :: gsm.call.clir.init = " + clirInit);
        return clirSettingValue;
    }

    public static String[] getHomeNetworks(Context context, int phoneId) {
        String homeNetworks = getProfileInfo(null, LgeKeyProfiling.KEY_HOME_NETWORK, phoneId);
        if (TextUtils.isEmpty(homeNetworks)) {
            return null;
        }
        return homeNetworks.split(",");
    }

    public static String[] getHomeSimNumeric(Context context, int phoneId) {
        String homeNetworks = getProfileInfo(null, LgeKeyProfiling.KEY_HOME_SIMNUMERIC, phoneId);
        if (TextUtils.isEmpty(homeNetworks)) {
            return null;
        }
        return homeNetworks.split(",");
    }

    public static String[] getRoamingNetworks(Context context, int phoneId) {
        String roamingNetworks = getProfileInfo(null, LgeKeyProfiling.KEY_ROAMING_NETWORK, phoneId);
        if (TextUtils.isEmpty(roamingNetworks)) {
            return null;
        }
        return roamingNetworks.split(",");
    }

    public static String[] getNotRoamingCountry(Context context, int phoneId) {
        String notRoamingCountry = getProfileInfo(null, LgeKeyProfiling.KEY_NOT_ROAMING_COUNTRY, phoneId);
        if (TextUtils.isEmpty(notRoamingCountry)) {
            return null;
        }
        return notRoamingCountry.split(",");
    }

    public static boolean isSupportedFeature(Context context, String key) {
        boolean result = getFeatureInfo(context, key).equalsIgnoreCase("true");
        if (!result && TextUtils.equals(key, LgeKeyProfiling.KEY_SUPPORT_VOLTE_IF)) {
            result = sIsSupportGII;
        }
        if (result) {
            return result;
        }
        return isSupportedDeviceFeature(context, key);
    }

    public static boolean checkEccIdleMode(String dialNumber, int phoneId) {
        if (TextUtils.isEmpty(dialNumber)) {
            return false;
        }
        String eccIdleModeList = SystemProperties.get(getAutoProfileEcclistPropName(LgeAutoProfilingConstants.SYSTEM_PROP_AUTOPROFILE_ECC_IDLEMODE, phoneId));
        if (TextUtils.isEmpty(eccIdleModeList)) {
            return false;
        }
        for (String token : eccIdleModeList.split(",")) {
            if (dialNumber.equals(token)) {
                Log.v(LgeAutoProfilingConstants.TAG, "[checkEccIdleMode] Ecc_IdleMode : true - " + privateLogHandler(dialNumber, 16));
                return true;
            }
        }
        return false;
    }

    public static String getEccListMerged(String eccList, int phoneId) {
        eccList = mergeEcclist(eccList, SystemProperties.get(getAutoProfileEcclistPropName(LgeAutoProfilingConstants.SYSTEM_PROP_AUTOPROFILE_ECCLIST, phoneId)));
        if ("mtk".equalsIgnoreCase(SystemProperties.get("ro.lge.chip.vendor"))) {
            return mergeMtkNetworkEccList(eccList, phoneId);
        }
        return eccList;
    }

    private static String mergeEcclist(String ecc1, String ecc2) {
        if (ecc1 == null) {
            return ecc2;
        }
        if (ecc1.isEmpty() || ecc1.endsWith(",") || ecc2.isEmpty()) {
            ecc1 = ecc1.concat(ecc2);
        } else {
            ecc1 = ecc1.concat("," + ecc2);
        }
        return ecc1;
    }

    public static String getMtkNetworkEccList(int phoneId) {
        if ("mtk".equalsIgnoreCase(SystemProperties.get("ro.lge.chip.vendor"))) {
            return mergeMtkNetworkEccList(null, phoneId);
        }
        return null;
    }

    private static String mergeMtkNetworkEccList(String eccList, int phoneId) {
        StringBuilder mtkNwEcc = new StringBuilder();
        if (buildMtkNetworkEccList(mtkNwEcc, SystemProperties.get(getAutoProfileEcclistPropName("ril.ecc.service.category.list", phoneId)))) {
            return mergeEcclist(eccList, mtkNwEcc.toString());
        }
        return eccList;
    }

    private static boolean buildMtkNetworkEccList(StringBuilder builder, String eccCatList) {
        if (TextUtils.isEmpty(eccCatList)) {
            return false;
        }
        boolean updated = false;
        for (String eccCat : eccCatList.split(";")) {
            if (!TextUtils.isEmpty(eccCat)) {
                String[] fields = eccCat.split(",");
                if (2 == fields.length && !TextUtils.isEmpty(fields[0])) {
                    if (updated) {
                        builder.append(",");
                    }
                    builder.append(fields[0]);
                    updated = true;
                }
            }
        }
        return updated;
    }

    public static boolean checkShortCodeCall(Context context, String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        String shortCode = getProfileInfo(context, LgeAutoProfilingConstants.KEY_SHORTCODE_CALL, LgeSimInfo.getDefaultPhoneId());
        if (!TextUtils.isEmpty(shortCode)) {
            for (String token : shortCode.split(",")) {
                if (number.equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIsNonLTESim(Context context, int phoneId) {
        boolean isNonLTESim = getProfileInfo(context, LgeAutoProfilingConstants.KEY_IS_NONLTE_SIM, phoneId).equalsIgnoreCase("true");
        Log.d(LgeAutoProfilingConstants.TAG, "[Net_Patch_2062][checkIsNonLTESim] isNonLTESim : " + isNonLTESim);
        TelephonyManager.setTelephonyProperty(phoneId, LGTelephonyProperties.PROPERTY_IS_NONLTE_SIM, String.valueOf(isNonLTESim));
        return isNonLTESim;
    }

    public static boolean isPSCallOverLGIMS(int phoneId) {
        return (!MULTI_SIM_ENABLED || phoneId == 0) ? sIsPSCall_Slot1 : sIsPSCall_Slot2;
    }

    public static boolean isOIRMethodFullTB(Context context, int phoneId) {
        if (IS_GLOBAL_COUNTRY && !isPSCallOverLGIMS(phoneId)) {
            return false;
        }
        if (isCountry(Country.US)) {
            boolean result = isSupportedFeature(null, LgeKeyProfiling.KEY_SET_CLIR_OPTION_BY_CALL_SETTING);
            Log.d(LgeAutoProfilingConstants.TAG, "[isOIRMethodTB] US model, check featureset.xml, result = " + result);
            if (result) {
                return result;
            }
        }
        String tbClir = getProfileInfo(context, LgeAutoProfilingConstants.KEY_TBClir, phoneId);
        if (TextUtils.isEmpty(tbClir) || !tbClir.equals("1")) {
            return false;
        }
        return true;
    }

    public static boolean isCWMethodTB(Context context, int phoneId) {
        if (IS_GLOBAL_COUNTRY && !isPSCallOverLGIMS(phoneId)) {
            return false;
        }
        String tbCW = getProfileInfo(context, LgeAutoProfilingConstants.KEY_TBCW, phoneId);
        if (TextUtils.isEmpty(tbCW) || !tbCW.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }

    public static boolean isCWMethodFullTB(Context context, int phoneId) {
        if (IS_GLOBAL_COUNTRY && !isPSCallOverLGIMS(phoneId)) {
            return false;
        }
        String fullTBCW = getProfileInfo(context, LgeAutoProfilingConstants.KEY_FULL_TBCW, phoneId);
        if (TextUtils.isEmpty(fullTBCW) || !fullTBCW.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }

    public static boolean isHiddenSetCSCWEnableRequired(int phoneId) {
        String simNumeric = TelephonyManager.getDefault().getSimOperatorNumericForPhone(phoneId);
        if (TextUtils.isEmpty(simNumeric) || (!"26202".equals(simNumeric) && !"21401".equals(simNumeric) && !"21418".equals(simNumeric))) {
            return false;
        }
        return SystemProperties.getBoolean(LGTelephonyProperties.PROPERTY_CSCW_STATUS + phoneId, false);
    }

    public static boolean checkSRVCC(Context context, int phoneId) {
        if (IS_GLOBAL_COUNTRY && !isPSCallOverLGIMS(phoneId)) {
            return false;
        }
        String SRVCC = getProfileInfo(context, LgeAutoProfilingConstants.KEY_SRVCC, phoneId);
        if (TextUtils.isEmpty(SRVCC) || !SRVCC.equals("1")) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkABSRVCC(Context context, int phoneId) {
        if (IS_GLOBAL_COUNTRY && isPSCallOverLGIMS(phoneId) && getFeatureInfo(context, LgeKeyProfiling.KEY_SUPPORT_CALL_CONTEXT_TRANSFER).equalsIgnoreCase("false")) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkMidSRVCC(Context context, int phoneId) {
        if (IS_GLOBAL_COUNTRY && isPSCallOverLGIMS(phoneId) && !getFeatureInfo(context, LgeKeyProfiling.KEY_SUPPORT_MID_CALL_SRVCC).equalsIgnoreCase("false")) {
            return true;
        }
        return false;
    }

    public static boolean supportBSRVCC(Context context, int phoneId) {
        return getProfileInfo(context, LgeAutoProfilingConstants.KEY_BSRVCC, phoneId).equals("1");
    }

    public static boolean supportSRVCC(Context context, String key, int phoneId) {
        if (getFeatureInfo(context, key).equalsIgnoreCase("true")) {
            return true;
        }
        return IS_GLOBAL_COUNTRY ? isPSCallOverLGIMS(phoneId) : false;
    }

    public static String getVMS(Context context) {
        return getVMS(context, LgeSimInfo.getDefaultPhoneId());
    }

    public static String getVMS(Context context, int phoneId) {
        return getProfileInfo(context, LgeAutoProfilingConstants.KEY_VMS, phoneId);
    }

    public static String getRVMS(Context context) {
        return getRVMS(context, LgeSimInfo.getDefaultPhoneId());
    }

    public static String getRVMS(Context context, int phoneId) {
        return getProfileInfo(context, LgeAutoProfilingConstants.KEY_RVMS, phoneId);
    }

    public static boolean isOperatorCountry(String country, String operator) {
        return isCountry(country) ? isOperator(operator) : false;
    }

    public static boolean isCountry(String country) {
        if (IS_GLOBAL_COUNTRY) {
            return checkForGlobalCountry(country);
        }
        return PCASInfo.isCountry(new String[]{country});
    }

    public static boolean isOperator(String operator) {
        if (sIsCTO || sIsCMO) {
            return checkForChinaOperator(operator);
        }
        if (IS_GLOBAL_COUNTRY) {
            return checkForGlobalOperator(operator);
        }
        return PCASInfo.isOperator(new String[]{operator});
    }

    private static String getDynamicPropVal(String defval, String... propNames) {
        int defPhoneId = 0;
        if (MULTI_SIM_ENABLED && propNames.length >= 2) {
            defPhoneId = LgeSimInfo.getDefaultPhoneId();
            if (defPhoneId >= propNames.length) {
                defPhoneId = 0;
            }
        }
        String propVal = SystemProperties.get(propNames[defPhoneId], defval);
        return propVal.equals(defval) ? "" : propVal;
    }

    private static boolean checkForGlobalCountry(String country) {
        String smartCACountry = getDynamicPropVal(IccCardConstants.INTENT_VALUE_ICC_UNKNOWN, "persist.sys.cota.country");
        if (!smartCACountry.isEmpty()) {
            return TextUtils.equals(country, smartCACountry);
        }
        String lateCountry = getDynamicPropVal("COM", PROP_LATE_SIM_PROFILING_COUNTRIES);
        if (lateCountry.isEmpty() || TextUtils.equals(country, Country.EU) || TextUtils.equals(country, Country.CIS)) {
            return TextUtils.equals(country, COUNTRY);
        }
        return TextUtils.equals(country, lateCountry);
    }

    private static boolean checkForGlobalOperator(String operator) {
        String smartCAOperator = getDynamicPropVal(Operator.OPEN, "persist.sys.cota.operator");
        if (!smartCAOperator.isEmpty()) {
            return TextUtils.equals(operator, smartCAOperator);
        }
        String lateOperator = getDynamicPropVal(Operator.OPEN, PROP_LATE_SIM_PROFILING_OPERATORS);
        if (lateOperator.isEmpty() || TextUtils.equals(operator, Operator.OPEN)) {
            return TextUtils.equals(operator, OPERATOR);
        }
        return TextUtils.equals(operator, lateOperator);
    }

    private static boolean checkForChinaOperator(String operator) {
        if (sIsCTO && (TextUtils.equals("CTC", operator) || TextUtils.equals("CTO", operator))) {
            return true;
        }
        if (sIsCMO && (TextUtils.equals(LgeAutoProfilingConstants.CMCC_OPERATOR, operator) || TextUtils.equals("CMO", operator))) {
            return true;
        }
        return PCASInfo.isOperator(new String[]{operator});
    }

    public static boolean isRegion(String region) {
        return PCASInfo.isConstRegion(new String[]{region});
    }

    public static boolean checkCountries(String... countries) {
        for (String country : countries) {
            if (isCountry(country)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkOperators(String... operators) {
        for (String operator : operators) {
            if (isOperator(operator)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRegions(String... regions) {
        for (String region : regions) {
            if (isRegion(region)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkOperatorCountries(String operator, String... countries) {
        return isOperator(operator) ? checkCountries(countries) : false;
    }

    public static boolean checkCountryOperators(String country, String... operators) {
        return isCountry(country) ? checkOperators(operators) : false;
    }

    public String getTargetOperator() {
        return OPERATOR;
    }

    public String getTargetCountry() {
        return COUNTRY;
    }

    private static void privateLogFeatureLoad() {
        String feature = getFeatureInfo(null, LgeKeyProfiling.KEY_PRIVATE_LOG_LEVEL);
        if (feature != null) {
            Log.d(LgeAutoProfilingConstants.TAG, "sIsEnabled : " + sIsEnabled);
            int loglevel = 0;
            if (!sIsEnabled) {
                try {
                    loglevel = Integer.decode(feature).intValue();
                } catch (NumberFormatException e) {
                }
                sLogUssd = loglevel & 1;
                sLogDialstring = loglevel & 16;
                sLogIdentity = loglevel & 256;
            }
        }
    }

    public static boolean isLogBlocked(int level) {
        if (!sLogFeatureLoaded) {
            privateLogFeatureLoad();
            sLogFeatureLoaded = true;
        }
        if (sIsEnabled) {
            return false;
        }
        if (IS_GLOBAL_COUNTRY && isOperator(Operator.ORG)) {
            return true;
        }
        switch (level) {
            case 1:
                if (sLogUssd != 0) {
                    return true;
                }
                break;
            case 16:
                if (sLogDialstring != 0) {
                    return true;
                }
                break;
            case 256:
                if (sLogIdentity != 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String privateLogHandler(String str, int level) {
        if (str == null || sIsEnabled || !isLogBlocked(level)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            if ((c < '0' || c > '9') && !Character.isAlphabetic(c)) {
                stringBuilder.append(c);
            } else {
                stringBuilder.append('*');
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isSimOperator(String operator) {
        if (!sIsCardOperatorCached) {
            String cardOperator = SystemProperties.get(LGTelephonyProperties.PROPERTY_CARD_OPERATOR, "");
            if (TextUtils.isEmpty(cardOperator)) {
                return isOperator(operator);
            }
            sCardOperator = cardOperator;
            sIsCardOperatorCached = true;
            Rlog.d(LgeAutoProfilingConstants.TAG, "sCardOperator=" + sCardOperator);
        }
        return sCardOperator.equalsIgnoreCase(operator);
    }

    public static boolean isSimOperatorCountry(String country, String operator) {
        boolean equalsIgnoreCase;
        if (!sIsCardOperatorCached) {
            String cardOperator = SystemProperties.get(LGTelephonyProperties.PROPERTY_CARD_OPERATOR, "");
            if (TextUtils.isEmpty(cardOperator)) {
                return isOperatorCountry(country, operator);
            }
            sCardOperator = cardOperator;
            sIsCardOperatorCached = true;
            Rlog.d(LgeAutoProfilingConstants.TAG, "sCardOperator=" + sCardOperator);
        }
        if (TextUtils.equals(country, COUNTRY)) {
            equalsIgnoreCase = sCardOperator.equalsIgnoreCase(operator);
        } else {
            equalsIgnoreCase = false;
        }
        return equalsIgnoreCase;
    }

    public static boolean isSimCountry(String country) {
        if (!TextUtils.equals(country, Country.KR)) {
            return false;
        }
        boolean isSimOperator = (isSimOperator(Operator.LGU) || isSimOperator(Operator.SKT)) ? true : isSimOperator(Operator.KT);
        return isSimOperator;
    }

    public static void resetSimOperatorCache() {
        sIsCardOperatorCached = false;
        sCardOperator = "";
    }

    public static boolean isSimOperatorRaw(String operator) {
        String cardOperator = SystemProperties.get(LGTelephonyProperties.PROPERTY_CARD_OPERATOR, "");
        if (TextUtils.isEmpty(cardOperator)) {
            return isOperator(operator);
        }
        return cardOperator.equalsIgnoreCase(operator);
    }

    public static boolean isSimOperatorCountryRaw(String country, String operator) {
        String cardOperator = SystemProperties.get(LGTelephonyProperties.PROPERTY_CARD_OPERATOR, "");
        if (TextUtils.isEmpty(cardOperator)) {
            return isOperatorCountry(country, operator);
        }
        boolean equalsIgnoreCase;
        if (TextUtils.equals(country, COUNTRY)) {
            equalsIgnoreCase = cardOperator.equalsIgnoreCase(operator);
        } else {
            equalsIgnoreCase = false;
        }
        return equalsIgnoreCase;
    }

    public static String getChinaDDSOperator() {
        return SystemProperties.get(LGTelephonyProperties.PROPERTY_DDS_OPERATOR, "");
    }

    private static String getChinaSimOperatorName(String operatorNumeric) {
        int i = 0;
        String operator = LgeAutoProfilingConstants.OTHER_OPERATOR;
        if (TextUtils.isEmpty(operatorNumeric) || operatorNumeric.length() < 5) {
            return operator;
        }
        String numeric = operatorNumeric.substring(0, 5);
        String testSimOperator = SystemProperties.get("persist.radio.ctc_operator", "");
        if (!TextUtils.isEmpty(operatorNumeric)) {
            String[] split = testSimOperator.split(",");
            int length = split.length;
            while (i < length) {
                if (numeric.equals(split[i])) {
                    return "CTC";
                }
                i++;
            }
        }
        if (numeric.equals("46003") || numeric.equals("46011") || numeric.equals("20404")) {
            operator = "CTC";
        } else if (numeric.equals("45502") || numeric.equals("45507")) {
            operator = "CTC";
        } else if (numeric.equals("46000") || numeric.equals("46002") || numeric.equals("46007") || numeric.equals("46008") || numeric.equals("46088")) {
            operator = LgeAutoProfilingConstants.CMCC_OPERATOR;
        } else if (numeric.equals("46001") || numeric.equals("46009")) {
            operator = LgeAutoProfilingConstants.CUCC_OPERATOR;
        } else if (numeric.startsWith("454") || numeric.startsWith("455")) {
            operator = "HK";
        } else if (numeric.equals("45000") || numeric.equals("00101")) {
            operator = LgeAutoProfilingConstants.CMCC_OPERATOR;
            if (isCountry("HK")) {
                operator = "HK";
            }
        }
        return operator;
    }

    public static String getChinaSimOperator(int phoneId) {
        if (!isOperator("CNO")) {
            return "";
        }
        String numeric = TelephonyManager.getDefault().getSimOperatorNumericForPhone(phoneId);
        if (TextUtils.isEmpty(numeric)) {
            numeric = TelephonyManager.getTelephonyProperty(phoneId, LGTelephonyProperties.PROPERTY_SUBSCRIBER_IDS, "");
        }
        return getChinaSimOperatorName(numeric);
    }

    public static boolean isChinaSimOperator(String operator, int phoneId) {
        if (isOperator("CNO") && !TextUtils.isEmpty(operator)) {
            return operator.equalsIgnoreCase(getChinaSimOperator(phoneId));
        }
        return false;
    }

    public static String getAutoProfileEcclistPropName(String name, int phoneId) {
        return phoneId <= 0 ? name : name + phoneId;
    }

    public static void loadProfile(Context context, int phoneType) {
        int defaultPhoneId = LgeSimInfo.getDefaultPhoneId();
        LgeProfile.getInstance(context).loadProfile(defaultPhoneId);
        if (phoneType == 2 && !isCountry(Country.KR)) {
            SystemProperties.set(LgeAutoProfilingConstants.SYSTEM_PROP_AUTOPROFILE_ECCLIST, getProfileInfo(context, LgeAutoProfilingConstants.KEY_ECC_LIST, defaultPhoneId));
        }
    }

    private static boolean isSupportedDeviceFeature(Context context, String key) {
        return false;
    }

    public static void handleCallWaitingResponseForTB(boolean enable, int serviceClass, int phoneId) {
        boolean z = true;
        if (isCWMethodTB(null, phoneId)) {
            String propNameTBCW = LGTelephonyProperties.PROPERTY_VOLTE_TBCW + phoneId;
            if (enable) {
                if (!isHiddenSetCSCWEnableRequired(phoneId)) {
                    if ((serviceClass & 1) != 1) {
                        z = false;
                    }
                    SystemProperties.set(propNameTBCW, String.valueOf(z));
                }
                SystemProperties.set(LGTelephonyProperties.PROPERTY_CSCW_STATUS + phoneId, "true");
                return;
            }
            SystemProperties.set(propNameTBCW, "false");
        }
    }

    public static boolean supportMptyForSRVCC(Context context, int phoneId) {
        if ((!IS_GLOBAL_COUNTRY || !supportMidSRVCC(context, phoneId) || !supportConfSub(context, phoneId)) && !isOperator(LgeAutoProfilingConstants.CMCC_OPERATOR) && !isChinaSimOperator(LgeAutoProfilingConstants.CMCC_OPERATOR, phoneId) && !SystemProperties.getBoolean("persist.force.mptysrvcc", false)) {
            return false;
        }
        Log.d(LgeAutoProfilingConstants.TAG, "supportMptyForSRVCC : true");
        return true;
    }

    private static boolean supportMidSRVCC(Context context, int phoneId) {
        return getProfileInfo(context, LgeAutoProfilingConstants.KEY_MID_SRVCC, phoneId).equals("1");
    }

    private static boolean supportConfSub(Context context, int phoneId) {
        return getProfileInfo(context, LgeAutoProfilingConstants.KEY_CONF_SUB, phoneId).equals("true");
    }
}
