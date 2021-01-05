package com.emazon.services.user.exceptions;

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
public class UsernameAlreadyExistsAdvice {
    private ObjectMapper objectMapper ;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ObjectNode usernameAlreadyInUseHandler(UsernameAlreadyExistsException e){
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error description",e.getMessage());
        objectNode.put("error status ",HttpStatus.BAD_REQUEST.value());
        return objectNode ;
    }
}
