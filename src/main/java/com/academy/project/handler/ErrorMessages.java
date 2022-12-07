package com.academy.project.handler;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ErrorMessages {

    private Map<String, List<String>> message;

    public ErrorMessages(Map<String, List<String>> message){
        this.message = message;
    }

    public Map<String, List<String>> getMessage() {
        return message;
    }
}
