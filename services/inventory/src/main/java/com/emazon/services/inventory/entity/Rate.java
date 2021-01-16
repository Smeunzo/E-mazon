package com.emazon.services.inventory.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Pattern(regexp = "[1-5]")
    private int rateValue ;

    @Pattern(regexp = "[A-Za-z0-9]{1,60}")
    private String commentary ;

}
