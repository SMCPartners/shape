package com.smcpartners.shape.usecases.activate_user;

import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/3/15.
 * <p>
 * Changes:<b/>
 */
public interface ActivateUserUCAdapter {
    public static final String TARGET_USER_ID = "REQUESTING_USER_ID";

    SecurityRoleEnum getRequestingUsersRole() throws UseCaseException;
    void activateTargetUser(String targetUserId) throws UseCaseException;

}