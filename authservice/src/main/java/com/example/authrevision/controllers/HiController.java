package com.example.authrevision.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @GetMapping("/hi")
    public String getData(){
        System.out.println("hi");
        return "hi";
    }
}
