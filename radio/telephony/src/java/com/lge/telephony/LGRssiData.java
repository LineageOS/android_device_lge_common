package com.lge.telephony;

import android.os.SystemProperties;
import android.util.Log;
import com.android.internal.telephony.lgeautoprofiling.LgeAutoProfiling;
import com.android.internal.telephony.lgeautoprofiling.LgeAutoProfilingConstants;
import com.lge.lgdata.Operator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LGRssiData {
    private static final String ATTR_NAME_KEY = "name";
    private static final boolean DBG = false;
    private static final int DEFAULT_RSSI_LEVEL = 5;
    private static final String ELEMENT_NAME_ITEM = "item";
    private static final String FILE_PATH_RSSI = "/etc/rssi.xml";
    private static final String FILE_PATH_RSSI_LAOP = (SystemProperties.get(LgeAutoProfilingConstants.SYSTEM_PROP_CUPSS_ROOTDIR, "/oem/OP") + "/config/rssi.xml");
    private static final String TAG = "LGRssiData";
    private static LGRssiData instance = new LGRssiData();
    public static int mRssiLevel = -1;
    private static HashMap<String, String> mRssiMap;
    private int[] mAsuEtcValue;
    private int[] mAsuGsmValue;
    private int[] mAsuUmtsValue;
    private int[] mAxgpRsrpValue;
    private int[] mAxgpRssnrValue;
    private int[] mCdmaDbmValue;
    private int[] mCdmaEcioValue;
    private int[] mEvdoDbmValue;
    private int[] mEvdoSnrValue;
    private int[] mGsmDbmValue;
    private int[] mGsmEcioValue;
    private int[] mLteBandListValue;
    private int[][] mLteRsrpBandValue;
    private int[] mLteRsrpOffsetValue;
    private int[] mLteRsrpValue;
    private int[] mLteRsrqValue;
    private int[] mLteRssnrValue;
    private int[] mLteSignalValue;
    private int[] mTdscdmaDbmValue;
    private int[] mUmtsDbmValue;
    private int[] mUmtsEcioValue;

    private LGRssiData() {
    }

    public static LGRssiData getInstance() {
        return instance;
    }

    public void init() {
        loadRssi();
    }

    private void loadRssi() {
        IOException e;
        XmlPullParserException e2;
        FileNotFoundException e3;
        IOException e11;
        Throwable th;
        FileReader fileReader = null;
        if (isXMLRequired() && mRssiMap == null) {
            File confFile = new File(FILE_PATH_RSSI_LAOP);
            if (!confFile.exists()) {
                confFile = new File(FILE_PATH_RSSI);
                if (!confFile.exists()) {
                    Log.v(TAG, "[loadRssi] confFile is null or File not exist ");
                    return;
                }
            }
            Log.v(TAG, "[loadRssi] selected file : " + confFile.getPath());
            try {
                FileReader in = new FileReader(confFile);
                try {
                    XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                    parser.setInput(in);
                    readRssiData(parser);
                    parseRssiData();
                    Log.d(TAG, "[loadRssi]Loaded mRssiMap is " + mRssiMap);
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    fileReader = in;
                } catch (XmlPullParserException e5) {
                    e2 = e5;
                    fileReader = in;
                    e2.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e6) {
                    e3 = e6;
                    fileReader = in;
                    e3.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                } catch (IOException e7) {
                    e11 = e7;
                    fileReader = in;
                    try {
                        e11.printStackTrace();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e4222) {
                                e4222.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e42222) {
                                e42222.printStackTrace();
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
            } catch (XmlPullParserException e8) {
                e2 = e8;
                e2.printStackTrace();
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (FileNotFoundException e9) {
                e3 = e9;
                e3.printStackTrace();
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e10) {
                e11 = e10;
                e11.printStackTrace();
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        }
    }

    private void readRssiData(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        mRssiMap = new HashMap();
        while (eventType != 1) {
            switch (eventType) {
                case 2:
                    if (!"item".equals(parser.getName())) {
                        break;
                    }
                    String key = parser.getAttributeValue(null, "name");
                    if (key != null && parser.next() == 4) {
                        mRssiMap.put(key, parser.getText());
                        break;
                    }
                default:
                    break;
            }
            eventType = parser.next();
        }
    }

    private void parseRssiData() {
        if (mRssiMap != null) {
            String value = (String) mRssiMap.get("levelNum");
            mRssiLevel = value == null ? 5 : Integer.parseInt(value);
            Log.d(TAG, "mRssiLevel : " + mRssiLevel);
            this.mLteRsrpValue = getItemValue("mLteRsrp");
            this.mLteRssnrValue = getItemValue("mLteRssnr");
            this.mLteSignalValue = getItemValue("mLteSignalStrength");
            this.mLteRsrqValue = getItemValue("mLteRsrq");
            this.mAsuGsmValue = getItemValue("asu_gsm");
            this.mAsuUmtsValue = getItemValue("asu_umts");
            this.mAsuEtcValue = getItemValue("asu_etc");
            this.mGsmDbmValue = getItemValue("gsmDbm");
            this.mGsmEcioValue = getItemValue("gsmEcio");
            this.mUmtsDbmValue = getItemValue("umtsDbm");
            this.mUmtsEcioValue = getItemValue("umtsEcio");
            this.mCdmaDbmValue = getItemValue("cdmaDbm");
            this.mCdmaEcioValue = getItemValue("cdmaEcio");
            this.mEvdoDbmValue = getItemValue("evdoDbm");
            this.mEvdoSnrValue = getItemValue("evdoSnr");
            this.mAxgpRsrpValue = getItemValue("mAxgpRsrp");
            this.mAxgpRssnrValue = getItemValue("mAxgpRssnr");
            this.mTdscdmaDbmValue = getItemValue("mTdscdmaDbm");
            this.mLteRsrpOffsetValue = getItemValue("mLteRsrpOffset");
            this.mLteBandListValue = getItemValue("mLteBandList");
            this.mLteRsrpBandValue = getItemValueforBand("mLteRsrp");
        }
    }

    private int[][] getItemValueforBand(String key) {
        if (this.mLteBandListValue == null) {
            return null;
        }
        int bandListCount = this.mLteBandListValue.length;
        int[][] lteRsrpBandValue = new int[bandListCount][];
        int i = 0;
        while (i < bandListCount) {
            lteRsrpBandValue[i] = getItemValue(key + this.mLteBandListValue[i]);
            int j = 0;
            while (lteRsrpBandValue[i] != null && j < lteRsrpBandValue[i].length) {
                Log.d(TAG, "[getItemValueforBand] lteRsrpBandValue[" + i + "][" + j + "] = " + lteRsrpBandValue[i][j]);
                j++;
            }
            i++;
        }
        return lteRsrpBandValue;
    }

    private int[] getItemValue(String key) {
        int count = 0;
        int dataNum = 0;
        String value = (String) mRssiMap.get(key);
        if (value == null || !mRssiMap.containsKey(key)) {
            return null;
        }
        if (value != null) {
            for (String tempValue : value.split(",")) {
                if (count == 0) {
                    dataNum = Integer.parseInt(tempValue);
                }
                count++;
            }
        }
        if (dataNum != count - 1) {
            try {
                throw new Exception("Data Size MisMatch");
            } catch (Exception e) {
                Log.e(TAG, "[getItemValue] Data Size MisMatch ->count : " + count + ", dataNum : " + dataNum);
                e.printStackTrace();
            }
        }
        return convertToInt(value);
    }

    public int getRssiLevel() {
        if (mRssiMap == null) {
            init();
        }
        return mRssiLevel;
    }

    private int[] convertToInt(String value) {
        if (value == null) {
            return null;
        }
        String[] tempValue = value.split(",");
        int[] array = new int[Integer.parseInt(tempValue[0])];
        for (int i = 1; i < tempValue.length; i++) {
            array[i - 1] = Integer.parseInt(tempValue[i]);
        }
        return array;
    }

    public int[] getRsrpValue() {
        return this.mLteRsrpValue;
    }

    public int[] getRssnrValue() {
        return this.mLteRssnrValue;
    }

    public int[] getRsrqValue() {
        return this.mLteRsrqValue;
    }

    public int[] getLteSignalValue() {
        return this.mLteSignalValue;
    }

    public int[] getLteRsrpOffsetValue() {
        return this.mLteRsrpOffsetValue;
    }

    public int[][] getLteRsrpBandValue() {
        return this.mLteRsrpBandValue;
    }

    public int[] getLteBandListValue() {
        return this.mLteBandListValue;
    }

    public int[] getCdmaDbmValue() {
        return this.mCdmaDbmValue;
    }

    public int[] getEcioValue() {
        return this.mCdmaEcioValue;
    }

    public int[] getEvdoDbmValue() {
        return this.mEvdoDbmValue;
    }

    public int[] getEvdoSnrValue() {
        return this.mEvdoSnrValue;
    }

    public int[] getAsuGsmValue() {
        return this.mAsuGsmValue;
    }

    public int[] getAsuUmtsValue() {
        return this.mAsuUmtsValue;
    }

    public int[] getAsuEtcValue() {
        return this.mAsuUmtsValue;
    }

    public int[] getGsmDbmValue() {
        return this.mGsmDbmValue;
    }

    public int[] getGsmEcioValue() {
        return this.mGsmEcioValue;
    }

    public int[] getUmtsDbmValue() {
        return this.mUmtsDbmValue;
    }

    public int[] getUmtsEcioValue() {
        return this.mUmtsEcioValue;
    }

    public int[] getAxgpRsrpValue() {
        return this.mAxgpRsrpValue;
    }

    public int[] getAxgpRssnrValue() {
        return this.mAxgpRssnrValue;
    }

    public int[] getTdscdmaDbmValue() {
        return this.mTdscdmaDbmValue;
    }

    private boolean isXMLRequired() {
        if (Operator.TRF.equalsIgnoreCase(SystemProperties.get("ro.build.target_operator"))) {
            boolean i;
            if (Operator.TRF_VZW.equalsIgnoreCase(SystemProperties.get("persist.sys.sim.operator"))) {
                i = true;
            } else {
                i = Operator.TRF_VZW.equalsIgnoreCase(SystemProperties.get("ro.build.target_operator_ext"));
            }
            if (!i) {
                Log.v(TAG, "[loadRssi] TRF GSM does not use rssi.xml!");
                return false;
            }
        }
        if (!LgeAutoProfiling.IS_NAOP_BASED_ATT && !LgeAutoProfiling.IS_NAOP_BASED_TMUS) {
            return true;
        }
        Log.v(TAG, "[loadRssi] does not use rssi.xml!");
        return false;
    }
}
