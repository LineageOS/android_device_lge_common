package com.android.internal.telephony.lgeautoprofiling;

import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import com.lge.lgdata.Country;
import com.lge.lgdata.Operator;
import com.lge.pcas.PCASInfo;

public interface LgeAutoProfilingConstants {
    public static final String SYSTEM_PROP_AUTOPROFILE_ECCLIST = "ril.ecclist.autoprofile";
    public static final String SYSTEM_PROP_AUTOPROFILE_ECC_IDLEMODE = "ril.ecclist.eccidlemode";
    public static final String SYSTEM_PROP_CUPSS_ROOTDIR = "ro.lge.capp_cupss.rootdir";
    public static final String SYSTEM_PROP_CUPSS_SUBCA = "persist.sys.cupss.subca-prop";
    public static final String SYSTEM_PROP_DEBUGGABLE = "ro.debuggable";
    public static final String SYSTEM_PROP_MCC_FOR_ONE_IMAGE = "persist.sys.iccid-mcc";
    public static final String SYSTEM_PROP_TARGET_COUNTRY = "ro.build.target_country";
    public static final String SYSTEM_PROP_TARGET_OPERATOR = "ro.build.target_operator";
    public static final String SYSTEM_PROP_TEST_COUNTRY = "persist.callfrw.test_country";
    public static final String SYSTEM_PROP_TEST_OPERATOR = "persist.callfrw.test_operator";
    public static final String SYSTEM_PROP_TEST_REGION = "persist.callfrw.test_region";
    public static final String COUNTRY = SystemProperties.get(SYSTEM_PROP_TEST_COUNTRY, PCASInfo.getConstCountry());
    public static final String OPERATOR = SystemProperties.get(SYSTEM_PROP_TEST_OPERATOR, PCASInfo.getConstOperator());
    public static final String PCAS_CONST_COUNTRY = PCASInfo.getConstCountry();

    public static final String ATTR_NAME_COUNTRY = "country";
    public static final String ATTR_NAME_DEFAULT = "default";
    public static final String ATTR_NAME_GID = "gid";
    public static final String ATTR_NAME_IMSI_RANGE = "imsi";
    public static final String ATTR_NAME_KEY = "name";
    public static final String ATTR_NAME_MCC = "mcc";
    public static final String ATTR_NAME_MNC = "mnc";
    public static final String ATTR_NAME_OPERATOR = "operator";
    public static final String ATTR_NAME_SPN = "spn";
    public static final boolean AUTO_PROFILE_ENABLED = true;
    public static final String CMCC_OPERATOR = "CMCC";
    public static final String CTC_OPERATOR = "CTC";
    public static final String CUCC_OPERATOR = "CUCC";
    public static final String ELEMENT_NAME_COMMONPROFILE = "CommonProfile";
    public static final String ELEMENT_NAME_FEATURESET = "FeatureSet";
    public static final String ELEMENT_NAME_ITEM = "item";
    public static final String ELEMENT_NAME_PROFILE = "profile";
    public static final String ELEMENT_NAME_PROFILES = "profiles";
    public static final String ELEMENT_NAME_SIMINFO = "siminfo";
    public static final boolean ENABLED_DRA = (SystemProperties.get("persist.ltdra.enable", "0").equals("1"));
    public static final boolean ENABLED_LGIQC = (SystemProperties.get("persist.lgiqc.ext", "0").equals("1"));
    public static final int FEATURE = 2;
    public static final String FILE_PATH_COTA_PROFILE = "data/shared/cust/config/telephony.xml";
    public static final String FILE_PATH_CUPSS_PROFILE = (SystemProperties.get(SYSTEM_PROP_CUPSS_ROOTDIR, "/OP") + "/config/telephony.xml");
    public static final String FILE_PATH_FEATURE = "/etc/featureset.xml";
    public static final String FILE_PATH_FEATURE_CMCC = "/etc/featureset_cmcc.xml";
    public static final String FILE_PATH_FEATURE_CTC = "/etc/featureset_ct.xml";
    public static final String FILE_PATH_FEATURE_HK = "/etc/featureset_hk.xml";
    public static final String FILE_PATH_FEATURE_OPEN = "/etc/featuresetOpen.xml";
    public static final String FILE_PATH_FEATURE_OPEN_VENDOR = "/vendor/etc/featuresetOpen.xml";
    public static final String FILE_PATH_FEATURE_VENDOR = "/vendor/etc/featureset.xml";
    public static final String FILE_PATH_PROFILE = "/etc/telephony.xml";
    public static final String FILE_PATH_PROFILE_VENDOR = "/vendor/etc/telephony.xml";
    public static final String HK_OPERATOR = "HK";
    public static final boolean IS_GLOBAL_COUNTRY = (Country.KR.equals(COUNTRY) || Country.US.equals(COUNTRY) || Country.CA.equals(COUNTRY) || Country.JP.equals(COUNTRY) || Country.CN.equals(COUNTRY));
    public static final boolean IS_GLOBAL_COUNTRY_IN_PCAS = (!(Country.KR.equals(PCAS_CONST_COUNTRY) || Country.US.equals(PCAS_CONST_COUNTRY) || Country.CA.equals(PCAS_CONST_COUNTRY) || Country.JP.equals(PCAS_CONST_COUNTRY)) && Country.CN.equals(PCAS_CONST_COUNTRY));
    public static final boolean IS_SUPPORT_NAOP = (Operator.NAO.equals(OPERATOR) && Country.US.equals(COUNTRY));
    public static final boolean IS_NAOP_BASED_ATT = (IS_SUPPORT_NAOP && SystemProperties.get("ro.lge.sku_carrier").equalsIgnoreCase(Operator.ATT));
    public static final boolean IS_NAOP_BASED_TMUS = (IS_SUPPORT_NAOP && SystemProperties.get("ro.lge.sku_carrier").equalsIgnoreCase("TMUS"));
    public static final boolean IS_SIM_OPERATOR_PROP_SUPPORT = SystemProperties.get("ro.lge.sim.operator.use", "false").equals("true");
    public static final String ITEM_NAME_BUILD_DATE = "build_date";
    public static final String ITEM_NAME_COTA = "cota_updated";
    public static final String ITEM_NAME_GID = "gid";
    public static final String ITEM_NAME_IMSI = "imsi";
    public static final String ITEM_NAME_MCC = "mcc";
    public static final String ITEM_NAME_MNC = "mnc";
    public static final String ITEM_NAME_SPN = "spn";
    public static final String KEY_ASRVCC = "aSRVCC";
    public static final String KEY_BSRVCC = "bSRVCC";
    public static final String KEY_CONF_SUB = "ConfSub";
    public static final String KEY_ECC_IDLE_MODE = "ECC_IdleMode";
    public static final String KEY_ECC_LIST = "ECC_list";
    public static final String KEY_FULL_TBCW = "FULL_TBCW";
    public static final String KEY_IS_NONLTE_SIM = "isNonLTESim";
    public static final String KEY_MID_SRVCC = "MidCall_SRVCC";
    public static final String KEY_NO_SIM_ECCLIST = "no_sim_ecclist";
    public static final String KEY_ROAMING_ICON_HIDE = "ROAMING_ICON_HIDE";
    public static final String KEY_RVMS = "RVMS";
    public static final String KEY_SHORTCODE_CALL = "ShortCodeCall";
    public static final String KEY_SIM_LOCK_ECCLIST = "sim_lock_ecclist";
    public static final String KEY_SRVCC = "SRVCC_Support";
    public static final String KEY_TBCW = "TBCW";
    public static final String KEY_TBClir = "TBClir";
    public static final String KEY_VMS = "VMS";
    public static final int LOG_DIALSTRING = 16;
    public static final int LOG_IDENTITY = 256;
    public static final int LOG_USSD = 1;
    public static final boolean MULTI_SIM_ENABLED = TelephonyManager.getDefault().isMultiSimEnabled();
    public static final String OTHER_OPERATOR = "OTHER";
    public static final String PREF_NAME_FEATURE = "feature";
    public static final String PREF_NAME_PROFILE = "profile_";
    public static final String PREF_NAME_PROFILE_DEFAULT = "defaultProfile";
    public static final String PREF_NAME_SIM_INFO = "simInfo_";
    public static final String PRIVATE_LOG_PROP = "persist.service.plog.enable";
    public static final String PRIVATE_LOG_TMUS_PROP = "persist.service.privacy.enable";
    public static final int PROFILE = 1;
    public static final int PROFILE_COTA = 3;
    public static final String PROPERTY_BUILD_DATE = "ro.build.date";
    public static final String PROPERTY_GSM_COTA = "persist.radio.gsm.cota";
    public static final String REGION = SystemProperties.get(SYSTEM_PROP_TEST_REGION, PCASInfo.getConstRegion());
    public static final String SUPPORT_ASRVCC = "1";
    public static final String SUPPORT_BSRVCC = "1";
    public static final String SUPPORT_MID_SRVCC = "1";
    public static final String SUPPORT_SRVCC = "1";
    public static final String SUPPORT_TB_CLIR = "1";
    public static final String TAG = "TelephonyAutoProfiling";
    public static final boolean USE_DEFAULT_PROFILE_XML = false;
    public static final boolean USE_PREFERENCES_FEATURE = false;
    public static final boolean USE_PREFERENCES_PROFILE = true;
    public static final boolean USE_PREFERENCES_PROFILE_DEFAULT = true;

/*
    static {
        boolean z;
        int i;
        boolean z2 = false;
        int i2 = 1;
        if (SystemProperties.get("persist.ltdra.enable", "0").equals("1")) {
            z = true;
        } else {
            z = false;
        }
        ENABLED_DRA = z;
        if (Country.KR.equals(COUNTRY) || Country.US.equals(COUNTRY) || Country.CA.equals(COUNTRY) || Country.JP.equals(COUNTRY)) {
            i = 1;
        } else {
            i = Country.CN.equals(COUNTRY);
        }
        IS_GLOBAL_COUNTRY = i ^ 1;
        if (Operator.NAO.equals(OPERATOR)) {
            z = Country.US.equals(COUNTRY);
        } else {
            z = false;
        }
        IS_SUPPORT_NAOP = z;
        if (IS_SUPPORT_NAOP && SystemProperties.get("ro.lge.sku_carrier").equalsIgnoreCase("TMUS")) {
            z = true;
        } else {
            z = false;
        }
        IS_NAOP_BASED_TMUS = z;
        if (IS_SUPPORT_NAOP && SystemProperties.get("ro.lge.sku_carrier").equalsIgnoreCase(Operator.ATT)) {
            z2 = true;
        }
        IS_NAOP_BASED_ATT = z2;
        if (!(Country.KR.equals(PCAS_CONST_COUNTRY) || Country.US.equals(PCAS_CONST_COUNTRY) || Country.CA.equals(PCAS_CONST_COUNTRY) || Country.JP.equals(PCAS_CONST_COUNTRY))) {
            i2 = Country.CN.equals(PCAS_CONST_COUNTRY);
        }
        IS_GLOBAL_COUNTRY_IN_PCAS = i2 ^ 1;
    }
*/
}
