package com.smcpartners.shape.crosscutting.security.annotations;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Responsible:<br/>
 * 1. Used with the LogLogin interceptor<br/>
 * <p>
 * Created by johndestefano on 3/16/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
@InterceptorBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface LogLogin {
}