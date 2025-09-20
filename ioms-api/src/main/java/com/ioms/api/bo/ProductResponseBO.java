package com.ioms.api.bo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class ProductResponseBO {
    private Long id;
    private String sku;
    private String name;
    private String unit;
    private BigDecimal costPrice;
    private BigDecimal sellPrice;
    private Integer reorderLevel;
    private Boolean isActive;
    private Long categoryId;
    private String categoryName;
    
}

