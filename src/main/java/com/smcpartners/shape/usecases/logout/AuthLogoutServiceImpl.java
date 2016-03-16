package com.smcpartners.shape.usecases.logout;

import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1.Application logout </br>
 * <p>
 * <p>
 * Created by johndestefano on 9/30/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
//TODO: Seems like and user role should be able to call this?
@RequestScoped
public class AuthLogoutServiceImpl implements AuthLogoutService {

    @Inject
    private Logger log;

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.REGISTERED})
    public Response logout(String userId) throws UseCaseException {
        try {
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "logout", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
