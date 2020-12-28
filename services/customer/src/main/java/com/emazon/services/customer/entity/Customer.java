package com.emazon.services.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String customerId;

    @Size(min = 3, max = 20)
    @NotBlank
    private String firstName;

    @Size(max = 20)
    private String middleName;

    @Size(min = 3, max = 20)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Past
    @NotNull
    private LocalDate birthdate;

    @OneToOne
    private Address address;

}
