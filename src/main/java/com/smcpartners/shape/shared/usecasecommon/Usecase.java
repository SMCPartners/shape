package com.smcpartners.shape.shared.usecasecommon;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Responsible:<br/>
 * 1. Annotate a use case with this if required
 * <p>
 * Created by johndestefano on 10/14/15.
 * </p>
 * <p>
 * Changes:<b/>
 * </p>
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
public @interface Usecase {
}
