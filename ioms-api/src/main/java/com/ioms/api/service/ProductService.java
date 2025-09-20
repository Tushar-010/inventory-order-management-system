package com.ioms.api.service;

import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.bo.CreateProductBO;

public interface ProductService {
	
	public CommonResponseBO create(CreateProductBO req);

}
