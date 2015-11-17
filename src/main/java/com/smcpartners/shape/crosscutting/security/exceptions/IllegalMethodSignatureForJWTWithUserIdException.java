package com.smcpartners.shape.crosscutting.security.exceptions;

/**
 * Responsible:<br/>
 * 1. Exception is throw when a method secured with the JWTWithUserId producers
 * does not have the userId as the first parameter.<br/>
 * <p>
 * Created by johndestefano on 9/18/15.
 * <p>
 * Changes:<b/>
 */
public class IllegalMethodSignatureForJWTWithUserIdException extends SecurityException {
    public IllegalMethodSignatureForJWTWithUserIdException() {
    }
}
