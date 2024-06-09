package com.example.agr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class AgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrApplication.class, args);
	}

}
