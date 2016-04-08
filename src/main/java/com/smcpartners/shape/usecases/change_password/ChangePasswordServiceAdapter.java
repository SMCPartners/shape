package com.smcpartners.shape.usecases.change_password;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.request.CreateUserRequestDTO;
import com.smcpartners.shape.shared.utils.SecurityUtils;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. Request a password change. The user must provide the old password and a new password.</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
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
    public BooleanValueDTO changeUserPassword(CreateUserRequestDTO user) throws UseCaseException {
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
                    if (user.getQuestionOne() != null && user.getQuestionTwo() != null) {
                        dao.addUserSecurityQuestions(userId, user.getQuestionOne(),
                                user.getQuestionTwo(), user.getAnswerOne(), user.getAnswerTwo());
                    }
                }else{
                    throw new UseCaseException("The new password you entered did not meet the required format.");
                }
            }else{
                throw new UseCaseException("Your current password is not correct.");
            }
            //returns true if new password uses correct regex and is a valid user
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "changeUserPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
