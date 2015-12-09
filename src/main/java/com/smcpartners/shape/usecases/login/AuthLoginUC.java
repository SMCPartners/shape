package com.smcpartners.shape.usecases.login;


import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.utils.Dtohelpers;
import com.smcpartners.shape.shared.utils.UCHelpers;
import com.smcpartners.shape.shared.usecasecommon.AbstractUsecase;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/9/15.
 * <p>
 * Changes:<b/>
 */
public class AuthLoginUC extends AbstractUsecase<AuthLoginUsecaseAdapter> {

    public AuthLoginUC(AuthLoginUsecaseAdapter serviceAdaper) {
        super(serviceAdaper);
    }

    @Override
    public UsecaseResponse processRequest(UsecaseRequest request) throws UseCaseException {
        UsecaseResponse response = UCHelpers.createEmptyResponse();
        try {
            String userId = Dtohelpers.getValue(request.getMap(), String.class, AuthLoginUsecaseAdapter.USER_ID);
            String password = Dtohelpers.getValue(request.getMap(), String.class, AuthLoginUsecaseAdapter.PASSWORD);
            UserDTO user = serviceAdapter.getUser(userId, password);
            if (user != null) {
                if (!user.isActive()) {
                    response.setErrMsg("Account is inactive.");
                }
            } else {
                response.setErrMsg("Invalid credentials.");
            }
        } catch (Exception e) {
            UCHelpers.setExceptionResponse(response, e);
        }
        return response;
    }
}
