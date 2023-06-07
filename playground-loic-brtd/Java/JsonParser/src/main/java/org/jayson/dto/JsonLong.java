package org.jayson.dto;

import org.jayson.format.JsonFormatter;

import java.util.Objects;

public class JsonLong implements JsonNumber {

    private final long value;

    public JsonLong(long value) {
        this.value = value;
    }

    @Override
    public boolean isLong() {
        return true;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public String toString() {
        return JsonFormatter.DEFAULT.format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonLong jsonLong = (JsonLong) o;
        return value == jsonLong.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
