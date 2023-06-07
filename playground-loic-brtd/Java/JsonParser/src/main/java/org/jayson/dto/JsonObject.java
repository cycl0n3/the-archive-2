package org.jayson.dto;

import org.jayson.format.JsonFormatter;

import java.util.*;
import java.util.Map.Entry;

public class JsonObject implements JsonElement {

    private final Map<String, JsonElement> map;

    public JsonObject() {
        map = new LinkedHashMap<>();
    }

    @Override
    public boolean isObject() {
        return true;
    }

    public Set<String> keys() {
        return map.keySet();
    }

    public Collection<JsonElement> values() {
        return map.values();
    };

    public Set<Entry<String, JsonElement>> entries() {
        return map.entrySet();
    }

    public JsonElement get(String key) {
        JsonElement element = map.get(key);
        if (element == null)
            throw new NoSuchElementException(key);
        return element;
    }

    public JsonObject put(String key, JsonElement value) {
        Objects.requireNonNull(key);
        map.put(key, (value == null) ? JsonNull.INSTANCE : value);
        return this;
    }

    public JsonObject put(String key, String value) {
        Objects.requireNonNull(key);
        map.put(key, (value == null) ? JsonNull.INSTANCE : new JsonString(value));
        return this;
    }

    public JsonObject put(String key, double value) {
        Objects.requireNonNull(key);
        map.put(key, new JsonDouble(value));
        return this;
    }

    public JsonObject put(String key, long value) {
        Objects.requireNonNull(key);
        map.put(key, new JsonLong(value));
        return this;
    }

    public JsonObject put(String key, boolean value) {
        Objects.requireNonNull(key);
        map.put(key, new JsonBoolean(value));
        return this;
    }

    public JsonObject putNull(String key) {
        Objects.requireNonNull(key);
        map.put(key, JsonNull.INSTANCE);
        return this;
    }

    @Override
    public String toString() {
        return JsonFormatter.DEFAULT.format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonObject object = (JsonObject) o;
        return Objects.equals(map, object.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
