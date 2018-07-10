package vendor.lge.hardware.radio.V1_0;

import android.hardware.radio.V1_0.IccIoResult;
import android.hardware.radio.V1_0.RadioError;
import android.hardware.radio.V1_0.RadioResponseInfo;
import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwBinder.DeathRecipient;
import android.os.IHwInterface;
import android.os.RemoteException;
import android.os.SystemProperties;
import com.android.internal.telephony.RadioNVItems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public interface ILgeRadioResponse extends IBase {
    public static final String kInterfaceName = "vendor.lge.hardware.radio@1.0::ILgeRadioResponse";

    public static abstract class Stub extends HwBinder implements ILgeRadioResponse {
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> interfaceChain() {
            return new ArrayList(Arrays.asList(new String[]{ILgeRadioResponse.kInterfaceName, IBase.kInterfaceName}));
        }

        public final String interfaceDescriptor() {
            return ILgeRadioResponse.kInterfaceName;
        }

        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList(Arrays.asList(new byte[][]{new byte[]{(byte) 119, (byte) 13, (byte) -117, (byte) 29, (byte) 62, (byte) 63, (byte) 73, (byte) -14, (byte) 112, (byte) -23, (byte) -118, (byte) -94, (byte) -61, (byte) 75, (byte) -74, (byte) -56, (byte) -60, (byte) -96, (byte) -12, (byte) 64, (byte) -7, (byte) 34, (byte) -23, (byte) -43, (byte) -52, (byte) -83, (byte) 94, (byte) 79, (byte) -50, (byte) 112, (byte) -27, (byte) -39}, new byte[]{(byte) -67, (byte) -38, (byte) -74, (byte) 24, (byte) 77, (byte) 122, (byte) 52, (byte) 109, (byte) -90, (byte) -96, (byte) 125, (byte) -64, (byte) -126, (byte) -116, (byte) -15, (byte) -102, (byte) 105, (byte) 111, (byte) 76, (byte) -86, (byte) 54, (byte) 17, (byte) -59, (byte) 31, (byte) 46, (byte) 20, (byte) 86, (byte) 90, (byte) 20, (byte) -76, (byte) 15, (byte) -39}}));
        }

        public final void setHALInstrumentation() {
        }

        public final boolean linkToDeath(DeathRecipient recipient, long cookie) {
            return true;
        }

        public final void ping() {
        }

        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = -1;
            info.ptr = 0;
            info.arch = 0;
            return info;
        }

        public final void notifySyspropsChanged() {
            SystemProperties.reportSyspropChanged();
        }

        public final boolean unlinkToDeath(DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (ILgeRadioResponse.kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int _hidl_code, HwParcel _hidl_request, HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            RadioResponseInfo aInfo;
            LgeMocaGetMisc aMiscData;
            RadioResponseInfo info;
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    testLgeRadioInterfaceResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 2:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsEnableRespMsg aEnableInfo = new LgeEmbmsEnableRespMsg();
                    aEnableInfo.readFromParcel(_hidl_request);
                    embmsEnableResponse(aInfo, aEnableInfo);
                    return;
                case 3:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsDisableRespMsg aDisableInfo = new LgeEmbmsDisableRespMsg();
                    aDisableInfo.readFromParcel(_hidl_request);
                    embmsDisableResponse(aInfo, aDisableInfo);
                    return;
                case 4:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsStartSessionRespMsg aStartSessionInfo = new LgeEmbmsStartSessionRespMsg();
                    aStartSessionInfo.readFromParcel(_hidl_request);
                    embmsStartSessionResponse(aInfo, aStartSessionInfo);
                    return;
                case 5:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsStartSessionRespMsg aStopSessionInfo = new LgeEmbmsStartSessionRespMsg();
                    aStopSessionInfo.readFromParcel(_hidl_request);
                    embmsStopSessionResponse(aInfo, aStopSessionInfo);
                    return;
                case 6:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsSwitchSessionRespMsg aSwitchSessionInfo = new LgeEmbmsSwitchSessionRespMsg();
                    aSwitchSessionInfo.readFromParcel(_hidl_request);
                    embmsSwitchSessionResponse(aInfo, aSwitchSessionInfo);
                    return;
                case 7:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsGetTimeRespMsg aTimeInfo = new LgeEmbmsGetTimeRespMsg();
                    aTimeInfo.readFromParcel(_hidl_request);
                    embmsGetTimeResponse(aInfo, aTimeInfo);
                    return;
                case 8:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsGetCoverageStateRespMsg aCoverStateInfo = new LgeEmbmsGetCoverageStateRespMsg();
                    aCoverStateInfo.readFromParcel(_hidl_request);
                    embmsGetCoverageStateResponse(aInfo, aCoverStateInfo);
                    return;
                case 9:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeEmbmsInterestedTmgiRespMsg aInterestedSessionInfo = new LgeEmbmsInterestedTmgiRespMsg();
                    aInterestedSessionInfo.readFromParcel(_hidl_request);
                    embmsInterestedSessionResponse(aInfo, aInterestedSessionInfo);
                    return;
                case 10:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgePbmRecords aRecordInfo = new LgePbmRecords();
                    aRecordInfo.readFromParcel(_hidl_request);
                    PBMReadRecordResponse(aInfo, aRecordInfo);
                    return;
                case 11:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    PBMWriteRecordResponse(aInfo, _hidl_request.readInt32Vector());
                    return;
                case 12:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    PBMDeleteRecordResponse(aInfo, _hidl_request.readInt32Vector());
                    return;
                case 13:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    PBMGetInitStateResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 14:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeUsimLibAuthResult aAuthResult = new LgeUsimLibAuthResult();
                    aAuthResult.readFromParcel(_hidl_request);
                    getUsimAuthenticationResponse(aInfo, aAuthResult);
                    return;
                case 15:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    smartCardTransmitResponse(aInfo, _hidl_request.readInt8Vector());
                    return;
                case 16:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgePbmRecordInfo aRecordInfo2 = new LgePbmRecordInfo();
                    aRecordInfo2.readFromParcel(_hidl_request);
                    PBMGetInfoResponse(aInfo, aRecordInfo2);
                    return;
                case 17:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    UIMInternalRequestCmdResponse(aInfo, _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 18:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    uiccSelectApplicationResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 19:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    uiccDeactivateApplicationResponse(aInfo);
                    return;
                case 20:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    IccIoResult aSimIoResult = new IccIoResult();
                    aSimIoResult.readFromParcel(_hidl_request);
                    uiccApplicationResponse(aInfo, aSimIoResult);
                    return;
                case 21:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeUiccAkaAuthenticateRes aAuthRes = new LgeUiccAkaAuthenticateRes();
                    aAuthRes.readFromParcel(_hidl_request);
                    uiccAkaAuthenticateResponse(aInfo, aAuthRes);
                    return;
                case 22:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    uiccGbaAuthenticateBootstrapResponse(aInfo, _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 23:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    uiccGbaAuthenticateNafResponse(aInfo, _hidl_request.readString());
                    return;
                case 24:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    uimPowerDownRequestResponse(aInfo);
                    return;
                case 25:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    USIMSmartcardGetAtrResponse(aInfo, _hidl_request.readString());
                    return;
                case 26:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    iccSetTransmitBehaviourResponse(aInfo);
                    return;
                case 27:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setCdmaEriVersionResponse(aInfo);
                    return;
                case 28:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setCdmaFactoryResetResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 29:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getEhrpdInfoForImsResponse(aInfo, _hidl_request.readString());
                    return;
                case 30:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getMipErrorCodeResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 31:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    cancelManualSearchingRequestResponse(aInfo);
                    return;
                case 32:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setPreviousNetworkSelectionModeManualResponse(aInfo);
                    return;
                case 33:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setRmnetAutoconnectResponse(aInfo);
                    return;
                case 34:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getSearchStatusResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 35:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getEngineeringModeInfoResponse(aInfo, _hidl_request.readString());
                    return;
                case 36:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setCSGSelectionManualResponse(aInfo, _hidl_request.readString());
                    return;
                case 37:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getLteEmmErrorCodeResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 38:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    sendApnDisableFlagResponse(aInfo);
                    return;
                case 39:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    loadVolteE911ScanListResponse(aInfo);
                    return;
                case 40:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getVolteE911NetworkTypeResponse(aInfo);
                    return;
                case 41:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    exitVolteE911EmergencyModeResponse(aInfo);
                    return;
                case 42:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsRegistrationResponse(aInfo);
                    return;
                case 43:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    sendE911CallStateResponse(aInfo);
                    return;
                case 44:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsRegistrationForHVoLTEResponse(aInfo);
                    return;
                case 45:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setVoiceDomainPrefResponse(aInfo);
                    return;
                case 46:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setVoLteCallResponse(aInfo);
                    return;
                case 47:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    closeImsPdnResponse(aInfo);
                    return;
                case 48:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getLteInfoForImsResponse(aInfo, _hidl_request.readString());
                    return;
                case 49:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setSrvccCallContextTransferResponse(aInfo);
                    return;
                case 50:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    sendIMSCallStateResponse(aInfo);
                    return;
                case 51:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setRssiTestAntConfResponse(aInfo, _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 52:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getRssiTestResponse(aInfo, _hidl_request.readInt32Vector());
                    return;
                case 53:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setQcrilResponse(aInfo);
                    return;
                case 54:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setMiMoAntennaControlTestResponse(aInfo);
                    return;
                case 55:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setModemInfoResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 56:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getModemInfoResponse(aInfo, _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 57:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getGPRIItemResponse(aInfo, _hidl_request.readString());
                    return;
                case 58:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setGNOSInfoResponse(aInfo);
                    return;
                case 59:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setLteBandModeResponse(aInfo);
                    return;
                case RadioError.NETWORK_NOT_READY /*60*/:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setEmergencyResponse(aInfo, _hidl_request.readInt32());
                    return;
                case RadioError.NOT_PROVISIONED /*61*/:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    vssModemResetResponse(aInfo);
                    return;
                case RadioError.NO_SUBSCRIPTION /*62*/:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getDefaultAPNResponse(aInfo, _hidl_request.readString());
                    return;
                case 63:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getAdminAPNResponse(aInfo, _hidl_request.readString());
                    return;
                case 64:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getTetheringAPNResponse(aInfo, _hidl_request.readString());
                    return;
                case 65:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setScriptValueResponse(aInfo);
                    return;
                case 66:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    aMiscData = new LgeMocaGetMisc();
                    aMiscData.readFromParcel(_hidl_request);
                    mocaGetRFParameterResponse(aInfo, aMiscData);
                    return;
                case 67:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    aMiscData = new LgeMocaGetMisc();
                    aMiscData.readFromParcel(_hidl_request);
                    mocaGetMiscResponse(aInfo, aMiscData);
                    return;
                case 68:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    mocaAlarmEventResponse(aInfo, _hidl_request.readInt8());
                    return;
                case 69:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    mocaSetLogResponse(aInfo, _hidl_request.readInt8());
                    return;
                case 70:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeModemLoggingData aLoggingData = new LgeModemLoggingData();
                    aLoggingData.readFromParcel(_hidl_request);
                    mocaGetDataResponse(aInfo, aLoggingData);
                    return;
                case 71:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    mocaSetMemResponse(aInfo, _hidl_request.readInt32Vector());
                    return;
                case 72:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    mocaAlarmEventRegResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 73:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    DMRequestResponse(aInfo, _hidl_request.readInt8Vector());
                    return;
                case 74:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsDataFlushEnabledResponse(aInfo);
                    return;
                case 75:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    NSRI_SetCaptureMode_requestProcResponse(aInfo, _hidl_request.readInt8Vector());
                    return;
                case 76:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    NSRI_requestProcResponse(aInfo, _hidl_request.readInt8Vector());
                    return;
                case 77:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    NSRI_Oem_requestProcResponse(aInfo, _hidl_request.readInt8Vector());
                    return;
                case 78:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setNSRICallInfoTransferResponse(aInfo);
                    return;
                case 79:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    iwlanSetRegisterCellularQualityReportResponse(aInfo);
                    return;
                case 80:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    iwlanSendImsPdnStatusResponse(aInfo);
                    return;
                case 81:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setProximitySensorStateResponse(aInfo);
                    return;
                case RadioNVItems.RIL_NV_LTE_BSR_MAX_TIME /*82*/:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsRegistrationStatusResponse(aInfo);
                    return;
                case 83:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsCallStatusResponse(aInfo);
                    return;
                case 84:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setScmModeResponse(aInfo);
                    return;
                case 85:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsStatusResponse(aInfo);
                    return;
                case 86:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getIMSNetworkInfoResponse(aInfo, _hidl_request.readStringVector());
                    return;
                case 87:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    lgeSetNetworkSelectionModeManualResponse(aInfo);
                    return;
                case 88:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    lgeCdmaWriteSmsToRuimResponse(aInfo, _hidl_request.readInt32());
                    return;
                case 89:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    LgeSignalStrength aSigStrength = new LgeSignalStrength();
                    aSigStrength.readFromParcel(_hidl_request);
                    lgeGetSignalStrengthResponse(aInfo, aSigStrength);
                    return;
                case 90:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    info = new RadioResponseInfo();
                    info.readFromParcel(_hidl_request);
                    lgeGetCurrentCallsResponse(info, LgeCall.readVectorFromParcel(_hidl_request));
                    return;
                case 91:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    getAvailableNetworksResponse(aInfo, LgeOperatorInfo.readVectorFromParcel(_hidl_request));
                    return;
                case 92:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    info = new RadioResponseInfo();
                    info.readFromParcel(_hidl_request);
                    LgeSetupDataCallResult dcResponse = new LgeSetupDataCallResult();
                    dcResponse.readFromParcel(_hidl_request);
                    setupDataCallExResponse(info, dcResponse);
                    return;
                case 93:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    info = new RadioResponseInfo();
                    info.readFromParcel(_hidl_request);
                    LgeCardStatus cardStatus = new LgeCardStatus();
                    cardStatus.readFromParcel(_hidl_request);
                    lgeGetIccCardStatusResponse(info, cardStatus);
                    return;
                case 94:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    DataRegStateResult dataRegResponse = new DataRegStateResult();
                    dataRegResponse.readFromParcel(_hidl_request);
                    getDataRegistrationStateResponse(aInfo, dataRegResponse);
                    return;
                case 95:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    lgeAcknowledgeRequest(_hidl_request.readInt32());
                    return;
                case 96:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setPcasInfofaceResponse(aInfo);
                    return;
                case 97:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsNewRegistrationStateResponse(aInfo);
                    return;
                case 98:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setImsRegistrationErrorStatusResponse(aInfo);
                    return;
                case 99:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setLteProcResponse(aInfo);
                    return;
                case 100:
                    _hidl_request.enforceInterface(ILgeRadioResponse.kInterfaceName);
                    aInfo = new RadioResponseInfo();
                    aInfo.readFromParcel(_hidl_request);
                    setOtasnPdnStateResponse(aInfo);
                    return;
                case 256067662:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    ArrayList<String> _hidl_out_descriptors = interfaceChain();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeStringVector(_hidl_out_descriptors);
                    _hidl_reply.send();
                    return;
                case 256131655:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 256136003:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    String _hidl_out_descriptor = interfaceDescriptor();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_descriptor);
                    _hidl_reply.send();
                    return;
                case 256398152:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                    _hidl_reply.writeStatus(0);
                    HwBlob _hidl_blob = new HwBlob(16);
                    int _hidl_vec_size = _hidl_out_hashchain.size();
                    _hidl_blob.putInt32(8, _hidl_vec_size);
                    _hidl_blob.putBool(12, false);
                    HwBlob hwBlob = new HwBlob(_hidl_vec_size * 32);
                    for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                        long _hidl_array_offset_1 = (long) (_hidl_index_0 * 32);
                        for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                            hwBlob.putInt8(_hidl_array_offset_1, ((byte[]) _hidl_out_hashchain.get(_hidl_index_0))[_hidl_index_1_0]);
                            _hidl_array_offset_1++;
                        }
                    }
                    _hidl_blob.putBlob(0, hwBlob);
                    _hidl_reply.writeBuffer(_hidl_blob);
                    _hidl_reply.send();
                    return;
                case 256462420:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    setHALInstrumentation();
                    return;
                case 257049926:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    DebugInfo _hidl_out_info = getDebugInfo();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_info.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 257120595:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    notifySyspropsChanged();
                    return;
                default:
                    return;
            }
        }
    }

    public static final class Proxy implements ILgeRadioResponse {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(remote);
        }

        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.lge.hardware.radio@1.0::ILgeRadioResponse]@Proxy";
            }
        }

        public void testLgeRadioInterfaceResponse(RadioResponseInfo aInfo, int serial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(serial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsEnableResponse(RadioResponseInfo aInfo, LgeEmbmsEnableRespMsg aEnableInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aEnableInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsDisableResponse(RadioResponseInfo aInfo, LgeEmbmsDisableRespMsg aDisableInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aDisableInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsStartSessionResponse(RadioResponseInfo aInfo, LgeEmbmsStartSessionRespMsg aStartSessionInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aStartSessionInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsStopSessionResponse(RadioResponseInfo aInfo, LgeEmbmsStartSessionRespMsg aStopSessionInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aStopSessionInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsSwitchSessionResponse(RadioResponseInfo aInfo, LgeEmbmsSwitchSessionRespMsg aSwitchSessionInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aSwitchSessionInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsGetTimeResponse(RadioResponseInfo aInfo, LgeEmbmsGetTimeRespMsg aTimeInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aTimeInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsGetCoverageStateResponse(RadioResponseInfo aInfo, LgeEmbmsGetCoverageStateRespMsg aCoverStateInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aCoverStateInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsInterestedSessionResponse(RadioResponseInfo aInfo, LgeEmbmsInterestedTmgiRespMsg aInterestedSessionInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aInterestedSessionInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMReadRecordResponse(RadioResponseInfo aInfo, LgePbmRecords aRecordInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aRecordInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMWriteRecordResponse(RadioResponseInfo aInfo, ArrayList<Integer> aRecordInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32Vector(aRecordInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMDeleteRecordResponse(RadioResponseInfo aInfo, ArrayList<Integer> aRecordInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32Vector(aRecordInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMGetInitStateResponse(RadioResponseInfo aInfo, int aInitDone) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aInitDone);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getUsimAuthenticationResponse(RadioResponseInfo aInfo, LgeUsimLibAuthResult aAuthResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aAuthResult.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void smartCardTransmitResponse(RadioResponseInfo aInfo, ArrayList<Byte> aSimIoInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8Vector(aSimIoInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMGetInfoResponse(RadioResponseInfo aInfo, LgePbmRecordInfo aRecordInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aRecordInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void UIMInternalRequestCmdResponse(RadioResponseInfo aInfo, int aNum, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aNum);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccSelectApplicationResponse(RadioResponseInfo aInfo, int aSessionId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aSessionId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccDeactivateApplicationResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccApplicationResponse(RadioResponseInfo aInfo, IccIoResult aSimIoResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aSimIoResult.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccAkaAuthenticateResponse(RadioResponseInfo aInfo, LgeUiccAkaAuthenticateRes aAuthRes) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aAuthRes.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateBootstrapResponse(RadioResponseInfo aInfo, String aRes, String aAuts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aRes);
            _hidl_request.writeString(aAuts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateNafResponse(RadioResponseInfo aInfo, String aGbaNafRes) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aGbaNafRes);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uimPowerDownRequestResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void USIMSmartcardGetAtrResponse(RadioResponseInfo aInfo, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iccSetTransmitBehaviourResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCdmaEriVersionResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCdmaFactoryResetResponse(RadioResponseInfo aInfo, int aOutData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aOutData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getEhrpdInfoForImsResponse(RadioResponseInfo aInfo, String aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getMipErrorCodeResponse(RadioResponseInfo aInfo, int aErrorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aErrorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void cancelManualSearchingRequestResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPreviousNetworkSelectionModeManualResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setRmnetAutoconnectResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getSearchStatusResponse(RadioResponseInfo aInfo, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getEngineeringModeInfoResponse(RadioResponseInfo aInfo, String aModemInfoStr) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aModemInfoStr);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCSGSelectionManualResponse(RadioResponseInfo aInfo, String aCsgSession) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aCsgSession);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getLteEmmErrorCodeResponse(RadioResponseInfo aInfo, int aEmmReject) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aEmmReject);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(37, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendApnDisableFlagResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(38, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void loadVolteE911ScanListResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(39, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getVolteE911NetworkTypeResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(40, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void exitVolteE911EmergencyModeResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(41, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(42, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendE911CallStateResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(43, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationForHVoLTEResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(44, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setVoiceDomainPrefResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(45, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setVoLteCallResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(46, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void closeImsPdnResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(47, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getLteInfoForImsResponse(RadioResponseInfo aInfo, String aLgeInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aLgeInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(48, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setSrvccCallContextTransferResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(49, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendIMSCallStateResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(50, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setRssiTestAntConfResponse(RadioResponseInfo aInfo, int aAntConfNum, int aResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aAntConfNum);
            _hidl_request.writeInt32(aResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(51, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getRssiTestResponse(RadioResponseInfo aInfo, ArrayList<Integer> aAntennaInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32Vector(aAntennaInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(52, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setQcrilResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(53, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setMiMoAntennaControlTestResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(54, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setModemInfoResponse(RadioResponseInfo aInfo, int aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(55, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getModemInfoResponse(RadioResponseInfo aInfo, int aNum, String aText) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aNum);
            _hidl_request.writeString(aText);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(56, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getGPRIItemResponse(RadioResponseInfo aInfo, String aGpriInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aGpriInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(57, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setGNOSInfoResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(58, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setLteBandModeResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(59, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setEmergencyResponse(RadioResponseInfo aInfo, int aRet) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aRet);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(60, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssModemResetResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(61, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDefaultAPNResponse(RadioResponseInfo aInfo, String aDefaultApn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aDefaultApn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(62, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAdminAPNResponse(RadioResponseInfo aInfo, String aAdminApn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aAdminApn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(63, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getTetheringAPNResponse(RadioResponseInfo aInfo, String aTetheringApn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeString(aTetheringApn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(64, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScriptValueResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(65, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetRFParameterResponse(RadioResponseInfo aInfo, LgeMocaGetMisc aMiscData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aMiscData.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(66, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetMiscResponse(RadioResponseInfo aInfo, LgeMocaGetMisc aMiscData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aMiscData.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(67, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEventResponse(RadioResponseInfo aInfo, byte aResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8(aResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(68, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetLogResponse(RadioResponseInfo aInfo, byte aResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8(aResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(69, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetDataResponse(RadioResponseInfo aInfo, LgeModemLoggingData aLoggingData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aLoggingData.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(70, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetMemResponse(RadioResponseInfo aInfo, ArrayList<Integer> aRet) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32Vector(aRet);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(71, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEventRegResponse(RadioResponseInfo aInfo, int aRet) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aRet);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(72, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void DMRequestResponse(RadioResponseInfo aInfo, ArrayList<Byte> aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8Vector(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(73, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsDataFlushEnabledResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(74, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_SetCaptureMode_requestProcResponse(RadioResponseInfo aInfo, ArrayList<Byte> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8Vector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(75, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_requestProcResponse(RadioResponseInfo aInfo, ArrayList<Byte> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8Vector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(76, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_Oem_requestProcResponse(RadioResponseInfo aInfo, ArrayList<Byte> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt8Vector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(77, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setNSRICallInfoTransferResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(78, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSetRegisterCellularQualityReportResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(79, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSendImsPdnStatusResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(80, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setProximitySensorStateResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(81, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationStatusResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(82, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsCallStatusResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(83, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScmModeResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(84, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsStatusResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(85, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getIMSNetworkInfoResponse(RadioResponseInfo aInfo, ArrayList<String> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeStringVector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(86, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeSetNetworkSelectionModeManualResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(87, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeCdmaWriteSmsToRuimResponse(RadioResponseInfo aInfo, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(88, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetSignalStrengthResponse(RadioResponseInfo aInfo, LgeSignalStrength aSigStrength) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            aSigStrength.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(89, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetCurrentCallsResponse(RadioResponseInfo info, ArrayList<LgeCall> calls) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            info.writeToParcel(_hidl_request);
            LgeCall.writeVectorToParcel(_hidl_request, calls);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(90, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAvailableNetworksResponse(RadioResponseInfo aInfo, ArrayList<LgeOperatorInfo> networkInfos) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            LgeOperatorInfo.writeVectorToParcel(_hidl_request, networkInfos);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(91, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setupDataCallExResponse(RadioResponseInfo info, LgeSetupDataCallResult dcResponse) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            info.writeToParcel(_hidl_request);
            dcResponse.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(92, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetIccCardStatusResponse(RadioResponseInfo info, LgeCardStatus cardStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            info.writeToParcel(_hidl_request);
            cardStatus.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(93, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDataRegistrationStateResponse(RadioResponseInfo aInfo, DataRegStateResult dataRegResponse) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            dataRegResponse.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(94, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeAcknowledgeRequest(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(95, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPcasInfofaceResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(96, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsNewRegistrationStateResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(97, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationErrorStatusResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(98, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setLteProcResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(99, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setOtasnPdnStateResponse(RadioResponseInfo aInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioResponse.kInterfaceName);
            aInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(100, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        public String interfaceDescriptor() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<byte[]> _hidl_out_hashchain = new ArrayList();
                HwBlob _hidl_blob = _hidl_reply.readBuffer(16);
                int _hidl_vec_size = _hidl_blob.getInt32(8);
                HwBlob childBlob = _hidl_reply.readEmbeddedBuffer((long) (_hidl_vec_size * 32), _hidl_blob.handle(), 0, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    final byte[/* 32 */] _hidl_vec_element = new byte[32];
                    long _hidl_array_offset_1 = (long) (_hidl_index_0 * 32);
                    for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                        _hidl_vec_element[_hidl_index_1_0] = childBlob.getInt8(_hidl_array_offset_1);
                        _hidl_array_offset_1++;
                    }
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        public void setHALInstrumentation() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public boolean linkToDeath(DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        public void ping() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        public void notifySyspropsChanged() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public boolean unlinkToDeath(DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    void DMRequestResponse(RadioResponseInfo radioResponseInfo, ArrayList<Byte> arrayList) throws RemoteException;

    void NSRI_Oem_requestProcResponse(RadioResponseInfo radioResponseInfo, ArrayList<Byte> arrayList) throws RemoteException;

    void NSRI_SetCaptureMode_requestProcResponse(RadioResponseInfo radioResponseInfo, ArrayList<Byte> arrayList) throws RemoteException;

    void NSRI_requestProcResponse(RadioResponseInfo radioResponseInfo, ArrayList<Byte> arrayList) throws RemoteException;

    void PBMDeleteRecordResponse(RadioResponseInfo radioResponseInfo, ArrayList<Integer> arrayList) throws RemoteException;

    void PBMGetInfoResponse(RadioResponseInfo radioResponseInfo, LgePbmRecordInfo lgePbmRecordInfo) throws RemoteException;

    void PBMGetInitStateResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void PBMReadRecordResponse(RadioResponseInfo radioResponseInfo, LgePbmRecords lgePbmRecords) throws RemoteException;

    void PBMWriteRecordResponse(RadioResponseInfo radioResponseInfo, ArrayList<Integer> arrayList) throws RemoteException;

    void UIMInternalRequestCmdResponse(RadioResponseInfo radioResponseInfo, int i, String str) throws RemoteException;

    void USIMSmartcardGetAtrResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    IHwBinder asBinder();

    void cancelManualSearchingRequestResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void closeImsPdnResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void embmsDisableResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsDisableRespMsg lgeEmbmsDisableRespMsg) throws RemoteException;

    void embmsEnableResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsEnableRespMsg lgeEmbmsEnableRespMsg) throws RemoteException;

    void embmsGetCoverageStateResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsGetCoverageStateRespMsg lgeEmbmsGetCoverageStateRespMsg) throws RemoteException;

    void embmsGetTimeResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsGetTimeRespMsg lgeEmbmsGetTimeRespMsg) throws RemoteException;

    void embmsInterestedSessionResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsInterestedTmgiRespMsg lgeEmbmsInterestedTmgiRespMsg) throws RemoteException;

    void embmsStartSessionResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsStartSessionRespMsg lgeEmbmsStartSessionRespMsg) throws RemoteException;

    void embmsStopSessionResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsStartSessionRespMsg lgeEmbmsStartSessionRespMsg) throws RemoteException;

    void embmsSwitchSessionResponse(RadioResponseInfo radioResponseInfo, LgeEmbmsSwitchSessionRespMsg lgeEmbmsSwitchSessionRespMsg) throws RemoteException;

    void exitVolteE911EmergencyModeResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void getAdminAPNResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void getAvailableNetworksResponse(RadioResponseInfo radioResponseInfo, ArrayList<LgeOperatorInfo> arrayList) throws RemoteException;

    void getDataRegistrationStateResponse(RadioResponseInfo radioResponseInfo, DataRegStateResult dataRegStateResult) throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    void getDefaultAPNResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void getEhrpdInfoForImsResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void getEngineeringModeInfoResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void getGPRIItemResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getIMSNetworkInfoResponse(RadioResponseInfo radioResponseInfo, ArrayList<String> arrayList) throws RemoteException;

    void getLteEmmErrorCodeResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void getLteInfoForImsResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void getMipErrorCodeResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void getModemInfoResponse(RadioResponseInfo radioResponseInfo, int i, String str) throws RemoteException;

    void getRssiTestResponse(RadioResponseInfo radioResponseInfo, ArrayList<Integer> arrayList) throws RemoteException;

    void getSearchStatusResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void getTetheringAPNResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void getUsimAuthenticationResponse(RadioResponseInfo radioResponseInfo, LgeUsimLibAuthResult lgeUsimLibAuthResult) throws RemoteException;

    void getVolteE911NetworkTypeResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void iccSetTransmitBehaviourResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    void iwlanSendImsPdnStatusResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void iwlanSetRegisterCellularQualityReportResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void lgeAcknowledgeRequest(int i) throws RemoteException;

    void lgeCdmaWriteSmsToRuimResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void lgeGetCurrentCallsResponse(RadioResponseInfo radioResponseInfo, ArrayList<LgeCall> arrayList) throws RemoteException;

    void lgeGetIccCardStatusResponse(RadioResponseInfo radioResponseInfo, LgeCardStatus lgeCardStatus) throws RemoteException;

    void lgeGetSignalStrengthResponse(RadioResponseInfo radioResponseInfo, LgeSignalStrength lgeSignalStrength) throws RemoteException;

    void lgeSetNetworkSelectionModeManualResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    boolean linkToDeath(DeathRecipient deathRecipient, long j) throws RemoteException;

    void loadVolteE911ScanListResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void mocaAlarmEventRegResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void mocaAlarmEventResponse(RadioResponseInfo radioResponseInfo, byte b) throws RemoteException;

    void mocaGetDataResponse(RadioResponseInfo radioResponseInfo, LgeModemLoggingData lgeModemLoggingData) throws RemoteException;

    void mocaGetMiscResponse(RadioResponseInfo radioResponseInfo, LgeMocaGetMisc lgeMocaGetMisc) throws RemoteException;

    void mocaGetRFParameterResponse(RadioResponseInfo radioResponseInfo, LgeMocaGetMisc lgeMocaGetMisc) throws RemoteException;

    void mocaSetLogResponse(RadioResponseInfo radioResponseInfo, byte b) throws RemoteException;

    void mocaSetMemResponse(RadioResponseInfo radioResponseInfo, ArrayList<Integer> arrayList) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    void sendApnDisableFlagResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void sendE911CallStateResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void sendIMSCallStateResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setCSGSelectionManualResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void setCdmaEriVersionResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setCdmaFactoryResetResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void setEmergencyResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void setGNOSInfoResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void setImsCallStatusResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsDataFlushEnabledResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsNewRegistrationStateResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsRegistrationErrorStatusResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsRegistrationForHVoLTEResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsRegistrationResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsRegistrationStatusResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setImsStatusResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setLteBandModeResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setLteProcResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setMiMoAntennaControlTestResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setModemInfoResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void setNSRICallInfoTransferResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setOtasnPdnStateResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setPcasInfofaceResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setPreviousNetworkSelectionModeManualResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setProximitySensorStateResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setQcrilResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setRmnetAutoconnectResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setRssiTestAntConfResponse(RadioResponseInfo radioResponseInfo, int i, int i2) throws RemoteException;

    void setScmModeResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setScriptValueResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setSrvccCallContextTransferResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setVoLteCallResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setVoiceDomainPrefResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void setupDataCallExResponse(RadioResponseInfo radioResponseInfo, LgeSetupDataCallResult lgeSetupDataCallResult) throws RemoteException;

    void smartCardTransmitResponse(RadioResponseInfo radioResponseInfo, ArrayList<Byte> arrayList) throws RemoteException;

    void testLgeRadioInterfaceResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void uiccAkaAuthenticateResponse(RadioResponseInfo radioResponseInfo, LgeUiccAkaAuthenticateRes lgeUiccAkaAuthenticateRes) throws RemoteException;

    void uiccApplicationResponse(RadioResponseInfo radioResponseInfo, IccIoResult iccIoResult) throws RemoteException;

    void uiccDeactivateApplicationResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    void uiccGbaAuthenticateBootstrapResponse(RadioResponseInfo radioResponseInfo, String str, String str2) throws RemoteException;

    void uiccGbaAuthenticateNafResponse(RadioResponseInfo radioResponseInfo, String str) throws RemoteException;

    void uiccSelectApplicationResponse(RadioResponseInfo radioResponseInfo, int i) throws RemoteException;

    void uimPowerDownRequestResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    boolean unlinkToDeath(DeathRecipient deathRecipient) throws RemoteException;

    void vssModemResetResponse(RadioResponseInfo radioResponseInfo) throws RemoteException;

    static ILgeRadioResponse asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof ILgeRadioResponse)) {
            return (ILgeRadioResponse) iface;
        }
        ILgeRadioResponse proxy = new Proxy(binder);
        try {
            for (String descriptor : proxy.interfaceChain()) {
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static ILgeRadioResponse castFrom(IHwInterface iface) {
        return iface == null ? null : asInterface(iface.asBinder());
    }

    static ILgeRadioResponse getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static ILgeRadioResponse getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }
}
