package com.emazon.services.inventory.exception;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String message){
        super(message+" already exists");
    }
}
