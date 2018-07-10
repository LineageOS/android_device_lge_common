package vendor.lge.hardware.radio.V1_0;

import android.hardware.radio.V1_0.RadioError;
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

public interface ILgeRadioPcfalcon extends IBase {
    public static final String kInterfaceName = "vendor.lge.hardware.radio@1.0::ILgeRadioPcfalcon";

    public static abstract class Stub extends HwBinder implements ILgeRadioPcfalcon {
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> interfaceChain() {
            return new ArrayList(Arrays.asList(new String[]{ILgeRadioPcfalcon.kInterfaceName, IBase.kInterfaceName}));
        }

        public final String interfaceDescriptor() {
            return ILgeRadioPcfalcon.kInterfaceName;
        }

        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList(Arrays.asList(new byte[][]{new byte[]{(byte) -87, (byte) 71, (byte) 68, (byte) 88, (byte) 68, (byte) 64, (byte) 42, (byte) -43, (byte) -102, (byte) -81, (byte) -69, (byte) 46, (byte) -47, (byte) -99, (byte) 69, (byte) -77, (byte) 92, (byte) 95, (byte) 37, (byte) 50, (byte) 28, (byte) 21, (byte) 54, (byte) -120, (byte) -33, (byte) -69, (byte) -97, (byte) 6, (byte) 111, (byte) -17, (byte) -60, (byte) 46}, new byte[]{(byte) -67, (byte) -38, (byte) -74, (byte) 24, (byte) 77, (byte) 122, (byte) 52, (byte) 109, (byte) -90, (byte) -96, (byte) 125, (byte) -64, (byte) -126, (byte) -116, (byte) -15, (byte) -102, (byte) 105, (byte) 111, (byte) 76, (byte) -86, (byte) 54, (byte) 17, (byte) -59, (byte) 31, (byte) 46, (byte) 20, (byte) 86, (byte) 90, (byte) 20, (byte) -76, (byte) 15, (byte) -39}}));
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
            if (ILgeRadioPcfalcon.kInterfaceName.equals(descriptor)) {
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
            int aSerial;
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setResponseFunctions(ILgeRadioFalconResponse.asInterface(_hidl_request.readStrongBinder()));
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsEnable(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 3:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsDisable(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 4:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsStartSession(_hidl_request.readInt32());
                    return;
                case 5:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsStopSession(_hidl_request.readInt32());
                    return;
                case 6:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsSwitchSession(_hidl_request.readInt32());
                    return;
                case 7:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsGetTime(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 8:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    embmsGetCoverageState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 9:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    PBMReadRecord(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 10:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    PBMWriteRecord(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 11:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    PBMDeleteRecord(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 12:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    PBMGetInitState(_hidl_request.readInt32());
                    return;
                case 13:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getUsimAuthentication(_hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readInt32());
                    return;
                case 14:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    smartCardTransmit(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 15:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    PBMGetInfo(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 16:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    UIMInternalRequestCmd(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 17:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uiccSelectApplication(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 18:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uiccDeactivateApplication(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 19:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uiccApplication(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 20:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uiccAkaAuthenticate(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 21:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uiccGbaAuthenticateBootstrap(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 22:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uiccGbaAuthenticateNaf(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 23:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    uimPowerDownRequest(_hidl_request.readInt32());
                    return;
                case 24:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    USIMSmartcardGetAtr(_hidl_request.readInt32());
                    return;
                case 25:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setCdmaEriVersion(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 26:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setCdmaFactoryReset(_hidl_request.readInt32());
                    return;
                case 27:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getEhrpdInfoForIms(_hidl_request.readInt32());
                    return;
                case 28:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getMipErrorCode(_hidl_request.readInt32());
                    return;
                case 29:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    cancelManualSearchingRequest(_hidl_request.readInt32());
                    return;
                case 30:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setPreviousNetworkSelectionModeManual(_hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 31:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setRmnetAutoconnect(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 32:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getSearchStatus(_hidl_request.readInt32());
                    return;
                case 33:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getEngineeringModeInfo(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 34:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setCSGSelectionManual(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 35:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getLteEmmErrorCode(_hidl_request.readInt32());
                    return;
                case 36:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    sendApnDisableFlag(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 37:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    loadVolteE911ScanList(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 38:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getVolteE911NetworkType(_hidl_request.readInt32());
                    return;
                case 39:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    exitVolteE911EmergencyMode(_hidl_request.readInt32());
                    return;
                case 40:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setImsRegistration(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 41:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    sendE911CallState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 42:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setImsRegistrationForHVoLTE(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 43:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setVoiceDomainPref(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 44:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setVoLteCall(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 45:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    closeImsPdn(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 46:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getLteInfoForIms(_hidl_request.readInt32());
                    return;
                case 47:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setSrvccCallContextTransfer(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 48:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    sendImsCallState(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 49:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setRssiTestAntConf(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 50:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getRssiTest(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 51:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setQcril(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 52:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setMiMoAntennaControlTest(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 53:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setModemInfo(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 54:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getModemInfo(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 55:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getGPRIItem(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 56:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    aSerial = _hidl_request.readInt32();
                    LgeIntString aData = new LgeIntString();
                    aData.readFromParcel(_hidl_request);
                    setGNOSInfo(aSerial, aData);
                    return;
                case 57:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setLteBandMode(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 58:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setEmergency(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 59:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    vssModemReset(_hidl_request.readInt32());
                    return;
                case RadioError.NETWORK_NOT_READY /*60*/:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getDefaultAPN(_hidl_request.readInt32());
                    return;
                case RadioError.NOT_PROVISIONED /*61*/:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getAdminAPN(_hidl_request.readInt32());
                    return;
                case RadioError.NO_SUBSCRIPTION /*62*/:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getTetheringAPN(_hidl_request.readInt32());
                    return;
                case 63:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setScriptValue(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 64:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaGetRFParameter(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 65:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaGetMisc(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 66:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaAlarmEvent(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 67:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaSetLog(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 68:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaGetData(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 69:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaSetMem(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 70:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    mocaAlarmEventReg(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 71:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    DMRequest(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 72:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setImsDataFlushEnabled(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 73:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    NSRI_SetCaptureMode_requestProc(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 74:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    NSRI_requestProc(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 75:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    NSRI_Oem_requestProc(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 76:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setNSRICallInfoTransfer(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 77:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    aSerial = _hidl_request.readInt32();
                    int i = aSerial;
                    iwlanSetRegisterCellularQualityReport(i, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 78:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    iwlanSendImsPdnStatus(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 79:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setProximitySensorState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 80:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    aSerial = _hidl_request.readInt32();
                    int i2 = aSerial;
                    setImsRegistrationStatus(i2, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 81:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setImsCallStatus(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case RadioNVItems.RIL_NV_LTE_BSR_MAX_TIME /*82*/:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setScmMode(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 83:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setImsStatus(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 84:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getIMSNetworkInfo(_hidl_request.readInt32());
                    return;
                case 85:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    lgeSetNetworkSelectionModeManual(_hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 86:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setupDataCallEx(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 87:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    lgeCdmaWriteSmsToRuim(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 88:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    lgeGetSignalStrength(_hidl_request.readInt32());
                    return;
                case 89:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    lgeGetIccCardStatus(_hidl_request.readInt32());
                    return;
                case 90:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    lgeGetCurrentCalls(_hidl_request.readInt32());
                    return;
                case 91:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getAvailableNetworks(_hidl_request.readInt32());
                    return;
                case 92:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    getDataRegistrationState(_hidl_request.readInt32());
                    return;
                case 93:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setPcasInfo(_hidl_request.readInt32());
                    return;
                case 94:
                    _hidl_request.enforceInterface(ILgeRadioPcfalcon.kInterfaceName);
                    setOtasnPdnState(_hidl_request.readInt32(), _hidl_request.readInt32());
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
                    HwBlob hwBlob = new HwBlob(16);
                    int _hidl_vec_size = _hidl_out_hashchain.size();
                    hwBlob.putInt32(8, _hidl_vec_size);
                    hwBlob.putBool(12, false);
                    hwBlob = new HwBlob(_hidl_vec_size * 32);
                    for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                        long _hidl_array_offset_1 = (long) (_hidl_index_0 * 32);
                        for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                            hwBlob.putInt8(_hidl_array_offset_1, ((byte[]) _hidl_out_hashchain.get(_hidl_index_0))[_hidl_index_1_0]);
                            _hidl_array_offset_1++;
                        }
                    }
                    hwBlob.putBlob(0, hwBlob);
                    _hidl_reply.writeBuffer(hwBlob);
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

    public static final class Proxy implements ILgeRadioPcfalcon {
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
                return "[class or subclass of vendor.lge.hardware.radio@1.0::ILgeRadioPcfalcon]@Proxy";
            }
        }

        public void setResponseFunctions(ILgeRadioFalconResponse respnose) throws RemoteException {
            IHwBinder iHwBinder = null;
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            if (respnose != null) {
                iHwBinder = respnose.asBinder();
            }
            _hidl_request.writeStrongBinder(iHwBinder);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsEnable(int aSerial, int aTransId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aTransId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsDisable(int aSerial, int aTransId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aTransId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsStartSession(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsStopSession(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsSwitchSession(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsGetTime(int aSerial, int aTransId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aTransId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsGetCoverageState(int aSerial, int aTransId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aTransId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMReadRecord(int aSerial, int aEfDevice, int aRecIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEfDevice);
            _hidl_request.writeInt32(aRecIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMWriteRecord(int aSerial, int aDevice, int aIndex, int aType, int aAdtype, String number, String name, String aAdditionalNumber, String aAdditionalNumberA, String aAdditionalNumberB, String aEmailAddress, String aSecondName, int aGasId, int aSyncCnt) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aDevice);
            _hidl_request.writeInt32(aIndex);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aAdtype);
            _hidl_request.writeString(number);
            _hidl_request.writeString(name);
            _hidl_request.writeString(aAdditionalNumber);
            _hidl_request.writeString(aAdditionalNumberA);
            _hidl_request.writeString(aAdditionalNumberB);
            _hidl_request.writeString(aEmailAddress);
            _hidl_request.writeString(aSecondName);
            _hidl_request.writeInt32(aGasId);
            _hidl_request.writeInt32(aSyncCnt);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMDeleteRecord(int aSerial, int aEfDevice, int aRecIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEfDevice);
            _hidl_request.writeInt32(aRecIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMGetInitState(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getUsimAuthentication(int aSerial, String aAid, String aRandS, int aRandLength, String aAutnS, int AautnLength) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aAid);
            _hidl_request.writeString(aRandS);
            _hidl_request.writeInt32(aRandLength);
            _hidl_request.writeString(aAutnS);
            _hidl_request.writeInt32(AautnLength);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void smartCardTransmit(int aSerial, String aCmd) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aCmd);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMGetInfo(int aSerial, int aEFDevice) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEFDevice);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void UIMInternalRequestCmd(int aSerial, int aCmd, int aSlotid, int aIsvolte) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aCmd);
            _hidl_request.writeInt32(aSlotid);
            _hidl_request.writeInt32(aIsvolte);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccSelectApplication(int aSerial, String aAid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aAid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccDeactivateApplication(int aSerial, int aSessionId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSessionId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccApplication(int aSerial, int aSessionId, int aCommand, int aField, String aPath, int aP1, int aP2, int aP3, String aData, String aPin2) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSessionId);
            _hidl_request.writeInt32(aCommand);
            _hidl_request.writeInt32(aField);
            _hidl_request.writeString(aPath);
            _hidl_request.writeInt32(aP1);
            _hidl_request.writeInt32(aP2);
            _hidl_request.writeInt32(aP3);
            _hidl_request.writeString(aData);
            _hidl_request.writeString(aPin2);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccAkaAuthenticate(int aSerial, int aSessionId, String aRand, String aAutn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSessionId);
            _hidl_request.writeString(aRand);
            _hidl_request.writeString(aAutn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateBootstrap(int aSerial, int aSessionId, String aRand, String aAutn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSessionId);
            _hidl_request.writeString(aRand);
            _hidl_request.writeString(aAutn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateNaf(int aSerial, int aSessionId, String aNafld) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSessionId);
            _hidl_request.writeString(aNafld);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uimPowerDownRequest(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void USIMSmartcardGetAtr(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCdmaEriVersion(int aSerial, int aVersion) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aVersion);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCdmaFactoryReset(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getEhrpdInfoForIms(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getMipErrorCode(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void cancelManualSearchingRequest(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPreviousNetworkSelectionModeManual(int aSerial, String aOperatorNumeric, String aOperatorRat) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aOperatorNumeric);
            _hidl_request.writeString(aOperatorRat);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setRmnetAutoconnect(int aSerial, int aParam) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aParam);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getSearchStatus(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getEngineeringModeInfo(int aSerial, int aParam) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aParam);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCSGSelectionManual(int aSerial, int aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getLteEmmErrorCode(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendApnDisableFlag(int aSerial, int aProfileId, int aDisable) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aProfileId);
            _hidl_request.writeInt32(aDisable);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void loadVolteE911ScanList(int aSerial, int aAirplaneModeState, int aImsRegiState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aAirplaneModeState);
            _hidl_request.writeInt32(aImsRegiState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(37, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getVolteE911NetworkType(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(38, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void exitVolteE911EmergencyMode(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(39, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistration(int aSerial, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(40, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendE911CallState(int aSerial, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(41, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationForHVoLTE(int aSerial, int aSystemMode, int aStateLength, int aType1, int aType2, int aRegistered1, int aRegistered2) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSystemMode);
            _hidl_request.writeInt32(aStateLength);
            _hidl_request.writeInt32(aType1);
            _hidl_request.writeInt32(aType2);
            _hidl_request.writeInt32(aRegistered1);
            _hidl_request.writeInt32(aRegistered2);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(42, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setVoiceDomainPref(int aSerial, int aMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(43, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setVoLteCall(int aSerial, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(44, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void closeImsPdn(int aSerial, int aReason) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aReason);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(45, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getLteInfoForIms(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(46, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setSrvccCallContextTransfer(int aSerial, int mInstanceId, int mCallType, int mCallState, int mCallSubState, int mIsMptyCall, int mDirection, String mAddress, int mIsAlertingTypeValid, int mAlertingType, String mName, int mNamePresent, int mNumberPresent) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(mInstanceId);
            _hidl_request.writeInt32(mCallType);
            _hidl_request.writeInt32(mCallState);
            _hidl_request.writeInt32(mCallSubState);
            _hidl_request.writeInt32(mIsMptyCall);
            _hidl_request.writeInt32(mDirection);
            _hidl_request.writeString(mAddress);
            _hidl_request.writeInt32(mIsAlertingTypeValid);
            _hidl_request.writeInt32(mAlertingType);
            _hidl_request.writeString(mName);
            _hidl_request.writeInt32(mNamePresent);
            _hidl_request.writeInt32(mNumberPresent);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(47, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendImsCallState(int aSerial, int aState, int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aState);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(48, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setRssiTestAntConf(int aSerial, int aAntType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aAntType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(49, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getRssiTest(int aSerial, int aSysMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSysMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(50, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setQcril(int aSerial, int aSet) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aSet);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(51, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setMiMoAntennaControlTest(int aSerial, int sys_mode, int mask) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(sys_mode);
            _hidl_request.writeInt32(mask);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(52, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setModemInfo(int aSerial, int aParam, String data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aParam);
            _hidl_request.writeString(data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(53, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getModemInfo(int aSerial, int aParam, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aParam);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(54, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getGPRIItem(int aSerial, int aParam) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aParam);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(55, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setGNOSInfo(int aSerial, LgeIntString aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            aData.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(56, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setLteBandMode(int aSerial, int aBandMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aBandMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(57, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setEmergency(int aSerial, int aState, int aMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aState);
            _hidl_request.writeInt32(aMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(58, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssModemReset(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(59, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDefaultAPN(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(60, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAdminAPN(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(61, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getTetheringAPN(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(62, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScriptValue(int aSerial, int aOnOff) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aOnOff);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(63, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetRFParameter(int aSerial, int aKindOfData, int aBufNum) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aKindOfData);
            _hidl_request.writeInt32(aBufNum);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(64, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetMisc(int aSerial, int aKindOfData, int aBufNum) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aKindOfData);
            _hidl_request.writeInt32(aBufNum);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(65, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEvent(int aSerial, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(66, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetLog(int aSerial, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(67, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetData(int aSerial, int aBufNum) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aBufNum);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(68, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetMem(int aSerial, int aPercent) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aPercent);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(69, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEventReg(int aSerial, int aEvent) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEvent);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(70, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void DMRequest(int aSerial, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(71, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsDataFlushEnabled(int aSerial, int aEnable) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEnable);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(72, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_SetCaptureMode_requestProc(int aSerial, int aIndex, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aIndex);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(73, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_requestProc(int aSerial, int aDataLen, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aDataLen);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(74, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_Oem_requestProc(int aSerial, int aIndex, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aIndex);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(75, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setNSRICallInfoTransfer(int aSerial, int aCallState, int aUEType, String aPhoneNumber) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aCallState);
            _hidl_request.writeInt32(aUEType);
            _hidl_request.writeString(aPhoneNumber);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(76, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSetRegisterCellularQualityReport(int aSerial, int aNumOfValues, int qualityRegister, int aType, ArrayList<Integer> aValues) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aNumOfValues);
            _hidl_request.writeInt32(qualityRegister);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aValues);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(77, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSendImsPdnStatus(int aSerial, int aImsPdnStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aImsPdnStatus);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(78, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setProximitySensorState(int aSerial, int aNear) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aNear);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(79, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationStatus(int aSerial, int aRegState, int aRegServices, int aDetailState, int aSystemMode, int aReason, int aSlotId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aRegState);
            _hidl_request.writeInt32(aRegServices);
            _hidl_request.writeInt32(aDetailState);
            _hidl_request.writeInt32(aSystemMode);
            _hidl_request.writeInt32(aReason);
            _hidl_request.writeInt32(aSlotId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(80, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsCallStatus(int aSerial, int aWholeCallState, int aIndividualCallIdentification, int aIndividualCallState, int aIndividualReason, int aIndividualCallType, int aSlotId, int aSystemMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aWholeCallState);
            _hidl_request.writeInt32(aIndividualCallIdentification);
            _hidl_request.writeInt32(aIndividualCallState);
            _hidl_request.writeInt32(aIndividualReason);
            _hidl_request.writeInt32(aIndividualCallType);
            _hidl_request.writeInt32(aSlotId);
            _hidl_request.writeInt32(aSystemMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(81, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScmMode(int aSerial, int aType, int aMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(82, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsStatus(int aSerial, int aType, int aImsState, int aReason, int aSlotId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aImsState);
            _hidl_request.writeInt32(aReason);
            _hidl_request.writeInt32(aSlotId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(83, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getIMSNetworkInfo(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(84, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeSetNetworkSelectionModeManual(int aSerial, String aOperatorNumeric, String aOperatorRat) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aOperatorNumeric);
            _hidl_request.writeString(aOperatorRat);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(85, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setupDataCallEx(int aSerial, int aRadioTechnology, int aIsRoaming, int aAllowRoaming, String aHandoverMode, String aV6addr, String aV4addr, int aNeedPcscf, int aIsEmergency) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aRadioTechnology);
            _hidl_request.writeInt32(aIsRoaming);
            _hidl_request.writeInt32(aAllowRoaming);
            _hidl_request.writeString(aHandoverMode);
            _hidl_request.writeString(aV6addr);
            _hidl_request.writeString(aV4addr);
            _hidl_request.writeInt32(aNeedPcscf);
            _hidl_request.writeInt32(aIsEmergency);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(86, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeCdmaWriteSmsToRuim(int aSerial, int aStatus, String aPdu) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aStatus);
            _hidl_request.writeString(aPdu);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(87, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetSignalStrength(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(88, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetIccCardStatus(int serial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(89, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetCurrentCalls(int serial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(90, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAvailableNetworks(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(91, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDataRegistrationState(int serial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(92, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPcasInfo(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(93, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setOtasnPdnState(int serial, int otasnPdnState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioPcfalcon.kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeInt32(otasnPdnState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(94, _hidl_request, _hidl_reply, 1);
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

    void DMRequest(int i, String str) throws RemoteException;

    void NSRI_Oem_requestProc(int i, int i2, String str) throws RemoteException;

    void NSRI_SetCaptureMode_requestProc(int i, int i2, String str) throws RemoteException;

    void NSRI_requestProc(int i, int i2, String str) throws RemoteException;

    void PBMDeleteRecord(int i, int i2, int i3) throws RemoteException;

    void PBMGetInfo(int i, int i2) throws RemoteException;

    void PBMGetInitState(int i) throws RemoteException;

    void PBMReadRecord(int i, int i2, int i3) throws RemoteException;

    void PBMWriteRecord(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i6, int i7) throws RemoteException;

    void UIMInternalRequestCmd(int i, int i2, int i3, int i4) throws RemoteException;

    void USIMSmartcardGetAtr(int i) throws RemoteException;

    IHwBinder asBinder();

    void cancelManualSearchingRequest(int i) throws RemoteException;

    void closeImsPdn(int i, int i2) throws RemoteException;

    void embmsDisable(int i, int i2) throws RemoteException;

    void embmsEnable(int i, int i2) throws RemoteException;

    void embmsGetCoverageState(int i, int i2) throws RemoteException;

    void embmsGetTime(int i, int i2) throws RemoteException;

    void embmsStartSession(int i) throws RemoteException;

    void embmsStopSession(int i) throws RemoteException;

    void embmsSwitchSession(int i) throws RemoteException;

    void exitVolteE911EmergencyMode(int i) throws RemoteException;

    void getAdminAPN(int i) throws RemoteException;

    void getAvailableNetworks(int i) throws RemoteException;

    void getDataRegistrationState(int i) throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    void getDefaultAPN(int i) throws RemoteException;

    void getEhrpdInfoForIms(int i) throws RemoteException;

    void getEngineeringModeInfo(int i, int i2) throws RemoteException;

    void getGPRIItem(int i, int i2) throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getIMSNetworkInfo(int i) throws RemoteException;

    void getLteEmmErrorCode(int i) throws RemoteException;

    void getLteInfoForIms(int i) throws RemoteException;

    void getMipErrorCode(int i) throws RemoteException;

    void getModemInfo(int i, int i2, String str) throws RemoteException;

    void getRssiTest(int i, int i2) throws RemoteException;

    void getSearchStatus(int i) throws RemoteException;

    void getTetheringAPN(int i) throws RemoteException;

    void getUsimAuthentication(int i, String str, String str2, int i2, String str3, int i3) throws RemoteException;

    void getVolteE911NetworkType(int i) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    void iwlanSendImsPdnStatus(int i, int i2) throws RemoteException;

    void iwlanSetRegisterCellularQualityReport(int i, int i2, int i3, int i4, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeCdmaWriteSmsToRuim(int i, int i2, String str) throws RemoteException;

    void lgeGetCurrentCalls(int i) throws RemoteException;

    void lgeGetIccCardStatus(int i) throws RemoteException;

    void lgeGetSignalStrength(int i) throws RemoteException;

    void lgeSetNetworkSelectionModeManual(int i, String str, String str2) throws RemoteException;

    boolean linkToDeath(DeathRecipient deathRecipient, long j) throws RemoteException;

    void loadVolteE911ScanList(int i, int i2, int i3) throws RemoteException;

    void mocaAlarmEvent(int i, String str) throws RemoteException;

    void mocaAlarmEventReg(int i, int i2) throws RemoteException;

    void mocaGetData(int i, int i2) throws RemoteException;

    void mocaGetMisc(int i, int i2, int i3) throws RemoteException;

    void mocaGetRFParameter(int i, int i2, int i3) throws RemoteException;

    void mocaSetLog(int i, String str) throws RemoteException;

    void mocaSetMem(int i, int i2) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    void sendApnDisableFlag(int i, int i2, int i3) throws RemoteException;

    void sendE911CallState(int i, int i2) throws RemoteException;

    void sendImsCallState(int i, int i2, int i3) throws RemoteException;

    void setCSGSelectionManual(int i, int i2) throws RemoteException;

    void setCdmaEriVersion(int i, int i2) throws RemoteException;

    void setCdmaFactoryReset(int i) throws RemoteException;

    void setEmergency(int i, int i2, int i3) throws RemoteException;

    void setGNOSInfo(int i, LgeIntString lgeIntString) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void setImsCallStatus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws RemoteException;

    void setImsDataFlushEnabled(int i, int i2) throws RemoteException;

    void setImsRegistration(int i, int i2) throws RemoteException;

    void setImsRegistrationForHVoLTE(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException;

    void setImsRegistrationStatus(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException;

    void setImsStatus(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void setLteBandMode(int i, int i2) throws RemoteException;

    void setMiMoAntennaControlTest(int i, int i2, int i3) throws RemoteException;

    void setModemInfo(int i, int i2, String str) throws RemoteException;

    void setNSRICallInfoTransfer(int i, int i2, int i3, String str) throws RemoteException;

    void setOtasnPdnState(int i, int i2) throws RemoteException;

    void setPcasInfo(int i) throws RemoteException;

    void setPreviousNetworkSelectionModeManual(int i, String str, String str2) throws RemoteException;

    void setProximitySensorState(int i, int i2) throws RemoteException;

    void setQcril(int i, int i2) throws RemoteException;

    void setResponseFunctions(ILgeRadioFalconResponse iLgeRadioFalconResponse) throws RemoteException;

    void setRmnetAutoconnect(int i, int i2) throws RemoteException;

    void setRssiTestAntConf(int i, int i2) throws RemoteException;

    void setScmMode(int i, int i2, int i3) throws RemoteException;

    void setScriptValue(int i, int i2) throws RemoteException;

    void setSrvccCallContextTransfer(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, int i8, int i9, String str2, int i10, int i11) throws RemoteException;

    void setVoLteCall(int i, int i2) throws RemoteException;

    void setVoiceDomainPref(int i, int i2) throws RemoteException;

    void setupDataCallEx(int i, int i2, int i3, int i4, String str, String str2, String str3, int i5, int i6) throws RemoteException;

    void smartCardTransmit(int i, String str) throws RemoteException;

    void uiccAkaAuthenticate(int i, int i2, String str, String str2) throws RemoteException;

    void uiccApplication(int i, int i2, int i3, int i4, String str, int i5, int i6, int i7, String str2, String str3) throws RemoteException;

    void uiccDeactivateApplication(int i, int i2) throws RemoteException;

    void uiccGbaAuthenticateBootstrap(int i, int i2, String str, String str2) throws RemoteException;

    void uiccGbaAuthenticateNaf(int i, int i2, String str) throws RemoteException;

    void uiccSelectApplication(int i, String str) throws RemoteException;

    void uimPowerDownRequest(int i) throws RemoteException;

    boolean unlinkToDeath(DeathRecipient deathRecipient) throws RemoteException;

    void vssModemReset(int i) throws RemoteException;

    static ILgeRadioPcfalcon asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof ILgeRadioPcfalcon)) {
            return (ILgeRadioPcfalcon) iface;
        }
        ILgeRadioPcfalcon proxy = new Proxy(binder);
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

    static ILgeRadioPcfalcon castFrom(IHwInterface iface) {
        return iface == null ? null : asInterface(iface.asBinder());
    }

    static ILgeRadioPcfalcon getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static ILgeRadioPcfalcon getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }
}
