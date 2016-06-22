package com.smcpartners.shape.usecases.add_measure_upload;

import com.smcpartners.shape.shared.dto.shape.response.FileUploadResponseDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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
public interface AddMeasureUploadService {

    @POST
    @Path("/measure/add/upload")
    @Produces("application/json")
    @Consumes("multipart/form-data")
    FileUploadResponseDTO addMeasureUpload(MultipartFormDataInput input) throws UseCaseException;
}
