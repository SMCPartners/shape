package com.smcpartners.shape.usecases.login;


import com.smcpartners.shape.shared.dto.shape.request.LoginRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
public interface AuthLoginService {

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    //@Consumes("application/x-www-form-urlencoded")
    Response login(LoginRequestDTO loginRequest) throws UseCaseException;
}
