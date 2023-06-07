package org.jayson.format;

import org.jayson.dto.*;

public interface JsonFormatter {

    JsonFormatter INLINE = new CustomFormatter();
    JsonFormatter MINIMIZED = new CustomFormatter().separator(",").colon(":");
    JsonFormatter FOUR_SPACES = new CustomFormatter().indent("    ").separator(",").newline("\n");
    JsonFormatter TWO_SPACES = new CustomFormatter().indent("  ").separator(",").newline("\n");
    JsonFormatter DEFAULT = INLINE;

    default String format(JsonElement element) {
        if (element == null || element.isNull()) return formatNull();
        if (element.isObject()) return format((JsonObject) element);
        if (element.isArray()) return format((JsonArray) element);
        if (element.isString()) return format((JsonString) element);
        if (element.isNumber()) return format((JsonNumber) element);
        if (element.isBoolean()) return format((JsonBoolean) element);
        throw new IllegalStateException();
    }

    default String formatNull() {
        return "null";
    }

    String format(JsonObject object);

    String format(JsonArray array);

    String format(JsonBoolean bool);

    String format(JsonNumber number);

    String format(JsonString string);
}
