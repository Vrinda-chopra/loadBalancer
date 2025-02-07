package com.example;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBusinessLogic implements LoadBalancerBusinessLogic{
    @Override
    public Optional<BackendInstance> select(Map<String, BackendInstance> instances){
        if (instances.isEmpty()){
            return Optional.empty();
        }
        List<String> addressList = instances.keySet().stream().toList();
        String randomAddress = addressList.get(ThreadLocalRandom.current().nextInt(instances.size()));
        return Optional.of(instances.get(randomAddress));
    }
}
