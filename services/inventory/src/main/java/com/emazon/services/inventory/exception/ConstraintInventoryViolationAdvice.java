package com.emazon.services.inventory.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@AllArgsConstructor
public class ConstraintInventoryViolationAdvice {
    
    private final ObjectMapper objectMapper ;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ObjectNode constraintViolationHandler(ConstraintViolationException e){
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error",e.getMessage());
        objectNode.put("status",HttpStatus.BAD_REQUEST.value());
        return objectNode ;
    }
    
}
