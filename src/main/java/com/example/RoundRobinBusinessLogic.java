package com.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinBusinessLogic implements LoadBalancerBusinessLogic{
    private final AtomicInteger counter = new AtomicInteger(0);
    @Override
    public Optional<BackendInstance> select(Map<String, BackendInstance> instances){
        List<String> addressList = instances.keySet().stream().toList();
        if(addressList.isEmpty()) {
            return Optional.empty();
        }
        int index =counter.getAndIncrement() % addressList.size();
        System.out.println(index);
        System.out.println(counter);
        return Optional.of(instances.get(addressList.get(index)));
    }
}
