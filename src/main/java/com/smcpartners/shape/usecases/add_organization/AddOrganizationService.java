package com.smcpartners.shape.usecases.add_organization;

import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@Path("admin")
public interface AddOrganizationService {

    @POST
    @Path("/organization/add")
    @Produces("application/json")
    @Consumes("application/json")
    IntEntityResponseDTO addOrganization(OrganizationDTO org) throws UseCaseException;
}
