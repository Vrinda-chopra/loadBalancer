package com.example;
import com.example.BackendInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
public class BackendInstanceTest {

    @Test
    public void testGetAddress(){
        BackendInstance instance = new BackendInstance("127.0.0.1");
        assertEquals(instance.getAddress(), "127.0.0.1");
    }

    @Test
    public void testNullAddress(){
        BackendInstance instance = new BackendInstance(null);
        assertNull(instance.getAddress());
    }

    @Test
    public void testEmptyAddress(){
        BackendInstance instance = new BackendInstance("");
        assertEquals("", instance.getAddress());
    }

    @Test
    public void testSpecialCharactersInAddress(){
        BackendInstance instance = new BackendInstance("abch&");
        assertEquals("Wrong input format", instance.getAddress());
    }
}
