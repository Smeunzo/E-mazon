package com.emazon.services.userinfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
