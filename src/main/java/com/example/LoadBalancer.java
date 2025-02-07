package com.example;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancer 
{
    Map<String, BackendInstance> instances = new ConcurrentHashMap<>();
    private static final int MAX_CAPACITY = 10;
    private LoadBalancerBusinessLogic strategy;
    public LoadBalancer(LoadBalancerBusinessLogic strategy){
        this.strategy = strategy;
    }
    public void register(BackendInstance instance){
        if (instance==null){
            throw new IllegalArgumentException("null instance");
        }
        else if (instance.getAddress()== null){
            throw new IllegalArgumentException("null address");
        }
        else if(instance.getAddress().isEmpty()){
            throw new IllegalArgumentException("empty address");
        }
        System.out.println("-----------------" + instances.size());
        System.out.println(MAX_CAPACITY);
        if (instances.size()==MAX_CAPACITY){
            throw new IllegalStateException("maximum capacity of load balancer exceeded");
        }
        instances.put(instance.getAddress(), instance);
    }

    public Optional<BackendInstance> get(){
        if (instances.isEmpty()){
            return Optional.empty();
        }
        return strategy.select(instances);
    }
}
