package com.smcpartners.shape.usecases.add_provider;

import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.usecases.common.UseCaseException;

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
public interface AddProviderService {

    @POST
    @Path("/provider/add")
    @Produces("application/json")
    @Consumes("application/json")
    IntEntityResponseDTO addProvider(ProviderDTO prov) throws UseCaseException;
}
