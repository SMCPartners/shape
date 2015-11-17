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
package com.smcpartners.shape.crosscutting.security.interceptors;

import com.smcpartners.shape.crosscutting.security.ServerUserToken;
import com.smcpartners.shape.crosscutting.security.annotations.AppServerUser;
import com.smcpartners.shape.crosscutting.security.annotations.SecureAccess;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import org.apache.deltaspike.security.api.authorization.Secures;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Context;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * SecurityAuthorizer.java<br/>
 * Responsibilities:<br/>
 * 1. Request scoped component that manages dao to methods through role
 * based security<br/>
 *
 * @author John DeStefano
 * @version 1.0
 * @since May 26, 2013
 */
@RequestScoped
public class SecurityAuthorizer {

    @Context
    private Context ctx;

    /**
     * Constructor
     */
    public SecurityAuthorizer() {
        super();
    }

    @Secures
    @SecureAccess
    public boolean doSecuredCheck(InvocationContext ctx, BeanManager manager,
                                  @AppServerUser ServerUserToken userToken) throws Exception {

        String id = userToken.getUserId();
        SecureAccess secure = getCustomSecurityBindingAnnotation(ctx
                .getMethod());
        SecurityRoleEnum[] allowedRoles = secure.value();
        boolean allowAccess = userInRole(allowedRoles, userToken);

        // If the user has the appropriate role and is logged in then proceed
        // allowAccess = false;
        if (allowAccess == true) {
            String roleAccess = SecurityRoleEnum.REGISTERED.toString();
            String tokenRole = userToken.getRole();
            if (roleAccess.equals(tokenRole)) {
                return true;
            } else {
                if (userToken.getLoggedIn() == true) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private boolean userInRole(SecurityRoleEnum[] roles,
                               ServerUserToken userToken) {
        String userRole = userToken.getRole();

        if (userRole != null) {
            for (SecurityRoleEnum role : roles) {
                if (userRole.equals(role.getName())) {
                    return true;
                }
            }
        }

        // Not in any roles
        return false;

    }

    private SecureAccess getCustomSecurityBindingAnnotation(Method m) {
        // Check method first
        for (Annotation a : m.getAnnotations()) {
            if (a instanceof SecureAccess) {
                return (SecureAccess) a;
            }
        }

        // Check type level producers next
        for (Annotation a : m.getDeclaringClass().getAnnotations()) {
            if (a instanceof SecureAccess) {
                return (SecureAccess) a;
            }
        }

        // Didn't find one, that's a problem
        throw new RuntimeException("@SecureAccess not found on method "
                + m.getName() + " or its class " + m.getClass().getName());
    }

}
