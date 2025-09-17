package com.ioms.api.product;

import com.ioms.api.BOs.CommonResponseBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ioms.api.category.Category;
import com.ioms.api.category.CategoryRepository;
import com.ioms.api.inventory.Inventory;
import com.ioms.api.inventory.InventoryRepository;
import com.ioms.api.product.dto.CreateProductBO;
import com.ioms.api.product.dto.ProductResponseBO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import com.ioms.api.enums.Status;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class.getName());
	
	@Autowired
    private ProductRepository productRepo;
	
	@Autowired
    private CategoryRepository categoryRepo;
	
	@Autowired
    private InventoryRepository inventoryRepo;


    @Transactional
    public CommonResponseBO create(CreateProductBO req) {
    	CommonResponseBO bo = new CommonResponseBO();
    	try {
            if (productRepo.existsBySku(req.getSku())) {
                throw new IllegalArgumentException("SKU already exists: " + req.getSku());
            }
            Category category = null;
            if (req.getCategoryId() != null) {
                category = categoryRepo.findById(req.getCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Category not found: " + req.getCategoryId()));
            }
            
            Product p = new Product();
            p.setSku(req.getSku());		
            p.setName(req.getName());
            p.setCategory(category);
            p.setUnit(req.getUnit());
            p.setCostPrice(req.getCostPrice());
            p.setSellPrice(req.getSellPrice());
            p.setReorderLevel(req.getReorderLevel());
            p.setIsActive(req.getIsActive() == null ? true : req.getIsActive());
            p = productRepo.save(p);

            // initialize inventory row
            Inventory in = new Inventory();
            in.setProduct(p);
            in.setId(p.getId());
            in.setQtyOnHand(BigDecimal.ZERO);
            inventoryRepo.save(in);
            bo.setStatus(Status.OK.name());
    	} catch(Exception e) {
    		bo.setStatus(Status.ERR.name());
    		LOGGER.error("Exception while getting capacities of fetched provider shifts:", e);
    	}

        return bo;
    }
}
