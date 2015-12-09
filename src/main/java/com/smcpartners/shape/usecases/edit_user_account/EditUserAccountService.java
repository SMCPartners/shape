package com.smcpartners.shape.usecases.edit_user_account;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by bhokanson on 12/3/2015.
 */
@Path("/common")
public interface EditUserAccountService {

    @POST
    @Path("/account/edit")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO editUserAccount(UserDTO user) throws UseCaseException;
}