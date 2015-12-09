package com.smcpartners.shape.usecases.find_user_by_id;

import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by johndestefano on 9/28/15.
 */
@Path("/admin")
public interface FindUserByIdService {

    /**
     * Find a user
     *
     * @return
     * @throws UseCaseException
     */
    @GET
    @NoCache
    @Path("/user/find/{targetuserid}")
    @Produces("application/json")
    UserDTO findUser(@PathParam("targetuserid") String targetUserId) throws UseCaseException;

}
