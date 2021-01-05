package com.emazon.services.user.exceptions;

public class RoleAlreadyExistsException extends RuntimeException{
    public RoleAlreadyExistsException(String rolesName) {
        super(rolesName+" already exist !");
    }
}