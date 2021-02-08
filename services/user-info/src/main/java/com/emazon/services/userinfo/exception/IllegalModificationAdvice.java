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
class IllegalModificationAdvice {

    ObjectMapper objectMapper;

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalModificationException.class)
    @ResponseBody
    ObjectNode illegalModificationHandler(IllegalModificationException ex){
        ObjectNode nodes = objectMapper.createObjectNode();
        nodes.put("error", ex.getMessage() );
        nodes.put("status", HttpStatus.FORBIDDEN.value());

        return nodes;
    }
}
