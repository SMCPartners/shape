package com.smcpartners.shape.usecases.change_password;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.CreateUserResponseDTO;
import com.smcpartners.shape.shared.utils.SecurityUtils;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bhokanson on 12/3/2015.
 */
@RequestScoped
public class ChangePasswordServiceAdapter implements ChangePasswordService {
    @Inject
    private Logger log;

    @EJB
    private UserDAO dao;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    /**
     * Constructor
     */
    public ChangePasswordServiceAdapter() {
    }

    @Override
    public BooleanValueDTO changeUserPassword(CreateUserResponseDTO user) throws UseCaseException {
        try {

            String userId = user.getId();
            String userPwd = user.getPassword();
            String newPwd = user.getNewPassword();

            //check new password validity
            boolean validPassword = SecurityUtils.checkPasswordCompliance(newPwd);
            //check if valid user
            if (dao.validateUser(userId, userPwd) != null) {
                //check if valid password with correct regex
                if (validPassword) {
                    dao.changePassword(userId, userPwd, newPwd);
                    dao.resetPasswordToggle(userId, false);
                }else{
                    throw new UseCaseException("Did not meet regex");
                }
            }else{
                throw new UseCaseException("Not a valid user");
            }
            //returns true if new password uses correct regex and is a valid user
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "changeUserPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}