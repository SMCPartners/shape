package com.smcpartners.shape.usecases.logout;


import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
@Path("/common")
public interface AuthLogoutService {

    @PUT
    @Path("/logout/{userid}")
    @Produces("application/json")
    Response logout(@PathParam("userid") String userId) throws UseCaseException;

}
