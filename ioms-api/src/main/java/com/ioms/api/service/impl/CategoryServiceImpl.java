package com.ioms.api.service.impl;

import com.ioms.api.bo.CategoryBO;
import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.entity.Category;
import com.ioms.api.repo.CategoryRepository;
import com.ioms.api.service.CategoryService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository repo;

    @Transactional
    public CommonResponseBO create(CategoryBO category) {
    	CommonResponseBO bo = new CommonResponseBO();
        if (repo.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists: " + category.getName());
        }
        Category c = new Category();
        c.setName(category.getName().trim());
        c.setDescription(category.getDescription());
        c = repo.save(c);
        return bo;
    }
}

