package org.jayson.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.jayson.parser.Token.Type.NUMBER;
import static org.jayson.parser.Token.Type.STRING;

public class JsonLexer {

    private static final Pattern NUMBER_PATTERN =
            Pattern.compile("-?(0|[1-9]\\d*)(\\.\\d+)?([eE][+-]?\\d+)?");

    private final CharIterator iterator;
    private Character current;

    public JsonLexer(String source) {
        iterator = new StringCharIterator(source);
        current = iterator.next();
    }

    public JsonLexer(File file) {
        iterator = new FileCharIterator(file);
        current = iterator.next();
    }

    public boolean hasNext() {
        current = skipAnyWhitespace();
        return current != null;
    }

    public Token nextToken() {
        if (!hasNext())
            return null;

        switch (current) {
            case '{':
                return parseSingle(Token.OPENING_CURLY);
            case '}':
                return parseSingle(Token.CLOSING_CURLY);
            case '[':
                return parseSingle(Token.OPENING_BRACKET);
            case ']':
                return parseSingle(Token.CLOSING_BRACKET);
            case ':':
                return parseSingle(Token.COLON);
            case ',':
                return parseSingle(Token.COMMA);
            case 't':
                return parseWord("true", Token.TRUE);
            case 'f':
                return parseWord("false", Token.FALSE);
            case 'n':
                return parseWord("null", Token.NULL);
            case '"':
                return parseString();
            case '-': case '0': case '1': case '2':
            case '3': case '4': case '5': case '6':
            case '7': case '8': case '9':
                return parseNumber();
            default:
                throwUnexpectedChar("Unexpected character '%s'", current);
                return null;
        }
    }

    private Token parseString() {
        StringBuilder token = new StringBuilder();
        assertChar('"', current);
        token.append(current);
        current = iterator.next();
        while (current != null && current != '"') {
            if (current == '\\') {
                parseEscapeSequence(token);
            } else {
                token.append(current);
                current = iterator.next();
            }
        }
        assertChar('"', current);
        token.append(current);
        current = iterator.next();
        return new Token(token.toString(), STRING);
    }

    private void parseEscapeSequence(StringBuilder token) {
        assertChar('\\', current);
        token.append(current);
        current = iterator.next();
        if (isAnyChar("\"\\/bfnrt", current)) {
            token.append(current);
            current = iterator.next();
        } else if (current == 'u') {
            token.append(current);
            current = iterator.next();
            for (int i = 0; i < 4; i++) {
                if (!isHexDigit(current))
                    throwUnexpectedChar("Character '%c' is not a valid hexadecimal digit", current);
                token.append(current);
                current = iterator.next();
            }
        } else {
            throwUnexpectedChar("Wrong character '%c' in escape sequence", current);
        }
    }

    private Token parseNumber() {
        int line = iterator.getLine(), col = iterator.getColumn();
        StringBuilder sb = new StringBuilder();
        while (isAnyChar("0123456789-+.eE", current)) {
            sb.append(current);
            current = iterator.next();
        }
        String token = sb.toString();
        if (!isValidNumber(token)) {
            current = null;
            throwUnexpectedChar("Invalid number '%s'", token);
        }
        return new Token(token, NUMBER, line, col);
    }

    private Token parseWord(String expected, Token token) {
        for (char letter : expected.toCharArray()) {
            assertChar(letter, current);
            current = iterator.next();
        }
        return token;
    }

    private Token parseSingle(Token constant) {
        current = iterator.next();
        return constant;
    }

    private void assertChar(Character expected, Character actual) {
        if (actual == null)
            throwEndOfSource("Expected '%c' but reached the end", expected);
        if (actual != expected)
            throwUnexpectedChar("Expected '%c' but got '%c'", expected, actual);
    }

    private Character skipAnyWhitespace() {
        if (current == null || isNonWhite(current))
            return current;
        while (iterator.hasNext()) {
            char ch = iterator.next();
            if (isNonWhite(ch))
                return ch;
        }
        return null;
    }

    private boolean isNonWhite(char ch) {
        return ch != ' ' && ch != '\n' && ch != '\r' && ch != '\t';
    }

    private boolean isHexDigit(Character ch) {
        return ch != null &&
                (ch >= '0' && ch <= '9'
                        || ch >= 'A' && ch <= 'F'
                        || ch >= 'a' && ch <= 'f');
    }

    private boolean isAnyChar(String s, Character actual) {
        return actual != null && s.indexOf(actual) != -1;
    }

    private boolean isValidNumber(String token) {
        return NUMBER_PATTERN.matcher(token).matches();
    }

    public String[] consumeStrings() {
        List<String> list = new ArrayList<>();
        while (hasNext()) {
            list.add(nextString());
        }
        return list.toArray(new String[0]);
    }

    public Token[] consumeTokens() {
        List<Token> list = new ArrayList<>();
        while (hasNext()) {
            list.add(nextToken());
        }
        return list.toArray(new Token[0]);
    }

    public String nextString() {
        Token token = nextToken();
        return token == null ? null : token.value;
    }

    private void throwUnexpectedChar(String message, Object... args) {
        current = null;
        message = String.format(message, args);
        message = String.format("(%d:%d) %s", iterator.getLine(), iterator.getColumn(), message);
        throw new UnexpectedCharacterException(message);
    }

    private void throwEndOfSource(String message, Object... args) {
        current = null;
        message = String.format(message, args);
        throw new EndOfSourceException(message);
    }

    public static class EndOfSourceException extends RuntimeException {
        private EndOfSourceException(String message) {
            super(message);
        }
    }

    public static class UnexpectedCharacterException extends RuntimeException {
        private UnexpectedCharacterException(String message) {
            super(message);
        }
    }
}
