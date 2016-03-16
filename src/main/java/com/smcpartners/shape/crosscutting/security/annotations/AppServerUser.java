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

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * Responsible:</br>
 * 1. Used with a ServerUserToken when using session based security</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface AppServerUser {
}
