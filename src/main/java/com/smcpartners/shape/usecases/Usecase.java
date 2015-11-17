package com.smcpartners.shape.usecases;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/14/15.
 * <p>
 * Changes:<b/>
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Usecase {
}
