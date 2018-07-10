package com.lge.uicc.framework;

import android.app.ActivityManagerNative;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncResult;
import android.os.Message;
import android.telephony.SubscriptionManager;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.RIL;
import com.android.internal.telephony.SingleBinaryConstants;
import com.android.internal.telephony.uicc.IccFileHandler;
import com.android.internal.telephony.uicc.IccRecords;
import com.android.internal.telephony.uicc.UiccCard;
import com.android.internal.telephony.uicc.UiccCardApplication;
import com.android.internal.telephony.uicc.UiccController;
import com.lge.uicc.EfUtils;
import org.codeaurora.ims.QtiCallConstants;

public final class IccTools {
    protected static final int APP_FAM_3GPP = 1;
    protected static final int APP_FAM_3GPP2 = 2;
    protected static final int APP_FAM_IMS = 3;
    private static Context mContext;
    private static RIL[] mRils;
    private static LGUiccRIL[] mUiccRils;

    protected static void setup(Context c, RIL[] cis) {
        mContext = c;
        mRils = cis;
        mUiccRils = new LGUiccRIL[cis.length];
        for (int i = 0; i < cis.length; i++) {
            mUiccRils[i] = LGUiccRIL.make(cis[i]);
        }
    }

    protected static Context getContext() {
        if (mContext == null) {
            loge("getContext: not ready!!");
        }
        return mContext;
    }

    public static RIL getRIL(int slotId) {
        if (mRils != null && mRils.length >= slotId) {
            return mRils[slotId];
        }
        loge("getRIL(" + slotId + "): not ready!!");
        return null;
    }

    public static int getSlots() {
        if (mRils != null) {
            return mRils.length;
        }
        loge("getSlots: not ready!!");
        return 0;
    }

    public static boolean isMultiSimEnabled() {
        boolean z = true;
        if (mRils == null) {
            loge("isMultiSimEnabled: not ready!!");
            return false;
        }
        if (mRils.length <= 1) {
            z = false;
        }
        return z;
    }

    public static int getSlotId(Object ci) {
        if ((ci instanceof RIL) && mRils != null) {
            for (int i = 0; i < mRils.length; i++) {
                if (ci == mRils[i]) {
                    return i;
                }
            }
            loge("getSlotId: unknown CommandsInterface !!");
        }
        return 0;
    }

    public static LGUiccRIL getUiccRIL(int slotId) {
        if (mUiccRils != null && mUiccRils.length >= slotId) {
            return mUiccRils[slotId];
        }
        loge("getUiccRIL(" + slotId + "): not ready!!");
        return null;
    }

    public static <T> Class<T> getDerivedClass(Class<T> cls) {
        int i = 0;
        String[] suffixes = new String[]{"QCT", "MTK"};
        if (cls != null) {
            int length = suffixes.length;
            while (i < length) {
                try {
                    cls = Class.forName(cls.getName() + suffixes[i]);
                    break;
                } catch (Exception e) {
                    i++;
                }
            }
            logd("load " + cls.getName());
        }
        return cls;
    }

    public static <T> T getDerivedInstance(Class<T> cls) {
        try {
            return getDerivedClass(cls).newInstance();
        } catch (Exception e) {
            loge("getDerivedInstance: " + e.toString());
            return null;
        }
    }

    public static void postGenericFailure(Message msg) {
        if (msg != null) {
            AsyncResult.forMessage(msg, null, CommandException.fromRilErrno(2));
            msg.sendToTarget();
        }
    }

    protected static UiccCard getUiccCard(int slotId) {
        try {
            return UiccController.getInstance().getUiccCard(slotId);
        } catch (RuntimeException e) {
            loge("getUiccCard: " + e);
            return null;
        }
    }

    protected static UiccCardApplication getApplication(int slotId, int family) {
        UiccCard card = getUiccCard(slotId);
        if (card != null) {
            return card.getApplication(family);
        }
        loge("getApplication: card==null, slot=" + slotId);
        return null;
    }

    protected static IccFileHandler getFileHandler(int slotId, int family) {
        UiccCardApplication app = getApplication(slotId, family);
        if (app != null) {
            return app.getIccFileHandler();
        }
        loge("getFileHandler: app==null, family=" + family);
        return null;
    }

    protected static IccRecords getIccRecords(int slotId, int family) {
        UiccCardApplication app = getApplication(slotId, family);
        if (app != null) {
            return app.getIccRecords();
        }
        loge("getIccRecords: app==null, family=" + family);
        return null;
    }

    protected static void broadcastIntent(String intentString) {
        broadcastIntent(new Intent(intentString));
    }

    protected static void broadcastIntent(Intent intent) {
        if (mContext == null) {
            loge("broadcastIntent: fails");
            return;
        }
        logd("broadcastIntent " + intent.getAction());
        intent.addFlags(QtiCallConstants.CAPABILITY_SUPPORTS_DOWNGRADE_TO_VOICE_REMOTE);
        mContext.sendBroadcast(intent);
    }

    protected static void broadcastStickyIntent(Intent intent) {
        logd("broadcastStickyIntent " + intent.getAction());
        intent.addFlags(QtiCallConstants.CAPABILITY_SUPPORTS_DOWNGRADE_TO_VOICE_REMOTE);
        ActivityManagerNative.broadcastStickyIntent(intent, "android.permission.READ_PHONE_STATE", -1);
    }

    protected static void broadcastStickyIntent(Intent intent, String value) {
        broadcastStickyIntent(intent, value, 0);
    }

    protected static void broadcastStickyIntent(Intent intent, String value, int slotId) {
        intent.addFlags(553648128);
        intent.putExtra("phoneName", "Phone");
        intent.putExtra(SingleBinaryConstants.INTENT_KEY_ICC_STATE, value);
        SubscriptionManager.putPhoneIdAndSubIdExtra(intent, slotId);
        logd("broadcastStickyIntent " + intent.getAction() + " " + value + " for mCardIndex : " + slotId);
        ActivityManagerNative.broadcastStickyIntent(intent, "android.permission.READ_PHONE_STATE", -1);
    }

    protected static void broadcastIccStateChangedIntent(String value, String reason) {
        broadcastIccStateChangedIntent(value, reason, 0);
    }

    protected static void broadcastIccStateChangedIntent(String value, String reason, int slotId) {
        Intent intent = new Intent(SingleBinaryConstants.ACTION_SIM_STATE_CHANGED);
        intent.addFlags(536870912);
        intent.putExtra("phoneName", "Phone");
        intent.putExtra(SingleBinaryConstants.INTENT_KEY_ICC_STATE, value);
        intent.putExtra("reason", reason);
        SubscriptionManager.putPhoneIdAndSubIdExtra(intent, slotId);
        logd("Broadcasting intent ACTION_SIM_STATE_CHANGED " + value + " reason " + reason + " for mCardIndex : " + slotId);
        ActivityManagerNative.broadcastStickyIntent(intent, "android.permission.READ_PHONE_STATE", -1);
    }

    public static boolean checkEventInEnvCmd(String contents, byte evt) {
        if (contents == null) {
            logd("contents is null");
            return false;
        } else if (contents.length() == 0) {
            logd("contents.length() == 0");
            return false;
        } else {
            byte[] envelope = EfUtils.hexStringToBytes(contents);
            if (envelope[0] == (byte) -42) {
                int offset;
                if (envelope.length > 127) {
                    offset = 3;
                } else {
                    offset = 2;
                }
                if ((envelope[offset] & 127) != 25) {
                    logd("missing mandatory param Event List");
                    return false;
                }
                offset++;
                if (offset >= envelope.length || offset >= 255) {
                    logd("offset out of bound.");
                    return false;
                } else if (envelope[offset] != (byte) 1) {
                    logd("Event List length is greater than 1.");
                    return false;
                } else {
                    offset++;
                    if (offset >= envelope.length || offset >= 255) {
                        logd("offset out of bound.");
                        return false;
                    } else if (envelope[offset] == evt) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private static void loge(String s) {
        LGUICC.loge("[IccTools] " + s);
    }

    private static void logd(String s) {
        LGUICC.logd("[IccTools] " + s);
    }
}
