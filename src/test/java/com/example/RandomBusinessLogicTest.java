package com.example;
import org.junit.jupiter.api.*;
import com.example.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class RandomBusinessLogicTest {
    
    @Test
    public void testRandomSelection() {
        Map<String, BackendInstance> instances = Map.of(
                "127.0.0.1", new BackendInstance("127.0.0.1"),
                "192.168.0.1", new BackendInstance("192.168.0.1"),
                "10.0.0.1", new BackendInstance("10.0.0.1")
        );
        RandomBusinessLogic strategy = new RandomBusinessLogic();
        for (int i = 0; i < 100; i++) {
            Optional<BackendInstance> selected = strategy.select(instances);
            assertTrue(selected.isPresent());
            assertTrue(instances.containsKey(selected.get().getAddress()));
        }
    }

    @Test
    public void testEmptyInstances() {
        Map<String, BackendInstance> instances = Map.of();
        RandomBusinessLogic strategy = new RandomBusinessLogic();
        assertTrue(strategy.select(instances).isEmpty());
    }

}
