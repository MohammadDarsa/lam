package com.actionprime.lam.action.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ActionExceptionHandler {

    @ExceptionHandler(ActionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleActionNotFoundException(ActionNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ActionNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleActionNameAlreadyExistsException(ActionNameAlreadyExistsException e) {
        return e.getMessage();
    }
}
