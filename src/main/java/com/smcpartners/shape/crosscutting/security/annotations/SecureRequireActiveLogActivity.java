package com.smcpartners.shape.crosscutting.security.annotations;


import com.smcpartners.shape.shared.constants.SecurityRoleEnum;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * Created by johndestefano on 10/2/15.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@InterceptorBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface SecureRequireActiveLogActivity {
    @Nonbinding SecurityRoleEnum[] value() default {};
}
