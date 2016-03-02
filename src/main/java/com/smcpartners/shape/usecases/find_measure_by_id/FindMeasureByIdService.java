package com.smcpartners.shape.usecases.find_measure_by_id;

import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by bryanhokanson on 3/2/16.
 */
@Path("/common")
public interface FindMeasureByIdService {

    /**
     * Find a user
     *
     * @return
     * @throws UseCaseException
     */
    @GET
    @NoCache
    @Path("/find/measure_by_id/{measureId}")
    @Produces("application/json")
    MeasureDTO findMeasureById(@PathParam("measureId") int measureId) throws UseCaseException;

}
