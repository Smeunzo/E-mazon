package com.emazon.services.inventory.util;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductRateData {
    private String productName ;
    String username ;
    private int value ;
    private String commentary ;
}
