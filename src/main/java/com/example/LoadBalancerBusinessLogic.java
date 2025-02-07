package com.example;
import java.util.*;

public interface LoadBalancerBusinessLogic {
    Optional<BackendInstance> select(Map<String, BackendInstance> instances);
}
