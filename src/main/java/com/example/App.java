package com.example;

import java.util.Optional;

public class App {
    
    public static void main( String[] args )
    {
        LoadBalancer loadBalancer = new LoadBalancer(new RoundRobinBusinessLogic());
        BackendInstance instance = new BackendInstance("127.0.0.1");
        BackendInstance instance2 = new BackendInstance("127.0.0.2");
        BackendInstance instance3 = new BackendInstance("127.0.0.3");
        loadBalancer.register(instance);
        loadBalancer.register(instance2);
        loadBalancer.register(instance3);
        Optional<BackendInstance> result = loadBalancer.get();
        result.ifPresentOrElse(
            backend -> System.out.println(backend.getAddress()),
            () -> System.out.println("No instance available")
        );
        result = loadBalancer.get();
        result.ifPresentOrElse(
            backend -> System.out.println(backend.getAddress()),
            () -> System.out.println("No instance available")
        );
    }
}
