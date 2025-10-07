package com.ioms.api.service.impl;

import com.ioms.api.bo.CategoryBO;
import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.entity.Category;
import com.ioms.api.enums.CategoryStatus;
import com.ioms.api.enums.Status;
import com.ioms.api.repo.CategoryRepository;
import com.ioms.api.service.CategoryService;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public CommonResponseBO create(CategoryBO category) {
    	CommonResponseBO bo = new CommonResponseBO();
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists: " + category.getName());
        }
        Category c = new Category();
        c.setName(category.getName().trim());
        c.setDescription(category.getDescription());
        c = categoryRepository.save(c);
        return bo;
    }
    
    @Transactional
    public CommonResponseBO updateCategory(CategoryBO category) {
    	CommonResponseBO bo = new CommonResponseBO();
        try {
        	 Optional<Category> optionalCategory  = categoryRepository.findById(category.getId());
        	 if (optionalCategory .isPresent()) {
                 Category c = optionalCategory.get();
                 c.setName(category.getName());
                 c.setDescription(category.getDescription());
                 categoryRepository.save(c);
                 bo.setStatus(Status.OK.name());
                 bo.setMessage("Category updated");
             } else {
                 bo.setMessage("Category not found");
                 bo.setStatus(Status.ERR.name());
             }
        } catch(Exception e) {
        	bo.setMessage("An error occurred: " + e.getMessage());
        	bo.setStatus(Status.ERR.name());
        }
        return bo;
    }
    
    @Transactional
    public CommonResponseBO deleteCategory(Long id) {
    	CommonResponseBO bo = new CommonResponseBO();
        try {
        	 Optional<Category> optionalCategory  = categoryRepository.findById(id);
        	 if (optionalCategory .isPresent()) {
                 Category c = optionalCategory.get();
                 c.setStatus(CategoryStatus.DELETED.name());
                 categoryRepository.save(c);
                 bo.setStatus(Status.OK.name());
                 bo.setMessage("Category deleted");
             } else {
                 bo.setMessage("Category not found");
                 bo.setStatus(Status.ERR.name());
             }
        } catch(Exception e) {
        	bo.setMessage("An error occurred while deleting category: " + e.getMessage());
        	bo.setStatus(Status.ERR.name());
        }
        return bo;
    }
}

