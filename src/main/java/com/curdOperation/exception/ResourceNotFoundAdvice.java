package com.curdOperation.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceNotFoundAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String,String> exceptionHandler(ResourceNotFoundException exception){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage", exception.getMessage());
        return errorMap;
    }
}
