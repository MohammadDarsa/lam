package com.actionprime.lam.action.exception;

/**
 * This exception is thrown when an action cannot be found.
 *
 * @author ActionPrime
 */
public class ActionNotFoundException extends RuntimeException {

    /**
     * Constructs a new ActionNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ActionNotFoundException(String message) {
        super(message);
    }

}
