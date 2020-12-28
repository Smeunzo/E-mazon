package com.emazon.services.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(fraction = 0, integer = 3)
    @Positive
    private int streetNumber;

    @Size(min = 5, max = 40)
    @NotBlank
    private String streetName;

    private String locality;

    @Pattern(regexp = "^[0-9]{5}$")
    @NotBlank
    private String zipCode;

    @Size(min = 3, max = 40)
    @NotBlank
    private String city;

    @Size(min = 5, max = 15)
    @NotBlank
    private String country;

}
