package com.emazon.services.user.exceptions;

public class UsernameAlreadyInUseException extends RuntimeException{
    public UsernameAlreadyInUseException(String username) {
        super(username+" already in use !");
    }
}
