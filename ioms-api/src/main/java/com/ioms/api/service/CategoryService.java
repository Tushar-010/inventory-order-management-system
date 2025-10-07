package com.ioms.api.service;

import com.ioms.api.bo.CategoryBO;
import com.ioms.api.bo.CommonResponseBO;

public interface CategoryService {
	public CommonResponseBO create(CategoryBO category);
	public CommonResponseBO updateCategory(CategoryBO bo);
	public CommonResponseBO deleteCategory(Long id);
}
