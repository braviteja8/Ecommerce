package com.example.authrevision.controllers;

import com.example.authrevision.dtos.LoginRequestDto;
import com.example.authrevision.dtos.SignupRequestDto;
import com.example.authrevision.models.Token;
import com.example.authrevision.models.User;
import com.example.authrevision.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/users/signup")
    public User signup(@RequestBody SignupRequestDto requestDto) throws JsonProcessingException {
        return this.userService.signup(requestDto.getEmail(),requestDto.getName(),requestDto.getPassword());
    }
    @PostMapping("/users/login")
    public Token login(@RequestBody LoginRequestDto requestDto){
        return this.userService.login(requestDto.getEmail(),requestDto.getPassword());
    }
    @PostMapping("/users/{token}")
    public boolean validateToken(@PathVariable("token")String token){
        return this.userService.validateToken(token);
    }
    @PostMapping("/validateToken/{token}")
    public void logout(@PathVariable("token")String token){
        this.userService.logout(token);
    }
}
