package com.ioms.api.bo;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class CreateProductBO {
	@NotBlank private String sku;
    @NotBlank private String name;
    private Long categoryId;
    @NotBlank private String unit;
    @NotNull @PositiveOrZero private BigDecimal costPrice;
    @NotNull @PositiveOrZero private BigDecimal sellPrice;
    @NotNull @PositiveOrZero private Integer reorderLevel;
    private Boolean isActive;
    
	public CreateProductBO() {
	}

	public CreateProductBO(@NotBlank String sku, @NotBlank String name, Long categoryId, @NotBlank String unit,
			@NotNull @PositiveOrZero BigDecimal costPrice, @NotNull @PositiveOrZero BigDecimal sellPrice,
			@NotNull @PositiveOrZero Integer reorderLevel, Boolean isActive) {
		super();
		this.sku = sku;
		this.name = name;
		this.categoryId = categoryId;
		this.unit = unit;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.reorderLevel = reorderLevel;
		this.isActive = isActive;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
    
	
    
}
