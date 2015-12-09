package com.smcpartners.shape.usecases.find_all_organization_stratifications_by_organization;

import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
public interface FindAllOrganizationStratificationsByOrganizationService {

    @GET
    @NoCache
    @Path("/organization_stratification/findAllByOrg/{orgId}")
    @Produces("application/json")
    List<OrganizationStratificationDTO> findAllOrganizationStratificationsByOrg(@PathParam("orgId") int orgId) throws UseCaseException;
}
