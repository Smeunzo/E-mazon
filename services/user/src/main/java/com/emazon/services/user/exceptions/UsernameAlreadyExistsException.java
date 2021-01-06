package com.emazon.services.user.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username) {
        super(username+" already in use !");
    }
}
