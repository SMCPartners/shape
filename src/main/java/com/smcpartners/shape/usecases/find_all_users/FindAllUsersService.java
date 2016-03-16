package com.smcpartners.shape.usecases.find_all_users;

import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Responsible:</br>
 * 1. Support REST framework </br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Path("/admin")
public interface FindAllUsersService {

    /**
     * Find all users
     *
     * @return
     * @throws UseCaseException
     */
    @GET
    @NoCache
    @Path("/user/findAll")
    @Produces("application/json")
    List<UserDTO> findAllUser() throws UseCaseException;


}
