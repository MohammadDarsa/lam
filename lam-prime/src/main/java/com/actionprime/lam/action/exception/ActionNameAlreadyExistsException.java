package com.actionprime.lam.action.exception;

/**
 * This exception is thrown when an attempt is made to create an action with an existing name.
 *
 * @author ActionPrime
 */
public class ActionNameAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new ActionNameAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public ActionNameAlreadyExistsException(String message) {
        super(message);
    }

}
