package org.jayson.dto;

import org.jayson.format.JsonFormatter;

import java.util.Objects;

public class JsonBoolean implements JsonElement {

    private final boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    public boolean value() {
        return value;
    }

    @Override
    public String toString() {
        return JsonFormatter.DEFAULT.format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonBoolean that = (JsonBoolean) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
