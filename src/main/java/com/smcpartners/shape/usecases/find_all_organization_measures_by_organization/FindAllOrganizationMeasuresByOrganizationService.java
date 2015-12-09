package com.smcpartners.shape.usecases.find_all_organization_measures_by_organization;

import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.usecases.common.UseCaseException;
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
public interface FindAllOrganizationMeasuresByOrganizationService {

    @GET
    @NoCache
    @Path("/organization_measure/findAllByOrg/{orgId}")
    @Produces("application/json")
    List<OrganizationMeasureDTO> findAllOrganizationMeasuresByOrg(@PathParam("orgId") int orgId) throws UseCaseException;
}
