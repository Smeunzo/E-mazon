package com.emazon.services.userinfo.exception;

public class UserInfoNotFoundException extends RuntimeException{

    public UserInfoNotFoundException(String id) {
        super("Could not find customer with id : "+ id);
    }
}
