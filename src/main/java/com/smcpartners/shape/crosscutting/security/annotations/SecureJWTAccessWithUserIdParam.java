package com.smcpartners.shape.crosscutting.security.annotations;

import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import org.apache.deltaspike.security.api.authorization.SecurityBindingType;

import javax.enterprise.util.Nonbinding;
import java.lang.annotation.*;

/**
 * If using this producers to secure method calls the first
 * parameter of the method signature will be treated as the userId
 * of the caller.
 *
 * @see SecureJWTAccessWithUserIdParam
 * <p>
 * Created by johndestefano on 9/14/15.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@SecurityBindingType
public @interface SecureJWTAccessWithUserIdParam {
    @Nonbinding SecurityRoleEnum[] value() default {};
}
