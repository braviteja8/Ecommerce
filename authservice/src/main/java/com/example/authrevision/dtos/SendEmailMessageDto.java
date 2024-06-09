package com.example.authrevision.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendEmailMessageDto {
    private String from;
    private String to;
    private String body;
    private String subject;
}
