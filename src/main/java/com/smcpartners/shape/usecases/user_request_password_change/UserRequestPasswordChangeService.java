package com.smcpartners.shape.usecases.user_request_password_change;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.request.UserPasswordResetRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by bhokanson on 12/3/2015.
 */
@Path("/common")
public interface UserRequestPasswordChangeService {

    @POST
    @Path("/password_change")
    @Produces("application/json")
    @Consumes("application/json")
    BooleanValueDTO requestPasswordChange(UserPasswordResetRequestDTO pwdResetReq) throws UseCaseException;

}