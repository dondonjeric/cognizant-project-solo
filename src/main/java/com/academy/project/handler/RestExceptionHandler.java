package com.academy.project.handler;

import com.academy.project.exception.InvalidInputException;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseMessage> invalidInputException(InvalidInputException ex) throws JsonProcessingException {
        Map<String, List<String>> map = new ObjectMapper().readValue(ex.getMessage(), LinkedHashMap.class);
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Encountered an error!", new ErrorMessages(map)), HttpStatus.BAD_REQUEST);
    }
}
