package com.emazon.services.inventory.service;

import com.emazon.services.inventory.entity.Rate;

public interface RateService {

    Rate addRate(int value, String commentary,String username);
    Rate addRate(Rate rate);

}
