package org.jayson.parser;

import java.util.Objects;

public class Token {

    static final Token OPENING_BRACKET = new Token("[", Type.OPENING_BRACKET);
    static final Token CLOSING_BRACKET = new Token("]", Type.CLOSING_BRACKET);
    static final Token OPENING_CURLY = new Token("{", Type.OPENING_CURLY);
    static final Token CLOSING_CURLY = new Token("}", Type.CLOSING_CURLY);
    static final Token COMMA = new Token(",", Type.COMMA);
    static final Token COLON = new Token(":", Type.COLON);
    static final Token TRUE = new Token("true", Type.BOOLEAN);
    static final Token FALSE = new Token("false", Type.BOOLEAN);
    static final Token NULL = new Token("null", Type.NULL);

    public final String value;
    public final Type type;
    public final int line, column;

    public Token(String value, Type type) {
        this.value = value;
        this.type = type;
        this.line = 0;
        this.column = 0;
    }

    public Token(String value, Type type, int line, int column) {
        this.value = value;
        this.type = type;
        this.line = line;
        this.column = column;
    }

    enum Type {
        STRING, BOOLEAN, NUMBER, COMMA, COLON, NULL,
        OPENING_BRACKET, CLOSING_BRACKET,
        OPENING_CURLY, CLOSING_CURLY
    }

    @Override
    public String toString() {
        return type + "(" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(value, token.value) &&
                type == token.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
