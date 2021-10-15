package com.example.samplecodedump;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class StringHolderTest {
    StringHolder stringHolder;

    @Before
    public void setUp() throws Exception {
        stringHolder = new StringHolder("hello", "world", "!");
    }

    @Test
    public void testGetString() throws Exception {
        assertEquals("hello", stringHolder.getFirst());
        assertEquals("world", stringHolder.getSecond());
        assertEquals("!", stringHolder.getThird());
    }

    @Test (expected = NullPointerException.class)
    public void testBaseConstructor() throws Exception {
        StringHolder nullTestHolder = new StringHolder(null, null, null);
        int nullPointer = nullTestHolder.getFirst().length();
    }

    @Ignore ("im testing the ignore functionality")
    @Test
    public void testIgnore() throws Exception {
        // hey guys what's up
    }
}