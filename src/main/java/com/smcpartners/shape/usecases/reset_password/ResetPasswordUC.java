package com.smcpartners.shape.usecases.reset_password;

import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.PasswordUpdateRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.AbstractUsecase;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.SecurityUtils;
import com.smcpartners.shape.shared.utils.UCHelpers;

/**
 * Responsible:<br/>
 * 1. Processes a request to reset a users password</br>
 * - Checks the new password for validity</br>
 * - Checks that the question submitted matches the answer on file</br>
 * - Update the password</br>
 *
 * <p>
 * Created by johndestefano on 12/21/15.
 * <p>
 * Changes:<b/>
 */
public class ResetPasswordUC extends AbstractUsecase<ResetPasswordUCAdapter> {

    public ResetPasswordUC(ResetPasswordUCAdapter serviceAdapter) {
        super(serviceAdapter);
    }

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
            }

            // Find matching question and check answer
            if (question.equalsIgnoreCase(user.getQuestionOne())) {
                if (!answer.equalsIgnoreCase(user.getAnswerOne())) {
                    UCHelpers.setErrorResponse(response, "Answer not valid.");
                }
            } else if (question.equalsIgnoreCase(user.getQuestionTwo())) {
                if (!answer.equalsIgnoreCase(user.getAnswerTwo())) {
                    UCHelpers.setErrorResponse(response, "Answer not valid.");
                }
            } else {
                UCHelpers.setErrorResponse(response, "Question not valid.");
            }

            // Update user
            if (!response.isErr()) {
                serviceAdapter.resetPassword(userId, password);

                // Return value
                UCHelpers.setPositiveResponse(response, new BooleanValueDTO(true));
            }

        } catch (Exception e) {
            UCHelpers.setExceptionResponse(response, e);
        }
        return response;
    }
}
