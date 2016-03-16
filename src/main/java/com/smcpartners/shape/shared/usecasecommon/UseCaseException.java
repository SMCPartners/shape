package com.smcpartners.shape.shared.usecasecommon;

/**
 * Responsible:</br>
 * 1. Thrown by a use case on an exception</br
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
public class UseCaseException extends RuntimeException {

    public UseCaseException(String message) {
        super(message);
    }

    public UseCaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
