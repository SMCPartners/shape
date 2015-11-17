package com.smcpartners.shape.gateway.rest.mappedexceptions;

import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;
import org.apache.deltaspike.security.api.authorization.AccessDeniedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Responsible:<br/>
 * 1. Maps a deltaspike AccessDeniedException to this exception so an
 * appropriate response can be returned to REST clients.<br/>
 * <p>
 * Created by johndestefano on 9/14/15.
 * <p>
 * Changes:<b/>
 */
@Provider
public class MappedAccessDeniedException implements ExceptionMapper<AccessDeniedException> {
    @Override
    public Response toResponse(AccessDeniedException exception) {
        ErrorMsgResponse msg = new ErrorMsgResponse(ErrorMessagesEnum.Access_ERR.getCode(), Response.Status.UNAUTHORIZED.getStatusCode(),
                ErrorMessagesEnum.Access_ERR.getMsg());
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(msg)
                .build();
    }
}
