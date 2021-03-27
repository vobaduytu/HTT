package com.tu.service.impl;

import com.tu.model.Category;
import com.tu.repository.CategoryRepository;
import com.tu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Page<Category> showAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
      categoryRepository.save(category);
    }

    @Override
    public void delete(long id) {
       categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> findAllByNameContaining(String s, Pageable pageable) {
        return categoryRepository.findAllByNameContaining(s,pageable);
    }
}
