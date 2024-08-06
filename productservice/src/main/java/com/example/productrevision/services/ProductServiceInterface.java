package com.example.productrevision.services;

import com.example.productrevision.dtos.ProductRequestDto;
import com.example.productrevision.exceptions.InvalidIdException;
import com.example.productrevision.models.Product;
import com.example.productrevision.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServiceInterface {
    Product getSingleProduct(Long id) throws InvalidIdException;
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductRequestDto productRequestDto);
    Product addProduct(ProductRequestDto productRequestDto);
}
