package com.smcpartners.shape.usecases.logout;

import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. </br>
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
@RequestScoped
public class AuthLogoutServiceImpl implements AuthLogoutService {

    @Inject
    private Logger log;

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.REGISTERED})
    public Response logout(String userId) throws UseCaseException {
        try {
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
