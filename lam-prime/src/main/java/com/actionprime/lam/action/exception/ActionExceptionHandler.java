package com.actionprime.lam.action.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles exceptions that occur in the ActionController.
 * It is annotated with @ControllerAdvice, which means that any exceptions that are thrown by the methods in the ActionController will be caught by this class and handled accordingly.
 * The @ExceptionHandler annotation is used to specify which exceptions should be handled by this class.
 * The @ResponseStatus annotation is used to specify the HTTP status code that should be returned to the client in case of an exception.
 * The handleActionNotFoundException method returns a string that contains the error message, while the handleActionNameAlreadyExistsException method returns a string that contains a different error message.
 */
@ControllerAdvice
public class ActionExceptionHandler {

    /**
     * This method handles ActionNotFoundException exceptions, which are thrown when an action with a given ID cannot be found.
     * It returns a string that contains a custom error message.
     * @param e The ActionNotFoundException exception that was thrown
     * @return A string that contains an error message
     */
    @ExceptionHandler(ActionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleActionNotFoundException(ActionNotFoundException e) {
        return e.getMessage();
    }

    /**
     * This method handles ActionNameAlreadyExistsException exceptions, which are thrown when an attempt is made to create an action with a name that already exists.
     * It returns a string that contains a custom error message.
     * @param e The ActionNameAlreadyExistsException exception that was thrown
     * @return A string that contains an error message
     */
    @ExceptionHandler(ActionNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleActionNameAlreadyExistsException(ActionNameAlreadyExistsException e) {
        return e.getMessage();
    }
}
