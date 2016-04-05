package com.smcpartners.shape.crosscutting.security.interceptors;

import com.smcpartners.shape.crosscutting.activitylogging.ClickLogQueuer;
import com.smcpartners.shape.crosscutting.activitylogging.dto.ClickLogDTO;
import com.smcpartners.shape.crosscutting.security.annotations.LogLogin;
import com.smcpartners.shape.shared.dto.shape.request.LoginRequestDTO;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. Used to record logins<br/>
 * <p>
 * Created by johndestefano on 3/16/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
@Interceptor
@LogLogin
public class LogLoginInterceptor {
    @Inject
    private Logger log;

    @EJB
    private ClickLogQueuer clickLogQueuer;

    /**
     * Record userid and outcome of login attempt
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object runInterceptor(InvocationContext ctx) throws Exception {
        // Get used id
        Object[] params = ctx.getParameters();
        LoginRequestDTO dto = (LoginRequestDTO) params[0];
        String userId = dto.getUserId();

        // Run login
        Object retObj = ctx.proceed();

        // Get response
        String respStr = null;
        if (retObj instanceof Response) {
            Response response = (Response) retObj;
            respStr = "" + response.getStatus();
        } else if (retObj instanceof Exception) {
            Exception e = (Exception) retObj;
            respStr = e.getMessage();
        }

        // Record attempt
        String eventName = String.format("%s.%s", ctx.getMethod().getDeclaringClass().getCanonicalName(), ctx.getMethod().getName());
        ClickLogDTO clDTO = new ClickLogDTO();
        clDTO.setAdditionalInfo("");
        clDTO.setEvent(eventName);
        clDTO.setEventDt(new Date());
        clDTO.setUserId(userId);
        clDTO.setResponseInfo(respStr);
        clickLogQueuer.sendMessage(clDTO);

        // Return
        return retObj;
    }
}
