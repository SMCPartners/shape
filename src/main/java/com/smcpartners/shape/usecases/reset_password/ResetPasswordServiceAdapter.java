package com.smcpartners.shape.usecases.reset_password;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.PasswordUpdateRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.SecurityUtils;
import com.smcpartners.shape.shared.utils.UCHelpers;
import com.smcpartners.shape.usecases.activate_organization.ActivateOrganizationUsecaseAdapter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bhokanson on 11/30/2015.
 */
@RequestScoped
public class ResetPasswordServiceAdapter implements ResetPasswordService, ResetPasswordUCAdapter {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    private ResetPasswordUC resetPasswordUC;


    public ResetPasswordServiceAdapter() {
    }

    @PostConstruct
    protected void postConstruct() {
        this.resetPasswordUC = new ResetPasswordUC(this);
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.REGISTERED, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO resetPassword(PasswordUpdateRequestDTO userReq) throws UseCaseException {
        try {
            // Request needs the organization id
            UsecaseRequest request = UCHelpers.createRequest(
                    UCHelpers.makeStringArray(ResetPasswordUCAdapter.USER_REQUEST), userReq);
            UsecaseResponse resp = this.resetPasswordUC.processRequest(request);
            return UCHelpers.processResponse(resp, BooleanValueDTO.class);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "resetPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public PasswordUpdateRequestDTO getUserRequest() throws Exception {
        try {
            return null;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "getUserRequest", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public void resetPassword(String userId, String newPassword) throws Exception {
        try {
            userDAO.forcePasswordChange(userId, newPassword);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "resetPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public UserDTO getUserData(String userId) throws Exception {
        try {
            return userDAO.findById(userId);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "getUserData", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
