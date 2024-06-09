package com.example.authrevision.services;

import com.example.authrevision.dtos.SendEmailMessageDto;
import com.example.authrevision.models.Token;
import com.example.authrevision.models.User;
import com.example.authrevision.repositories.TokenRepository;
import com.example.authrevision.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    public User signup(String email, String name, String password) throws JsonProcessingException {
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser=userRepository.save(user);
//        SendEmailMessageDto messageDto=new SendEmailMessageDto();
//        messageDto.setFrom("scalertestemail@gmail.com");
//        messageDto.setTo(email);
//        messageDto.setBody("welcome to scaler");
//        messageDto.setSubject("Thank you for signing up");
//        kafkaTemplate.send("handleEmail",objectMapper.writeValueAsString(messageDto));
//
//        System.out.println("user signedin");
        return savedUser;
    }

    public Token login(String email, String password) {
        Optional<User>userOptional=userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException();
        }
        User user=userOptional.get();
        if (!bCryptPasswordEncoder.matches(password,user.getPassword())) {
            throw new RuntimeException();
        }
        Token token=new Token();
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,30);
        Date getExpireDate=calendar.getTime();
        token.setExpireAt(getExpireDate);

        return tokenRepository.save(token);
    }

    public boolean validateToken(String token) {
        Optional<Token>tokenOptional=tokenRepository.findByValueAndDeletedEqualsAndExpireAtGreaterThan(token,false,new Date());
        if (tokenOptional.isEmpty()) {
            return false;

        }
            return true;
    }

    public void logout(String token) {
        boolean flag=validateToken(token);

        if (flag==true) {
            Optional<Token>tokenOptional=tokenRepository.findByValue(token);
            Token token1= tokenOptional.get();
            token1.setDeleted(true);
            tokenRepository.save(token1);
        }
        else{
            System.out.println("token already deleted");
        }
    }
}
