package com.smcpartners.shape.usecases.add_measure;

import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

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
public interface AddMeasureService {

    @POST
    @Path("/measure/add")
    @Produces("application/json")
    @Consumes("application/json")
    IntEntityResponseDTO addMeasure(MeasureDTO org) throws UseCaseException;
}
