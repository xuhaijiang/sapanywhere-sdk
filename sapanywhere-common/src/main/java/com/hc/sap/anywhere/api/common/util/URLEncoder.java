package com.hc.sap.anywhere.api.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class URLEncoder {

    public static String encode(final String s) {
        if (s == null) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < s.length(); i++) {
                final char c = s.charAt(i);
                if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) ||
                        ((c >= '0') && (c <= '9')) || (c == '(') || (c == ')') ||
                        (c == '-') || (c == '.') || (c == '_') || (c == '~') || (c == ',')) {
                    sb.append(c);
                } else {
                    final byte[] bytes = ("" + c).getBytes("UTF-8");
                    for (byte b : bytes) {
                        sb.append('%');
                        int upper = (((int) b) >> 4) & 0xf;
                        sb.append(Integer.toHexString(upper).toUpperCase(Locale.US));
                        int lower = ((int) b) & 0xf;
                        sb.append(Integer.toHexString(lower).toUpperCase(Locale.US));
                    }
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("UTF-8 unsupported!?", uee);
        }
    }

}
