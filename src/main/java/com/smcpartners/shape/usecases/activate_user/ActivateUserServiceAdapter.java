package com.smcpartners.shape.usecases.activate_user;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1.If its ADMIN then make the changes. If its ORG_ADMIN find the requesting users organization
 * and make sure the requesting users organization matches the target users organization</br
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
@RequestScoped
public class ActivateUserServiceAdapter implements ActivateUserService, ActivateUserUCAdapter {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    private ActivateUserUC activateUserUC;

    /**
     * Default constructor
     */
    public ActivateUserServiceAdapter() {
    }

    @PostConstruct
    protected void postConstruct() {
        this.activateUserUC = new ActivateUserUC(this);
    }

    /**
     * This method is protected so only Admin users can dao it and
     * it requires an active Admin user.
     *
     * @param targetUserId - The user to be activated
     * @throws UseCaseException
     * @see ActivateUserService
     */
    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO activateUser(String targetUserId) throws UseCaseException {
        try {
            // Find the requesting users role
            SecurityRoleEnum reqUserRoleEnum = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            // If its ADMIN then make the changes
            // If its ORG_ADMIN find the requesting users organization
            // Make sure the requesting users organization matches the target users organization
            // Make the change
            if (reqUserRoleEnum == SecurityRoleEnum.ADMIN) {
                userDAO.activateUser(targetUserId);
            } else {
                // Its an org admin so only for there organization
                UserDTO targetUser  = userDAO.findById(targetUserId);
                if (targetUser.getOrganizationId() == requestScopedUserId.getOrgId()) {
                    userDAO.activateUser(targetUserId);
                } else {
                    throw new IllegalAccessException();
                }
            }

            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "activateUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public SecurityRoleEnum getRequestingUsersRole() throws UseCaseException {
        try {
            String requestingUserId = requestScopedUserId.getRequestUserId();
            UserDTO requestiongUserDTO = userDAO.findById(requestingUserId);
            return SecurityRoleEnum.valueOf(requestiongUserDTO.getRole());
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "getRequestingUsersRole", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public void activateTargetUser(String targetUserId) throws UseCaseException {
        try {
            userDAO.activateUser(targetUserId);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "activateTargetUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
