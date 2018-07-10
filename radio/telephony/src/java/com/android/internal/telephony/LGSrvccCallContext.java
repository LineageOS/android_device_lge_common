package com.android.internal.telephony;

import android.telephony.PhoneNumberUtils;
import android.telephony.PreciseCallState;
import com.android.internal.telephony.Call.State;
import com.android.internal.telephony.imsphone.ImsPhoneConnection;
import com.android.internal.telephony.lgeautoprofiling.LgeAutoProfiling;
import java.util.ArrayList;

public class LGSrvccCallContext {
    private static LGSrvccCallContext[] sCallContextList = null;
    private String mAddress;
    private int mAlertingType;
    private int mCallState;
    private int mCallSubState;
    private int mCallType;
    private int mDirection;
    private int mInstanceId;
    private int mIsAlertingTypeValid;
    private boolean mIsMptyCall;
    private String mName;
    private int mNamePresent;
    private int mNumberPresent;

    public LGSrvccCallContext(int instanceId, int callType, int callState, int callSubState, boolean isMptyCall, int direction, String address, int isAlertingTypeValid, int alertingType) {
        this.mInstanceId = instanceId;
        this.mCallType = callType;
        this.mCallState = callState;
        this.mCallSubState = callSubState;
        this.mIsMptyCall = isMptyCall;
        this.mDirection = direction;
        this.mAddress = address;
        this.mIsAlertingTypeValid = isAlertingTypeValid;
        this.mAlertingType = alertingType;
    }

    public LGSrvccCallContext(int instanceId, int callType, int callState, int callSubState, boolean isMptyCall, int direction, String address, int numberPresent, String name, int namePresent, int isAlertingTypeValid, int alertingType) {
        this(instanceId, callType, callState, callSubState, isMptyCall, direction, address, isAlertingTypeValid, alertingType);
        this.mName = name;
        this.mNamePresent = namePresent;
        this.mNumberPresent = numberPresent;
    }

    public void setInstanceId(int instanceId) {
        this.mInstanceId = instanceId;
    }

    public void setCallType(int callType) {
        this.mCallType = callType;
    }

    public void setCallState(int callState) {
        this.mCallState = callState;
    }

    public void setCallSubState(int callSubState) {
        this.mCallSubState = callSubState;
    }

    public void setIsMptyCall(boolean isMptyCall) {
        this.mIsMptyCall = isMptyCall;
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public void setIsAlertingTypeValid(int isAlertingTypeValid) {
        this.mIsAlertingTypeValid = isAlertingTypeValid;
    }

    public void setAlertingType(int alertingType) {
        this.mAlertingType = alertingType;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setNamePresentation(int present) {
        this.mNamePresent = present;
    }

    public void setNumberPresentation(int present) {
        this.mNumberPresent = present;
    }

    public int getInstanceId() {
        return this.mInstanceId;
    }

    public int getCallType() {
        return this.mCallType;
    }

    public int getCallState() {
        return this.mCallState;
    }

    public int getCallSubState() {
        return this.mCallSubState;
    }

    public boolean isMptyCall() {
        return this.mIsMptyCall;
    }

    public int getDirection() {
        return this.mDirection;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public int getIsAlertingTypeValid() {
        return this.mIsAlertingTypeValid;
    }

    public int getAlertingType() {
        return this.mAlertingType;
    }

    public String getName() {
        return this.mName;
    }

    public int getNamePresentation() {
        return this.mNamePresent;
    }

    public int getNumberPresentation() {
        return this.mNumberPresent;
    }

    public static void resetCallContextList() {
        if (sCallContextList != null) {
            for (int i = 0; i < sCallContextList.length; i++) {
                sCallContextList[i] = null;
            }
            sCallContextList = null;
        }
    }

    public static LGSrvccCallContext[] getCallContextList() {
        return sCallContextList;
    }

    public static LGSrvccCallContext[] getCallContextList(ArrayList<Connection> conList, int isAlertTypeValid, int alertType, int phoneId) {
        if (conList == null || conList.size() == 0) {
            return null;
        }
        int i;
        Connection candidate = null;
        for (i = 0; i < conList.size(); i++) {
            Connection c = (Connection) conList.get(i);
            if (c.getState().isAlive()) {
                if (candidate == null) {
                    candidate = c;
                } else if (c.getState() == State.ACTIVE) {
                    candidate = c;
                } else if (c.getState().isRinging() || c.getState().isDialing()) {
                    candidate = c;
                }
                if (candidate.getState() == State.ACTIVE) {
                    break;
                }
            }
        }
        if (candidate == null) {
            return null;
        }
        ((ImsPhoneConnection) candidate).setLGSubStates(2);
        ArrayList<Connection> candidateList = new ArrayList();
        candidateList.add(candidate);
        if (LgeAutoProfiling.isSupportedFeature(null, "mid_call_srvcc") || LgeAutoProfiling.checkMidSRVCC(null, phoneId)) {
            candidateList.clear();
            for (i = 0; i < conList.size(); i++) {
                Connection con = (Connection) conList.get(i);
                if (con != null && con.getState().isAlive()) {
                    ((ImsPhoneConnection) con).setLGSubStates(2);
                    candidateList.add(con);
                }
            }
            if (candidateList.size() == 0) {
                return null;
            }
        }
        LGSrvccCallContext[] list = new LGSrvccCallContext[candidateList.size()];
        for (i = 0; i < candidateList.size(); i++) {
            Connection c = (Connection) candidateList.get(i);
            int id = i + 1;
            int isAlertingTypeValid = 0;
            int alertingType = 0;
            String address = c.getAddress();
            if (address == null) {
                address = "";
            }
            String name = c.getCnapName();
            if (name == null) {
                name = "";
            }
            int numberPresent = c.getNumberPresentation();
            int namePresent = c.getCnapNamePresentation();
            int callType = getCallType(address);
            int callState = getCallState(c, phoneId);
            if (callState == 1 || callState == 5) {
                isAlertingTypeValid = isAlertTypeValid;
                alertingType = alertType;
            }
            int callSubState = 0;
            if (callState == 1) {
                callSubState = 4;
            }
            Call call = c.getCall();
            list[i] = new LGSrvccCallContext(id, callType, callState, callSubState, call == null ? false : call.isMultiparty(), c.isIncoming() ? 2 : 1, address, numberPresent, name, namePresent, isAlertingTypeValid, alertingType);
        }
        sCallContextList = list;
        return list;
    }

    private static int getCallType(String address) {
        if (PhoneNumberUtils.isEmergencyNumber(address)) {
            return 9;
        }
        return 0;
    }

    private static int getCallState(Connection c, int phoneId) {
        switch (c.getState()) {
            case ACTIVE:
                return PreciseCallState.PRECISE_CALL_STATE_ACTIVE;
            case HOLDING:
                return PreciseCallState.PRECISE_CALL_STATE_HOLDING;
            case DIALING:
                return PreciseCallState.PRECISE_CALL_STATE_DIALING;
            case ALERTING:
                return PreciseCallState.PRECISE_CALL_STATE_ALERTING;
            case INCOMING:
                return PreciseCallState.PRECISE_CALL_STATE_INCOMING;
            case WAITING:
                return PreciseCallState.PRECISE_CALL_STATE_WAITING;
            case DISCONNECTED:
                return PreciseCallState.PRECISE_CALL_STATE_DISCONNECTED;
            case DISCONNECTING:
                return PreciseCallState.PRECISE_CALL_STATE_DISCONNECTING;
            default:
                return PreciseCallState.PRECISE_CALL_STATE_IDLE;
        }
    }
}
