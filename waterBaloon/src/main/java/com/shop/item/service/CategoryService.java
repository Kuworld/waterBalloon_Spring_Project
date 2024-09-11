package com.shop.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.item.entity.Category;
import com.shop.item.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    
    
    public List<Category> getAllCategories() {
        
    	return categoryRepository.findAll();
    }
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}