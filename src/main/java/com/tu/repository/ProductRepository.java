package com.tu.repository;

import com.tu.model.Category;
import com.tu.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);


    Page<Product> findAllByCategoryId(Long id ,Pageable pageable);
}
