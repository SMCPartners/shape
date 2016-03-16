package com.smcpartners.shape.crosscutting.security.exceptions;

import java.rmi.RemoteException;

/**
 * Responsible:</br>
 * 1. Used in conjunction with the RequiresActiveUser producers.</br>
 * 2. This exception is thrown if the method being called does not have the
 * userid as the first parameter.</br>
 * <p>
 * Created by johndestefano on 9/29/15.
 * </p>
 * <p>
 * Changes:</br>
 * </p>
 */
public class IllegalMethodSignatureForRequiesActiveUserException extends RemoteException {
    public IllegalMethodSignatureForRequiesActiveUserException() {
    }
}
