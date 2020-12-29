package com.emazon.services.customer.controller;

class CustomerNotFoundException extends RuntimeException{

    CustomerNotFoundException(String id) {
        super("Could not find customer with id : "+ id);
    }
}
