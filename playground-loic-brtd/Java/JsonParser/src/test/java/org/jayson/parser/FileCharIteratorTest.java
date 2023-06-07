package org.jayson.parser;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class FileCharIteratorTest {

    @Test
    public void testNext() throws URISyntaxException {
        File file = new File(getClass().getResource("/abc.txt").toURI());
        CharIterator iterator = new FileCharIterator(file);
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
    public void testEmptySource() throws URISyntaxException {
        File file = new File(getClass().getResource("/empty.txt").toURI());
        CharIterator iterator = new FileCharIterator(file);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNullSource() {
        assertThrows(NullPointerException.class, () -> new FileCharIterator(null));
    }
}
