package com.example.productrevision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private RestTemplate restTemplate;
    @Autowired
    public HelloController(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @RequestMapping ("/hello")
   public String getData(){
        return restTemplate.getForObject("https://authrevision/hi",String.class);
   }

}
