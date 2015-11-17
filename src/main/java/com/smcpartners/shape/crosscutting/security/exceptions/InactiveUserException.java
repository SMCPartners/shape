package com.smcpartners.shape.crosscutting.security.exceptions;

/**
 * Responsible:</br>
 * 1. </br
 * <p>
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
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
