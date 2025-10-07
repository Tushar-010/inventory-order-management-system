package com.ioms.api.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ioms.api.repo.CategoryRepository;
import com.ioms.api.repo.InventoryRepository;
import com.ioms.api.repo.ProductRepository;
import com.ioms.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import com.ioms.api.bo.CommonResponseBO;
import com.ioms.api.bo.CreateProductBO;
import com.ioms.api.bo.ProductBO;
import com.ioms.api.entity.Category;
import com.ioms.api.entity.Inventory;
import com.ioms.api.entity.Product;
import com.ioms.api.enums.ProductEnum;
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
                		.filter(prod -> prod.getStatus() == ProductEnum.ACTIVE.name())
        			    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            }
            
            Product p = new Product();
            p.setSku(req.getSku());		
            p.setName(req.getName());
            p.setSku(ProductEnum.ACTIVE.name());		
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
            in.setQtyOnHand(BigDecimal.ZERO);
            inventoryRepo.save(in);
            bo.setStatus(Status.OK.name());
    	} catch(Exception e) {
    		bo.setStatus(Status.ERR.name());
    		LOGGER.error("Exception while getting capacities of fetched provider shifts:", e);
    	}

        return bo;
    }
    
    
    @Transactional
    public CommonResponseBO findById(Long id) {
    	CommonResponseBO bo = new CommonResponseBO();
    	try {
            
    		Optional<Product> p = productRepo.findById(id);
    		if(p!= null) {
    			bo.setData(p);
    			bo.setStatus(Status.OK.name());
    		} else {
    			bo.setStatus(Status.ERR.name());
    			bo.setMessage("Product not found");
    		}
            
    	} catch(Exception e) {
    		bo.setStatus(Status.ERR.name());
    		LOGGER.error("Exception while fetching Product:", e);
    	}

        return bo;
    }
    
    @Transactional
    public Page<ProductBO> findByName(String q, Pageable pageable) {
    	Page<Product> page = (q == null || q.isBlank())
                ? productRepo.findAll(pageable)
                : productRepo.findByNameContainingIgnoreCase(q, pageable);
        return page.map(ProductBO::new);
    }
    
    @Transactional
    public CommonResponseBO updateProduct(CreateProductBO bo) {
    	CommonResponseBO responsebo = new CommonResponseBO();
    	try {
            
    		Product p = productRepo.findById(bo.getId()).filter(prod -> prod.getStatus() == ProductEnum.ACTIVE.name())
    			    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    		if(p!= null) {
    			p.setName(bo.getName());
    		    p.setUnit(bo.getUnit());
    		    p.setSellPrice(bo.getSellPrice());
    		    p.setCostPrice(bo.getCostPrice());
    		    p.setIsActive(bo.getIsActive());
    		    p.setReorderLevel(bo.getReorderLevel());
    		    Category category  = categoryRepo.findById(bo.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));;
    		    p.setCategory(category);
    		    productRepo.save(p);
    		    responsebo.setStatus(Status.OK.name());
    			responsebo.setMessage("Product updated");
    		} else {
    			responsebo.setStatus(Status.ERR.name());
    			responsebo.setMessage("Product not found");
    		}
            
    	} catch(Exception e) {
    		responsebo.setStatus(Status.ERR.name());
    		LOGGER.error("Exception while updating Product:", e);
    	}

        return responsebo;
    }
    
    @Transactional
    public CommonResponseBO deleteById(Long id) {
    	CommonResponseBO responsebo = new CommonResponseBO();
    	try {
    		Product p = productRepo.findById(id).filter(prod -> prod.getStatus() == ProductEnum.ACTIVE.name())
    			    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    		if(p!= null) {
    			p.setStatus(ProductEnum.DELETED.name());
    			productRepo.save(p);
    			responsebo.setStatus(Status.OK.name());
    			responsebo.setMessage("Product deleted");
    		} else {
    			responsebo.setStatus(Status.ERR.name());
    			responsebo.setMessage("Product not found");
    		}
    	} catch(Exception e) {
    		responsebo.setStatus(Status.ERR.name());
    		LOGGER.error("Exception while deleting Product:", e);
    	}
    	return responsebo;
    }
}
