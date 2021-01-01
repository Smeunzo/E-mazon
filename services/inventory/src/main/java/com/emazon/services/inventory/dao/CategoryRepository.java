package com.emazon.services.inventory.dao;

import com.emazon.services.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Inventory,Long> {
}
