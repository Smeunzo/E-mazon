package com.emazon.services.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double price ;
    String name ;

    @ManyToOne(cascade = CascadeType.ALL)
    private Inventory inventory ;
}
