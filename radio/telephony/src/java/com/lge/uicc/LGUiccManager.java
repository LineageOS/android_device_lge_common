package com.lge.uicc;

import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.Rlog;
import com.lge.uicc.ILGUiccService.Stub;

public class LGUiccManager {
    private static final String TAG = "LGUICC";

    public static String getProperty(String key, String def) {
        return getProperty(key, 0, def);
    }

    public static String getProperty(String key, int slot, String def) {
        String val = null;
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return def;
            }
            val = service.getProperty(key, slot);
            if (val != null && !val.isEmpty()) {
                return val;
            }
            logd("getProperty: not ready: key=" + key + ", slot=" + slot);
            traceCallerPackage();
            return def;
        } catch (RemoteException e) {
            loge("getProperty: " + e);
        } catch (NullPointerException e2) {
            loge("getProperty: " + e2);
        }
    }

    public static boolean setProperty(String key, String val) {
        return setProperty(key, 0, val);
    }

    public static boolean setProperty(String key, int slot, String val) {
        boolean result = false;
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return false;
            }
            result = service.setProperty(key, slot, val);
            if (!result) {
                traceCallerPackage();
            }
            return result;
        } catch (RemoteException e) {
            loge("setProperty: " + e);
        } catch (NullPointerException e2) {
            loge("setProperty: " + e2);
        }
    }

    public static byte[] genericIO(String command, byte[] in) {
        byte[] out = null;
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return null;
            }
            out = service.request(command, in, null);
            if (out == null) {
                traceCallerPackage();
            }
            return out;
        } catch (RemoteException e) {
            loge("genericIO: " + e);
        } catch (NullPointerException e2) {
            loge("genericIO: " + e2);
        }
    }

    public static byte[] readIccRecord(int efid) {
        return readIccRecord(0, efid);
    }

    public static byte[] readIccRecord(int slot, int efid) {
        Parcel p = Parcel.obtain();
        p.writeInt(slot);
        p.writeInt(efid);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return null;
            }
            byte[] out = service.request("IccFileIO.read", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                return p.createByteArray();
            }
            return null;
        } catch (RemoteException e) {
            loge("readIccRecord: " + e);
        } catch (NullPointerException e2) {
            loge("readIccRecord: " + e2);
        }
    }

    public static boolean updateIccRecord(int efid, byte[] data) {
        return updateIccRecord(0, efid, data);
    }

    public static boolean updateIccRecord(int slot, int efid, byte[] data) {
        boolean z = true;
        Parcel p = Parcel.obtain();
        p.writeInt(slot);
        p.writeInt(efid);
        p.writeByteArray(data);
        p.writeString(null);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return false;
            }
            byte[] out = service.request("IccFileIO.update", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                if (p.readInt() != 1) {
                    z = false;
                }
                return z;
            }
            return false;
        } catch (RemoteException e) {
            loge("updateIccRecord: " + e);
        } catch (NullPointerException e2) {
            loge("updateIccRecord: " + e2);
        }
    }

    public static String readIccRecordToString(int efid) {
        Parcel p = Parcel.obtain();
        p.writeInt(0);
        p.writeInt(efid);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return null;
            }
            byte[] out = service.request("IccFileIO.read", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                p.createByteArray();
                return p.readString();
            }
            return null;
        } catch (RemoteException e) {
            loge("readIccRecord: " + e);
        } catch (NullPointerException e2) {
            loge("readIccRecord: " + e2);
        }
    }

    public static String[] readIccRecordAllToString(int efid) {
        Parcel p = Parcel.obtain();
        p.writeInt(0);
        p.writeInt(efid);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return null;
            }
            byte[] out = service.request("IccFileIO.read", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                p.createByteArray();
                p.readString();
                return p.createStringArray();
            }
            return null;
        } catch (RemoteException e) {
            loge("readIccRecord: " + e);
        } catch (NullPointerException e2) {
            loge("readIccRecord: " + e2);
        }
    }

    public static boolean updateIccRecordFromString(int efid, String dataString) {
        boolean z = true;
        Parcel p = Parcel.obtain();
        p.writeInt(0);
        p.writeInt(efid);
        p.writeByteArray(null);
        p.writeString(dataString);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return false;
            }
            byte[] out = service.request("IccFileIO.update", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                if (p.readInt() != 1) {
                    z = false;
                }
                return z;
            }
            return false;
        } catch (RemoteException e) {
            loge("updateIccRecord: " + e);
        } catch (NullPointerException e2) {
            loge("updateIccRecord: " + e2);
        }
    }

    public static String requestEnvelope(String envName, String data) {
        Parcel p = Parcel.obtain();
        p.writeString(data);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return null;
            }
            byte[] out = service.request(envName, p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                return p.readString();
            }
            return "FAIL";
        } catch (RemoteException e) {
            loge("requestEnvelope: " + e);
        } catch (NullPointerException e2) {
            loge("requestEnvelope: " + e2);
        }
    }

    public static void listen(final SimStateListener listener, final int events) {
        if (listener == null) {
            loge("invalid listener");
        } else {
            listener.resetConnection(new Runnable() {
                final int myEvents = events;
                final SimStateListener myListener = listener;

                public void run() {
                    String caller = this.myListener.getClass().getName();
                    LGUiccManager.logd("listen: try to connect: " + caller);
                    try {
                        ILGUiccService service = LGUiccManager.getUiccService();
                        if (service != null) {
                            Parcel p = Parcel.obtain();
                            p.writeInt(this.myEvents);
                            p.writeString(caller);
                            service.request("SimStateListener", p.marshall(), this.myListener.mCallback);
                            if (this.myEvents != 0) {
                                service.asBinder().linkToDeath(this.myListener.mDeath, 0);
                            } else {
                                service.asBinder().unlinkToDeath(this.myListener.mDeath, 0);
                            }
                        } else if (this.myEvents != 0) {
                            this.myListener.resetConnection(null);
                        }
                    } catch (RemoteException e) {
                        LGUiccManager.loge("listen: " + e);
                    } catch (RuntimeException e2) {
                        LGUiccManager.loge("listen: " + e2);
                    }
                }
            });
        }
    }

    public static int getCurrentUiccCardProvisioningStatus(int slotId) {
        Parcel p = Parcel.obtain();
        p.writeInt(1);
        p.writeInt(slotId);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return -1;
            }
            byte[] out = service.request("extphone", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                return p.readInt();
            }
            return -1;
        } catch (RemoteException e) {
            loge("getCurrentUiccCardProvisioningStatus: " + e);
        } catch (NullPointerException e2) {
            loge("getCurrentUiccCardProvisioningStatus: " + e2);
        }
    }

    public static int activateUiccCard(int slotId) {
        Parcel p = Parcel.obtain();
        p.writeInt(2);
        p.writeInt(slotId);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return -1;
            }
            byte[] out = service.request("extphone", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                return p.readInt();
            }
            return -1;
        } catch (RemoteException e) {
            loge("activateUiccCard: " + e);
        } catch (NullPointerException e2) {
            loge("activateUiccCard: " + e2);
        }
    }

    public static int deactivateUiccCard(int slotId) {
        Parcel p = Parcel.obtain();
        p.writeInt(3);
        p.writeInt(slotId);
        try {
            ILGUiccService service = getUiccService();
            if (service == null) {
                return -1;
            }
            byte[] out = service.request("extphone", p.marshall(), null);
            if (out != null) {
                p.unmarshall(out, 0, out.length);
                p.setDataPosition(0);
                return p.readInt();
            }
            return -1;
        } catch (RemoteException e) {
            loge("deactivateUiccCard: " + e);
        } catch (NullPointerException e2) {
            loge("deactivateUiccCard: " + e2);
        }
    }

    private static ILGUiccService getUiccService() {
        ILGUiccService service = Stub.asInterface(ServiceManager.getService("lguicc"));
        if (service == null) {
            loge("service is not ready");
            traceCallerPackage();
        }
        return service;
    }

    private static void traceCallerPackage() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        int s = 4;
        while (s < stack.length) {
            String cls = stack[s].getClassName();
            if (cls.startsWith("com.lge.uicc") || cls.startsWith("java.lang")) {
                s++;
            } else {
                logd(stack[3].getMethodName() + ": traceCaller: " + stack[s].toString());
                return;
            }
        }
    }

    private static void logd(String s) {
        Rlog.d(TAG, "[LGUiccManager] " + s);
    }

    private static void loge(String s) {
        Rlog.e(TAG, "[LGUiccManager] " + s);
    }
}
