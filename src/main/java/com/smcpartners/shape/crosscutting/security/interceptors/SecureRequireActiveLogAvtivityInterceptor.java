package com.smcpartners.shape.crosscutting.security.interceptors;


import com.smcpartners.shape.crosscutting.activitylogging.ClickLogQueuer;
import com.smcpartners.shape.crosscutting.activitylogging.dto.ClickLogDTO;
import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.crosscutting.security.exceptions.SecureRequireActiveLogAvtivityException;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.utils.JWTUtils;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/2/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Interceptor
@SecureRequireActiveLogAvtivity
public class SecureRequireActiveLogAvtivityInterceptor {

    @Inject
    private Logger log;

    @Inject
    private HttpServletRequest request;

    @Inject
    private JWTUtils jwtUtils;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    @EJB
    private UserDAO userDAO;

    @EJB
    private ClickLogQueuer clickLogQueuer;

    @AroundInvoke
    public Object runInterceptor(InvocationContext ctx) throws SecureRequireActiveLogAvtivityException {
        try {
            // Get the token from the request scope
            String token = null;
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                String[] authData = authHeader.trim().split("\\s+");
                if (authData != null && authData.length > 1) {
                    token = authData[1].trim();
                } else {
                    throw new Exception("Token not present");
                }
            } else {
                throw new Exception("No authorization header");
            }

            // Get the requester's role
            Map<String, String> tokenValues = jwtUtils.getValues(token);
            String role = tokenValues.get("role");
            String userId = tokenValues.get("userId");

            // Check role
            if (role != null) {
                // Get the allowed roles
                SecureRequireActiveLogAvtivity secure = getCustomSecurityBindingAnnotation(ctx
                        .getMethod());
                SecurityRoleEnum[] allowedRoles = secure.value();
                boolean allowAccess = userInRole(allowedRoles, role);

                // If the user has the appropriate role and is logged in then proceed
                // allowAccess = false;
                if (allowAccess == false) {
                    throw new Exception("User does not have dao.");
                }
            } else {
                throw new Exception("User has no dao");
            }

            // Check for active user
            boolean isActive = (userDAO.isActive(userId)).isValue();
            if (!isActive) {
                throw new Exception("User is not active");
            }

            // Log Activity
            ClickLogDTO clDTO = getClickLogDTO(ctx, userId);
            clickLogQueuer.sendMessage(clDTO);

            // Set request object
            requestScopedUserId.setRequestUserId(userId);
            requestScopedUserId.setSecurityRole(role);

            // Proceed with method invocation
            return ctx.proceed();
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "runInterceptor", e.getMessage(), e);
            throw new SecureRequireActiveLogAvtivityException(e.getMessage());
        }
    }

    private ClickLogDTO getClickLogDTO(InvocationContext ctx, String userId) {
        String eventName = String.format("%s.%s", ctx.getMethod().getDeclaringClass().getCanonicalName(), ctx.getMethod().getName());
        ClickLogDTO clDTO = new ClickLogDTO();
        clDTO.setAdditionalInfo("");
        clDTO.setEvent(eventName);
        clDTO.setEventDt(new Date());
        clDTO.setUserId(userId);
        return clDTO;
    }

    private boolean userInRole(SecurityRoleEnum[] roles,
                               String userRole) {
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

    private SecureRequireActiveLogAvtivity getCustomSecurityBindingAnnotation(Method m) {
        // Check method first
        for (Annotation a : m.getAnnotations()) {
            if (a instanceof SecureRequireActiveLogAvtivity) {
                return (SecureRequireActiveLogAvtivity) a;
            }
        }

        // Check type level producers next
        for (Annotation a : m.getDeclaringClass().getAnnotations()) {
            if (a instanceof SecureRequireActiveLogAvtivity) {
                return (SecureRequireActiveLogAvtivity) a;
            }
        }

        // Didn't find one, that's a problem
        throw new RuntimeException("@SecureAccessJWT not found on method "
                + m.getName() + " or its class " + m.getClass().getName());
    }
}
