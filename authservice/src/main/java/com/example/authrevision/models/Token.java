package com.example.authrevision.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Token extends BaseModel{
    /*
    1  1
    1   1
     */
    @OneToOne
    private User user;
    private String value;
    private boolean deleted;
    private Date expireAt;
}
