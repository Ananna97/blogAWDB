package com.example.blog.service;

import com.example.blog.model.Category;
import com.example.blog.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;




@Slf4j

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}