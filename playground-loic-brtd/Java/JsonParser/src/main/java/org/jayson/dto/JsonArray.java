package org.jayson.dto;

import org.jayson.format.JsonFormatter;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class JsonArray implements JsonElement, Iterable<JsonElement> {

    private final List<JsonElement> elements = new ArrayList<>();

    public JsonArray() {
    }

    public JsonArray(JsonElement... elements) {
        Collections.addAll(this.elements, elements);
    }

    public int size() {
        return elements.size();
    }

    public Stream<JsonElement> stream() {
        return elements.stream();
    }

    @Override
    public boolean isArray() {
        return true;
    }

    public JsonElement get(int index) {
        return elements.get(index);
    }

    public JsonArray push(String value) {
        elements.add(value == null ? JsonNull.INSTANCE : new JsonString(value));
        return this;
    }

    public JsonArray push(boolean value) {
        elements.add(new JsonBoolean(value));
        return this;
    }

    public JsonArray push(double value) {
        elements.add(new JsonDouble(value));
        return this;
    }

    public JsonArray push(long value) {
        elements.add(new JsonLong(value));
        return this;
    }

    public JsonArray push(JsonElement element) {
        elements.add(element == null ? JsonNull.INSTANCE : element);
        return this;
    }

    public JsonArray pushNull() {
        elements.add(JsonNull.INSTANCE);
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
        JsonArray jsonArray = (JsonArray) o;
        return Objects.equals(elements, jsonArray.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public Iterator<JsonElement> iterator() {
        return elements.iterator();
    }

    @Override
    public Spliterator<JsonElement> spliterator() {
        return elements.spliterator();
    }

    @Override
    public void forEach(Consumer<? super JsonElement> action) {
        elements.forEach(action);
    }
}
