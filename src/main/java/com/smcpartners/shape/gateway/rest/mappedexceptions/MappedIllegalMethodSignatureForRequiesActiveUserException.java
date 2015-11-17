package com.smcpartners.shape.gateway.rest.mappedexceptions;

import com.smcpartners.shape.crosscutting.security.exceptions.IllegalMethodSignatureForRequiesActiveUserException;
import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Responsible:</br>
 * 1. </br
 * <p>
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
@Provider
public class MappedIllegalMethodSignatureForRequiesActiveUserException implements ExceptionMapper<IllegalMethodSignatureForRequiesActiveUserException> {
    @Override
    public Response toResponse(IllegalMethodSignatureForRequiesActiveUserException exception) {
        ErrorMsgResponse msg = new ErrorMsgResponse(ErrorMessagesEnum.IllegalMethodSignatureRequiresActiveUser_ERR.getCode(), Response.Status.UNAUTHORIZED.getStatusCode(),
                ErrorMessagesEnum.IllegalMethodSignatureRequiresActiveUser_ERR.getMsg());
        return Response.status(Response.Status.UNAUTHORIZED).entity(msg).build();
    }
}
