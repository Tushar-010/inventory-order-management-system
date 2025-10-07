package com.ioms.api.bo;

import java.math.BigDecimal;

import com.ioms.api.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductBO {
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
    
    public ProductBO() {}
    
	public ProductBO(Long id, String sku, String name, String unit, BigDecimal costPrice, BigDecimal sellPrice,
			Integer reorderLevel, Boolean isActive, Long categoryId, String categoryName) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.unit = unit;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.reorderLevel = reorderLevel;
		this.isActive = isActive;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public ProductBO(Product product) {
	    this.id = product.getId();
	    this.sku = product.getSku();
	    this.name = product.getName();
	    this.unit = product.getUnit();
	    this.costPrice = product.getCostPrice();
	    this.sellPrice = product.getSellPrice();
	    this.reorderLevel = product.getReorderLevel();
	    this.isActive = product.getIsActive();
	    this.categoryId = product.getCategory().getId();
	    this.categoryName = product.getCategory().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    
}

