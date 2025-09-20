package com.ioms.api.controller;

import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.bo.CreateProductBO;
import com.ioms.api.service.ProductService;

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