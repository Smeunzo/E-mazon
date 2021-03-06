package com.emazon.services.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Positive
    private double price ;

    @NotBlank
    private String name ;

    @PositiveOrZero
    private int stock ;

    private String description ;

}
