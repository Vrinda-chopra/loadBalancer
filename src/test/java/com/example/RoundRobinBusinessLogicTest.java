package com.example;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import com.example.*;

public class RoundRobinBusinessLogicTest {
    
    @Test
    public void TestSelectRoundRobinStrategy(){//it fails so i need to modify the logic in Round Robin
        Map<String, BackendInstance> instances = Map.of(
                "127.0.0.1", new BackendInstance("127.0.0.1"),
                "192.168.0.1", new BackendInstance("192.168.0.1"),
                "10.0.0.1", new BackendInstance("10.0.0.1")
        );

        RoundRobinBusinessLogic strategy = new RoundRobinBusinessLogic();
        assertEquals("127.0.0.1", strategy.select(instances).get().getAddress());
        assertEquals("192.168.0.1", strategy.select(instances).get().getAddress());
        assertEquals("10.0.0.1", strategy.select(instances).get().getAddress());
    }

    @Test
    public void TestEmptyInstance(){
        Map<String, BackendInstance> instances = new HashMap<>();
        RoundRobinBusinessLogic strategy = new RoundRobinBusinessLogic();
        assertTrue(strategy.select(instances).isEmpty());
    }

    @Test
    public void TestSingleInstance(){
        Map<String, BackendInstance> instances = Map.of(
            "127.0.0.1", new BackendInstance("127.0.0.1")
        );
        RoundRobinBusinessLogic strategy = new RoundRobinBusinessLogic();
        assertEquals("127.0.0.1", strategy.select(instances).get().getAddress());
        assertEquals("127.0.0.1", strategy.select(instances).get().getAddress());
        assertEquals("127.0.0.1", strategy.select(instances).get().getAddress());
    }
}
