package com.smcpartners.shape.crosscutting.security.exceptions;

/**
 * Responsible:</br>
 * 1. Throw by the SecureRequireActiveLogActivityInterceptor when an error occurs.</br>
 * <p>
 * Created by johndestefano on 10/2/15.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public class SecureRequireActiveLogActivityException extends RuntimeException {

    public SecureRequireActiveLogActivityException(String message) {
        super(message);
    }
}
