package com.smcpartners.shape.usecases.get_measure_years;

import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by bryanhokanson on 2/26/16.
 */
@Path("/common")
public interface GetMeasureYearsService {

    /**
     * Find a user
     *
     * @return
     * @throws UseCaseException
     */
    @GET
    @NoCache
    @Path("/get/measure_years/{orgId}/{measureId}")
    @Produces("application/json")
    List<Integer> getMeasureYears(@PathParam("orgId") int orgId, @PathParam("measureId") int measureId) throws UseCaseException;

}
