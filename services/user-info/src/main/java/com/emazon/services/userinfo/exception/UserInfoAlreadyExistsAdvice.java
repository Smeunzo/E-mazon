package com.emazon.services.userinfo.exception;


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
public class UserInfoAlreadyExistsAdvice {
    ObjectMapper objectMapper;

    @ResponseBody @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserInfoAlreadyExistsException.class)
    ObjectNode customerAlreadyExistsHandler(UserInfoAlreadyExistsException ex){
        ObjectNode nodes = objectMapper.createObjectNode();
        nodes.put("error",ex.getMessage());
        nodes.put("status", HttpStatus.BAD_REQUEST.value());

        return nodes;
    }

}
