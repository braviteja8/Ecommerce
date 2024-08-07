package com.example.productrevision.controllers;

import com.example.productrevision.dtos.ProductRequestDto;
import com.example.productrevision.exceptions.InvalidIdException;
import com.example.productrevision.models.Category;
import com.example.productrevision.models.Product;

import com.example.productrevision.services.ProductServiceInterface;
import com.example.productrevision.services.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductServiceInterface productService;
    public ProductController (@Qualifier("selfProductService") ProductServiceInterface productService){
        this.productService=productService;
    }

     @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id")Long id) throws InvalidIdException {
        return this.productService.getSingleProduct(id);
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }
    @PostMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id")Long id,@RequestBody ProductRequestDto requestDto){
        Product product=new Product();
        product.setId(id);
        product.setName(requestDto.getTitle());
        product.setImage(requestDto.getImage());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(requestDto.getCategory());
        return this.productService.updateProduct(id,product);
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto requestDto){

        Product product=new Product();
        product.setName(requestDto.getTitle());
        product.setImage(requestDto.getImage());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(requestDto.getCategory());
        return this.productService.addProduct(product);
    }

}
