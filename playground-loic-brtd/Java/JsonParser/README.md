# JSON parser

Json parser / formatter built from scratch in Java.

user.json :
```json
{
    "string": "value",
    "array": [1, 2, 3],
    "object": {
        "number": 12,
        "boolean": true
    }
}
```

```java
// Json parsing
File file = new File("user.json");
JsonElement obj = Json.parse(file);

String value = obj.get("string").asString();
int two      = obj.get("array").get(1).asInt();
int twelve   = obj.get("object").get("number").asInt();
boolean yes  = obj.get("object").get("boolean").asBoolean();

// Json formatting
obj.format(JsonFormatter.INLINE);
obj.format(JsonFormatter.FOUR_SPACES);
obj.format(new CustomFormatter().indent("  ")
                                .separator(", ")
                                .colon(": ")
                                .newline("\r\n"));
```