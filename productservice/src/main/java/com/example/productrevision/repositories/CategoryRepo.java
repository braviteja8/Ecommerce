package com.example.productrevision.repositories;

import com.example.productrevision.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Optional<Category> findById(Long id);
    Optional<Category>findByName(String name);
}
