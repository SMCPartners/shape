package com.smcpartners.shape.usecases.show_app_hist_demographic;

import com.smcpartners.shape.shared.dto.shape.response.AppHistDemographicsDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

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
@Path("/common")
public interface ShowAppHistDemographicService {

    @GET
    @NoCache
    @Path("/show/appHistDemographic/{orgId}/{measureId}/{year}")
    @Produces("application/json")
    List<AppHistDemographicsDTO> showAppHistDemographic(@PathParam("orgId") int orgId,
                                                        @PathParam("measureId") int measureId,
                                                        @PathParam("year") int year) throws UseCaseException;
}
