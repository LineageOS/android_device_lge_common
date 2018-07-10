package com.lge.uicc.framework;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.AsyncResult;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import com.android.internal.telephony.gsm.LgeNetworkNameConstants;
import com.lge.pcas.PCASInfo;
import com.lge.uicc.Plog;
import com.lge.uicc.framework.LGUICC.ConfigListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class LGUICC {
    private static final String PREFERENCE_NAME = "lguicc";
    private static int SLOTS = 1;
    private static final String TAG = "LGUICC";
    private static final String TARGET_COUNTRY = SystemProperties.get(LgeNetworkNameConstants.PROP_COUNTRY, "COM");
    private static final String TARGET_OPERATOR = SystemProperties.get(LgeNetworkNameConstants.PROP_OPERATOR, "OPEN");
    private static final String TARGET_OPERATOR_EXT = SystemProperties.get("ro.build.target_operator_ext", "");
    public static final boolean NAOP = "NAO".equals(TARGET_OPERATOR);
    private static final String TARGET_COMBO = (TARGET_OPERATOR + "@" + TARGET_COUNTRY);
    public static final boolean USERDEBUG = Build.TYPE.equals("userdebug");
    public static final boolean hotswapCapable = Resources.getSystem().getBoolean(17956968);
    public static final boolean isMtkBoard = Build.BOARD.startsWith("mt");
    public static final boolean isQctBoard;
    private static Context mContext;
    private static TreeMap<String, MyRegistrantList> mRegistrantsData = new TreeMap();
    private static TreeMap<String, String> mUiccData = new TreeMap();

    public interface ConfigListener {
        void onConfigChanged(String str, int i, String str2);
    }

    static native String dumpFeatureList();

    static native boolean getFeatureEnabled(String str);

    static native boolean makeDefaultFeatureList(boolean z);

    static native boolean resetFeatureList();

    static native boolean setFeatureEnabled(String str, boolean z);

    static {
        boolean z = true;
        System.loadLibrary("uicc");
        if (!(Build.BOARD.startsWith("msm") || Build.BOARD.startsWith("apq"))) {
            z = Build.BOARD.startsWith("sd");
        }
        isQctBoard = z;
    }

    private LGUICC() {
    }

    public static boolean cotaOperator(String... operators) {
        return PCASInfo.isOperator(operators);
    }

    public static boolean simOperator(String... operators) {
        return PCASInfo.isOperator(operators);
    }

    public static boolean targetOperator(String... operators) {
        if (PCASInfo.isConstOperator(new String[]{"TRF"})) {
            return PCASInfo.isOperator(operators);
        }
        return PCASInfo.isConstOperator(operators);
    }

    public static boolean targetCountry(String... countries) {
        return PCASInfo.isConstCountry(countries);
    }

    public static boolean cardCarrier(String... operators) {
        return PCASInfo.isOperator(operators);
    }

    protected static void setup(Context c, int count) {
        if (count > 0 && count <= 3) {
            mContext = c;
            SLOTS = count;
            setConfig("#target", TARGET_COMBO);
            setConfig("#hotswapCapable", String.valueOf(hotswapCapable));
        }
    }

    private static String[] splitValues(String values) {
        String[] v = new String[SLOTS];
        if (values != null) {
            int n = 0;
            int size = values.length();
            int start = 0;
            while (n < v.length && start < size) {
                int end = values.indexOf(44, start);
                if (end < 0) {
                    end = size;
                }
                int n2 = n + 1;
                v[n] = values.substring(start, end);
                start = end + 1;
                n = n2;
            }
        }
        return v;
    }

    private static String joinValues(String[] v) {
        String values = v[0] == null ? "" : v[0];
        for (int n = 1; n < v.length; n++) {
            values = values + ",";
            if (v[n] != null) {
                values = values + v[n];
            }
        }
        return values;
    }

    public static String removeConfig(String key) {
        return setConfig(key, -1, null);
    }

    public static String removeConfig(String key, int slotId) {
        return setConfig(key, slotId, null);
    }

    public static String setConfig(String key, String val) {
        return setConfig(key, -1, val);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized String setConfig(String key, int slotId, String value) {
        synchronized (LGUICC.class) {
            if (key != null) {
                if (key.length() != 0) {
                    boolean remove_config;
                    String oldValue;
                    if (value != null) {
                        if (value.length() != 0) {
                            remove_config = false;
                            if (slotId > 0 || SLOTS != 1) {
                                if (key.charAt(0) != '#') {
                                    if (slotId >= 0 || SLOTS <= 1) {
                                    } else {
                                        String[] v = splitValues((String) mUiccData.get(key));
                                        if (slotId >= v.length) {
                                            return null;
                                        }
                                        oldValue = v[slotId];
                                        v[slotId] = value;
                                        String values = joinValues(v);
                                        if (values.length() < v.length) {
                                            mUiccData.remove(key);
                                        } else {
                                            mUiccData.put(key, values);
                                        }
                                        if (!remove_config) {
                                            notifyRegistrantsConfigChanged(key, slotId, value);
                                        }
                                    }
                                }
                            }
                            if (remove_config) {
                                oldValue = (String) mUiccData.put(key, value);
                            } else {
                                oldValue = (String) mUiccData.remove(key);
                            }
                            slotId = 0;
                            if (remove_config) {
                                notifyRegistrantsConfigChanged(key, slotId, value);
                            }
                        }
                    }
                    remove_config = true;
                    if (key.charAt(0) != '#') {
                        if (slotId >= 0) {
                        }
                    }
                    if (remove_config) {
                        oldValue = (String) mUiccData.put(key, value);
                    } else {
                        oldValue = (String) mUiccData.remove(key);
                    }
                    slotId = 0;
                    if (remove_config) {
                        notifyRegistrantsConfigChanged(key, slotId, value);
                    }
                }
            }
        }
    }

    public static String getConfig(String key) {
        return getConfig(key, -1, "");
    }

    public static String getConfig(String key, String def) {
        return getConfig(key, -1, def);
    }

    public static String getConfig(String key, int slotId) {
        return getConfig(key, slotId, "");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized String getConfig(String key, int slotId, String def) {
        synchronized (LGUICC.class) {
            if (key != null) {
                if (key.length() != 0) {
                    String value;
                    if (slotId <= 0) {
                    }
                    if (key.charAt(0) != '#') {
                        if (slotId >= 0 && SLOTS > 1) {
                            String values = (String) mUiccData.get(key);
                            if (values != null) {
                                String[] v = splitValues(values);
                                if (slotId < v.length) {
                                    value = v[slotId];
                                    if (!(value == null || value.length() == 0)) {
                                        return value;
                                    }
                                }
                            }
                        }
                    }
                    value = (String) mUiccData.get(key);
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized String getConfigAll(String key, String def) {
        synchronized (LGUICC.class) {
            if (key != null) {
                if (key.length() != 0) {
                    String values = (String) mUiccData.get(key);
                    if (values != null) {
                        def = values;
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void registerForConfig(String key, int slotId, String value, Handler h, Object what) {
        synchronized (LGUICC.class) {
            if (key != null) {
                if (!(key.length() == 0 || what == null)) {
                    MyRegistrantList rs = (MyRegistrantList) mRegistrantsData.get(key);
                    if (rs == null) {
                        rs = new MyRegistrantList();
                        mRegistrantsData.put(key, rs);
                    }
                    MyRegistrant r = new MyRegistrant(h, slotId, value, what);
                    rs.remove(h);
                    rs.add(r);
                    if (mUiccData.get(key) != null) {
                        notifyRegistrantConfigExisted(key, r);
                    }
                }
            }
        }
    }

    public static void registerForConfig(String key, Handler h, int what, String value) {
        registerForConfig(key, -1, value, h, Integer.valueOf(what));
    }

    public static void registerForConfig(String key, Handler h, ConfigListener cl) {
        registerForConfig(key, -1, null, h, cl);
    }

    public static void registerForConfig(String key, int slotId, Handler h, ConfigListener cl) {
        registerForConfig(key, slotId, null, h, cl);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void unregisterForConfig(String key, Handler h) {
        synchronized (LGUICC.class) {
            if (key != null) {
                if (key.length() != 0) {
                    MyRegistrantList rs = (MyRegistrantList) mRegistrantsData.get(key);
                    if (rs == null) {
                        return;
                    }
                    rs.remove(h);
                    if (rs.size() == 0) {
                        mRegistrantsData.remove(key);
                    }
                }
            }
        }
    }

    public static void notifyConfig(String key, String value) {
        notifyConfig(key, -1, value);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void notifyConfig(String key, int slotId, String value) {
        synchronized (LGUICC.class) {
            if (key != null) {
                if (!(key.length() == 0 || value == null)) {
                    if (slotId <= 0) {
                    }
                    if (key.charAt(0) != '#') {
                        if (slotId >= SLOTS) {
                            return;
                        }
                        notifyRegistrantsConfigChanged(key, slotId, value);
                    }
                    slotId = 0;
                    notifyRegistrantsConfigChanged(key, slotId, value);
                }
            }
        }
    }

    private static void notifyRegistrantConfigExisted(String key, MyRegistrant r) {
        int slotId = 0;
        while (slotId < SLOTS) {
            String value = getConfig(key, slotId, null);
            if (value != null && ((r.value == null || value.equals(r.value)) && (r.slot == -1 || r.slot == slotId))) {
                r.notifyResult(key, slotId, value);
            }
            slotId++;
        }
    }

    private static void notifyRegistrantsConfigChanged(String key, int slotId, String value) {
        MyRegistrantList rs = (MyRegistrantList) mRegistrantsData.get(key);
        if (rs != null && value != null) {
            int s = rs.size();
            for (int i = 0; i < s; i++) {
                MyRegistrant r = rs.get(i);
                if ((r.value == null || value.equals(r.value)) && (r.slot == -1 || r.slot == slotId)) {
                    r.notifyResult(key, slotId, value);
                }
            }
        }
    }

    public static void removePreference(String key) {
        setPreference(key, null);
    }

    public static void setPreference(String key, String value) {
        if (key != null && key.length() != 0) {
            if (mContext == null) {
                loge("setPreference: fails to get context : " + key);
                return;
            }
            Editor editor = mContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
            if (value == null || value.length() == 0) {
                editor.remove(key);
            } else {
                editor.putString(key, value);
            }
            editor.commit();
        }
    }

    public static String getPreference(String key) {
        return getPreference(key, "");
    }

    public static String getPreference(String key, String def) {
        if (key == null || key.length() == 0) {
            return def;
        }
        if (mContext != null) {
            return mContext.getSharedPreferences(PREFERENCE_NAME, 0).getString(key, def);
        }
        loge("getPreference: fails to get context: " + key);
        return def;
    }

    public static boolean hasFeature(String... feats) {
        for (String feat : feats) {
            if (getFeatureEnabled(feat)) {
                return true;
            }
        }
        return false;
    }

    public static void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println("LGUICC Config:");
        for (Entry<String, String> e : mUiccData.entrySet()) {
            pw.println("[" + ((String) e.getKey()) + "]: [" + ((String) e.getValue()) + "]");
        }
        if (args.length != 0 && args[0].equals("all")) {
            pw.println("\nLGUICC Registrants:");
            for (Entry<String, MyRegistrantList> e2 : mRegistrantsData.entrySet()) {
                pw.println("[" + ((String) e2.getKey()) + "]:");
                MyRegistrantList rs = (MyRegistrantList) e2.getValue();
                for (int i = 0; i < rs.size(); i++) {
                    MyRegistrant r = rs.get(i);
                    Handler h = r.getHandler();
                    if (h != null) {
                        pw.println("  [" + r.value + "] -> " + h.getClass().getName());
                    }
                }
            }
            if (mContext != null) {
                pw.println("\nLGUICC Preferences:");
                for (Entry<String, String> e3 : ((HashMap) mContext.getSharedPreferences(PREFERENCE_NAME, 0).getAll()).entrySet()) {
                    pw.println("{" + ((String) e3.getKey()) + "}: {" + ((String) e3.getValue()) + "}");
                }
            }
        }
    }

    public static String traceStack(int trace_count) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StringBuffer trace = new StringBuffer();
        int s = 3;
        while (s < stack.length && s < trace_count + 3) {
            trace.append(stack[s].toString() + "\n");
            s++;
        }
        return trace.toString();
    }

    public static void logd(String s) {
        Plog.d(TAG, s);
    }

    public static void loge(String s) {
        Plog.e(TAG, s);
    }

    public static void logp(String s) {
        Plog.d(TAG, s);
    }
}

class MyRegistrant {
    WeakReference refH;
    int slot;
    String value;
    Object what;

    MyRegistrant(Handler h, int s, String v, Object w) {
        this.refH = new WeakReference(h);
        this.slot = s;
        this.value = v;
        this.what = w;
    }

    void notifyResult(final String key, final int slot, final String value) {
        Handler h = getHandler();
        if (h == null) {
            this.refH = null;
        } else if (this.what instanceof ConfigListener) {
            Message.obtain(h, new Runnable() {
                final ConfigListener cl = ((ConfigListener) MyRegistrant.this.what);

                public void run() {
                    this.cl.onConfigChanged(key, slot, value);
                }
            }).sendToTarget();
        } else if (this.what instanceof Integer) {
            Message.obtain(h, ((Integer) this.what).intValue(), new AsyncResult(null, Integer.valueOf(slot), null)).sendToTarget();
        }
    }

    Handler getHandler() {
        return this.refH == null ? null : (Handler) this.refH.get();
    }
}

class MyRegistrantList {
    ArrayList registrants = new ArrayList();

    MyRegistrantList() {
    }

    void add(MyRegistrant r) {
        removeCleared();
        this.registrants.add(r);
    }

    void remove(Handler h) {
        int s = this.registrants.size();
        for (int i = 0; i < s; i++) {
            MyRegistrant r = (MyRegistrant) this.registrants.get(i);
            if (r.getHandler() == h) {
                r.refH = null;
            }
        }
        removeCleared();
    }

    MyRegistrant get(int index) {
        return (MyRegistrant) this.registrants.get(index);
    }

    int size() {
        return this.registrants.size();
    }

    private void removeCleared() {
        for (int i = this.registrants.size() - 1; i >= 0; i--) {
            if (((MyRegistrant) this.registrants.get(i)).refH == null) {
                this.registrants.remove(i);
            }
        }
    }
}
