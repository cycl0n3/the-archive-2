package org.jayson.dto;

public interface JsonNumber extends JsonElement {

    @Override
    default boolean isNumber() {
        return true;
    }

    default boolean isDouble() {
        return false;
    }

    default boolean isLong() {
        return false;
    }

    double doubleValue();

    long longValue();

    int intValue();
}
