package com.smcpartners.shape.usecases.activate_user;

import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.FrameworkUsecaseAdapter;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

/**
 * Responsible:<br/>
 * 1. Support contract between use case and adapter
 * <p>
 * Created by johndestefano on 11/3/15.
 * <p>
 * Changes:<b/>
 */
public interface ActivateUserUCAdapter extends FrameworkUsecaseAdapter {
    public static final String TARGET_USER_ID = "REQUESTING_USER_ID";

    SecurityRoleEnum getRequestingUsersRole() throws UseCaseException;
    void activateTargetUser(String targetUserId) throws UseCaseException;

}