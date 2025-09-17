package com.ioms.api.category;

import com.ioms.api.BOs.CommonResponseBO;
import com.ioms.api.category.dto.CategoryBO;

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
