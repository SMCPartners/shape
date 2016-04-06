package com.smcpartners.shape.usecases.user_request_password_change;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.UserPasswordResetRequestDTO;
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
 * 1. A user can request a passwprd reset. The request must be for the logged in user. The
 * user must send the old password which must match the current password, the new password, and
 * the answer to the provided question (provided by previous call to UserRequestPasswordQuestionService.<br/>
 * <p>
 * Created by johndestefano on 4/5/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class UserRequestPasswordchangeServiceAdapter implements UserRequestPasswordChangeService {
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
    public UserRequestPasswordchangeServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public BooleanValueDTO requestPasswordChange(UserPasswordResetRequestDTO pwdResetReq) throws UseCaseException {
        try {
            // The user requesting the change must be the same user as the change is for
            String userId = requestScopedUserId.getRequestUserId();
            if (pwdResetReq.getUserId().equalsIgnoreCase(userId)) {
                // Get the user and check password
                UserDTO userDTO = userDAO.validateUser(userId, pwdResetReq.getOldPassword());
                if (userDTO == null) {
                    throw new IllegalAccessException();
                }

                // Compare the answer returned to the question sent
                String answer = null;
                if (userDTO.getUserResetPwdChallenge() == 1) {
                    answer = userDTO.getAnswerOne();
                } else if (userDTO.getUserResetPwdChallenge() == 2) {
                    answer = userDTO.getAnswerTwo();
                } else {
                    // Can't send back anything else
                    throw new IllegalAccessException();
                }

                // If answer doesn't match throw exception
                if (!pwdResetReq.getQuestionAnswer().equals(answer)) {
                    throw new IllegalAccessException("Answer doesn't match question.");
                }

                // Set the new password for the user
                // This will also set the user request password change flag to 0
                userDAO.forcePasswordChange(userId, pwdResetReq.getNewPassword());

                // Return value
                return BooleanValueDTO.get(true);
            } else {
                throw new IllegalAccessException();
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "requestPasswordChange", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
