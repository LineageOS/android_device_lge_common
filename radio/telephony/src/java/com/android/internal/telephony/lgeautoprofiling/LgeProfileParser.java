package com.android.internal.telephony.lgeautoprofiling;

import android.content.res.Resources;
import android.os.SystemProperties;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LgeProfileParser implements LgeAutoProfilingConstants {
    private static final boolean DBG = false;
    private static final boolean EDBG = true;
    private static final boolean VDBG = true;
    private static boolean checkLoadXmlDBG = false;
    private boolean mUsingfeatureOpen = false;

    private class MatchedProfile {
        public ProfileData mBestMatchedProfile;
        public ProfileData mCandidateProfile;
        public ProfileData mDefaultProfile;

        private MatchedProfile() {
        }
    }

    public static class ProfileData {
    }

    public static class NameValueProfile extends ProfileData {
        private HashMap<String, String> mNameValueMap = new HashMap();

        public void setValue(String key, String value) {
            this.mNameValueMap.put(key, value);
        }

        public String getValue(String key) {
            return (String) this.mNameValueMap.get(key);
        }

        public String getValue(String key, String defaultValue) {
            if (this.mNameValueMap.containsKey(key)) {
                return (String) this.mNameValueMap.get(key);
            }
            return defaultValue;
        }

        public void remove(String key) {
            this.mNameValueMap.remove(key);
        }

        public HashMap<String, String> getValueMap() {
            return this.mNameValueMap;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String key : this.mNameValueMap.keySet()) {
                sb.append(key).append("=").append((String) this.mNameValueMap.get(key)).append("\n");
            }
            return sb.toString();
        }
    }

    protected final void beginDocument(XmlPullParser parser, String firstElementName) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String first = parser.getName();
        if (!firstElementName.equals(first)) {
            throw new XmlPullParserException("Unexpected start tag: found " + first + ", expected " + firstElementName);
        }
    }

    protected final void nextElement(XmlPullParser parser) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                return;
            }
        } while (type != 1);
    }

    protected final void skipCurrentElement(XmlPullParser parser) throws XmlPullParserException, IOException {
        nextElement(parser);
        if (!LgeAutoProfilingConstants.ELEMENT_NAME_SIMINFO.equals(parser.getName()) && !"profile".equals(parser.getName())) {
            while (parser.next() != 1) {
                if ("item".equals(parser.getName())) {
                    nextElement(parser);
                }
                if ("profile".equals(parser.getName())) {
                    break;
                }
            }
        }
    }

    protected ProfileData mergeProfileIfNeeded(ProfileData globalProfile, ProfileData matchedProfile, ProfileData featureProfile) {
        if (globalProfile == null && featureProfile == null && matchedProfile == null) {
            return null;
        }
        if (matchedProfile != null) {
            return mergeProfile(globalProfile, matchedProfile, featureProfile);
        }
        if (featureProfile != null) {
            return mergeProfile(globalProfile, featureProfile, null);
        }
        return globalProfile;
    }

    protected boolean existInTokens(String string, String v) {
        if (string == null) {
            return false;
        }
        StringTokenizer st = new StringTokenizer(string, ",");
        while (st.hasMoreTokens()) {
            if (st.nextToken().equals(v)) {
                return true;
            }
        }
        return false;
    }

    protected boolean existInTokens(String string, String v, int len) {
        if (string == null || v == null) {
            return false;
        }
        int final_length;
        int xml_length = string.length();
        if (xml_length > len) {
            final_length = len;
        } else {
            final_length = xml_length;
        }
        if (string.substring(0, final_length).equalsIgnoreCase(v.substring(0, final_length))) {
            return true;
        }
        return false;
    }

    protected boolean matchMccMnc(LgeSimInfo simInfo, String mccParsed, String mncParsed) {
        if (simInfo != null) {
            String mcc = simInfo.getMcc();
            String mnc = simInfo.getMnc();
            if (!TextUtils.isEmpty(mcc) && !TextUtils.isEmpty(mnc) && existInTokens(mccParsed, mcc) && existInTokens(mncParsed, mnc)) {
                return true;
            }
        }
        return false;
    }

    protected boolean matchExtension(LgeSimInfo simInfo, String gidParsed, String spnParsed, String imsiParsed) {
        if (simInfo == null) {
            return false;
        }
        String gid = simInfo.getGid();
        String spn = simInfo.getSpn();
        String imsi = simInfo.getImsi();
        if (TextUtils.isEmpty(gid) && TextUtils.isEmpty(spn) && TextUtils.isEmpty(imsi)) {
            return false;
        }
        if (!TextUtils.isEmpty(gid)) {
            int gidLength = gid.length();
            if (gidParsed != null && ("00".equals(gid) || !existInTokens(gidParsed, gid, gidLength))) {
                return false;
            }
        } else if (gidParsed != null) {
            return false;
        }
        if (spn != null) {
            if (!(spnParsed == null || !existInTokens(spnParsed, spn))) {
                return false;
            }
        } else if (spnParsed != null) {
            return false;
        }
        if (imsiParsed == null || imsiParsed.length() == 0 || matchImsi(imsiParsed, imsi)) {
            return true;
        }
        return false;
    }

    protected boolean matchImsi(String imsiParsed, String imsi) {
        boolean found = false;
        if (imsi != null) {
            int imsiLength = imsi.length();
            StringTokenizer st = new StringTokenizer(imsiParsed, ",");
            while (!found && st.hasMoreTokens()) {
                String t = st.nextToken();
                int len = t.length();
                if (len <= imsiLength) {
                    int i = 0;
                    while (i < len) {
                        char c = t.charAt(i);
                        if (c != 'x' && c != 'X' && c != imsi.charAt(i)) {
                            break;
                        }
                        i++;
                    }
                    if (i == len) {
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProfileData getMatchedProfile(int type, XmlPullParser parser, LgeSimInfo simInfo) {
        ProfileData profileData = null;
        ProfileData featureProfile = null;
        MatchedProfile profile = new MatchedProfile();
        if (parser == null) {
            return null;
        }
        try {
            beginDocument(parser, LgeAutoProfilingConstants.ELEMENT_NAME_PROFILES);
            while (true) {
                if (LgeAutoProfilingConstants.ELEMENT_NAME_PROFILES.equals(parser.getName())) {
                    nextElement(parser);
                }
                if ("profile".equals(parser.getName())) {
                    nextElement(parser);
                }
                if (parser.getEventType() == 1) {
                    break;
                } else if (LgeAutoProfilingConstants.ELEMENT_NAME_SIMINFO.equals(parser.getName())) {
                    boolean found = getValidProfile(profile, parser, simInfo);
                    if ((simInfo != null && !simInfo.isNull()) || profile.mDefaultProfile == null) {
                        if (profile.mBestMatchedProfile != null) {
                            break;
                        } else if (!found) {
                            skipCurrentElement(parser);
                        }
                    } else {
                        break;
                    }
                } else if (LgeAutoProfilingConstants.ELEMENT_NAME_COMMONPROFILE.equals(parser.getName())) {
                    profileData = readProfile(parser);
                } else if (LgeAutoProfilingConstants.ELEMENT_NAME_FEATURESET.equals(parser.getName())) {
                    if (this.mUsingfeatureOpen) {
                        String operatorValue = parser.getAttributeValue(null, "operator");
                        if (operatorValue != null) {
                            ProfileData floatingProfile = getMatchedFeatureByCupssRootDir(parser, operatorValue, parser.getAttributeValue(null, "country"));
                            if (floatingProfile != null) {
                                break;
                            }
                        }
                    }
                    featureProfile = readProfile(parser);
                } else {
                    throw new XmlPullParserException("Unexpected tag: found " + parser.getName());
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        ProfileData validProfile = profile.mBestMatchedProfile != null ? profile.mBestMatchedProfile : profile.mCandidateProfile != null ? profile.mCandidateProfile : profile.mDefaultProfile;
        return mergeProfileIfNeeded(profileData, validProfile, featureProfile);
    }

    public ProfileData setParseDataPrio(String prio, XmlPullParser parser) {
        ProfileData profileData = null;
        try {
            profileData = readProfile(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        Log.d(LgeAutoProfilingConstants.TAG, prio + " is found");
        ((NameValueProfile) profileData).setValue("p", prio);
        return profileData;
    }

    public boolean getValidProfile(MatchedProfile profile, XmlPullParser parser, LgeSimInfo simInfo) {
        ProfileData profileData = null;
        boolean found = false;
        String mccValue = parser.getAttributeValue(null, "mcc");
        String mncValue = parser.getAttributeValue(null, "mnc");
        String operatorValue = parser.getAttributeValue(null, "operator");
        String countryValue = parser.getAttributeValue(null, "country");
        String gidValue = parser.getAttributeValue(null, "gid");
        String spnValue = parser.getAttributeValue(null, "spn");
        String imsiValue = parser.getAttributeValue(null, "imsi");
        if ("true".equals(parser.getAttributeValue(null, "default"))) {
            if (profile.mDefaultProfile == null) {
                profileData = setParseDataPrio("3", parser);
                profile.mDefaultProfile = profileData;
            }
            found = true;
            if (simInfo == null || simInfo.isNull()) {
                return true;
            }
        }
        if (matchMccMnc(simInfo, mccValue, mncValue)) {
            if (profile.mCandidateProfile == null && TextUtils.isEmpty(gidValue) && TextUtils.isEmpty(spnValue) && TextUtils.isEmpty(imsiValue)) {
                if (profileData == null) {
                    profileData = setParseDataPrio("2", parser);
                }
                profile.mCandidateProfile = profileData;
                found = true;
            }
            if (profile.mBestMatchedProfile == null && (!(TextUtils.isEmpty(gidValue) && TextUtils.isEmpty(spnValue) && TextUtils.isEmpty(imsiValue)) && matchExtension(simInfo, gidValue, spnValue, imsiValue))) {
                Log.v(LgeAutoProfilingConstants.TAG, "[getMatchedProfile] Set bestMatchedProfile  -MCC : " + LgeAutoProfiling.privateLogHandler(mccValue, 256) + ", MNC : " + LgeAutoProfiling.privateLogHandler(mncValue, 256) + ", GID : " + LgeAutoProfiling.privateLogHandler(gidValue, 256) + ", SPN : " + LgeAutoProfiling.privateLogHandler(spnValue, 256) + ", IMSI : " + LgeAutoProfiling.privateLogHandler(imsiValue, 256));
                profile.mBestMatchedProfile = setParseDataPrio("1", parser);
                found = true;
            }
        }
        return found;
    }

    public ProfileData getMatchedProfile(int type, LgeSimInfo simInfo) {
        File file;
        ProfileData matchedProfile;
        File confFileOpen = null;
        String fileName;
        switch (type) {
            case 1:
            case 3:
                if (type == 3) {
                    file = new File(LgeAutoProfilingConstants.FILE_PATH_COTA_PROFILE);
                    if (!file.exists()) {
                        file = new File(FILE_PATH_CUPSS_PROFILE);
                    }
                } else {
                    fileName = SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_ROOTDIR, "/OP") + "/config/telephony_" + SystemProperties.get("persist.sys.sim.operator") + ".xml";
                    file = new File(fileName);
                    if (!file.exists()) {
                        file = new File(FILE_PATH_CUPSS_PROFILE);
                    }
                    Log.d(LgeAutoProfilingConstants.TAG, "Single parse telephony.xml, filename = " + fileName);
                }
                if ("/data/local/cust".equals(SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_ROOTDIR, "")) && "trigger_restart_min_framework".equals(SystemProperties.get("vold.decrypt", ""))) {
                    String cupssSubca = SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_SUBCA, "");
                    String confFile_path = "";
                    file = new File((cupssSubca.substring(0, cupssSubca.lastIndexOf("/") + 1) + "_config/config_" + OPERATOR + Session.SESSION_SEPARATION_CHAR_CHILD + COUNTRY) + "/telephony.xml");
                }
                if (!file.exists()) {
                    file = new File(LgeAutoProfilingConstants.FILE_PATH_PROFILE);
                    if (!file.exists()) {
                        file = new File(LgeAutoProfilingConstants.FILE_PATH_PROFILE_VENDOR);
                    }
                }
                matchedProfile = parse(file, type, simInfo);
                Log.d(LgeAutoProfilingConstants.TAG, "matchedProfile : " + matchedProfile);
                if (matchedProfile != null) {
                    ((NameValueProfile) matchedProfile).remove("p");
                    break;
                }
                break;
            case 2:
                ProfileData parse;
                if (!LgeAutoProfiling.isOperator("CNO")) {
                    String carrier = SystemProperties.get("persist.sys.sim.operator");
                    fileName = SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_ROOTDIR, "/OP") + "/config/featureset_" + carrier + ".xml";
                    file = new File(fileName);
                    if (!file.exists()) {
                        fileName = SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_ROOTDIR, "/OP") + "/config/featureset.xml";
                        file = new File(fileName);
                    }
                    if (!file.exists()) {
                        fileName = "etc/featureset_" + carrier + ".xml";
                        file = new File(fileName);
                    }
                    Log.d(LgeAutoProfilingConstants.TAG, "Single parse featureset.xml, filename = " + fileName);
                    if (!file.exists()) {
                        file = new File(LgeAutoProfilingConstants.FILE_PATH_FEATURE);
                        if (!file.exists()) {
                            file = new File(LgeAutoProfilingConstants.FILE_PATH_FEATURE_VENDOR);
                        }
                    }
                    confFileOpen = new File(LgeAutoProfilingConstants.FILE_PATH_FEATURE_OPEN);
                    if (!confFileOpen.exists()) {
                        confFileOpen = new File(LgeAutoProfilingConstants.FILE_PATH_FEATURE_OPEN_VENDOR);
                    }
                    if (!confFileOpen.exists() || !IS_GLOBAL_COUNTRY_IN_PCAS) {
                        matchedProfile = parse(file, type, simInfo);
                        break;
                    }
                    Log.d(LgeAutoProfilingConstants.TAG, "Country is global (from PCASInfo)/try parsing " + file.getPath());
                    parse = file.exists() ? parse(file, type, simInfo) : null;
                    this.mUsingfeatureOpen = true;
                    Log.d(LgeAutoProfilingConstants.TAG, "try parsing " + confFileOpen.getPath());
                    matchedProfile = mergeProfileIfNeeded(parse, parse(confFileOpen, type, simInfo), null);
                    break;
                }
                File file2 = null;
                String operator = LgeAutoProfiling.getChinaDDSOperator();
                file = new File(LgeAutoProfilingConstants.FILE_PATH_FEATURE);
                if (!file.exists()) {
                    file = new File(LgeAutoProfilingConstants.FILE_PATH_FEATURE_VENDOR);
                }
                if (!TextUtils.isEmpty(operator)) {
                    String filePath = LgeAutoProfilingConstants.FILE_PATH_FEATURE_CMCC;
                    if ("CTC".equalsIgnoreCase(operator)) {
                        filePath = LgeAutoProfilingConstants.FILE_PATH_FEATURE_CTC;
                    } else if ("HK".equalsIgnoreCase(operator)) {
                        filePath = LgeAutoProfilingConstants.FILE_PATH_FEATURE_HK;
                    }
                    file2 = new File(filePath);
                }
                parse = file.exists() ? parse(file, type, simInfo) : null;
                ProfileData featuresetOperator = null;
                if (file2 != null) {
                    featuresetOperator = file2.exists() ? parse(file2, type, simInfo) : null;
                }
                matchedProfile = mergeProfileIfNeeded(parse, featuresetOperator, null);
                break;
                break;
            default:
                Log.e(LgeAutoProfilingConstants.TAG, "[getMatchedProfile] unsupported type");
                return null;
        }
        if (isFileExist(file)) {
            Log.v(LgeAutoProfilingConstants.TAG, "[getMatchedProfile] selected file : " + file.getPath());
        }
        if (isFileExist(confFileOpen)) {
            Log.v(LgeAutoProfilingConstants.TAG, "[getMatchedProfile] selected file : " + confFileOpen.getPath());
        }
        return matchedProfile;
    }

    private ProfileData parse(File confFile, int type, LgeSimInfo simInfo) {
        XmlPullParserException e;
        Throwable th;
        FileReader fileReader = null;
        ProfileData parsedData = null;
        try {
            if (Resources.getSystem().getBoolean(R.bool.config_voice_capable) || confFile == null || confFile.exists()) {
                Log.d(LgeAutoProfilingConstants.TAG, "[parse] Load xml");
                FileReader in = new FileReader(confFile);
                try {
                    XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                    parser.setInput(in);
                    parsedData = getMatchedProfile(type, parser, simInfo);
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    fileReader = in;
                } catch (XmlPullParserException e3) {
                    e = e3;
                    fileReader = in;
                    e.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return parsedData;
                } catch (FileNotFoundException e4) {
                    fileReader = in;
                    try {
                        Log.d(LgeAutoProfilingConstants.TAG, "[parse] file not found : " + confFile.getPath());
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        return parsedData;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileReader = in;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
                return parsedData;
            }
            if (!checkLoadXmlDBG) {
                Log.d(LgeAutoProfilingConstants.TAG, "[parse] Do NOT load xml");
                checkLoadXmlDBG = true;
            }
            return null;
        } catch (XmlPullParserException e5) {
            e = e5;
            e.printStackTrace();
            if (fileReader != null) {
                fileReader.close();
            }
            return parsedData;
        } catch (FileNotFoundException e6) {
            Log.d(LgeAutoProfilingConstants.TAG, "[parse] file not found : " + confFile.getPath());
            if (fileReader != null) {
                fileReader.close();
            }
            return parsedData;
        }
    }

    private boolean isFileExist(File file) {
        return file != null ? file.exists() : false;
    }

    protected ProfileData readProfile(XmlPullParser parser) throws XmlPullParserException, IOException {
        NameValueProfile p = new NameValueProfile();
        while (true) {
            if (!LgeAutoProfilingConstants.ELEMENT_NAME_SIMINFO.equals(parser.getName()) && !LgeAutoProfilingConstants.ELEMENT_NAME_FEATURESET.equals(parser.getName())) {
                break;
            }
            nextElement(parser);
        }
        while ("item".equals(parser.getName())) {
            String tag = parser.getName();
            String key = parser.getAttributeValue(null, "name");
            if (key != null && parser.next() == 4) {
                p.setValue(key, parser.getText());
            }
            nextElement(parser);
        }
        return p;
    }

    protected ProfileData mergeProfile(ProfileData commonProfile, ProfileData matchedProfile, ProfileData featureProfile) {
        NameValueProfile cp = (NameValueProfile) commonProfile;
        NameValueProfile mp = (NameValueProfile) matchedProfile;
        NameValueProfile fp = (NameValueProfile) featureProfile;
        if (cp != null) {
            for (String key : cp.mNameValueMap.keySet()) {
                if (!mp.mNameValueMap.containsKey(key)) {
                    mp.mNameValueMap.put(key, cp.getValue(key));
                }
            }
        }
        if (fp != null) {
            for (String key2 : fp.mNameValueMap.keySet()) {
                if (!mp.mNameValueMap.containsKey(key2)) {
                    mp.mNameValueMap.put(key2, fp.getValue(key2));
                }
            }
        }
        return mp;
    }

    protected static HashMap<String, String> profileToMap(NameValueProfile profile) {
        HashMap<String, String> profileMap = new HashMap();
        for (String key : profile.getValueMap().keySet()) {
            profileMap.put(key, profile.getValue(key));
        }
        return profileMap;
    }

    private ProfileData getMatchedFeatureByCupssRootDir(XmlPullParser parser, String operatorValue, String countryValue) throws XmlPullParserException, IOException {
        String cupssTarget;
        String cupssRootDir = SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_ROOTDIR, "");
        if (cupssRootDir.equals("/data/local/cust")) {
            cupssRootDir = SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_SUBCA, "");
        }
        if ("SUPERSET".equals(operatorValue)) {
            cupssTarget = "SUPERSET";
        } else {
            cupssTarget = String.format("%s_%s", new Object[]{operatorValue, countryValue});
        }
        if (LgeAutoProfilingConstants.ELEMENT_NAME_FEATURESET.equals(parser.getName())) {
            nextElement(parser);
        }
        if (cupssRootDir.toUpperCase().contains(cupssTarget.toUpperCase())) {
            Log.d(LgeAutoProfilingConstants.TAG, "matched ntcode parse from xml : " + cupssTarget);
            if ("item".equals(parser.getName())) {
                return readProfile(parser);
            }
            return new NameValueProfile();
        }
        while ("item".equals(parser.getName())) {
            nextElement(parser);
        }
        return null;
    }
}
