package com.smcpartners.shape.crosscutting.security.interceptors;


import com.smcpartners.shape.crosscutting.activitylogging.ClickLogQueuer;
import com.smcpartners.shape.crosscutting.activitylogging.dto.ClickLogDTO;
import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.crosscutting.security.exceptions.SecureRequireActiveLogActivityException;
import com.smcpartners.shape.crosscutting.security.producers.JSONConverter;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.utils.JWTUtils;
import org.apache.deltaspike.core.api.config.ConfigProperty;

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
 * 1. Checks for a valid token</br>
 * 2. Gets the userid and role from the token<br/>
 * 3. Validates the user<br/>
 * 4. Makes sure the user is still active<br/>
 * 5. Tests the use role against the sole allowed by the method being invoked<br/>
 * 6. Logs the event<br/>
 * <p>
 * Created by johndestefano on 10/2/15.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Interceptor
@SecureRequireActiveLogActivity
public class SecureRequireActiveLogActivityInterceptor {

    @Inject
    private Logger log;

    @Inject
    private HttpServletRequest request;

    @Inject
    @ConfigProperty(name = "com.smc.server-core.full_click_logging")
    private boolean fullClickLogging;

    @Inject
    private JWTUtils jwtUtils;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    @Inject
    private JSONConverter jsonConverter;

    @EJB
    private UserDAO userDAO;

    @EJB
    private ClickLogQueuer clickLogQueuer;

    @AroundInvoke
    public Object runInterceptor(InvocationContext ctx) throws SecureRequireActiveLogActivityException {
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
            int orgId = Integer.parseInt(tokenValues.get("orgId"));

            // Check role
            if (role != null) {
                // Get the allowed roles
                SecureRequireActiveLogActivity secure = getCustomSecurityBindingAnnotation(ctx
                        .getMethod());
                SecurityRoleEnum[] allowedRoles = secure.value();
                boolean allowAccess = userInRole(allowedRoles, role);

                // If the user has the appropriate role and is logged in then proceed
                // allowAccess = false;
                if (allowAccess == false) {
                    throw new Exception("You do not have the appropriate role to access this");
                }
            } else {
                throw new Exception("You do not have the appropriate role to access this");
            }

            // Check for active user
            boolean isActive = (userDAO.isActive(userId)).isValue();
            if (!isActive) {
                throw new Exception("You do not have the appropriate role to access this");
            }

            // Set request object
            requestScopedUserId.setRequestUserId(userId);
            requestScopedUserId.setSecurityRole(role);
            requestScopedUserId.setOrgId(orgId);

            // Proceed with method invocation
            if (fullClickLogging) {
                Object retObj = ctx.proceed();

                // Log Activity
                ClickLogDTO clDTO = getClickLogDTO(ctx, userId);
                //TODO: Figure out what to use for request info
                if (retObj != null) {
                    String jsonRet = jsonConverter.getMapper().writeValueAsString(retObj);
                    clDTO.setResponseInfo(jsonRet);
                }
                clickLogQueuer.sendMessage(clDTO);

                return retObj;
            } else {
                // Log Activity
                ClickLogDTO clDTO = getClickLogDTO(ctx, userId);
                clickLogQueuer.sendMessage(clDTO);

                return ctx.proceed();
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "runInterceptor", e.getMessage(), e);
            throw new SecureRequireActiveLogActivityException(e.getMessage());
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

    private SecureRequireActiveLogActivity getCustomSecurityBindingAnnotation(Method m) {
        // Check method first
        for (Annotation a : m.getAnnotations()) {
            if (a instanceof SecureRequireActiveLogActivity) {
                return (SecureRequireActiveLogActivity) a;
            }
        }

        // Check type level producers next
        for (Annotation a : m.getDeclaringClass().getAnnotations()) {
            if (a instanceof SecureRequireActiveLogActivity) {
                return (SecureRequireActiveLogActivity) a;
            }
        }

        // Didn't find one, that's a problem
        throw new RuntimeException("@SecureRequireActiveLogActivity not found on method "
                + m.getName() + " or its class " + m.getClass().getName());
    }
}
