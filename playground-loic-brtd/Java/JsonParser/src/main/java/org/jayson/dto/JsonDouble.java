package org.jayson.dto;

import org.jayson.format.JsonFormatter;

import java.util.Objects;

public class JsonDouble implements JsonNumber {

    private final double value;

    public JsonDouble(double value) {
        this.value = value;
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public long longValue() {
        return (long) value;
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
        JsonDouble that = (JsonDouble) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
