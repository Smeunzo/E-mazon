package com.emazon.services.inventory.dao;

import com.emazon.services.inventory.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate,Long> {
    Rate findRateById(Long Id);

}
