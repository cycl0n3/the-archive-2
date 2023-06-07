package org.jayson.parser;

import org.junit.jupiter.api.Test;

import static org.jayson.parser.Token.Type.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonLexerTest {

    @Test
    public void testEmptyObjectSpaced() {
        JsonLexer lexer = new JsonLexer("  {  } ");
        assertEquals("{", lexer.nextString());
        assertEquals("}", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testEmptyObjectNoSpace() {
        JsonLexer lexer = new JsonLexer("{}");
        assertEquals("{", lexer.nextString());
        assertEquals("}", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testSimpleObjectNoSpace() {
        JsonLexer lexer = new JsonLexer("{\"key\":\"value\"}");
        assertEquals("{", lexer.nextString());
        assertEquals("\"key\"", lexer.nextString());
        assertEquals(":", lexer.nextString());
        assertEquals("\"value\"", lexer.nextString());
        assertEquals("}", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testSimpleObjectSpaced() {
        JsonLexer lexer = new JsonLexer(" \t   {  \"key\"  \t : \"value\"   \t }   ");
        assertEquals("{", lexer.nextString());
        assertEquals("\"key\"", lexer.nextString());
        assertEquals(":", lexer.nextString());
        assertEquals("\"value\"", lexer.nextString());
        assertEquals("}", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testConsumeToArray() {
        JsonLexer lexer = new JsonLexer("{\"key\":\"value\"}");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"{", "\"key\"", ":", "\"value\"", "}"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testStringWithSpaces() {
        JsonLexer lexer = new JsonLexer("\" string with\tspaces \"");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"\" string with\tspaces \""};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testEmptyStringsAndBoolean() {
        JsonLexer lexer = new JsonLexer("  \"\" \"\" true ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"\"\"", "\"\"", "true"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testEmptyStringNotClosed() {
        JsonLexer lexer = new JsonLexer("  \"\" \" true ");
        assertThrows(JsonLexer.EndOfSourceException.class, lexer::consumeStrings);
        assertConsumed(lexer);
    }

    @Test
    public void testStringWithoutEndingQuote() {
        JsonLexer lexer = new JsonLexer("\"string");
        assertThrows(JsonLexer.EndOfSourceException.class, lexer::consumeStrings);
        assertConsumed(lexer);
    }

    @Test
    public void testStringWithoutQuotes() {
        JsonLexer lexer = new JsonLexer("string");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::consumeStrings);
        assertConsumed(lexer);
    }

    @Test
    public void testMultipleStrings() {
        JsonLexer lexer = new JsonLexer(" \"123\"  \"hello\"  \"hi\" ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"\"123\"", "\"hello\"", "\"hi\""};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testInteger() {
        JsonLexer lexer = new JsonLexer("  125  ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"125"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testMultipleIntegers() {
        JsonLexer lexer = new JsonLexer("  12 5  ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"12", "5"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testIntegerLeadingZero() {
        JsonLexer lexer = new JsonLexer("  0125  ");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::consumeStrings);
        assertConsumed(lexer);
    }

    @Test
    public void testIntegerZeros() {
        JsonLexer lexer = new JsonLexer("  0   0  ");
        Token[] actual = lexer.consumeTokens();
        Token[] expected = {new Token("0", NUMBER), new Token("0", NUMBER)};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testFloat() {
        JsonLexer lexer = new JsonLexer("  12.5  ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"12.5"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testMultipleFloats() {
        JsonLexer lexer = new JsonLexer("  12.5 3.14  ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"12.5", "3.14"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testFloatLeadingZero() {
        JsonLexer lexer = new JsonLexer("  012.5  ");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::consumeStrings);
        assertConsumed(lexer);
    }

    @Test
    public void testFloatLeadingDecimalZero() {
        JsonLexer lexer = new JsonLexer("  12.005  ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"12.005"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testFloatNothingAfterPoint() {
        JsonLexer lexer = new JsonLexer("  12.  ");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::consumeStrings);
        assertConsumed(lexer);
    }

    @Test
    public void testCommaSeparatedNumbers() {
        JsonLexer lexer = new JsonLexer(" 12.3,12,-4.2,12e+45,8E-9,7e3 ");
        String[] actual = lexer.consumeStrings();
        String[] expected = {"12.3", ",", "12", ",", "-4.2", ",", "12e+45", ",", "8E-9", ",", "7e3"};
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testMultipleBooleans() {
        JsonLexer lexer = new JsonLexer("  true false  true");
        assertEquals("true", lexer.nextString());
        assertEquals("false", lexer.nextString());
        assertEquals("true", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testMultipleBooleansStuckTogether() {
        JsonLexer lexer = new JsonLexer("  truefalsetrue");
        assertEquals("true", lexer.nextString());
        assertEquals("false", lexer.nextString());
        assertEquals("true", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testNewlineEscape() {
        JsonLexer lexer = new JsonLexer("  \"new\\nline\"");
        assertEquals("\"new\\nline\"", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testWrongEscapeSequence() {
        JsonLexer lexer = new JsonLexer("  \"new\\oline\"");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::nextToken);
    }

    @Test
    public void testWrongHexadecimalSequence() {
        JsonLexer lexer = new JsonLexer("  \"hello\\u75w0world\" ");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::nextToken);
    }

    @Test
    public void testTooShortHexadecimalSequence() {
        JsonLexer lexer = new JsonLexer("  \"hello\\u75a \" ");
        assertThrows(JsonLexer.UnexpectedCharacterException.class, lexer::nextToken);
    }

    @Test
    public void testValidHexadecimalSequence() {
        JsonLexer lexer = new JsonLexer("  \"hello\\u75fAworld\" ");
        assertEquals("\"hello\\u75fAworld\"", lexer.nextString());
        assertConsumed(lexer);
    }

    @Test
    public void testBigExample() {
        JsonLexer lexer = new JsonLexer(" {\n" +
                "  \"integer\": 123,\n" +
                "  \"float\": 12.3,\n" +
                "  \"array\": [12, 12.3, 8],\n" +
                "  \"object\": {},\n" +
                "  \"b_true\": true,\n" +
                "  \"b_false\": false,\n" +
                "  \"null\": null\n" +
                "}\n");
        String[] actual = lexer.consumeStrings();
        String[] expected = ("{ 'integer' : 123 , 'float' : 12.3 , 'array' : [ 12 , 12.3 , 8 ] , " +
                "'object' : { } , 'b_true' : true , 'b_false' : false , 'null' : null }")
                .replaceAll("'", "\"").split(" ");
        assertArrayEquals(expected, actual);
        assertConsumed(lexer);
    }

    @Test
    public void testNullValue() {
        JsonLexer lexer = new JsonLexer("{\"here\":null,\"not_here\":12}");
        assertEquals(new Token("{", OPENING_CURLY), lexer.nextToken());
        assertEquals(new Token("\"here\"", STRING), lexer.nextToken());
        assertEquals(new Token(":", COLON), lexer.nextToken());
        assertEquals(new Token("null", NULL), lexer.nextToken());
        assertEquals(new Token(",", COMMA), lexer.nextToken());
        assertEquals(new Token("\"not_here\"", STRING), lexer.nextToken());
        assertEquals(new Token(":", COLON), lexer.nextToken());
        assertEquals(new Token("12", NUMBER), lexer.nextToken());
        assertEquals(new Token("}", CLOSING_CURLY), lexer.nextToken());
        assertConsumed(lexer);
    }

    private void assertConsumed(JsonLexer lexer) {
        assertFalse(lexer.hasNext());
        assertNull(lexer.nextToken());
    }
}