package org.jayson.parser;

import java.util.Iterator;

public interface CharIterator extends Iterator<Character> {

    int getLine();

    int getColumn();

}
