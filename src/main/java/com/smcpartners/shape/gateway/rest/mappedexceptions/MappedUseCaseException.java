package com.smcpartners.shape.gateway.rest.mappedexceptions;

import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Responsible:</br>
 * 1.Maps a use case excpetion. </br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Provider
public class MappedUseCaseException implements ExceptionMapper<UseCaseException> {
    @Override
    public Response toResponse(UseCaseException exception) {
        ErrorMsgResponse msg = new ErrorMsgResponse(99, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(msg)
                .build();
    }
}
