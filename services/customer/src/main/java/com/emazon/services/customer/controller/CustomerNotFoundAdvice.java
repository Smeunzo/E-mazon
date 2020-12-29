package com.emazon.services.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@AllArgsConstructor
class CustomerNotFoundAdvice {

    ObjectMapper objectMapper;

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ObjectNode customerNotFoundHandler(CustomerNotFoundException ex){
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", ex.getMessage());
        node.put("status", HttpStatus.NOT_FOUND.value());
        return node;
    }
}
