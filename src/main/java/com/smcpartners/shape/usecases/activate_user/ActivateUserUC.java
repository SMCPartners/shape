package com.smcpartners.shape.usecases.activate_user;

import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.utils.UCHelpers;
import com.smcpartners.shape.usecases.common.AbstractUsecase;
import com.smcpartners.shape.usecases.common.UseCaseException;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/3/15.
 * <p>
 * Changes:<b/>
 */
public class ActivateUserUC extends AbstractUsecase<ActivateUserUCAdapter> {

    public ActivateUserUC(ActivateUserUCAdapter serviceAdapter) {
        super(serviceAdapter);
    }

    @Override
    public UsecaseResponse processRequest(UsecaseRequest request) throws UseCaseException {
        UsecaseResponse resp = UCHelpers.createEmptyResponse();
        try {
            String targetUserId = request.getValue(serviceAdapter.TARGET_USER_ID, String.class);
            SecurityRoleEnum roleEnum = serviceAdapter.getRequestingUsersRole();
        } catch(Exception e) {
            resp.setError(e);
        }

        return resp;
    }
}
