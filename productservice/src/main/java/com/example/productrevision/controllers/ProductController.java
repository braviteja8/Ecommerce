package com.example.productrevision.controllers;

import com.example.productrevision.exceptions.InvalidIdException;
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

     @GetMapping("/products/{id}/{token}")
    public Product getSingleProduct(@PathVariable("id")Long id,@PathVariable("token")String token) throws InvalidIdException {

        return this.productService.getSingleProduct(id,token);
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }
    @PostMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id")Long id,@RequestBody Product product){

//         return this.productService.updateProduct(id,product);
        return new Product();
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
         return new Product();
    }

}
