package com.emazon.services.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class UserCredentials {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password ;

    @ManyToMany(fetch = FetchType.EAGER) //Load User will load user's roles too
    private Collection<Role> rolesOfUser = new ArrayList<>();


}
