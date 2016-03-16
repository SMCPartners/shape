/*******************************************************************************
 * Copyright (c) 2013 Pronoia Health LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p>
 * Contributors:
 * Pronoia Health LLC - initial API and implementation
 *******************************************************************************/
package com.smcpartners.shape.crosscutting.security.annotations;

import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import org.apache.deltaspike.security.api.authorization.SecurityBindingType;

import javax.enterprise.util.Nonbinding;
import java.lang.annotation.*;

/**
 * Responsible:</br>
 * 1. Annotation used as part of the security archeticture</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@SecurityBindingType
public @interface SecureAccess {
    @Nonbinding SecurityRoleEnum[] value() default {};
}
