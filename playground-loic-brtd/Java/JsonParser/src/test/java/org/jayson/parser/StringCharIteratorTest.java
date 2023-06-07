package org.jayson.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class StringCharIteratorTest {

    @Test
    public void testNext() {
        CharIterator iterator = new StringCharIterator("abc");
        assertTrue(iterator.hasNext());
        assertEquals('a', iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals('b', iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals('c', iterator.next());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void testEmptySource() {
        CharIterator iterator = new StringCharIterator("");
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNullSource() {
        assertThrows(NullPointerException.class, () -> new StringCharIterator(null));
    }
}
