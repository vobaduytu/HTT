package com.tu.repository;

import com.tu.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName(String name);
    Page<Category> findAllByNameContaining(String s, Pageable pageable);

}
