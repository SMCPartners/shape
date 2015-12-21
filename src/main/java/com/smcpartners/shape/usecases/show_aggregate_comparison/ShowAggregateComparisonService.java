package com.smcpartners.shape.usecases.show_aggregate_comparison;

import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by bryanhokanson on 12/18/15.
 */
@Path("/common")
public interface ShowAggregateComparisonService {

    @GET
    @NoCache
    @Path("/show/trendChartYearly/{measureId}/{year}")
    @Produces("application/json")
    List<List<Object>> showTrendChartYearly(@PathParam("measureId") int measureId,
                                             @PathParam("year") int year) throws UseCaseException;
}
