package com.example.authrevision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class AuthRevisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthRevisionApplication.class, args);
    }

}
