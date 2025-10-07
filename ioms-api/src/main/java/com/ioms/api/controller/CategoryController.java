package com.ioms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ioms.api.bo.CategoryBO;
import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;

	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	@ResponseBody
    public CommonResponseBO create(@RequestBody CategoryBO c) {
        return categoryService.create(c);
    }
	
	@PutMapping("/updateCategory")
	public CommonResponseBO updateCategory(@RequestBody CategoryBO bo) {
	    return categoryService.updateCategory(bo);
	}

	@DeleteMapping("/deleteCategory/{id}")
	public CommonResponseBO deleteCategory(@PathVariable Long id) { 
		return categoryService.deleteCategory(id);
	}
}
