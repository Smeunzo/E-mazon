package com.emazon.services.inventory.service.implementations;

import com.emazon.services.inventory.dao.RateRepository;
import com.emazon.services.inventory.entity.Rate;
import com.emazon.services.inventory.service.interfaces.RateService;
import com.emazon.services.inventory.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository ;
    
    @Override
    @Transactional
    public Rate addRate(Rate rate) {
        UtilService.validate(rate);
        return rateRepository.save(rate);
    }


}
