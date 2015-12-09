package com.smcpartners.shape.usecases.find_all_organization_stratifications;

import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.usecases.common.UseCaseException;
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
@Path("/common")
public interface FindAllOrganizationStratificationsService {

    @GET
    @NoCache
    @Path("/organization_stratification/findAll")
    @Produces("application/json")
    List<OrganizationStratificationDTO> findAllOrganizationStratifications() throws UseCaseException;
}
