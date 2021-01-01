package com.emazon.services.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private double price ;

    @NotBlank
    String name ;



}
