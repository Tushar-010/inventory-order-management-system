package com.ioms.api.product;

import com.ioms.api.BOs.CommonResponseBO;
import com.ioms.api.product.dto.CreateProductBO;

public interface ProductService {
	
	public CommonResponseBO create(CreateProductBO req);

}
