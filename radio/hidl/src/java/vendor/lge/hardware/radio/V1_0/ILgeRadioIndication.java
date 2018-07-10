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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public interface ILgeRadioIndication extends IBase {
    public static final String kInterfaceName = "vendor.lge.hardware.radio@1.0::ILgeRadioIndication";

    public static abstract class Stub extends HwBinder implements ILgeRadioIndication {
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> interfaceChain() {
            return new ArrayList(Arrays.asList(new String[]{ILgeRadioIndication.kInterfaceName, IBase.kInterfaceName}));
        }

        public final String interfaceDescriptor() {
            return ILgeRadioIndication.kInterfaceName;
        }

        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList(Arrays.asList(new byte[][]{new byte[]{(byte) 21, (byte) -43, (byte) -64, (byte) 16, (byte) -75, (byte) 81, (byte) -8, (byte) -24, (byte) 101, (byte) 120, (byte) -49, (byte) -31, (byte) -83, (byte) -31, (byte) -69, (byte) -106, (byte) 7, (byte) 65, (byte) -78, (byte) 94, (byte) -20, (byte) 82, (byte) -43, (byte) 11, (byte) 52, (byte) -17, (byte) 18, (byte) 6, (byte) 80, (byte) 72, (byte) 109, (byte) 60}, new byte[]{(byte) -67, (byte) -38, (byte) -74, (byte) 24, (byte) 77, (byte) 122, (byte) 52, (byte) 109, (byte) -90, (byte) -96, (byte) 125, (byte) -64, (byte) -126, (byte) -116, (byte) -15, (byte) -102, (byte) 105, (byte) 111, (byte) 76, (byte) -86, (byte) 54, (byte) 17, (byte) -59, (byte) 31, (byte) 46, (byte) 20, (byte) 86, (byte) 90, (byte) 20, (byte) -76, (byte) 15, (byte) -39}}));
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
            if (ILgeRadioIndication.kInterfaceName.equals(descriptor)) {
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
            int aType;
            LgeEmbmsUnsolActiveSessionNotiMsg aActiveSessionInfo;
            LgeMocaConfigInfo aMiscInfo;
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    testLgeRadioIndication(_hidl_request.readInt32());
                    return;
                case 2:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    embmsCellInfoNotification(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 3:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    embmsCoverageState(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 4:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    aActiveSessionInfo = new LgeEmbmsUnsolActiveSessionNotiMsg();
                    aActiveSessionInfo.readFromParcel(_hidl_request);
                    embmsActiveSession(aType, aActiveSessionInfo);
                    return;
                case 5:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    aActiveSessionInfo = new LgeEmbmsUnsolActiveSessionNotiMsg();
                    aActiveSessionInfo.readFromParcel(_hidl_request);
                    embmsAvailableSession(aType, aActiveSessionInfo);
                    return;
                case 6:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeEmbmsUnsolSaiNotiMsg aSaiInfo = new LgeEmbmsUnsolSaiNotiMsg();
                    aSaiInfo.readFromParcel(_hidl_request);
                    embmsSaiListNotification(aType, aSaiInfo);
                    return;
                case 7:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeEmbmsUnsolOosNotiMsg aOosInfo = new LgeEmbmsUnsolOosNotiMsg();
                    aOosInfo.readFromParcel(_hidl_request);
                    embmsOosNotification(aType, aOosInfo);
                    return;
                case 8:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    embmsRadioStateNotification(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 9:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    embmsSvcInterestNotification(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 10:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    pbmInitDone(_hidl_request.readInt32());
                    return;
                case 11:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    bipProcmdStatus(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 12:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    gstkOtaState(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 13:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    gstkSimImsiState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 14:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    simUartStatus(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 15:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    simStatusToLdb(_hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 16:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    racInd(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 17:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    sprintLteEhrpdforced(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 18:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    wcdmaNetChanged(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 19:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    wcdmaNetToKoreaChanged(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 20:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    periodicCsgSearch(_hidl_request.readInt32());
                    return;
                case 21:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeCipheringInd(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 22:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lteAcbInfoInd(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 23:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    logRfBandInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 24:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    aMiscInfo = new LgeMocaConfigInfo();
                    aMiscInfo.readFromParcel(_hidl_request);
                    vssMocaMiscNoti(aType, aMiscInfo);
                    return;
                case 25:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    aMiscInfo = new LgeMocaConfigInfo();
                    aMiscInfo.readFromParcel(_hidl_request);
                    vssMocaAlaramEvent(aType, aMiscInfo);
                    return;
                case 26:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    vssMocaMemLimit(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 27:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteE9111xConnected(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 28:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteEpsNetworkFeatureSupport(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 29:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteNetworkSibInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 30:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteEmergencyCallFailCause(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 31:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteEmergencyAttachInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 32:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteLteConnectionStatus(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 33:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    voiceCodecIndicator(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 34:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeLteCaInd(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 35:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeProtocolInfoUnsolInd aUnsolInfo = new LgeProtocolInfoUnsolInd();
                    aUnsolInfo.readFromParcel(_hidl_request);
                    protocolInfoInd(aType, aUnsolInfo);
                    return;
                case 36:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeDataQosResponse aQosInfo = new LgeDataQosResponse();
                    aQosInfo.readFromParcel(_hidl_request);
                    dataQosChanged(aType, aQosInfo);
                    return;
                case 37:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    volteE911NetworkType(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 38:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    dqslEvent(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 39:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    vzwReservedPcoInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 40:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lteRejectCause(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 41:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    sib16TimeReceived(_hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readInt64());
                    return;
                case 42:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lteNetworkInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 43:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    ldbModemReset(_hidl_request.readInt32());
                    return;
                case 44:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    wcdmaRejectReceived(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 45:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    wcdmaAcceptReceived(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 46:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lteEmmReject(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 47:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    sprintLteRoamIndicator(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 48:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    imsPrefStatusInd(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 49:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    SsacChangeInfoInd(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 50:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeNsriNotice aNsriNoti = new LgeNsriNotice();
                    aNsriNoti.readFromParcel(_hidl_request);
                    vssNsriNotiMsg(aType, aNsriNoti);
                    return;
                case 51:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    resimTimeExpired(_hidl_request.readInt32());
                    return;
                case 52:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    iwlanCellularQualityChangedInd(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 53:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    iwlanReserved(_hidl_request.readInt32());
                    return;
                case 54:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeCsfbStatusInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 55:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeHoStatusInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 56:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeNetBandInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 57:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeGsmEncrypInfo(_hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 58:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeRpIndResponse aInd = new LgeRpIndResponse();
                    aInd.readFromParcel(_hidl_request);
                    lgeUnsol(aType, aInd);
                    return;
                case 59:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeEccTaskResult(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case RadioError.NETWORK_NOT_READY /*60*/:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    LgeSignalStrength aSignalStrength = new LgeSignalStrength();
                    aSignalStrength.readFromParcel(_hidl_request);
                    lgeCurrentSignalStrength(aType, aSignalStrength);
                    return;
                case RadioError.NOT_PROVISIONED /*61*/:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    lgeRilConnect(_hidl_request.readInt32());
                    return;
                case RadioError.NO_SUBSCRIPTION /*62*/:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    capsensorRrcState(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 63:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    aType = _hidl_request.readInt32();
                    ImsPCSCFRestorationVZW aInts = new ImsPCSCFRestorationVZW();
                    aInts.readFromParcel(_hidl_request);
                    dataImsPCSCFResoration(aType, aInts);
                    return;
                case 64:
                    _hidl_request.enforceInterface(ILgeRadioIndication.kInterfaceName);
                    onUssdMtk(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
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

    public static final class Proxy implements ILgeRadioIndication {
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
                return "[class or subclass of vendor.lge.hardware.radio@1.0::ILgeRadioIndication]@Proxy";
            }
        }

        public void testLgeRadioIndication(int serial) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(serial);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsCellInfoNotification(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsCoverageState(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsActiveSession(int aType, LgeEmbmsUnsolActiveSessionNotiMsg aActiveSessionInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aActiveSessionInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsAvailableSession(int aType, LgeEmbmsUnsolActiveSessionNotiMsg aActiveSessionInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aActiveSessionInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsSaiListNotification(int aType, LgeEmbmsUnsolSaiNotiMsg aSaiInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aSaiInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsOosNotification(int aType, LgeEmbmsUnsolOosNotiMsg aOosInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aOosInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsRadioStateNotification(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void embmsSvcInterestNotification(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void pbmInitDone(int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void bipProcmdStatus(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void gstkOtaState(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void gstkSimImsiState(int aType, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void simUartStatus(int aType, String aStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeString(aStatus);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void simStatusToLdb(int aType, String aLdbStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeString(aLdbStatus);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void racInd(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sprintLteEhrpdforced(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void wcdmaNetChanged(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void wcdmaNetToKoreaChanged(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void periodicCsgSearch(int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeCipheringInd(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lteAcbInfoInd(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void logRfBandInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssMocaMiscNoti(int aType, LgeMocaConfigInfo aMiscInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aMiscInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssMocaAlaramEvent(int aType, LgeMocaConfigInfo aMiscInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aMiscInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssMocaMemLimit(int aType, int aMemLimit) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aMemLimit);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteE9111xConnected(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteEpsNetworkFeatureSupport(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteNetworkSibInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteEmergencyCallFailCause(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteEmergencyAttachInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteLteConnectionStatus(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void voiceCodecIndicator(int aType, int aWbCodec) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aWbCodec);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeLteCaInd(int aType, int aLteCaInd) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aLteCaInd);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void protocolInfoInd(int aType, LgeProtocolInfoUnsolInd aUnsolInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aUnsolInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void dataQosChanged(int aType, LgeDataQosResponse aQosInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aQosInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void volteE911NetworkType(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(37, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void dqslEvent(int aType, int aDqslEvent) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aDqslEvent);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(38, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vzwReservedPcoInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(39, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lteRejectCause(int aType, int aRejectCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aRejectCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(40, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sib16TimeReceived(int aType, String aSib16Time, long aRreceivedTime) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeString(aSib16Time);
            _hidl_request.writeInt64(aRreceivedTime);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(41, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lteNetworkInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(42, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void ldbModemReset(int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(43, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void wcdmaRejectReceived(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(44, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void wcdmaAcceptReceived(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(45, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lteEmmReject(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(46, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sprintLteRoamIndicator(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(47, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void imsPrefStatusInd(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(48, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void SsacChangeInfoInd(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(49, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssNsriNotiMsg(int aType, LgeNsriNotice aNsriNoti) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aNsriNoti.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(50, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void resimTimeExpired(int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(51, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanCellularQualityChangedInd(int aType, int aQualityType, int aValue) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aQualityType);
            _hidl_request.writeInt32(aValue);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(52, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanReserved(int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(53, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeCsfbStatusInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(54, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeHoStatusInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(55, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeNetBandInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(56, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGsmEncrypInfo(int aType, ArrayList<Integer> aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32Vector(aInts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(57, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeUnsol(int aType, LgeRpIndResponse aInd) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aInd.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(58, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeEccTaskResult(int aType, int aInd) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aInd);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(59, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeCurrentSignalStrength(int aType, LgeSignalStrength aSignalStrength) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aSignalStrength.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(60, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeRilConnect(int aType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(61, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void capsensorRrcState(int aType, int aInd) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            _hidl_request.writeInt32(aInd);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(62, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void dataImsPCSCFResoration(int aType, ImsPCSCFRestorationVZW aInts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(aType);
            aInts.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(63, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void onUssdMtk(int type, int modeType, int aInd, String msg) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(type);
            _hidl_request.writeInt32(modeType);
            _hidl_request.writeInt32(aInd);
            _hidl_request.writeString(msg);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(64, _hidl_request, _hidl_reply, 1);
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

    void SsacChangeInfoInd(int i, ArrayList<Integer> arrayList) throws RemoteException;

    IHwBinder asBinder();

    void bipProcmdStatus(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void capsensorRrcState(int i, int i2) throws RemoteException;

    void dataImsPCSCFResoration(int i, ImsPCSCFRestorationVZW imsPCSCFRestorationVZW) throws RemoteException;

    void dataQosChanged(int i, LgeDataQosResponse lgeDataQosResponse) throws RemoteException;

    void dqslEvent(int i, int i2) throws RemoteException;

    void embmsActiveSession(int i, LgeEmbmsUnsolActiveSessionNotiMsg lgeEmbmsUnsolActiveSessionNotiMsg) throws RemoteException;

    void embmsAvailableSession(int i, LgeEmbmsUnsolActiveSessionNotiMsg lgeEmbmsUnsolActiveSessionNotiMsg) throws RemoteException;

    void embmsCellInfoNotification(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void embmsCoverageState(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void embmsOosNotification(int i, LgeEmbmsUnsolOosNotiMsg lgeEmbmsUnsolOosNotiMsg) throws RemoteException;

    void embmsRadioStateNotification(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void embmsSaiListNotification(int i, LgeEmbmsUnsolSaiNotiMsg lgeEmbmsUnsolSaiNotiMsg) throws RemoteException;

    void embmsSvcInterestNotification(int i, ArrayList<Integer> arrayList) throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void gstkOtaState(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void gstkSimImsiState(int i, int i2) throws RemoteException;

    void imsPrefStatusInd(int i, ArrayList<Integer> arrayList) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    void iwlanCellularQualityChangedInd(int i, int i2, int i3) throws RemoteException;

    void iwlanReserved(int i) throws RemoteException;

    void ldbModemReset(int i) throws RemoteException;

    void lgeCipheringInd(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeCsfbStatusInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeCurrentSignalStrength(int i, LgeSignalStrength lgeSignalStrength) throws RemoteException;

    void lgeEccTaskResult(int i, int i2) throws RemoteException;

    void lgeGsmEncrypInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeHoStatusInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeLteCaInd(int i, int i2) throws RemoteException;

    void lgeNetBandInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeRilConnect(int i) throws RemoteException;

    void lgeUnsol(int i, LgeRpIndResponse lgeRpIndResponse) throws RemoteException;

    boolean linkToDeath(DeathRecipient deathRecipient, long j) throws RemoteException;

    void logRfBandInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lteAcbInfoInd(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lteEmmReject(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lteNetworkInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void lteRejectCause(int i, int i2) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void onUssdMtk(int i, int i2, int i3, String str) throws RemoteException;

    void pbmInitDone(int i) throws RemoteException;

    void periodicCsgSearch(int i) throws RemoteException;

    void ping() throws RemoteException;

    void protocolInfoInd(int i, LgeProtocolInfoUnsolInd lgeProtocolInfoUnsolInd) throws RemoteException;

    void racInd(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void resimTimeExpired(int i) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void sib16TimeReceived(int i, String str, long j) throws RemoteException;

    void simStatusToLdb(int i, String str) throws RemoteException;

    void simUartStatus(int i, String str) throws RemoteException;

    void sprintLteEhrpdforced(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void sprintLteRoamIndicator(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void testLgeRadioIndication(int i) throws RemoteException;

    boolean unlinkToDeath(DeathRecipient deathRecipient) throws RemoteException;

    void voiceCodecIndicator(int i, int i2) throws RemoteException;

    void volteE9111xConnected(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void volteE911NetworkType(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void volteEmergencyAttachInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void volteEmergencyCallFailCause(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void volteEpsNetworkFeatureSupport(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void volteLteConnectionStatus(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void volteNetworkSibInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void vssMocaAlaramEvent(int i, LgeMocaConfigInfo lgeMocaConfigInfo) throws RemoteException;

    void vssMocaMemLimit(int i, int i2) throws RemoteException;

    void vssMocaMiscNoti(int i, LgeMocaConfigInfo lgeMocaConfigInfo) throws RemoteException;

    void vssNsriNotiMsg(int i, LgeNsriNotice lgeNsriNotice) throws RemoteException;

    void vzwReservedPcoInfo(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void wcdmaAcceptReceived(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void wcdmaNetChanged(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void wcdmaNetToKoreaChanged(int i, ArrayList<Integer> arrayList) throws RemoteException;

    void wcdmaRejectReceived(int i, ArrayList<Integer> arrayList) throws RemoteException;

    static ILgeRadioIndication asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof ILgeRadioIndication)) {
            return (ILgeRadioIndication) iface;
        }
        ILgeRadioIndication proxy = new Proxy(binder);
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

    static ILgeRadioIndication castFrom(IHwInterface iface) {
        return iface == null ? null : asInterface(iface.asBinder());
    }

    static ILgeRadioIndication getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static ILgeRadioIndication getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }
}
