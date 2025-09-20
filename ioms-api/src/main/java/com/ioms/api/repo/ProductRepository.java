package com.ioms.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ioms.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
}
