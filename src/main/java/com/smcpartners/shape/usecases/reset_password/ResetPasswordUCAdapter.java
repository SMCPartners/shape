package com.smcpartners.shape.usecases.reset_password;

import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.PasswordUpdateRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.FrameworkUsecaseAdapter;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 12/21/15.
 * <p>
 * Changes:<b/>
 */
public interface ResetPasswordUCAdapter extends FrameworkUsecaseAdapter {

    /**
     * User request object key
     */
    public static final String USER_REQUEST = "user_request";

    /**
     * Get the user request
     *
     * @return
     * @throws Exception
     */
    PasswordUpdateRequestDTO getUserRequest() throws Exception;

    /**
     * Reset the user password to the new one
     *
     * @param userId
     * @param newPassword
     * @throws Exception
     */
    void resetPassword(String userId, String newPassword) throws Exception;

    /**
     * Get the user data
     *
     * @param userId
     * @return
     * @throws Exception
     */
    UserDTO getUserData(String userId) throws Exception;

}
