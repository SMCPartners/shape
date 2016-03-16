package com.smcpartners.shape.crosscutting.security.exceptions;

/**
 * Responsible:</br>
 * 1. Throw for an inactive user requesting access</br
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
public class InactiveUserException extends RuntimeException {

    public InactiveUserException() {
        super();
    }

    public InactiveUserException(String message) {
        super(message);
    }
}
