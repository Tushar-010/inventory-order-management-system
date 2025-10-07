package com.ioms.api.controller;

import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.bo.CreateProductBO;
import com.ioms.api.bo.ProductBO;
import com.ioms.api.entity.Product;
import com.ioms.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
    private ProductService productService;

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	@ResponseBody
    public CommonResponseBO createProduct(@RequestBody CreateProductBO req) {
        return productService.create(req);
    }
    
    @GetMapping("/fetchProduct/{id}")
    public CommonResponseBO fetchProductById(@PathVariable Long id) {
        return productService.findById(id);
    }
    
    @GetMapping("/products?search=&page=&size=")
    public Page<ProductBO> list(@RequestParam(required=false) String search,
                              @RequestParam(defaultValue="0") int page,
                              @RequestParam(defaultValue="10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findByName(search, PageRequest.of(page, size));
    }
    
    
    @PutMapping("/updateProduct")
    public CommonResponseBO update(@RequestBody  CreateProductBO bo) {
        
        return productService.updateProduct(bo);
    }
    
    @PutMapping("/deleteProduct/{id}")
    public void delete(@PathVariable Long id) { productService.deleteById(id); }
}