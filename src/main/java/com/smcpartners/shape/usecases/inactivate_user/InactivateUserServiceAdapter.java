package com.smcpartners.shape.usecases.inactivate_user;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. The ADMIN can inactivate anyone. ORG_ADMIN can only inactivate users in their ORG</br
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
public class InactivateUserServiceAdapter implements InactivateUserService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    /**
     * Default constructor
     */
    public InactivateUserServiceAdapter() {
    }

    @Override

    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO inactivateUser(String targetUserId) throws UseCaseException {
        try {
            // The ADMIN can inactivate anyone
            // ORG_ADMIN can only inactivate users in their ORG
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            if (SecurityRoleEnum.ADMIN == reqUserRole) {
                userDAO.inactivateUser(targetUserId);
                return new BooleanValueDTO(true);
            } else if (SecurityRoleEnum.ORG_ADMIN == reqUserRole) {
                // Find the org of the target user
                UserDTO targetUser = userDAO.findById(targetUserId);

                // If they match then inactivate
                if (targetUser.getOrganizationId() == requestScopedUserId.getOrgId()) {
                    userDAO.inactivateUser(targetUserId);
                    return new BooleanValueDTO(true);
                } else {
                    throw new IllegalAccessException();
                }
            }

            return new BooleanValueDTO(false);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "inactivateUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

}
