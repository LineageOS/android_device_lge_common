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

public interface ILgeRadioTest extends IBase {
    public static final String kInterfaceName = "vendor.lge.hardware.radio@1.0::ILgeRadioTest";

    public static abstract class Stub extends HwBinder implements ILgeRadioTest {
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> interfaceChain() {
            return new ArrayList(Arrays.asList(new String[]{ILgeRadioTest.kInterfaceName, IBase.kInterfaceName}));
        }

        public final String interfaceDescriptor() {
            return ILgeRadioTest.kInterfaceName;
        }

        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList(Arrays.asList(new byte[][]{new byte[]{Byte.MAX_VALUE, (byte) -23, (byte) -81, (byte) -95, (byte) 123, (byte) -100, (byte) 5, (byte) -22, (byte) 39, (byte) 25, (byte) -109, (byte) -70, (byte) 54, (byte) 52, (byte) -63, (byte) 58, (byte) 95, (byte) -33, (byte) 18, Byte.MAX_VALUE, (byte) 43, (byte) 100, (byte) 0, (byte) 87, (byte) -51, (byte) -97, (byte) 52, (byte) -19, (byte) 17, (byte) 15, (byte) 65, (byte) 15}, new byte[]{(byte) -67, (byte) -38, (byte) -74, (byte) 24, (byte) 77, (byte) 122, (byte) 52, (byte) 109, (byte) -90, (byte) -96, (byte) 125, (byte) -64, (byte) -126, (byte) -116, (byte) -15, (byte) -102, (byte) 105, (byte) 111, (byte) 76, (byte) -86, (byte) 54, (byte) 17, (byte) -59, (byte) 31, (byte) 46, (byte) 20, (byte) 86, (byte) 90, (byte) 20, (byte) -76, (byte) 15, (byte) -39}}));
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
            if (ILgeRadioTest.kInterfaceName.equals(descriptor)) {
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
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    testLgeRadioInterface(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 2:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsEnable(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 3:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsDisable(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 4:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsStartSession(_hidl_request.readInt32());
                    return;
                case 5:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsStopSession(_hidl_request.readInt32());
                    return;
                case 6:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsSwitchSession(_hidl_request.readInt32());
                    return;
                case 7:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsGetTime(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 8:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    embmsGetCoverageState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 9:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    PBMReadRecord(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 10:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    PBMWriteRecord(_hidl_request.readInt32());
                    return;
                case 11:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    PBMDeleteRecord(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 12:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    PBMGetInitState(_hidl_request.readInt32());
                    return;
                case 13:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getUsimAuthentication(_hidl_request.readInt32());
                    return;
                case 14:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    smartCardTransmit(_hidl_request.readInt32());
                    return;
                case 15:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    PBMGetInfo(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 16:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    UIMInternalRequestCmd(_hidl_request.readInt32());
                    return;
                case 17:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uiccSelectApplication(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 18:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uiccDeactivateApplication(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 19:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uiccApplication(_hidl_request.readInt32());
                    return;
                case 20:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uiccAkaAuthenticate(_hidl_request.readInt32());
                    return;
                case 21:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uiccGbaAuthenticateBootstrap(_hidl_request.readInt32());
                    return;
                case 22:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uiccGbaAuthenticateNaf(_hidl_request.readInt32());
                    return;
                case 23:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    uimPowerDownRequest(_hidl_request.readInt32());
                    return;
                case 24:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    USIMSmartcardGetAtr(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 25:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setCdmaEriVersion(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 26:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setCdmaFactoryReset(_hidl_request.readInt32());
                    return;
                case 27:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getEhrpdInfoForIms(_hidl_request.readInt32());
                    return;
                case 28:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getMipErrorCode(_hidl_request.readInt32());
                    return;
                case 29:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    cancelManualSearchingRequest(_hidl_request.readInt32());
                    return;
                case 30:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setPreviousNetworkSelectionModeManual(_hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 31:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setRmnetAutoconnect(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 32:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getSearchStatus(_hidl_request.readInt32());
                    return;
                case 33:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getEngineeringModeInfo(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 34:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setCSGSelectionManual(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 35:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getLteEmmErrorCode(_hidl_request.readInt32());
                    return;
                case 36:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    sendApnDisableFlag(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readBool());
                    return;
                case 37:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    loadVolteE911ScanList(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 38:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getVolteE911NetworkType(_hidl_request.readInt32());
                    return;
                case 39:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    exitVolteE911EmergencyMode(_hidl_request.readInt32());
                    return;
                case 40:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setImsRegistration(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 41:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    sendE911CallState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 42:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setImsRegistrationForHVoLTE(_hidl_request.readInt32());
                    return;
                case 43:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setVoiceDomainPref(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 44:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setVoLteCall(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 45:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    closeImsPdn(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 46:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getLteInfoForIms(_hidl_request.readInt32());
                    return;
                case 47:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setSrvccCallContextTransfer(_hidl_request.readInt32());
                    return;
                case 48:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    sendImsCallState(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 49:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setRssiTestAntConf(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 50:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getRssiTest(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 51:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setQcril(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 52:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setMiMoAntennaControlTest(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 53:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setModemInfo(_hidl_request.readInt32());
                    return;
                case 54:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getModemInfo(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 55:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getGPRIItem(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 56:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setLteBandMode(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 57:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setEmergency(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 58:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    vssModemReset(_hidl_request.readInt32());
                    return;
                case 59:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getDefaultAPN(_hidl_request.readInt32());
                    return;
                case RadioError.NETWORK_NOT_READY /*60*/:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getAdminAPN(_hidl_request.readInt32());
                    return;
                case RadioError.NOT_PROVISIONED /*61*/:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getTetheringAPN(_hidl_request.readInt32());
                    return;
                case RadioError.NO_SUBSCRIPTION /*62*/:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setScriptValue(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 63:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaGetRFParameter(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 64:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaGetMisc(_hidl_request.readInt32());
                    return;
                case 65:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaAlarmEvent(_hidl_request.readInt32());
                    return;
                case 66:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaSetLog(_hidl_request.readInt32());
                    return;
                case 67:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaGetData(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 68:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaSetMem(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 69:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    mocaAlarmEventReg(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 70:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    DMRequest(_hidl_request.readInt32());
                    return;
                case 71:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setImsDataFlushEnabled(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 72:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    NSRI_SetCaptureMode_requestProc(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 73:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    NSRI_requestProc(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 74:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    NSRI_Oem_requestProc(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 75:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    iwlanSetRegisterCellularQualityReport(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 76:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    iwlanSendImsPdnStatus(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 77:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setProximitySensorState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 78:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setImsRegistrationStatus(_hidl_request.readInt32());
                    return;
                case 79:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setImsCallStatus(_hidl_request.readInt32());
                    return;
                case 80:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setScmMode(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 81:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setImsStatus(_hidl_request.readInt32());
                    return;
                case RadioNVItems.RIL_NV_LTE_BSR_MAX_TIME /*82*/:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    getIMSNetworkInfo(_hidl_request.readInt32());
                    return;
                case 83:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    lgeSetNetworkSelectionModeManual(_hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 84:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setupDataCallEx(_hidl_request.readInt32());
                    return;
                case 85:
                    _hidl_request.enforceInterface(ILgeRadioTest.kInterfaceName);
                    setPcasInfo(_hidl_request.readInt32());
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

    public static final class Proxy implements ILgeRadioTest {
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
                return "[class or subclass of vendor.lge.hardware.radio@1.0::ILgeRadioTest]@Proxy";
            }
        }

        public void testLgeRadioInterface(int aSerial, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsEnable(int aSerial, int aTransId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void PBMWriteRecord(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getUsimAuthentication(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void smartCardTransmit(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void UIMInternalRequestCmd(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void uiccApplication(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccAkaAuthenticate(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateBootstrap(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateNaf(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void USIMSmartcardGetAtr(int aSerial, int aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aData);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendApnDisableFlag(int aSerial, int aProfileId, boolean aDisable) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aProfileId);
            _hidl_request.writeBool(aDisable);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void setImsRegistrationForHVoLTE(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(46, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setSrvccCallContextTransfer(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void setQcril(int aSerial, int AcmdId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(AcmdId);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void setModemInfo(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(53, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getModemInfo(int aSerial, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
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
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
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

        public void setLteBandMode(int aSerial, int aBandMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aBandMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(56, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setEmergency(int aSerial, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(57, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssModemReset(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(58, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDefaultAPN(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(59, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAdminAPN(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(60, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getTetheringAPN(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(61, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScriptValue(int aSerial, int aOnOff) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aOnOff);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(62, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetRFParameter(int aSerial, int aKindOfData, int aBufNum) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aKindOfData);
            _hidl_request.writeInt32(aBufNum);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(63, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetMisc(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(64, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEvent(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(65, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetLog(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(66, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetData(int aSerial, int aBufNum) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aBufNum);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(67, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetMem(int aSerial, int aPercent) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aPercent);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(68, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEventReg(int aSerial, int aEvent) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEvent);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(69, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void DMRequest(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(70, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsDataFlushEnabled(int aSerial, int aEnable) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aEnable);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(71, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_SetCaptureMode_requestProc(int aSerial, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(72, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_requestProc(int aSerial, int aDataLeng) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aDataLeng);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(73, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_Oem_requestProc(int aSerial, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(74, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSetRegisterCellularQualityReport(int aSerial, int qualityRegister, int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(qualityRegister);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(75, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSendImsPdnStatus(int aSerial, int aImsPdnStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aImsPdnStatus);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(76, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setProximitySensorState(int aSerial, int aNear) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aNear);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(77, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationStatus(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(78, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsCallStatus(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(79, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScmMode(int aSerial, int aType, int aMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(80, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsStatus(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(81, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getIMSNetworkInfo(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(82, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeSetNetworkSelectionModeManual(int aSerial, String aOperatorNumeric, String aOperatorRat) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            _hidl_request.writeString(aOperatorNumeric);
            _hidl_request.writeString(aOperatorRat);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(83, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setupDataCallEx(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(84, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPcasInfo(int aSerial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioTest.kInterfaceName);
            _hidl_request.writeInt32(aSerial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(85, _hidl_request, _hidl_reply, 1);
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

    void DMRequest(int i) throws RemoteException;

    void NSRI_Oem_requestProc(int i, int i2) throws RemoteException;

    void NSRI_SetCaptureMode_requestProc(int i, int i2) throws RemoteException;

    void NSRI_requestProc(int i, int i2) throws RemoteException;

    void PBMDeleteRecord(int i, int i2, int i3) throws RemoteException;

    void PBMGetInfo(int i, int i2) throws RemoteException;

    void PBMGetInitState(int i) throws RemoteException;

    void PBMReadRecord(int i, int i2, int i3) throws RemoteException;

    void PBMWriteRecord(int i) throws RemoteException;

    void UIMInternalRequestCmd(int i) throws RemoteException;

    void USIMSmartcardGetAtr(int i, int i2) throws RemoteException;

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

    void getModemInfo(int i, String str) throws RemoteException;

    void getRssiTest(int i, int i2) throws RemoteException;

    void getSearchStatus(int i) throws RemoteException;

    void getTetheringAPN(int i) throws RemoteException;

    void getUsimAuthentication(int i) throws RemoteException;

    void getVolteE911NetworkType(int i) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    void iwlanSendImsPdnStatus(int i, int i2) throws RemoteException;

    void iwlanSetRegisterCellularQualityReport(int i, int i2, int i3) throws RemoteException;

    void lgeSetNetworkSelectionModeManual(int i, String str, String str2) throws RemoteException;

    boolean linkToDeath(DeathRecipient deathRecipient, long j) throws RemoteException;

    void loadVolteE911ScanList(int i, int i2, int i3) throws RemoteException;

    void mocaAlarmEvent(int i) throws RemoteException;

    void mocaAlarmEventReg(int i, int i2) throws RemoteException;

    void mocaGetData(int i, int i2) throws RemoteException;

    void mocaGetMisc(int i) throws RemoteException;

    void mocaGetRFParameter(int i, int i2, int i3) throws RemoteException;

    void mocaSetLog(int i) throws RemoteException;

    void mocaSetMem(int i, int i2) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    void sendApnDisableFlag(int i, int i2, boolean z) throws RemoteException;

    void sendE911CallState(int i, int i2) throws RemoteException;

    void sendImsCallState(int i, int i2, int i3) throws RemoteException;

    void setCSGSelectionManual(int i, int i2) throws RemoteException;

    void setCdmaEriVersion(int i, int i2) throws RemoteException;

    void setCdmaFactoryReset(int i) throws RemoteException;

    void setEmergency(int i, int i2) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void setImsCallStatus(int i) throws RemoteException;

    void setImsDataFlushEnabled(int i, int i2) throws RemoteException;

    void setImsRegistration(int i, int i2) throws RemoteException;

    void setImsRegistrationForHVoLTE(int i) throws RemoteException;

    void setImsRegistrationStatus(int i) throws RemoteException;

    void setImsStatus(int i) throws RemoteException;

    void setLteBandMode(int i, int i2) throws RemoteException;

    void setMiMoAntennaControlTest(int i, int i2, int i3) throws RemoteException;

    void setModemInfo(int i) throws RemoteException;

    void setPcasInfo(int i) throws RemoteException;

    void setPreviousNetworkSelectionModeManual(int i, String str, String str2) throws RemoteException;

    void setProximitySensorState(int i, int i2) throws RemoteException;

    void setQcril(int i, int i2) throws RemoteException;

    void setRmnetAutoconnect(int i, int i2) throws RemoteException;

    void setRssiTestAntConf(int i, int i2) throws RemoteException;

    void setScmMode(int i, int i2, int i3) throws RemoteException;

    void setScriptValue(int i, int i2) throws RemoteException;

    void setSrvccCallContextTransfer(int i) throws RemoteException;

    void setVoLteCall(int i, int i2) throws RemoteException;

    void setVoiceDomainPref(int i, int i2) throws RemoteException;

    void setupDataCallEx(int i) throws RemoteException;

    void smartCardTransmit(int i) throws RemoteException;

    void testLgeRadioInterface(int i, int i2) throws RemoteException;

    void uiccAkaAuthenticate(int i) throws RemoteException;

    void uiccApplication(int i) throws RemoteException;

    void uiccDeactivateApplication(int i, int i2) throws RemoteException;

    void uiccGbaAuthenticateBootstrap(int i) throws RemoteException;

    void uiccGbaAuthenticateNaf(int i) throws RemoteException;

    void uiccSelectApplication(int i, String str) throws RemoteException;

    void uimPowerDownRequest(int i) throws RemoteException;

    boolean unlinkToDeath(DeathRecipient deathRecipient) throws RemoteException;

    void vssModemReset(int i) throws RemoteException;

    static ILgeRadioTest asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof ILgeRadioTest)) {
            return (ILgeRadioTest) iface;
        }
        ILgeRadioTest proxy = new Proxy(binder);
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

    static ILgeRadioTest castFrom(IHwInterface iface) {
        return iface == null ? null : asInterface(iface.asBinder());
    }

    static ILgeRadioTest getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static ILgeRadioTest getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }
}
