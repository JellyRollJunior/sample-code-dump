package com.example.samplecodedump;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringHolderTest {

    @Test
    public void testGetString() throws Exception{
        StringHolder stringHolder = new StringHolder("hello", "world", "!");
        assertEquals("hello", stringHolder.getFirst());
        assertEquals("world", stringHolder.getSecond());
        assertEquals("!", stringHolder.getThird());
    }
}