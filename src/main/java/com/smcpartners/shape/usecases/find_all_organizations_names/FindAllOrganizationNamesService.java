package com.smcpartners.shape.usecases.find_all_organizations_names;

import com.smcpartners.shape.shared.dto.shape.response.OrganizationNameResponseDTO;
import com.smcpartners.shape.usecases.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@Path("/admin")
public interface FindAllOrganizationNamesService {

    @GET
    @NoCache
    @Path("/organization/findNames")
    @Produces("application/json")
    List<OrganizationNameResponseDTO> findAllOrganizationNames() throws UseCaseException;
}
