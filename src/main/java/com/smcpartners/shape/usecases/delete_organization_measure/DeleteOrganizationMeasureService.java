package com.smcpartners.shape.usecases.delete_organization_measure;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by bryanhokanson on 12/21/15.
 */
@Path("/admin")
public interface DeleteOrganizationMeasureService {

    @POST
    @Path("/organization_measure/delete")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO deleteOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException;
}
