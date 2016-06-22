package com.smcpartners.shape.usecases.edit_user;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. Admin level users can edit users. The ORG_ADMIN can only edit users for their
 * organization and only if they are not ADMINs. No one can edit a users organization and ORG_ADMIN can't make a user
 * an ADMIN<p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class EditUserServiceAdapter implements EditUserService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public EditUserServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO editUser(UserDTO user) throws UseCaseException {
        try {
            // ADMIN can edit user but not the users organization
            UserDTO targetUser = userDAO.findById(user.getId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());
            if (reqRole == SecurityRoleEnum.ADMIN) {
                if (targetUser.getOrganizationId() == user.getOrganizationId()) {
                    userDAO.update(user, user.getId());
                } else {
                    throw new IllegalAccessException("Your account does not have permission to edit this user.");
                }
            } else {
                // ORG_ADMIN can edit user for their organization but not change
                // the organization or change to role to ADMIN
                // ORG_ADMIN Can't edit ADMIN
                if (!targetUser.getRole().equals("ADMIN")
                        // Must be the same organization as requester
                        && targetUser.getOrganizationId() == requestScopedUserId.getOrgId()
                        // No funny business with the incoming data
                        && user.getOrganizationId() == targetUser.getOrganizationId()
                        // ORG_ADMIN can't make the role ADMIN
                        && !user.getRole().equalsIgnoreCase("ADMIN")){
                    userDAO.update(user, user.getId());
                } else {
                    throw new IllegalAccessException("Your account does not have permission to edit this user.");
                }
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
