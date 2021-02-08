package com.emazon.services.userinfo.exception;

public class UserInfoAlreadyExistsException extends RuntimeException {

    public UserInfoAlreadyExistsException(String email){
        super("Customer with email : "+email+" already exists");
    }
}
