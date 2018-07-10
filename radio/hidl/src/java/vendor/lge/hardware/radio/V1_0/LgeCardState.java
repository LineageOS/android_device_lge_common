package vendor.lge.hardware.radio.V1_0;

import java.util.ArrayList;

public final class LgeCardState {
    public static final int ABSENT = 0;
    public static final int CHANGED = 6;
    public static final int DETECT_INSERTED = 5;
    public static final int ERROR = 2;
    public static final int PRESENT = 1;
    public static final int REMOVED = 4;
    public static final int RESTRICTED = 3;

    public static final String toString(int o) {
        if (o == 0) {
            return "ABSENT";
        }
        if (o == 1) {
            return "PRESENT";
        }
        if (o == 2) {
            return "ERROR";
        }
        if (o == 3) {
            return "RESTRICTED";
        }
        if (o == 4) {
            return "REMOVED";
        }
        if (o == 5) {
            return "DETECT_INSERTED";
        }
        if (o == 6) {
            return "CHANGED";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("ABSENT");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("PRESENT");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("ERROR");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("RESTRICTED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("REMOVED");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("DETECT_INSERTED");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("CHANGED");
            flipped |= 6;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
