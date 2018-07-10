package com.lge.pcas;

import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Slog;
import com.android.internal.telephony.TelephonyProperties;
import com.lge.lgdata.Country;
import com.lge.lgdata.Operator;
import com.lge.telephony.LGTelephonyProperties;
import com.lge.uicc.LGUiccManager;

public class PCASInfo {
    private static final String TAG = "PCAS";

    public static boolean isOperator(String... operators) {
        return isOperator(0, operators);
    }

    public static boolean isCountry(String... countries) {
        return isCountry(0, countries);
    }

    public static boolean isOperator(int slotId, String... operators) {
        String pcasOperator = getOperator(slotId);
        for (String op : operators) {
            if (TextUtils.equals(pcasOperator, op)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCountry(int slotId, String... countries) {
        String pcasCountry = getCountry(slotId);
        for (String cn : countries) {
            if (TextUtils.equals(pcasCountry, cn)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConstOperator(String... operators) {
        String constOperator = getConstOperator();
        for (String op : operators) {
            if (TextUtils.equals(constOperator, op)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConstCountry(String... countries) {
        String constCountry = getConstCountry();
        for (String cn : countries) {
            if (TextUtils.equals(constCountry, cn)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConstRegion(String... region) {
        String constRegion = getConstRegion();
        for (String re : region) {
            if (TextUtils.equals(constRegion, re)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isKrSimOperator(String... operators) {
        String simOperator = getKrSimOperator();
        for (String op : operators) {
            if (TextUtils.equals(simOperator, op)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isJpSimOperator(String... operators) {
        String simOperator = getJpSimOperator();
        for (String op : operators) {
            if (TextUtils.equals(simOperator, op)) {
                return true;
            }
        }
        return false;
    }

    public static String getOperator() {
        return getOperator(0);
    }

    public static String getOperator(int slotId) {
        if (useSimOperator() && !isSmartCaProvisioned()) {
            String pcasSimOpearator = getLaopSimOperator(slotId);
            if (!TextUtils.isEmpty(pcasSimOpearator)) {
                return pcasSimOpearator;
            }
        }
        return getConstOperator();
    }

    public static String getCountry() {
        return getCountry(0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String getCountry(int slotId) {
        if (!useSimOperator() || isSmartCaProvisioned()) {
            if (!isConstOperator(Operator.OPEN)) {
            }
            return getConstCountry();
        }
        String pcasSimCountry = getLaopSimCountry(slotId);
        if (!TextUtils.isEmpty(pcasSimCountry)) {
            return pcasSimCountry;
        }
        return getConstCountry();
    }

    public static String getConstOperator() {
        if (isSmartCaProvisioned()) {
            return SystemProperties.get("persist.sys.cota.operator", PCASConstants.OPERATOR);
        }
        return PCASConstants.OPERATOR;
    }

    public static String getConstCountry() {
        if (isSmartCaProvisioned()) {
            String smartCaCountry = SystemProperties.get("persist.sys.cota.country");
            if (!TextUtils.isEmpty(smartCaCountry)) {
                return smartCaCountry;
            }
            logw("getConstCountry: SmartCA country is empty, even if SmartCA is completed");
        }
        return PCASConstants.COUNTRY;
    }

    public static String getConstRegion() {
        return PCASConstants.REGION;
    }

    public static String getKrSimOperator() {
        if (!isConstCountry(Country.KR)) {
            return "NotSupported";
        }
        String ret = SystemProperties.get(LGTelephonyProperties.PROPERTY_CARD_OPERATOR);
        if (TextUtils.equals(ret, Operator.SKT) || TextUtils.equals(ret, Operator.KT) || TextUtils.equals(ret, Operator.LGU) || TextUtils.equals(ret, "TEST")) {
            return ret;
        }
        return "NotSupported";
    }

    public static String getJpSimOperator() {
        if (!isConstCountry(Country.JP)) {
            return "NotSupported";
        }
        String ret = SystemProperties.get(LGTelephonyProperties.PROPERTY_CARD_OPERATOR);
        if (TextUtils.equals(ret, Operator.DCM) || TextUtils.equals(ret, Operator.KDDI) || TextUtils.equals(ret, "SBM") || TextUtils.equals(ret, "TEST")) {
            return ret;
        }
        return "NotSupported";
    }

    public static String getSimMccMnc(int slotId) {
        return TelephonyManager.getTelephonyProperty(slotId, TelephonyProperties.PROPERTY_ICC_OPERATOR_NUMERIC, "");
    }

    public static String getSimMccMnc() {
        return getSimMccMnc(0);
    }

    public static String getSimGid1(int slotId) {
        return LGUiccManager.getProperty("gid1", slotId, "");
    }

    public static String getSimGid1() {
        return getSimGid1(0);
    }

    public static String getSimGid2(int slotId) {
        return LGUiccManager.getProperty("gid2", slotId, "");
    }

    public static String getSimGid2() {
        return getSimGid2(0);
    }

    public static String getSimSpn(int slotId) {
        return TelephonyManager.getTelephonyProperty(slotId, TelephonyProperties.PROPERTY_ICC_OPERATOR_ALPHA, "");
    }

    public static String getSimSpn() {
        return getSimSpn(0);
    }

    public static String getHwChipVendor() {
        return PCASConstants.BASEBAND_VENDOR;
    }

    public static String getHwChipsetName() {
        return PCASConstants.BASEBAND_NAME;
    }

    public static String getHwModelName() {
        return PCASConstants.DEVICE;
    }

    public static boolean isUserBuild() {
        return TextUtils.equals(PCASConstants.BUILD_TYPE, "user");
    }

    protected static boolean isOpenDevice() {
        return TextUtils.equals(PCASConstants.OPERATOR, Operator.OPEN) ? !TextUtils.equals(PCASConstants.COUNTRY, Country.CA) : false;
    }

    private static String getLaopSimOperator(int slotId) {
        if (isValidSlotId(slotId)) {
            String ret = "";
            if (PCASConstants.LAOP_SIM_OPERATOR_USE || PCASConstants.PCAS_SIM_OPERATOR_TEST) {
                if (slotId == 0) {
                    ret = SystemProperties.get("persist.sys.sim.operator");
                } else if (slotId > 0) {
                    ret = SystemProperties.get("persist.sys.sim" + (slotId + 1) + ".operator");
                }
                if (TextUtils.isEmpty(ret)) {
                    ret = PCASConstants.LAOP_DEFAULT_OPERATOR;
                }
            } else if (PCASConstants.LAOP_BUILD) {
                ret = PCASConstants.LAOP_DEFAULT_OPERATOR;
                if (TextUtils.equals(ret, "TMUS")) {
                    ret = PCASConstants.OPERATOR;
                }
            }
            return ret;
        }
        logw("getLaopSimOperator: parameter slotId is not valid, slotId=" + slotId + ", phoneCount=" + TelephonyManager.getDefault().getPhoneCount());
        return "";
    }

    private static String getLaopSimCountry(int slotId) {
        if (isValidSlotId(slotId)) {
            String ret = "";
            if (PCASConstants.LAOP_SIM_OPERATOR_USE || PCASConstants.PCAS_SIM_OPERATOR_TEST) {
                if (slotId == 0) {
                    ret = SystemProperties.get("persist.sys.sim.country");
                } else if (slotId > 0) {
                    ret = SystemProperties.get("persist.sys.sim" + (slotId + 1) + ".country");
                }
                if (TextUtils.isEmpty(ret)) {
                    ret = PCASConstants.COUNTRY;
                }
            }
            return ret;
        }
        logw("getLaopSimCountry: parameter slotId is not valid, slotId=" + slotId + ", phoneCount=" + TelephonyManager.getDefault().getPhoneCount());
        return "";
    }

    private static boolean useSimOperator() {
        if (TextUtils.equals(PCASConstants.OPERATOR, Operator.OPEN) || TextUtils.equals(PCASConstants.OPERATOR, Operator.NAO) || TextUtils.equals(PCASConstants.OPERATOR, Operator.TRF)) {
            return true;
        }
        return PCASConstants.PCAS_SIM_OPERATOR_TEST;
    }

    private static boolean isSmartCaProvisioned() {
        return !TextUtils.isEmpty(SystemProperties.get("persist.sys.cota.operator", ""));
    }

    private static boolean isValidSlotId(int slotId) {
        if (slotId < 0 || slotId >= TelephonyManager.getDefault().getPhoneCount()) {
            return false;
        }
        return true;
    }

    private static void logw(String s) {
        Slog.w(TAG, s);
    }
}
