package com.smcpartners.shape.usecases.show_trend_chart_quarterly;

import com.smcpartners.shape.shared.dto.shape.response.TrendChartDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by bryanhokanson on 12/14/15.
 */
@Path("/common")
public interface ShowTrendChartQuarterlyService {

    @GET
    @NoCache
    @Path("/show/trendChartQuarterly/{measureId}/{year}")
    @Produces("application/json")
    List<TrendChartDTO> showTrendChartQuarterly(@PathParam("measureId") int measureId,
                                       @PathParam("year") int year) throws UseCaseException;
}
