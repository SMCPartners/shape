package com.smcpartners.shape.usecases.find_all_measures;

import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.usecases.common.UseCaseException;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@Path("/common")
public interface FindAllMeasuresService {

    @GET
    @NoCache
    @Path("/measure/findAll")
    @Produces("application/json")
    List<MeasureDTO> findAllMeasures() throws UseCaseException;
}
