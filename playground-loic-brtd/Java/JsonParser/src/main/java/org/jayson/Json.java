package org.jayson;

import org.jayson.dto.JsonArray;
import org.jayson.dto.JsonElement;
import org.jayson.dto.JsonObject;
import org.jayson.format.JsonFormatter;
import org.jayson.parser.JsonLexer;
import org.jayson.parser.JsonParser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.joining;

public final class Json {

    private Json() {
    }

    public static JsonElement parse(String source) {
        JsonLexer lexer = new JsonLexer(source);
        JsonParser parser = new JsonParser(lexer);
        return parser.parse();
    }

    public static JsonElement parse(File source) {
        JsonLexer lexer = new JsonLexer(source);
        JsonParser parser = new JsonParser(lexer);
        return parser.parse();
    }

    public static JsonObject object() {
        return new JsonObject();
    }

    public static JsonArray array() {
        return new JsonArray();
    }

    public static JsonArray array(Object... elements) {
        JsonArray jsonArray = new JsonArray();
        for (Object element : elements) {
            if (element == null) {
                jsonArray.pushNull();
            } else if (element instanceof String) {
                jsonArray.push((String) element);
            } else if (element instanceof Double) {
                jsonArray.push((Double) element);
            } else if (element instanceof Float) {
                jsonArray.push((Float) element);
            } else if (element instanceof Long) {
                jsonArray.push((Long) element);
            } else if (element instanceof Integer) {
                jsonArray.push((Integer) element);
            } else if (element instanceof Boolean) {
                jsonArray.push((Boolean) element);
            } else if (element instanceof JsonElement) {
                jsonArray.push((JsonElement) element);
            } else {
                throw new IllegalArgumentException("Incompatible type for vararg element <" + element + ">");
            }
        }
        return jsonArray;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }
        File fileSource = new File(args[0]);
        String strSource = Files.lines(Paths.get(args[0])).collect(joining());
        System.out.println(Json.parse(strSource).format(JsonFormatter.MINIMIZED));
    }
}
