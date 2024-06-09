package com.example.productrevision.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@JsonSerialize
public class Category extends BaseModel implements Serializable {
    /*
    1    M
    1     1

     */
    @OneToMany
    List<Product>products;
    String name;
}
