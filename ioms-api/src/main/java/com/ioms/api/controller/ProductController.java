package com.ioms.api.controller;

import com.ioms.api.BOs.CommonResponseBO;
import com.ioms.api.product.ProductService;
import com.ioms.api.product.dto.CreateProductBO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
    private ProductService productService;

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	@ResponseBody
    public CommonResponseBO create(@RequestBody CreateProductBO req) {
        return productService.create(req);
    }
}