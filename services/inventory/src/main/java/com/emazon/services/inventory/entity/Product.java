package com.emazon.services.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {

    public Product(@Positive double price, @NotBlank String name, @PositiveOrZero int stock,String description) {
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.description = description;
    }
    public Product(@Positive double price, @NotBlank String name, @PositiveOrZero int stock,String description,String imageUrl) {
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;

    }


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Positive
    private double price ;

    @NotBlank
    private String name ;

    @PositiveOrZero
    private int stock ;

    private String imageUrl = "https://static.boutique.orange.fr/media-cms/mediatheque/318x450-vue-1-175380.png";

    private String description ;

}
