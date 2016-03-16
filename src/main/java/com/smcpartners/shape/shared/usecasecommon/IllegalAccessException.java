package com.smcpartners.shape.shared.usecasecommon;

/**
 * Responsible:<br/>
 * 1. Thrown when some type of illegal access takes place, like a request
 * for data that the requesting user, as identofoed by the JWT token,
 * does not have access to.
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * Changes:<b/>
 */
public class IllegalAccessException extends Exception {

    public IllegalAccessException() {
    }

    public IllegalAccessException(String message) {
        super(message);
    }

    public IllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
