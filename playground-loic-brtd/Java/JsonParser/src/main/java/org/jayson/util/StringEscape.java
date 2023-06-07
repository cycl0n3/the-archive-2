package org.jayson.util;

public class StringEscape {

    public static String unescape(String str) {
        if (str.charAt(0) != '"' || str.charAt(str.length()-1) != '"')
            throw new IllegalArgumentException("String to unescape must be in quotes");

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '\\') {
                i++;
                if (str.charAt(i) == 'u') {
                    String hex = str.substring(i + 1, i + 5);
                    result.append(hexToAscii(hex));
                    i += 4;
                } else {
                    result.append(charToSpecial(str.charAt(i)));
                }
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    public static String escape(String str) {
        StringBuilder result = new StringBuilder();
        result.append('"');
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isSpecial(ch)) {
                result.append('\\').append(specialToChar(ch));
            } else {
                result.append(ch);
            }
        }
        return result.append('"').toString();
    }

    private static char hexToAscii(String hex) {
        return (char) Integer.parseInt(hex, 16);
    }

    private static boolean isSpecial(char c) {
        return "\"\\/\b\f\n\r\t".indexOf(c) != -1;
    }

    private static char charToSpecial(char c) {
        switch (c) {
            case '"':
            case '\\':
            case '/':
                return c;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            default:
                throw new IllegalArgumentException(String.valueOf(c));
        }
    }

    private static char specialToChar(char c) {
        switch (c) {
            case '"':
            case '\\':
            case '/':
                return c;
            case '\b':
                return 'b';
            case '\f':
                return 'f';
            case '\n':
                return 'n';
            case '\r':
                return 'r';
            case '\t':
                return 't';
            default:
                throw new IllegalArgumentException(String.valueOf(c));
        }
    }
}
