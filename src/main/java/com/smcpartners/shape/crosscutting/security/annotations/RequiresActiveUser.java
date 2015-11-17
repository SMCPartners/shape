package com.smcpartners.shape.crosscutting.security.annotations;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Responsible:</br>
 * 1. Used to run the RequiresActiveUserInterceptor.</br>
 * 2. Only active users get dao</br>
 * 3. First parameter of the invocation context is treated as the user id. The
 * producers does not enforce this as the type is necessary to use on the interceptor
 * class declaration.</br>
 * <p>
 * <p>
 * Created by johndestefano on 9/29/15.
 * </p>
 * <p>
 * Changes: </br>
 * </p>
 */
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RequiresActiveUser {
}
