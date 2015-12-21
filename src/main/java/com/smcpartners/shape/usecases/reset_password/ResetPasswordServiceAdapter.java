package com.smcpartners.shape.usecases.reset_password;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.PasswordUpdateRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.SecurityUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bhokanson on 11/30/2015.
 */
@RequestScoped
public class ResetPasswordServiceAdapter implements ResetPasswordService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public ResetPasswordServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.REGISTERED, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO resetPassword(PasswordUpdateRequestDTO userReq) throws UseCaseException {
        try {
            String userId = userReq.getUserId();
            String password = userReq.getPassword();
            String question = userReq.getQuestion();
            String answer = userReq.getAnswer();

            // Look up user
            UserDTO user = userDAO.findById(userId);

            // Check password for compliance
            boolean validPassword = SecurityUtils.checkPasswordCompliance(password);
            if (!validPassword) {
                throw new Exception("Password is not valid.");
            }

            // Find matching question and check answer
            if (question.equalsIgnoreCase(user.getQuestionOne())) {
                if (!answer.equalsIgnoreCase(user.getAnswerOne())) {
                    throw new Exception("Answer not valid.");
                }
            } else if (question.equalsIgnoreCase(user.getQuestionTwo())) {
                if (!answer.equalsIgnoreCase(user.getAnswerTwo())) {
                    throw new Exception("Answer not valid.");
                }
            } else {
                throw new Exception("Question is not valid");
            }

            // Update user
            userDAO.forcePasswordChange(userId, password);

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "resetPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
