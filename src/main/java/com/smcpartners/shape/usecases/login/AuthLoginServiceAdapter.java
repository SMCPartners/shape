package com.smcpartners.shape.usecases.login;

import com.smcpartners.shape.crosscutting.security.exceptions.InactiveUserException;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.gateway.rest.mappedexceptions.ErrorMessagesEnum;
import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;
import com.smcpartners.shape.shared.dto.shape.request.LoginRequestDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.Usecase;
import com.smcpartners.shape.shared.utils.JWTUtils;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. </br
 * <p>
 * <p>
 * Created by johndestefano on 9/14/15.
 * </p>
 * <p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
@RequestScoped
public class AuthLoginServiceAdapter implements AuthLoginService, AuthLoginUsecaseAdapter {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private JWTUtils jwtUtils;

    private AuthLoginUC authLoginUC;

    @PostConstruct
    protected void postConstruct() {
        authLoginUC = new AuthLoginUC(this);
    }

    @Override
    public Response login(LoginRequestDTO loginRequest) throws UseCaseException {
        try {
            UserDTO ue = this.getUser(loginRequest.getUserId(), loginRequest.getPassword());

            // If the user is valid and active return a token
            if (ue != null) {
                if (ue.isActive() == true) {
                    String var = ue.isResetPwd() ? "true" : "false";
                    String token = jwtUtils.generateToken(ue.getId().toUpperCase(), ue.getRole(), true);
                    return Response.status(Response.Status.OK).entity("{\"token\":\"" + token + "\", \"resetRequired\":" + var + "}").header("Authorization", "Bearer " + token).build();
                } else {
                    throw new InactiveUserException("Inactive user.");
                }
            } else {
                ErrorMsgResponse msg = new ErrorMsgResponse(ErrorMessagesEnum.Bad_Credentials.getCode(), Response.Status.UNAUTHORIZED.getStatusCode(),
                        ErrorMessagesEnum.Bad_Credentials.getMsg());
                return Response.status(Response.Status.UNAUTHORIZED).entity(msg).build();
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "login", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    /**
     * Returns true for valid user, false otherwise
     *
     * @param userId
     * @param password
     * @return
     * @throws UseCaseException
     */
    @Override
    public UserDTO getUser(String userId, String password) throws UseCaseException {
        try {
            UserDTO ue = userDAO.validateUser(userId, password);
            return ue;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "login", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
