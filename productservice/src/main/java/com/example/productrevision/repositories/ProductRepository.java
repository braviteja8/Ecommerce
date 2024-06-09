package com.example.productrevision.repositories;


import com.example.productrevision.models.Product;
import jdk.jfr.Category;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Configuration
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Product product);
}
