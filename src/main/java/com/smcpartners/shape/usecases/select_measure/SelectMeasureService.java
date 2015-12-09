package com.smcpartners.shape.usecases.select_measure;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.request.IntEntityIdRequestDTO;
import com.smcpartners.shape.usecases.common.UseCaseException;

import javax.ws.rs.*;

/**
 * Created by johndestefano on 9/28/15.
 */
@Path("/admin")
public interface SelectMeasureService {
    /**
     * Inactivate the user
     *
     * @throws UseCaseException
     */
    @POST
    @Path("/measure/select")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO selectMeasure(IntEntityIdRequestDTO id) throws UseCaseException;
}
