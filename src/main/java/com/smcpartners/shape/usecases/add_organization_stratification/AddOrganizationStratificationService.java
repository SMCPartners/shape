package com.smcpartners.shape.usecases.add_organization_stratification;

import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Responsible:<br/>
 * 1. Support REST framework
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@Path("/common")
public interface AddOrganizationStratificationService {

    @POST
    @Path("/organization_stratification/add")
    @Produces("application/json")
    @Consumes("application/json")
    IntEntityResponseDTO addOrganizationStratification(OrganizationStratificationDTO org) throws UseCaseException;
}
