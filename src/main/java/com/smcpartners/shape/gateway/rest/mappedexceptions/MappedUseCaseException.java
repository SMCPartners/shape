package com.smcpartners.shape.gateway.rest.mappedexceptions;

import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by johndestefano on 9/28/15.
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
