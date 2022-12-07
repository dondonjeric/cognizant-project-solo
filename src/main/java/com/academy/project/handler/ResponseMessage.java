package com.academy.project.handler;


import lombok.Data;

@Data
public class ResponseMessage {

    private int code;
    private String message;
    private ErrorMessages error;

    public ResponseMessage(int code, String message, ErrorMessages error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }
}
