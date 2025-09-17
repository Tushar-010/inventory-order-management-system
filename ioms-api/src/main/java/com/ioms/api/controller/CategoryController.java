package com.ioms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ioms.api.BOs.CommonResponseBO;
import com.ioms.api.category.CategoryRepository;
import com.ioms.api.category.CategoryService;
import com.ioms.api.category.dto.CategoryBO;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

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

}
