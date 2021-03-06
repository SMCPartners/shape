package com.smcpartners.shape.usecases.login;

import com.smcpartners.shape.crosscutting.security.exceptions.InactiveUserException;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.gateway.rest.mappedexceptions.ErrorMessagesEnum;
import com.smcpartners.shape.shared.dto.common.ErrorMsgResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.LoginRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.JWTUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. Framework support for use case</br
 * <p>
 * Created by johndestefano on 9/14/15.
 * </p>
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
    // @LogLogin
    public Response login(LoginRequestDTO loginRequest) throws UseCaseException {
        try {
            UserDTO ue = this.getUser(loginRequest.getUserId(), loginRequest.getPassword());


            // If the user is valid and active return a token
            if (ue != null) {
                boolean isGenPwd = userDAO.isGeneratedPwd(ue.getId());
                boolean isExpired = userDAO.isExpired(ue.getId());
                if (ue.isActive()) {
                    if (isGenPwd && isExpired) {
                        throw new Exception ("Password has expired, please use Forgot Password to generate a new one");
                    }
                    String var = ue.isResetPwd() ? "true" : "false";
                    String token = jwtUtils.generateToken(ue.getId().toUpperCase(), ue.getRole(), ue.getOrganizationId(), true);
                    return Response.status(Response.Status.OK).entity("{\"token\":\"" + token + "\", \"resetRequired\":"
                            + var + "}").header("Authorization", "Bearer " + token).build();
                }else{
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
            log.logp(Level.SEVERE, this.getClass().getName(), "getUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
