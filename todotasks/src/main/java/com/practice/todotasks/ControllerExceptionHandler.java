package com.practice.todotasks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final String NOT_FOUND_MESSAGE = "Task not found for the number.";

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleIndexOutOfBoundsException (Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE + " Handled by global handler");
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}
