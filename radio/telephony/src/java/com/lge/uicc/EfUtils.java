package com.lge.uicc;

public class EfUtils {
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public static int hexCharToInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return 0;
        }
        return (c - 65) + 10;
    }

    public static int hexStringToInt(String s) {
        if (s == null) {
            return -1;
        }
        int value = 0;
        for (int i = 0; i < (s.length() < 8 ? s.length() : 8); i++) {
            value = (value * 16) + hexCharToInt(s.charAt(i));
        }
        return value;
    }

    public static int bytesToInt(byte[] bytes) {
        if (bytes == null) {
            return -1;
        }
        int value = 0;
        for (int i = 0; i < (bytes.length < 4 ? bytes.length : 4); i++) {
            value = (value * 256) + (bytes[i] & 255);
        }
        return value;
    }

    public static byte[] hexStringToBytes(String s) {
        if (s == null) {
            return null;
        }
        int sz = s.length();
        char[] chars = s.toCharArray();
        byte[] bytes = new byte[(sz / 2)];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((hexCharToInt(chars[i * 2]) << 4) | hexCharToInt(chars[(i * 2) + 1]));
        }
        return bytes;
    }

    public static String bytesToHexString(byte[] bytes) {
        return bytes == null ? "" : bytesToHexString(bytes, 0, bytes.length);
    }

    public static String bytesToHexString(byte[] bytes, int offset, int length) {
        if (bytes == null || bytes.length <= offset || offset < 0 || length <= 0) {
            return "";
        }
        if (offset + length > bytes.length) {
            length = bytes.length - offset;
        }
        char[] chars = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            byte b = bytes[offset + i];
            chars[i * 2] = HEX_CHARS[(b >> 4) & 15];
            chars[(i * 2) + 1] = HEX_CHARS[b & 15];
        }
        return new String(chars);
    }

    public static String bcdToString(byte[] data) {
        return data == null ? "" : bcdToString(data, 0, data.length);
    }

    public static String bcdToString(byte[] data, int offset, int length) {
        if (data == null || data.length <= offset || offset < 0 || length <= 0) {
            return "";
        }
        int count;
        if (offset + length > data.length) {
            length = data.length - offset;
        }
        char[] chars = new char[(length * 2)];
        int i = 0;
        int count2 = 0;
        while (i < length) {
            byte b = data[offset + i];
            int v = b & 15;
            if (v > 9) {
                count = count2;
                break;
            }
            count = count2 + 1;
            chars[count2] = (char) (v + 48);
            v = (b >> 4) & 15;
            if (v != 15) {
                if (v > 9) {
                    break;
                }
                count2 = count + 1;
                chars[count] = (char) (v + 48);
                count = count2;
            }
            i++;
            count2 = count;
        }
        count = count2;
        return new String(chars, 0, count);
    }

    public static byte[] subarray(byte[] array, int start, int end) {
        if (array == null || start > end || array.length < end) {
            return null;
        }
        byte[] arrayBuf = new byte[((end - start) + 1)];
        int i = start;
        int j = 0;
        while (i <= end) {
            arrayBuf[j] = array[i];
            i++;
            j++;
        }
        return arrayBuf;
    }
}
