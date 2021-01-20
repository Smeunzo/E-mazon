package com.emazon.services.inventory.service;

import com.emazon.services.inventory.dao.RateRepository;
import com.emazon.services.inventory.entity.Rate;
import com.emazon.services.inventory.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository ;

    @Override
    @Transactional
    public Rate addRate(int value, String commentary,String username) {
        Rate rate = new Rate(null,username,value,commentary);
        UtilService.validate(rate);
        return rateRepository.save(rate);
    }

    @Override
    @Transactional
    public Rate addRate(Rate rate) {
        UtilService.validate(rate);
        return rateRepository.save(rate);
    }




}
