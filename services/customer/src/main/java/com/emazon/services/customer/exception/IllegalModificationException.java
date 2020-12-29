package com.emazon.services.customer.exception;

public class IllegalModificationException extends RuntimeException{

    public IllegalModificationException( String message ){
        super(message);
    }
}
