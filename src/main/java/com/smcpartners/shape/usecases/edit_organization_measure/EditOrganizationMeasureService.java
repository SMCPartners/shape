package com.smcpartners.shape.usecases.edit_organization_measure;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
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
public interface EditOrganizationMeasureService {

    @POST
    @Path("/organization_measure/edit")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO editOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException;
}
