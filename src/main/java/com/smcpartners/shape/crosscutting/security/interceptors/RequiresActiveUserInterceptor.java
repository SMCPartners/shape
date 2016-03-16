package com.smcpartners.shape.crosscutting.security.interceptors;

import com.smcpartners.shape.crosscutting.security.annotations.RequiresActiveUser;
import com.smcpartners.shape.crosscutting.security.exceptions.IllegalMethodSignatureForRequiesActiveUserException;
import com.smcpartners.shape.crosscutting.security.exceptions.InactiveUserException;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Responsible:</br>
 * 1. Check that user is logged in.</br>
 * <p>
 * Created by johndestefano on 9/29/15.
 * </p>
 * <p>
 * Changes:</br>
 * </p>
 */
@Interceptor
@RequiresActiveUser
public class RequiresActiveUserInterceptor {

    @EJB
    private UserDAO userDAO;

    public RequiresActiveUserInterceptor() {
    }

    /**
     * Use the first parameter fo the method as the user id. Check that
     * the user is active using that as the unique user key.
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object checkUserActive(InvocationContext ctx) throws Exception {
        // Must have a userId parameter in method being invoked
        String callerUserId = null;
        Object[] params = ctx.getParameters();
        if (params != null && params.length > 0) {
            callerUserId = (String) params[0];
            if (callerUserId == null || callerUserId.length() == 0) {
                throw new IllegalMethodSignatureForRequiesActiveUserException();
            }
        } else {
            throw new IllegalMethodSignatureForRequiesActiveUserException();
        }

        // Check that user is active
        boolean isActive = (userDAO.isActive(callerUserId)).isValue();
        if (isActive) {
            return ctx.proceed();
        } else {
            throw new InactiveUserException();
        }
    }
}
