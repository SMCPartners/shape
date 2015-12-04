package com.smcpartners.shape.usecases.find_all_providers;

import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.usecases.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by bhokanson on 12/4/2015.
 */
@Path("/common")
public interface FindAllProvidersService {

    @GET
    @NoCache
    @Path("/provider/findAll")
    @Produces("application/json")
    List<ProviderDTO> findAllProviders() throws UseCaseException;
}
