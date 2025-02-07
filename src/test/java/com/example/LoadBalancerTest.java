package com.example;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.*;

public class LoadBalancerTest {
    private LoadBalancer loadBalancer;
    @BeforeEach
    public void setUp() {
        loadBalancer = new LoadBalancer(new RandomBusinessLogic());
    }

    @Test
    public void TestRegisterInstance(){
        BackendInstance instance = new BackendInstance("127.0.0.1");
        loadBalancer.register(instance);
        assertTrue(loadBalancer.instances.containsKey("127.0.0.1"));
        assertEquals(loadBalancer.instances.get("127.0.0.1"), instance);
    }

    @Test
    public void TestDuplicateInstance(){
        BackendInstance instance = new BackendInstance("127.0.0.1");
        BackendInstance instance2 = new BackendInstance("127.0.0.1");
        loadBalancer.register(instance);
        loadBalancer.register(instance2);
        assertEquals(1, loadBalancer.instances.size());
        assertEquals(instance2, loadBalancer.instances.get("127.0.0.1"));
    }

    @Test
    public void TestNullInstance(){
    assertThrows(IllegalArgumentException.class, () -> {loadBalancer.register(null);});
    }

    @Test
    public void TestNullInstanceAddress(){
        BackendInstance instance = new BackendInstance(null);
        assertThrows(IllegalArgumentException.class, () -> {loadBalancer.register(instance);});
    }

    @Test
    public void TestEmptyInstanceAddress(){
        BackendInstance instance = new BackendInstance(""); 
        assertThrows(IllegalArgumentException.class, () -> {loadBalancer.register(instance);});
    }

    @Test
    public void TestExceedCapacity(){
        for (int i =0; i<10; i++){
            loadBalancer.register(new BackendInstance("127.0.0." + i));
        }
        assertThrows(IllegalStateException.class, () -> {loadBalancer.register(new BackendInstance("abcd"));});
    }

    @Test
    public void testGetWithNoInstances() {
        Optional<BackendInstance> result = loadBalancer.get();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetWithInstances() {
        BackendInstance instance1 = new BackendInstance("127.0.0.1");
        BackendInstance instance2 = new BackendInstance("127.0.0.2");
        loadBalancer.register(instance1);
        loadBalancer.register(instance2);

        Optional<BackendInstance> result = loadBalancer.get();
        assertTrue(result.isPresent());
        assertTrue(loadBalancer.instances.containsValue(result.get())); //why
    }

    @Test
    public void testRegisterMixedInstances() {
        BackendInstance validInstance = new BackendInstance("127.0.0.1");
        BackendInstance invalidInstance = new BackendInstance("");
        
        loadBalancer.register(validInstance);
        assertThrows(IllegalArgumentException.class, () -> loadBalancer.register(invalidInstance));
        assertEquals(1, loadBalancer.instances.size());
    }
}
