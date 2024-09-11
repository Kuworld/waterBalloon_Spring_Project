package com.shop.item.service;

import com.shop.item.dto.SubCategoryDTO;
import com.shop.item.entity.SubCategory;
import com.shop.item.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }
    
    
    
    public List<SubCategoryDTO> getSubCategoriesByCategoryId(Integer categoryId) {
        List<SubCategory> subCategories = subCategoryRepository.findByCategoryC1Cat(categoryId);
        return subCategories.stream().map(subCategory -> {
            SubCategoryDTO dto = new SubCategoryDTO();
            dto.setC2Cat(subCategory.getC2Cat());
            dto.setC2Name(subCategory.getC2Name());
            return dto;
        }).collect(Collectors.toList());
    }
    
    public SubCategory getSubCategoryById(Integer id) {
        return subCategoryRepository.findById(id).orElse(null);
    }
}
