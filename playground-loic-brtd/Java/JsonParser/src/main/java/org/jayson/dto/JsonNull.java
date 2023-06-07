package org.jayson.dto;

public class JsonNull implements JsonElement {

    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
