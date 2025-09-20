package com.ioms.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ioms.api.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> { }
