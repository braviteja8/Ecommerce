package com.example.authrevision.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
}
