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

public interface ILgeRadioFalconResponse extends IBase {
    public static final String kInterfaceName = "vendor.lge.hardware.radio@1.0::ILgeRadioFalconResponse";

    public static final class Proxy implements ILgeRadioFalconResponse {
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
                return "[class or subclass of vendor.lge.hardware.radio@1.0::ILgeRadioFalconResponse]@Proxy";
            }
        }

        public void PBMReadRecordResponse(int aToken, int aError, int aDevice, int aIndex, int aType, int aAdtype, String number, String name, String aAdditionalNumber, String aAdditionalNumberA, String aAdditionalNumberB, String aEmailAddress, String aSecondName, int aGasId, int aSyncCnt) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
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
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMWriteRecordResponse(int aToken, int aError, int aPBerror, int aDevice, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aPBerror);
            _hidl_request.writeInt32(aDevice);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMDeleteRecordResponse(int aToken, int aError, int aPBerror, int aDevice, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aPBerror);
            _hidl_request.writeInt32(aDevice);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMGetInitStateResponse(int aToken, int aError, int aInitDone) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aInitDone);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getUsimAuthenticationResponse(int aToken, int aError, int aAuthRet, String aAuthData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aAuthRet);
            _hidl_request.writeString(aAuthData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void smartCardTransmitResponse(int aToken, int aError, String ScRetData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(ScRetData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void PBMGetInfoResponse(int aToken, int aError, int aDevice, int aStatus, int aMaxNumLength, int aMaxTextLength, int aNumofUsedRec, int aNumofFreeExt, int aNumofTotRec, int aFileType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aDevice);
            _hidl_request.writeInt32(aStatus);
            _hidl_request.writeInt32(aMaxNumLength);
            _hidl_request.writeInt32(aMaxTextLength);
            _hidl_request.writeInt32(aNumofUsedRec);
            _hidl_request.writeInt32(aNumofFreeExt);
            _hidl_request.writeInt32(aNumofTotRec);
            _hidl_request.writeInt32(aFileType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void UIMInternalRequestCmdResponse(int aToken, int aError, String aNum, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aNum);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccSelectApplicationResponse(int aToken, int aError, int aSessionId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aSessionId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccDeactivateApplicationResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccApplicationResponse(int aToken, int aError, int aSW1, int aSW2, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aSW1);
            _hidl_request.writeInt32(aSW2);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccAkaAuthenticateResponse(int aToken, int aError, String aRes, String aCk, String aLk, String aKc, String aAuts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aRes);
            _hidl_request.writeString(aCk);
            _hidl_request.writeString(aLk);
            _hidl_request.writeString(aKc);
            _hidl_request.writeString(aAuts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateBootstrapResponse(int aToken, int aError, String aRes, String aAuts) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aRes);
            _hidl_request.writeString(aAuts);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uiccGbaAuthenticateNafResponse(int aToken, int aError, String aGbaNafRes) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aGbaNafRes);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void uimPowerDownRequestResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void USIMSmartcardGetAtrResponse(int aToken, int aError, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCdmaEriVersionResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCdmaFactoryResetResponse(int aToken, int aError, int aOutData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aOutData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getEhrpdInfoForImsResponse(int aToken, int aError, String aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getMipErrorCodeResponse(int aToken, int aError, int aErrorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aErrorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void cancelManualSearchingRequestResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPreviousNetworkSelectionModeManualResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setRmnetAutoconnectResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getSearchStatusResponse(int aToken, int aError, int aState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getEngineeringModeInfoResponse(int aToken, int aError, String aModemInfoStr) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aModemInfoStr);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setCSGSelectionManualResponse(int aToken, int aError, String aCsgSession) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aCsgSession);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getLteEmmErrorCodeResponse(int aToken, int aError, int aEmmReject) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aEmmReject);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendApnDisableFlagResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void loadVolteE911ScanListResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getVolteE911NetworkTypeResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void exitVolteE911EmergencyModeResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendE911CallStateResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationForHVoLTEResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setVoiceDomainPrefResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setVoLteCallResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void closeImsPdnResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(37, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getLteInfoForImsResponse(int aToken, int aError, String aLgeInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aLgeInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(38, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void sendIMSCallStateResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(39, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setRssiTestAntConfResponse(int aToken, int aError, int aAntConfNum, int aResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aAntConfNum);
            _hidl_request.writeInt32(aResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(40, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getRssiTestResponse(int aToken, int aError, int aRx0, int aPhase0, int aRx1, int aPhase1, int aRx0Valid, int aPx1Valid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aRx0);
            _hidl_request.writeInt32(aPhase0);
            _hidl_request.writeInt32(aRx1);
            _hidl_request.writeInt32(aPhase1);
            _hidl_request.writeInt32(aRx0Valid);
            _hidl_request.writeInt32(aPx1Valid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(41, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setQcrilResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(42, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setMiMoAntennaControlTestResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(43, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setModemInfoResponse(int aToken, int aError, int aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(44, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getModemInfoResponse(int aToken, int aError, String aText) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aText);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(45, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getGPRIItemResponse(int aToken, int aError, String aGpriInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aGpriInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(46, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setGNOSInfoResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(47, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setLteBandModeResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(48, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setEmergencyResponse(int aToken, int aError, int aRet) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aRet);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(49, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void vssModemResetResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(50, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDefaultAPNResponse(int aToken, int aError, String aDefaultApn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aDefaultApn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(51, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAdminAPNResponse(int aToken, int aError, String aAdminApn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aAdminApn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(52, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getTetheringAPNResponse(int aToken, int aError, String aTetheringApn) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aTetheringApn);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(53, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScriptValueResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(54, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsDataFlushEnabledResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(55, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSetRegisterCellularQualityReportResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(56, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void iwlanSendImsPdnStatusResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(57, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setProximitySensorStateResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(58, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsRegistrationStatusResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(59, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsCallStatusResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(60, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setScmModeResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(61, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setImsStatusResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(62, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getIMSNetworkInfoResponse(int aToken, int aError, ArrayList<String> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeStringVector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(63, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeSetNetworkSelectionModeManualResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(64, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setupDataCallExResponse(int aToken, int aError, int aStatus, int aSuggestedRetryTime, int aCid, int aActive, String aType, String aIfname, String aAddresses, String aDnses, String aGateways, String aPcscf, int aMtu) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aStatus);
            _hidl_request.writeInt32(aSuggestedRetryTime);
            _hidl_request.writeInt32(aCid);
            _hidl_request.writeInt32(aActive);
            _hidl_request.writeString(aType);
            _hidl_request.writeString(aIfname);
            _hidl_request.writeString(aAddresses);
            _hidl_request.writeString(aDnses);
            _hidl_request.writeString(aGateways);
            _hidl_request.writeString(aPcscf);
            _hidl_request.writeInt32(aMtu);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(65, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeCdmaWriteSmsToRuimResponse(int aToken, int aError, int aIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(66, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetSignalStrengthResponse(int aToken, int aError, ArrayList<Integer> aSigStrength) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32Vector(aSigStrength);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(67, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetIccCardStatusResponse(int aToken, int aError, String aCardState, String aUniversalPinState, int aGsmUmtsSubscriptionAppIndex, int aCdmaSubscriptionAppIndex, int aImsSubscriptionAppIndex, ArrayList<String> aAppType, ArrayList<String> aAppState, ArrayList<String> aPersoSubState, ArrayList<String> aAid, ArrayList<String> aApp_label, ArrayList<Integer> aPin1_replaced, ArrayList<String> aPin1, ArrayList<String> aPin2, ArrayList<Integer> aRemaining_count_pin1, ArrayList<Integer> aRemaining_count_puk1, ArrayList<Integer> aRemaining_count_pin2, ArrayList<Integer> aRemaining_count_puk2) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeString(aCardState);
            _hidl_request.writeString(aUniversalPinState);
            _hidl_request.writeInt32(aGsmUmtsSubscriptionAppIndex);
            _hidl_request.writeInt32(aCdmaSubscriptionAppIndex);
            _hidl_request.writeInt32(aImsSubscriptionAppIndex);
            _hidl_request.writeStringVector(aAppType);
            _hidl_request.writeStringVector(aAppState);
            _hidl_request.writeStringVector(aPersoSubState);
            _hidl_request.writeStringVector(aAid);
            _hidl_request.writeStringVector(aApp_label);
            _hidl_request.writeInt32Vector(aPin1_replaced);
            _hidl_request.writeStringVector(aPin1);
            _hidl_request.writeStringVector(aPin2);
            _hidl_request.writeInt32Vector(aRemaining_count_pin1);
            _hidl_request.writeInt32Vector(aRemaining_count_puk1);
            _hidl_request.writeInt32Vector(aRemaining_count_pin2);
            _hidl_request.writeInt32Vector(aRemaining_count_puk2);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(68, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void lgeGetCurrentCallsResponse(int aToken, int aError, ArrayList<Integer> index, ArrayList<Integer> isMT, ArrayList<String> state, ArrayList<Integer> isMpty, ArrayList<String> number, ArrayList<Integer> TOA, ArrayList<Integer> isVoice, ArrayList<Integer> isVoicePrivacy, ArrayList<Integer> als, ArrayList<Integer> numberPresentation, ArrayList<String> name, ArrayList<Integer> namePresentation, ArrayList<Integer> uustype, ArrayList<Integer> uusdcs, ArrayList<String> uusdata, ArrayList<String> cdnipNumber, ArrayList<Integer> signal, ArrayList<String> redirectNumber) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32Vector(index);
            _hidl_request.writeInt32Vector(isMT);
            _hidl_request.writeStringVector(state);
            _hidl_request.writeInt32Vector(isMpty);
            _hidl_request.writeStringVector(number);
            _hidl_request.writeInt32Vector(TOA);
            _hidl_request.writeInt32Vector(isVoice);
            _hidl_request.writeInt32Vector(isVoicePrivacy);
            _hidl_request.writeInt32Vector(als);
            _hidl_request.writeInt32Vector(numberPresentation);
            _hidl_request.writeStringVector(name);
            _hidl_request.writeInt32Vector(namePresentation);
            _hidl_request.writeInt32Vector(uustype);
            _hidl_request.writeInt32Vector(uusdcs);
            _hidl_request.writeStringVector(uusdata);
            _hidl_request.writeStringVector(cdnipNumber);
            _hidl_request.writeInt32Vector(signal);
            _hidl_request.writeStringVector(redirectNumber);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(69, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getAvailableNetworksResponse(int aToken, int aError, int aOpInfoNum, ArrayList<String> aOperatorInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aOpInfoNum);
            _hidl_request.writeStringVector(aOperatorInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(70, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void getDataRegistrationStateResponse(int aToken, int aError, ArrayList<Integer> aDataRegStateResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32Vector(aDataRegStateResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(71, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetRFParameterResponse(int aToken, int aError, int aKindofdata, int aSendbufnum, int aDatalen, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aKindofdata);
            _hidl_request.writeInt32(aSendbufnum);
            _hidl_request.writeInt32(aDatalen);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(72, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetMiscResponse(int aToken, int aError, int aSendbufnum, int aDatalen, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aSendbufnum);
            _hidl_request.writeInt32(aDatalen);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(73, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEventResponse(int aToken, int aError, int aResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(74, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetLogResponse(int aToken, int aError, int aResult) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aResult);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(75, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaGetDataResponse(int aToken, int aError, int aSendbufnum, int aDatalen, String aData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aSendbufnum);
            _hidl_request.writeInt32(aDatalen);
            _hidl_request.writeString(aData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(76, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaSetMemResponse(int aToken, int aError, int aData1, int aData2) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aData1);
            _hidl_request.writeInt32(aData2);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(77, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void mocaAlarmEventRegResponse(int aToken, int aError, int aRet) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aRet);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(78, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void DMRequestResponse(int aToken, int aError, int aData1, int aData2) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeInt32(aData1);
            _hidl_request.writeInt32(aData2);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(79, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_SetCaptureMode_requestProcResponse(int aToken, int aError, ArrayList<String> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeStringVector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(80, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setNSRICallInfoTransferResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(81, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_requestProcResponse(int aToken, int aError, ArrayList<String> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeStringVector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(82, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void NSRI_Oem_requestProcResponse(int aToken, int aError, ArrayList<String> aResData) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            _hidl_request.writeStringVector(aResData);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(83, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setPcasInfofaceResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(84, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void setOtasnPdnStateResponse(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(85, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        public void ResponseError(int aToken, int aError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(ILgeRadioFalconResponse.kInterfaceName);
            _hidl_request.writeInt32(aToken);
            _hidl_request.writeInt32(aError);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(86, _hidl_request, _hidl_reply, 1);
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

    public static abstract class Stub extends HwBinder implements ILgeRadioFalconResponse {
        public IHwBinder asBinder() {
            return this;
        }

        public final ArrayList<String> interfaceChain() {
            return new ArrayList(Arrays.asList(new String[]{ILgeRadioFalconResponse.kInterfaceName, IBase.kInterfaceName}));
        }

        public final String interfaceDescriptor() {
            return ILgeRadioFalconResponse.kInterfaceName;
        }

        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList(Arrays.asList(new byte[][]{new byte[]{(byte) -30, Byte.MIN_VALUE, (byte) -95, (byte) -103, (byte) -107, (byte) 101, (byte) 97, (byte) 75, (byte) 51, (byte) 79, (byte) -70, (byte) 25, (byte) 59, (byte) -10, (byte) -26, (byte) 30, (byte) -42, (byte) -104, (byte) 121, (byte) 24, (byte) 85, (byte) 80, (byte) 35, (byte) -115, (byte) 9, (byte) -104, (byte) -30, (byte) 73, (byte) -39, (byte) -25, (byte) -81, (byte) -16}, new byte[]{(byte) -67, (byte) -38, (byte) -74, (byte) 24, (byte) 77, (byte) 122, (byte) 52, (byte) 109, (byte) -90, (byte) -96, (byte) 125, (byte) -64, (byte) -126, (byte) -116, (byte) -15, (byte) -102, (byte) 105, (byte) 111, (byte) 76, (byte) -86, (byte) 54, (byte) 17, (byte) -59, (byte) 31, (byte) 46, (byte) 20, (byte) 86, (byte) 90, (byte) 20, (byte) -76, (byte) 15, (byte) -39}}));
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
            if (ILgeRadioFalconResponse.kInterfaceName.equals(descriptor)) {
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
            int aToken;
            int aError;
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    PBMReadRecordResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 2:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    PBMWriteRecordResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 3:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    PBMDeleteRecordResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 4:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    PBMGetInitStateResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 5:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getUsimAuthenticationResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 6:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    smartCardTransmitResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 7:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    aError = _hidl_request.readInt32();
                    PBMGetInfoResponse(aToken, aError, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 8:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    UIMInternalRequestCmdResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 9:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    uiccSelectApplicationResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 10:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    uiccDeactivateApplicationResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 11:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    uiccApplicationResponse(aToken, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 12:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    uiccAkaAuthenticateResponse(aToken, _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 13:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    uiccGbaAuthenticateBootstrapResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString());
                    return;
                case 14:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    uiccGbaAuthenticateNafResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 15:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    uimPowerDownRequestResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 16:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    USIMSmartcardGetAtrResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 17:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setCdmaEriVersionResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 18:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setCdmaFactoryResetResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 19:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getEhrpdInfoForImsResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 20:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getMipErrorCodeResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 21:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    cancelManualSearchingRequestResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 22:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setPreviousNetworkSelectionModeManualResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 23:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setRmnetAutoconnectResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 24:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getSearchStatusResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 25:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getEngineeringModeInfoResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 26:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setCSGSelectionManualResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 27:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getLteEmmErrorCodeResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 28:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    sendApnDisableFlagResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 29:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    loadVolteE911ScanListResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 30:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getVolteE911NetworkTypeResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 31:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    exitVolteE911EmergencyModeResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 32:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setImsRegistrationResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 33:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    sendE911CallStateResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 34:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setImsRegistrationForHVoLTEResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 35:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setVoiceDomainPrefResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 36:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setVoLteCallResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 37:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    closeImsPdnResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 38:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getLteInfoForImsResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 39:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    sendIMSCallStateResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 40:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setRssiTestAntConfResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 41:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    getRssiTestResponse(aToken, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 42:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setQcrilResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 43:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setMiMoAntennaControlTestResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 44:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setModemInfoResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 45:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getModemInfoResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 46:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getGPRIItemResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 47:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setGNOSInfoResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 48:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setLteBandModeResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 49:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setEmergencyResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 50:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    vssModemResetResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 51:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getDefaultAPNResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 52:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getAdminAPNResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 53:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getTetheringAPNResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 54:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setScriptValueResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 55:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setImsDataFlushEnabledResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 56:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    iwlanSetRegisterCellularQualityReportResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 57:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    iwlanSendImsPdnStatusResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 58:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setProximitySensorStateResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 59:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setImsRegistrationStatusResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case RadioError.NETWORK_NOT_READY /*60*/:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setImsCallStatusResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case RadioError.NOT_PROVISIONED /*61*/:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setScmModeResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case RadioError.NO_SUBSCRIPTION /*62*/:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setImsStatusResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 63:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getIMSNetworkInfoResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readStringVector());
                    return;
                case 64:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    lgeSetNetworkSelectionModeManualResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 65:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    aError = _hidl_request.readInt32();
                    setupDataCallExResponse(aToken, aError, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readInt32());
                    return;
                case 66:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    lgeCdmaWriteSmsToRuimResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 67:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    lgeGetSignalStrengthResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 68:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    lgeGetIccCardStatusResponse(aToken, _hidl_request.readInt32(), _hidl_request.readString(), _hidl_request.readString(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readStringVector(), _hidl_request.readStringVector(), _hidl_request.readStringVector(), _hidl_request.readStringVector(), _hidl_request.readStringVector(), _hidl_request.readInt32Vector(), _hidl_request.readStringVector(), _hidl_request.readStringVector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector());
                    return;
                case 69:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    lgeGetCurrentCallsResponse(aToken, _hidl_request.readInt32(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readStringVector(), _hidl_request.readInt32Vector(), _hidl_request.readStringVector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readStringVector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readInt32Vector(), _hidl_request.readStringVector(), _hidl_request.readStringVector(), _hidl_request.readInt32Vector(), _hidl_request.readStringVector());
                    return;
                case 70:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getAvailableNetworksResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readStringVector());
                    return;
                case 71:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    getDataRegistrationStateResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32Vector());
                    return;
                case 72:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    aToken = _hidl_request.readInt32();
                    mocaGetRFParameterResponse(aToken, _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 73:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    mocaGetMiscResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 74:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    mocaAlarmEventResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 75:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    mocaSetLogResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 76:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    mocaGetDataResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readString());
                    return;
                case 77:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    mocaSetMemResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 78:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    mocaAlarmEventRegResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 79:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    DMRequestResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 80:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    NSRI_SetCaptureMode_requestProcResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readStringVector());
                    return;
                case 81:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setNSRICallInfoTransferResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case RadioNVItems.RIL_NV_LTE_BSR_MAX_TIME /*82*/:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    NSRI_requestProcResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readStringVector());
                    return;
                case 83:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    NSRI_Oem_requestProcResponse(_hidl_request.readInt32(), _hidl_request.readInt32(), _hidl_request.readStringVector());
                    return;
                case 84:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setPcasInfofaceResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 85:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    setOtasnPdnStateResponse(_hidl_request.readInt32(), _hidl_request.readInt32());
                    return;
                case 86:
                    _hidl_request.enforceInterface(ILgeRadioFalconResponse.kInterfaceName);
                    ResponseError(_hidl_request.readInt32(), _hidl_request.readInt32());
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

    void DMRequestResponse(int i, int i2, int i3, int i4) throws RemoteException;

    void NSRI_Oem_requestProcResponse(int i, int i2, ArrayList<String> arrayList) throws RemoteException;

    void NSRI_SetCaptureMode_requestProcResponse(int i, int i2, ArrayList<String> arrayList) throws RemoteException;

    void NSRI_requestProcResponse(int i, int i2, ArrayList<String> arrayList) throws RemoteException;

    void PBMDeleteRecordResponse(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void PBMGetInfoResponse(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) throws RemoteException;

    void PBMGetInitStateResponse(int i, int i2, int i3) throws RemoteException;

    void PBMReadRecordResponse(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i7, int i8) throws RemoteException;

    void PBMWriteRecordResponse(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void ResponseError(int i, int i2) throws RemoteException;

    void UIMInternalRequestCmdResponse(int i, int i2, String str, String str2) throws RemoteException;

    void USIMSmartcardGetAtrResponse(int i, int i2, String str) throws RemoteException;

    IHwBinder asBinder();

    void cancelManualSearchingRequestResponse(int i, int i2) throws RemoteException;

    void closeImsPdnResponse(int i, int i2) throws RemoteException;

    void exitVolteE911EmergencyModeResponse(int i, int i2) throws RemoteException;

    void getAdminAPNResponse(int i, int i2, String str) throws RemoteException;

    void getAvailableNetworksResponse(int i, int i2, int i3, ArrayList<String> arrayList) throws RemoteException;

    void getDataRegistrationStateResponse(int i, int i2, ArrayList<Integer> arrayList) throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    void getDefaultAPNResponse(int i, int i2, String str) throws RemoteException;

    void getEhrpdInfoForImsResponse(int i, int i2, String str) throws RemoteException;

    void getEngineeringModeInfoResponse(int i, int i2, String str) throws RemoteException;

    void getGPRIItemResponse(int i, int i2, String str) throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getIMSNetworkInfoResponse(int i, int i2, ArrayList<String> arrayList) throws RemoteException;

    void getLteEmmErrorCodeResponse(int i, int i2, int i3) throws RemoteException;

    void getLteInfoForImsResponse(int i, int i2, String str) throws RemoteException;

    void getMipErrorCodeResponse(int i, int i2, int i3) throws RemoteException;

    void getModemInfoResponse(int i, int i2, String str) throws RemoteException;

    void getRssiTestResponse(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws RemoteException;

    void getSearchStatusResponse(int i, int i2, int i3) throws RemoteException;

    void getTetheringAPNResponse(int i, int i2, String str) throws RemoteException;

    void getUsimAuthenticationResponse(int i, int i2, int i3, String str) throws RemoteException;

    void getVolteE911NetworkTypeResponse(int i, int i2) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    void iwlanSendImsPdnStatusResponse(int i, int i2) throws RemoteException;

    void iwlanSetRegisterCellularQualityReportResponse(int i, int i2) throws RemoteException;

    void lgeCdmaWriteSmsToRuimResponse(int i, int i2, int i3) throws RemoteException;

    void lgeGetCurrentCallsResponse(int i, int i2, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, ArrayList<String> arrayList3, ArrayList<Integer> arrayList4, ArrayList<String> arrayList5, ArrayList<Integer> arrayList6, ArrayList<Integer> arrayList7, ArrayList<Integer> arrayList8, ArrayList<Integer> arrayList9, ArrayList<Integer> arrayList10, ArrayList<String> arrayList11, ArrayList<Integer> arrayList12, ArrayList<Integer> arrayList13, ArrayList<Integer> arrayList14, ArrayList<String> arrayList15, ArrayList<String> arrayList16, ArrayList<Integer> arrayList17, ArrayList<String> arrayList18) throws RemoteException;

    void lgeGetIccCardStatusResponse(int i, int i2, String str, String str2, int i3, int i4, int i5, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, ArrayList<String> arrayList4, ArrayList<String> arrayList5, ArrayList<Integer> arrayList6, ArrayList<String> arrayList7, ArrayList<String> arrayList8, ArrayList<Integer> arrayList9, ArrayList<Integer> arrayList10, ArrayList<Integer> arrayList11, ArrayList<Integer> arrayList12) throws RemoteException;

    void lgeGetSignalStrengthResponse(int i, int i2, ArrayList<Integer> arrayList) throws RemoteException;

    void lgeSetNetworkSelectionModeManualResponse(int i, int i2) throws RemoteException;

    boolean linkToDeath(DeathRecipient deathRecipient, long j) throws RemoteException;

    void loadVolteE911ScanListResponse(int i, int i2) throws RemoteException;

    void mocaAlarmEventRegResponse(int i, int i2, int i3) throws RemoteException;

    void mocaAlarmEventResponse(int i, int i2, int i3) throws RemoteException;

    void mocaGetDataResponse(int i, int i2, int i3, int i4, String str) throws RemoteException;

    void mocaGetMiscResponse(int i, int i2, int i3, int i4, String str) throws RemoteException;

    void mocaGetRFParameterResponse(int i, int i2, int i3, int i4, int i5, String str) throws RemoteException;

    void mocaSetLogResponse(int i, int i2, int i3) throws RemoteException;

    void mocaSetMemResponse(int i, int i2, int i3, int i4) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    void sendApnDisableFlagResponse(int i, int i2) throws RemoteException;

    void sendE911CallStateResponse(int i, int i2) throws RemoteException;

    void sendIMSCallStateResponse(int i, int i2) throws RemoteException;

    void setCSGSelectionManualResponse(int i, int i2, String str) throws RemoteException;

    void setCdmaEriVersionResponse(int i, int i2) throws RemoteException;

    void setCdmaFactoryResetResponse(int i, int i2, int i3) throws RemoteException;

    void setEmergencyResponse(int i, int i2, int i3) throws RemoteException;

    void setGNOSInfoResponse(int i, int i2) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void setImsCallStatusResponse(int i, int i2) throws RemoteException;

    void setImsDataFlushEnabledResponse(int i, int i2) throws RemoteException;

    void setImsRegistrationForHVoLTEResponse(int i, int i2) throws RemoteException;

    void setImsRegistrationResponse(int i, int i2) throws RemoteException;

    void setImsRegistrationStatusResponse(int i, int i2) throws RemoteException;

    void setImsStatusResponse(int i, int i2) throws RemoteException;

    void setLteBandModeResponse(int i, int i2) throws RemoteException;

    void setMiMoAntennaControlTestResponse(int i, int i2) throws RemoteException;

    void setModemInfoResponse(int i, int i2, int i3) throws RemoteException;

    void setNSRICallInfoTransferResponse(int i, int i2) throws RemoteException;

    void setOtasnPdnStateResponse(int i, int i2) throws RemoteException;

    void setPcasInfofaceResponse(int i, int i2) throws RemoteException;

    void setPreviousNetworkSelectionModeManualResponse(int i, int i2) throws RemoteException;

    void setProximitySensorStateResponse(int i, int i2) throws RemoteException;

    void setQcrilResponse(int i, int i2) throws RemoteException;

    void setRmnetAutoconnectResponse(int i, int i2) throws RemoteException;

    void setRssiTestAntConfResponse(int i, int i2, int i3, int i4) throws RemoteException;

    void setScmModeResponse(int i, int i2) throws RemoteException;

    void setScriptValueResponse(int i, int i2) throws RemoteException;

    void setVoLteCallResponse(int i, int i2) throws RemoteException;

    void setVoiceDomainPrefResponse(int i, int i2) throws RemoteException;

    void setupDataCallExResponse(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, String str3, String str4, String str5, String str6, int i7) throws RemoteException;

    void smartCardTransmitResponse(int i, int i2, String str) throws RemoteException;

    void uiccAkaAuthenticateResponse(int i, int i2, String str, String str2, String str3, String str4, String str5) throws RemoteException;

    void uiccApplicationResponse(int i, int i2, int i3, int i4, String str) throws RemoteException;

    void uiccDeactivateApplicationResponse(int i, int i2) throws RemoteException;

    void uiccGbaAuthenticateBootstrapResponse(int i, int i2, String str, String str2) throws RemoteException;

    void uiccGbaAuthenticateNafResponse(int i, int i2, String str) throws RemoteException;

    void uiccSelectApplicationResponse(int i, int i2, int i3) throws RemoteException;

    void uimPowerDownRequestResponse(int i, int i2) throws RemoteException;

    boolean unlinkToDeath(DeathRecipient deathRecipient) throws RemoteException;

    void vssModemResetResponse(int i, int i2) throws RemoteException;

    static ILgeRadioFalconResponse asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof ILgeRadioFalconResponse)) {
            return (ILgeRadioFalconResponse) iface;
        }
        ILgeRadioFalconResponse proxy = new Proxy(binder);
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

    static ILgeRadioFalconResponse castFrom(IHwInterface iface) {
        return iface == null ? null : asInterface(iface.asBinder());
    }

    static ILgeRadioFalconResponse getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static ILgeRadioFalconResponse getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }
}
