package com.smcpartners.shape.gateway.rest.mappedexceptions;


import com.smcpartners.shape.crosscutting.activitylogging.exceptions.ActivityLogException;
import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 9/30/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Provider
public class MappedActivityLogException implements ExceptionMapper<ActivityLogException> {
    @Override
    public Response toResponse(ActivityLogException exception) {
        ErrorMsgResponse msg = new ErrorMsgResponse(ErrorMessagesEnum.ActivityLog_ERR.getCode(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                ErrorMessagesEnum.ActivityLog_ERR.getMsg());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build();
    }
}
