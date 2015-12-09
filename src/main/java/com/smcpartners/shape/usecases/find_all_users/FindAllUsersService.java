package com.smcpartners.shape.usecases.find_all_users;

import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by johndestefano on 9/28/15.
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
