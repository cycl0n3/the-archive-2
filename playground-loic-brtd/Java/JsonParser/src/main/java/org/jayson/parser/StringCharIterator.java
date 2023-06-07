package org.jayson.parser;

import java.util.Objects;

public class StringCharIterator implements CharIterator {

    private final String source;
    private int index = -1;
    private int line = 1;
    private int column = 0;

    public StringCharIterator(String source) {
        Objects.requireNonNull(source);
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return index + 1 < source.length();
    }

    @Override
    public Character next() {
        if (hasNext()) {
            updateLineAndColumn();
            index++;
            return source.charAt(index);
        } else {
            return null;
        }
    }

    private void updateLineAndColumn() {
        if (index > 0 && source.charAt(index) == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
