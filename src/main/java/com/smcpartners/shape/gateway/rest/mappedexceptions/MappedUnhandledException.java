package com.smcpartners.shape.gateway.rest.mappedexceptions;

import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;
import org.jboss.resteasy.spi.UnhandledException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
@Provider
public class MappedUnhandledException implements ExceptionMapper<UnhandledException> {

    @Override
    public Response toResponse(UnhandledException exception) {
        ErrorMsgResponse msg = new ErrorMsgResponse(ErrorMessagesEnum.SecureRequireActiveLogAvtivity_ERR.getCode(), Response.Status.UNAUTHORIZED.getStatusCode(),
                exception.getMessage());
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(msg)
                .build();
    }
}
