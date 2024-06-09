package com.example.productrevision.models;

import ch.qos.logback.core.joran.action.BaseModelAction;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@JsonSerialize
public class Product extends BaseModel implements Serializable {
    private String name;
    private String description;
    private int price;
    private String image;
    /*
    1   1
    M    1
     */
    @ManyToOne
    private Category category;

}
