package com.smcpartners.shape.usecases.activate_user;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.common.UseCaseException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. </br
 * <p>
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
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
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO activateUser(String targetUserId) throws UseCaseException {
        try {
            // Find the requesting users role
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqUserRoleEnum = SecurityRoleEnum.valueOf(reqUser.getRole());

            // If its ADMIN then make the changes
            // If its ORG_ADMIN find the requesting users organization
            // Make sure the requesting users organization matches the target users organization
            // Make the change
            if (reqUserRoleEnum == SecurityRoleEnum.ADMIN) {
                userDAO.activateUser(targetUserId);
            } else {
                int reqUserOrgId = reqUser.getOrganizationId();
                UserDTO targetUser  = userDAO.findById(targetUserId);
                if (targetUser.getOrganizationId() == reqUserOrgId) {
                    userDAO.activateUser(targetUserId);
                } else {
                    throw new Exception("Requesting user does not have authority.");
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
