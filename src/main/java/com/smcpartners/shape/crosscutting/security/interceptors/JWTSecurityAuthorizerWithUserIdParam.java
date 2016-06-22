package com.smcpartners.shape.crosscutting.security.interceptors;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureJWTAccessWithUserIdParam;
import com.smcpartners.shape.crosscutting.security.exceptions.IllegalMethodSignatureForJWTWithUserIdException;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.utils.JWTUtils;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import org.apache.deltaspike.security.api.authorization.Secures;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Looks at the first parameter of the invoked method for the
 * userId of the invoker. This will be compared to the userId
 * value in the JWT token. If they don't match an error will be thrown.
 * <p>
 * Created by johndestefano on 9/14/15.
 * </p>
 */
@RequestScoped
public class JWTSecurityAuthorizerWithUserIdParam {

    @Inject
    private Logger log;

    @Inject
    private HttpServletRequest request;

    @EJB
    private UserDAO userDAO;

    @Inject
    private JWTUtils jwtUtils;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    /**
     * Constructor
     */
    public JWTSecurityAuthorizerWithUserIdParam() {
        super();
    }

    //TODO: Explain how this works

    /**
     * @param ctx
     * @param manager
     * @return
     */
    @Secures
    @SecureJWTAccessWithUserIdParam
    public boolean doSecuredCheck(InvocationContext ctx, BeanManager manager) {
        try {
            // Must have a userId parameter in method being invoked
            String callerUserId = null;
            Object[] params = ctx.getParameters();
            if (params != null && params.length > 0) {
                callerUserId = (String) params[0];
                if (callerUserId == null || callerUserId.length() == 0) {
                    throw new IllegalMethodSignatureForJWTWithUserIdException();
                }
            } else {
                throw new IllegalMethodSignatureForJWTWithUserIdException();
            }

            // Get the token from the request scope
            String token = null;
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                String[] authData = authHeader.trim().split("\\s+");
                if (authData != null && authData.length > 1) {
                    token = authData[1].trim();
                } else {
                    return false;
                }
            } else {
                return false;
            }

            // Get the requestor's role
            Map<String, String> tokenValues = jwtUtils.getValues(token);
            String role = tokenValues.get("role");
            String userId = tokenValues.get("userId");
            int orgId = Integer.parseInt(tokenValues.get("orgId"));

            // Check role
            if (role != null) {
                // Get the allowed roles
                SecureJWTAccessWithUserIdParam secure = getCustomSecurityBindingAnnotation(ctx
                        .getMethod());
                SecurityRoleEnum[] allowedRoles = secure.value();
                boolean allowAccess = userInRole(allowedRoles, role);

                // If the user has the appropriate role and is logged in then proceed
                // allowAccess = false;
                if (allowAccess == false) {
                    return false;
                }
            } else {
                return false;
            }

            // Check for active user
            UserDTO userDTO = userDAO.findById(userId);
            if (!userDTO.isActive()) {
                throw new Exception("You do not have the appropriate role to access this");
            }

            // Check the users role in the token is the same as the current role
            if (!userDTO.getRole().equalsIgnoreCase(role)) {
                throw new Exception("User role is not the same as the role in the identity you are using. You must login again.");
            }

            // Check user id
            // Check that userId matches
            if (userId == null || userId.length() == 0 || !userId.equalsIgnoreCase(callerUserId)) {
                return false;
            }

            // Set request object
            requestScopedUserId.setRequestUserId(userId);
            requestScopedUserId.setSecurityRole(role);
            requestScopedUserId.setOrgId(orgId);

            // Passed security check
            return true;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "doSecuredCheck", e.getMessage(), e);
            return false;
        }
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

    private SecureJWTAccessWithUserIdParam getCustomSecurityBindingAnnotation(Method m) {
        // Check method first
        for (Annotation a : m.getAnnotations()) {
            if (a instanceof SecureJWTAccessWithUserIdParam) {
                return (SecureJWTAccessWithUserIdParam) a;
            }
        }

        // Check type level producers next
        for (Annotation a : m.getDeclaringClass().getAnnotations()) {
            if (a instanceof SecureJWTAccessWithUserIdParam) {
                return (SecureJWTAccessWithUserIdParam) a;
            }
        }

        // Didn't find one, that's a problem
        throw new RuntimeException("@SecureAccessJWT not found on method "
                + m.getName() + " or its class " + m.getClass().getName());
    }
}
