package com.smcpartners.shape.usecases.inactivate_user;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by johndestefano on 9/28/15.
 */
@Path("/admin")
public interface InactivateUserService {
    /**
     * Inactivate the user
     *
     * @throws UseCaseException
     */
    @PUT
    @Path("/inactivate/{targetuserid}")
    @Produces("application/json")
    BooleanValueDTO inactivateUser(@PathParam("targetuserid") String targetUserId) throws UseCaseException;
}
