package com.example.productrevision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductRevisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductRevisionApplication.class, args);
    }

}
