package com.ioms.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.bo.CreateProductBO;
import com.ioms.api.bo.ProductBO;

public interface ProductService {
	
	public CommonResponseBO create(CreateProductBO req);
	public CommonResponseBO findById(Long id);
	public Page<ProductBO> findByName(String q, Pageable pageable);
	public CommonResponseBO updateProduct(CreateProductBO req);
	public CommonResponseBO deleteById(Long id);

}
