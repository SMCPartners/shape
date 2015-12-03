package com.smcpartners.shape.usecases.change_password;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.CreateUserResponseDTO;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by bhokanson on 12/3/2015.
 */
@Path("/common")
public interface ChangePasswordService {

    @POST
    @Path("/changepassword")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO changeUserPassword(CreateUserResponseDTO user) throws UseCaseException;

}