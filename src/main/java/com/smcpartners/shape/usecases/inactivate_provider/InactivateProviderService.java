package com.smcpartners.shape.usecases.inactivate_provider;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.request.IntEntityIdRequestDTO;
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
@Path("/admin")
public interface InactivateProviderService {

    @POST
    @Path("/provider/inactivate")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO inactivateProvider(IntEntityIdRequestDTO id) throws UseCaseException;
}
