package com.ioms.api.category;

import com.ioms.api.BOs.CommonResponseBO;
import com.ioms.api.category.dto.CategoryBO;

public interface CategoryService {
	public CommonResponseBO create(CategoryBO category);
}
