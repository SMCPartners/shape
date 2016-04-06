package com.smcpartners.shape.usecases.user_request_password_question;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.UserPasswordQuestionResponseDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.MathUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. User can request a password change for himself and needs to answer a question
 * previously answered.The question returned will be save in the users record for
 * checking purposes by the UserRequestPasswordchangeService.<br/>
 * <p>
 * Created by johndestefano on 4/5/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class UserRequestPasswordQuestionServiceAdapter implements UserRequestPasswordQuestionService {
    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    @Inject
    private MathUtils mathUtils;

    /**
     * Constructor
     */
    public UserRequestPasswordQuestionServiceAdapter() {
    }

    //TODO: Is this safe? Anyone could change anyone else's password?
    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public UserPasswordQuestionResponseDTO requestPasswordQuestion(String userId) throws UseCaseException {
        try {
            // The user requesting the change must be the same user as the change is for
            if (userId.equalsIgnoreCase(requestScopedUserId.getRequestUserId())) {
                UserPasswordQuestionResponseDTO retDTO = new UserPasswordQuestionResponseDTO();
                retDTO.setUserId(userId);

                // Look up user and select random question
                UserDTO userDTO = userDAO.findById(userId);

                // Generate a random number between 1 and 2
                int choice = mathUtils.getRandomNumberInRange(1,2);
                if (choice == 1) {
                    retDTO.setPasswordQuestion(userDTO.getQuestionOne());
                } else {
                    retDTO.setPasswordQuestion(userDTO.getQuestionTwo());
                }

                // Make sure to persist the choice
                userDAO.setUserResetPwdChallenge(userId, choice);

                // Return the data
                return retDTO;
            } else {
                throw new IllegalAccessException();
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "requestPasswordQuestion", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
