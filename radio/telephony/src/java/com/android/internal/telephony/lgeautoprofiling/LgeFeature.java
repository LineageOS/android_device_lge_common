package com.android.internal.telephony.lgeautoprofiling;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.android.internal.telephony.lgeautoprofiling.LgeProfileParser.NameValueProfile;
import java.util.HashMap;
import java.util.Map.Entry;

public class LgeFeature implements LgeAutoProfilingConstants {
    private static final boolean DBG = true;
    private static final boolean EDBG = true;
    private static final boolean VDBG = true;
    private static LgeFeature instance;
    private Context mContext;
    private HashMap<String, String> mFeature;

    private LgeFeature(Context context) {
        this.mContext = context;
    }

    public static LgeFeature getInstance(Context context) {
        if (instance == null) {
            instance = new LgeFeature(context);
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

    public void loadFeature() {
        if (LgeAutoProfiling.isOperator("CNO") && this.mFeature != null) {
            this.mFeature.clear();
        }
        if (IS_SUPPORT_NAOP && this.mFeature != null) {
            this.mFeature.clear();
        }
        Context context = this.mContext;
        loadFeatureFromXml();
    }

    private boolean loadFeatureFromPreferences() {
        SharedPreferences preferences = this.mContext.getSharedPreferences(LgeAutoProfilingConstants.PREF_NAME_FEATURE, 0);
        if (preferences.getString("init", null) == null) {
            Log.e(LgeAutoProfilingConstants.TAG, "[loadFeatureFromPreferences] preferences do not exist");
            return false;
        }
        this.mFeature = (HashMap) preferences.getAll();
        this.mFeature.remove("init");
        Log.v(LgeAutoProfilingConstants.TAG, "[loadFeatureFromPreferences] load from preferences complete - " + this.mFeature);
        return true;
    }

    private void loadFeatureFromXml() {
        Log.v(LgeAutoProfilingConstants.TAG, "[loadFeatureFromXml] *** start feature loading from xml");
        NameValueProfile featureFromXml = (NameValueProfile) new LgeProfileParser().getMatchedProfile(2, null);
        if (featureFromXml == null) {
            Log.e(LgeAutoProfilingConstants.TAG, "[loadFeatureFromXml] load feature from xml failed");
            return;
        }
        this.mFeature = LgeProfileParser.profileToMap(featureFromXml);
        Context context = this.mContext;
        Log.v(LgeAutoProfilingConstants.TAG, "[loadFeatureFromXml] load feature from xml complete : " + this.mFeature);
    }

    private void saveFeatureToPreferences(HashMap<String, String> featureMap) {
        Editor editor = this.mContext.getSharedPreferences(LgeAutoProfilingConstants.PREF_NAME_FEATURE, 0).edit();
        Log.d(LgeAutoProfilingConstants.TAG, "[saveFeatureToPreferences] save to file : feature");
        editor.clear();
        editor.putString("init", "done");
        for (Entry<String, String> entry : featureMap.entrySet()) {
            editor.putString((String) entry.getKey(), (String) entry.getValue());
        }
        editor.apply();
    }

    public String getValue(String key) {
        String str = null;
        if (this.mFeature == null) {
            loadFeature();
        }
        if (this.mFeature != null) {
            str = (String) this.mFeature.get(key);
        }
        return str == null ? "" : str;
    }

    public HashMap<String, String> getFeature() {
        return this.mFeature;
    }
}
