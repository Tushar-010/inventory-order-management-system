package com.ioms.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ioms.api.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);
}
