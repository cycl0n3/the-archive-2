package org.jayson.parser;

import org.jayson.Json;
import org.jayson.dto.JsonElement;
import org.jayson.dto.JsonObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jayson.format.JsonFormatter.MINIMIZED;
import static org.jayson.format.JsonFormatter.TWO_SPACES;

public class User {

    private final String username;
    private final String passwordHash;
    private final LocalDate dateOfBirth;
    private final boolean isPremium;

    public User(String username, String passwordHash, LocalDate dateOfBirth, boolean isPremium) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.dateOfBirth = dateOfBirth;
        this.isPremium = isPremium;
    }

    private static User fromJson(String json) {
        JsonElement user = Json.parse(json);
        return new User(
                user.get("username").asString(),
                user.get("passwordHash").asString(),
                user.get("dateOfBirth").as(User::jsonToDate),
                user.get("isPremium").asBoolean());
    }

    public String toJson() {
        return Json.object()
                .put("username", username)
                .put("passwordHash", passwordHash)
                .put("dateOfBirth", dateToJson(dateOfBirth))
                .put("isPremium", isPremium)
                .format(MINIMIZED);
    }

    private JsonObject dateToJson(LocalDate d) {
        return Json.object()
                .put("year", d.getYear())
                .put("month", d.getMonthValue())
                .put("day", d.getDayOfMonth());
    }

    private static LocalDate jsonToDate(JsonElement elt) {
        return LocalDate.of(
                elt.get("year").asInt(),
                elt.get("month").asInt(),
                elt.get("day").asInt());
    }

    public static void main(String[] args) {
        // User user = new User("loic", "fg5h65fg", LocalDate.of(1998, 1, 19), true);
        // String json = user.toJson();
        // System.out.println(json);
        // User loaded = User.fromJson(json);
        // System.out.println(loaded.toJson());
        //
        // System.out.println(
        //         Json.parse("[{}, null, {}]").asArray()
        //                 .get(1).asObject()
        // );
        //
        // JsonObject object = Json.parse("null").asObject();
        // System.out.println(object);

        // String json = "{\n" +
        //         "    \"string\": \"value\",\n" +
        //         "    \"array\": [1, 2, 3],\n" +
        //         "    \"object\": {\n" +
        //         "        \"number\": 12,\n" +
        //         "        \"boolean\": true\n" +
        //         "    }\n" +
        //         "}";
        // JsonObject obj = Json.parse(json).asObject();
        // String value = obj.get("string").asString();
        // int two = obj.get("array").asArray()
        //              .get(1).asInt();
        // int twelve = obj.get("object").asObject()
        //                 .get("number").asInt();
        // boolean yes = obj.get("object").asObject()
        //                  .get("boolean").asBoolean();

        String json = "{\n" +
                "    \"string\": \"value\",\n" +
                "    \"array\": [1, 2, 3],\n" +
                "    \"object\": {\n" +
                "        \"number\": 12,\n" +
                "        \"boolean\": true\n" +
                "    }\n" +
                "}";

        JsonElement obj = Json.parse(json);

        String value = obj.get("string").asString();
        int two = obj.get("array").get(1).asInt();
        int twelve = obj.get("object").get("number").asInt();
        boolean yes = obj.get("object").get("boolean").asBoolean();

        Stream.of(value, two, twelve, yes).forEach(System.out::println);

        List<String> collect = Json.parse("[1, 2, 3]").asArray()
                .stream()
                .map(TWO_SPACES::format)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
