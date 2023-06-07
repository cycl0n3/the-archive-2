package org.jayson.format;

import org.jayson.dto.*;
import org.jayson.util.StringEscape;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class CustomFormatter implements JsonFormatter {

    // Parameters
    private String indent = "";
    private String lf = "";
    private String sep = ", ";
    private String colon = ": ";

    // Cached concatenations
    private String sepLf = sep + lf;
    private String openObj = '{' + lf;
    private String openArr = '[' + lf;
    private String quoteColon = '"' + colon;

    public CustomFormatter indent(String indent) {
        if (!this.indent.equals(indent))
            Arrays.fill(marginCache, null);
        this.indent = indent;
        return this;
    }

    public CustomFormatter newline(String newline) {
        this.lf = newline;
        sepLf = sep + lf;
        openObj = '{' + lf;
        openArr = '[' + lf;
        return this;
    }

    public CustomFormatter separator(String separator) {
        this.sep = separator;
        sepLf = sep + lf;
        return this;
    }

    public CustomFormatter colon(String colon) {
        this.colon = colon;
        quoteColon = '"' + colon;
        return this;
    }

    private int level = 0;

    public String format(JsonObject object) {
        level++;
        String formatted = object.entries().stream()
                .map(e -> margin(level) + '"' + e.getKey() + quoteColon + format(e.getValue()))
                .collect(joining(sepLf, openObj, lf + margin(level - 1) + '}'));
        level--;
        return formatted;
    }

    public String format(JsonArray array) {
        level++;
        String formatted = array.stream()
                .map(element -> margin(level) + format(element))
                .collect(joining(sepLf, openArr, lf + margin(level - 1) + ']'));
        level--;
        return formatted;
    }

    public String format(JsonBoolean bool) {
        return bool.value() ? "true" : "false";
    }

    public String format(JsonNumber number) {
        return number.isDouble()
                ? String.valueOf(number.doubleValue())
                : String.valueOf(number.longValue());
    }

    public String format(JsonString string) {
        return StringEscape.escape(string.value());
    }

    private String[] marginCache = new String[8];

    private String margin(int n) {
        if (n == 0) return "";
        if (n == 1) return indent;
        if (n >= marginCache.length) {
            String[] copy = new String[marginCache.length * 2];
            System.arraycopy(marginCache, 0, copy, 0, marginCache.length);
            marginCache = copy;
        }
        if (marginCache[n] == null) {
            marginCache[n] = new String(new char[n]).replace("\0", indent);
        }
        return marginCache[n];
    }
}
