package com.smcpartners.shape.usecases.select_measure;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.request.IntEntityIdRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.*;

/**
 * Responsible:</br>
 * 1.  Support REST framework</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
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
