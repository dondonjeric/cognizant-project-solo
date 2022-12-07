package com.academy.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends Exception{

    public InvalidInputException(String message){
        super(message);
    }
}
