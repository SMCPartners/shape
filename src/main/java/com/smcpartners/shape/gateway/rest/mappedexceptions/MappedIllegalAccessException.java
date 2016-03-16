package com.smcpartners.shape.gateway.rest.mappedexceptions;

import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 3/15/16.
 * <p>
 * Changes:<b/>
 */
public class MappedIllegalAccessException implements ExceptionMapper<IllegalAccessException> {
    @Override
    public Response toResponse(IllegalAccessException exception) {
        ErrorMsgResponse msg = new ErrorMsgResponse(ErrorMessagesEnum.IllegalAccess_ERR.getCode(), Response.Status.UNAUTHORIZED.getStatusCode(),
                ErrorMessagesEnum.IllegalAccess_ERR.getMsg());
        return Response.status(Response.Status.UNAUTHORIZED).entity(msg).build();
    }

}
