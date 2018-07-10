package com.android.internal.telephony.gsm;

import android.os.SystemProperties;

public interface LgeNetworkNameConstants {
    public static final String DELIMITER_MULTIPLE_VALUES = ";";
    public static final boolean IS_TEST_MODE = SystemProperties.getBoolean("persist.nwnametest", false);
    public static final String ITEM_COUNTRY = "country";
    public static final String ITEM_EMERGENCY_ONLY = "emergencyOnly";
    public static final String ITEM_GID = "gid";
    public static final String ITEM_IMSI = "imsi";
    public static final String ITEM_MCC = "mcc";
    public static final String ITEM_MNC = "mnc";
    public static final String ITEM_NETWORK_MCC = "networkMcc";
    public static final String ITEM_NETWORK_NAME_TEST_MODE = "networkNameTest";
    public static final String ITEM_NETWORK_OPERATOR = "networkOperator";
    public static final String ITEM_OPERATOR = "operator";
    public static final String ITEM_OPERATOR_NUMERIC = "operatorNumeric";
    public static final String ITEM_PLMN = "plmn";
    public static final String ITEM_REGION = "region";
    public static final String ITEM_ROAMING = "roaming";
    public static final String ITEM_SERVICE_STATE = "serviceState";
    public static final String ITEM_SHOW_PLMN = "showPlmn";
    public static final String ITEM_SHOW_SPN = "showSpn";
    public static final String ITEM_SPN = "spn";
    public static final String ITEM_VALUE_FUNCTION_GET_OPERTOR_ALPHA_LONG = "LONG_NAME";
    public static final String ITEM_VALUE_FUNCTION_GET_OPERTOR_ALPHA_SHORT = "SHORT_NAME";
    public static final String ITEM_VALUE_NOT_NULL = "not null";
    public static final String ITEM_VALUE_NULL = "null";
    public static final String PROP_COUNTRY = "ro.build.target_country";
    public static final String PROP_NETWORK_NAME_TEST_DBG = "persist.nwnametest.dbg";
    public static final String PROP_NETWORK_NAME_TEST_MODE_COUNTRY = "persist.nwnametest.country";
    public static final String PROP_NETWORK_NAME_TEST_MODE_EMERGENCY_ONLY = "persist.nwnametest.emergonly";
    public static final String PROP_NETWORK_NAME_TEST_MODE_GID = "persist.nwnametest.gid";
    public static final String PROP_NETWORK_NAME_TEST_MODE_IMSI = "persist.nwnametest.imsi";
    public static final String PROP_NETWORK_NAME_TEST_MODE_MCC = "persist.nwnametest.mcc";
    public static final String PROP_NETWORK_NAME_TEST_MODE_MNC = "persist.nwnametest.mnc";
    public static final String PROP_NETWORK_NAME_TEST_MODE_NETWORK_MCC = "persist.nwnametest.networkmcc";
    public static final String PROP_NETWORK_NAME_TEST_MODE_NETWORK_OPERATOR = "persist.nwnametest.nwoperator";
    public static final String PROP_NETWORK_NAME_TEST_MODE_OPERATOR = "persist.nwnametest.operator";
    public static final String PROP_NETWORK_NAME_TEST_MODE_OPERATOR_NUMERIC = "persist.nwnametest.opnumeric";
    public static final String PROP_NETWORK_NAME_TEST_MODE_PLMN = "persist.nwnametest.plmn";
    public static final String PROP_NETWORK_NAME_TEST_MODE_REGION = "persist.nwnametest.region";
    public static final String PROP_NETWORK_NAME_TEST_MODE_ROAMING = "persist.nwnametest.roaming";
    public static final String PROP_NETWORK_NAME_TEST_MODE_SERVICE_STATE = "persist.nwnametest.ss";
    public static final String PROP_NETWORK_NAME_TEST_MODE_SHOW_PLMN = "persist.nwnametest.showplmn";
    public static final String PROP_NETWORK_NAME_TEST_MODE_SHOW_SPN = "persist.nwnametest.showspn";
    public static final String PROP_NETWORK_NAME_TEST_MODE_SPN = "persist.nwnametest.spn";
    public static final String PROP_NETWORK_NAME_TEST_VDBG = "persist.nwnametest.vdbg";
    public static final String PROP_OPERATOR = "ro.build.target_operator";
    public static final String PROP_REGION = "ro.build.target_region";
    public static final boolean SUPPORT_MULTIPLE_REQUIREMENT = true;
    public static final String TAG_NAME_CONDITION = "condition";
    public static final String TAG_NAME_CORRECTION = "correction";
    public static final String XML_PATH = "/system/etc/networkNameMod.xml";

    public static final boolean DBG = SystemProperties.getBoolean(PROP_NETWORK_NAME_TEST_DBG, false);
    public static final boolean VDBG = SystemProperties.getBoolean(PROP_NETWORK_NAME_TEST_VDBG, true);
}
