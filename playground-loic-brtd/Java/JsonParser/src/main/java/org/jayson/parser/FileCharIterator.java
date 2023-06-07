package org.jayson.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileCharIterator implements CharIterator {

    private final File source;
    private final FileReader reader;
    private int line = 1;
    private int column = 0;
    private int next;

    public FileCharIterator(File source) {
        this.source = source;
        try {
            reader = new FileReader(source);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            next = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return next != -1;
    }

    @Override
    public Character next() {
        Character result = null;
        if (next != -1) {
            updateLineAndColumn();
            result = (char) next;
            try {
                next = reader.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private void updateLineAndColumn() {
        if (next == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
    }

    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

}
