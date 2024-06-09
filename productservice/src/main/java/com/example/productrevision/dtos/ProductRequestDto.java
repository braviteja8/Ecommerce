package com.example.productrevision.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;
}
