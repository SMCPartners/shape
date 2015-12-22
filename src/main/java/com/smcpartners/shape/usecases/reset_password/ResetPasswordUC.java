package com.smcpartners.shape.usecases.reset_password;

import com.smcpartners.shape.crosscutting.email.MailDTO;
import com.smcpartners.shape.crosscutting.email.SendMailService;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.PasswordUpdateRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.AbstractUsecase;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.RandomPasswordGenerator;
import com.smcpartners.shape.shared.utils.SecurityUtils;
import com.smcpartners.shape.shared.utils.UCHelpers;

import javax.ejb.EJB;
import java.util.Random;

/**
 * Responsible:<br/>
 * 1. Processes a request to reset a users password</br>
 * - Checks the new password for validity</br>
 * - Checks that the question submitted matches the answer on file</br>
 * - Update the password</br>
 * <p>
 * <p>
 * Created by johndestefano on 12/21/15.
 * <p>
 * Changes:<b/>
 */
public class ResetPasswordUC extends AbstractUsecase<ResetPasswordUCAdapter> {

    public ResetPasswordUC(ResetPasswordUCAdapter serviceAdapter) {
        super(serviceAdapter);
    }

    @EJB
    private UserDAO userDAO;

    @EJB
    private SendMailService sms;


    @Override
    public UsecaseResponse processRequest(UsecaseRequest request) throws UseCaseException {
        UsecaseResponse response = UCHelpers.createEmptyResponse();
        try {
            // Get user request
            PasswordUpdateRequestDTO userReq = UCHelpers.getValue(request,
                    ResetPasswordUCAdapter.USER_REQUEST, PasswordUpdateRequestDTO.class);
            String userId = userReq.getUserId();
            String password = userReq.getPassword();
            String question = userReq.getQuestion();
            String answer = userReq.getAnswer();

            // Get user data
            UserDTO user = serviceAdapter.getUserData(userId);

            // Check password for compliance
            boolean validPassword = SecurityUtils.checkPasswordCompliance(password);
            if (!validPassword) {
                UCHelpers.setErrorResponse(response, "Password not valid");
                return response;
            }

            // Find matching question and check answer
            if (question.equalsIgnoreCase(user.getQuestionOne())) {
                if (!answer.equalsIgnoreCase(user.getAnswerOne())) {
                    UCHelpers.setErrorResponse(response, "Answer not valid.");
                    return response;
                }
            } else if (question.equalsIgnoreCase(user.getQuestionTwo())) {
                if (!answer.equalsIgnoreCase(user.getAnswerTwo())) {
                    UCHelpers.setErrorResponse(response, "Answer not valid.");
                    return response;
                }
            } else {
                UCHelpers.setErrorResponse(response, "Question not valid.");
                return response;
            }

            serviceAdapter.resetPassword(userId, password);

            String newPassword = RandomPasswordGenerator.generateApplicationDefaultPwd();
            userDAO.changePassword(userId, password, newPassword);
            userDAO.resetPasswordToggle(userId, false);
            MailDTO mail = new MailDTO();
            mail.setToEmail(user.getEmail());
            mail.setSubject("Your password has been reset");
            mail.setMessage("Your password has been reset and changed to the temporary password: + " + newPassword + "/n" +
            "Please log in using your temporary password. You will be prompted to change this password after a successful login");
            sms.sendEmailMsg(mail);


            // Return value
            UCHelpers.setPositiveResponse(response, new BooleanValueDTO(true));

        } catch (Exception e) {
            UCHelpers.setExceptionResponse(response, e);
        }
        return response;
    }
}
