package com.example.productrevision.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean //this methods will be executed only once to avoid getting multiple instances.
   @LoadBalanced //I will not give you service URL, but rather a hint for what you need to discover
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
