package com.lge.pcas;

import android.os.Build;
import android.os.SystemProperties;
import com.lge.lgdata.Operator;

public class PCASConstants {
    protected static final String BASEBAND_NAME = Build.BOARD;
    protected static final String BASEBAND_VENDOR = SystemProperties.get("ro.lge.chip.vendor", "qct");
    protected static final String BUILD_TYPE = SystemProperties.get("ro.build.type");
    protected static final String CARD_OPERATOR_PROP = "ril.card_operator";
    protected static final String COUNTRY = SystemProperties.get("ro.build.target_country", "unknown");
    protected static final String DEVICE = Build.DEVICE;
    protected static final boolean LAOP_BUILD = SystemProperties.getBoolean("ro.lge.laop", false);
    protected static final String LAOP_DEFAULT_OPERATOR = SystemProperties.get("ro.lge.laop.default.operator", Operator.OPEN);
    protected static final String LAOP_PROP_COUNTRY_POSTFIX = ".country";
    protected static final String LAOP_PROP_OPERATOR_POSTFIX = ".operator";
    protected static final String LAOP_PROP_PREFIX = "persist.sys.sim";
    protected static final boolean LAOP_SIM_OPERATOR_USE;
    protected static final boolean LEGACY_NAOP = (!SystemProperties.getBoolean("ro.lge.naop.legacy", false) ? SystemProperties.getBoolean("ro.lge.naop", false) : true);
    protected static final String OPERATOR = SystemProperties.get("ro.build.target_operator", "unknown");
    protected static final boolean PCAS_SIM_OPERATOR_TEST = SystemProperties.getBoolean("persist.lge.pcas.test.simop", false);
    protected static final String REGION = SystemProperties.get("ro.build.target_region", "unknown");
    protected static final String SMART_CA_COUNTRY_PROP = "persist.sys.cota.country";
    protected static final String SMART_CA_OPERATOR_PROP = "persist.sys.cota.operator";

    private PCASConstants() {
    }

    static {
        boolean z = true;
        if (!SystemProperties.getBoolean("ro.lge.sim.operator.use", LAOP_BUILD)) {
            z = LEGACY_NAOP;
        }
        LAOP_SIM_OPERATOR_USE = z;
    }
}
