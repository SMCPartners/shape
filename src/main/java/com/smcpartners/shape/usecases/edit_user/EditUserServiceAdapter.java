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
 * Responsible:</br>
 * 1. Admin or org_admin can edit the user. The org-admin can't make the user an ADMIN or change the organization.</br>
 * <p>
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
            // Only ADMIN can edit user
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());
            if (reqRole == SecurityRoleEnum.ADMIN) {
                userDAO.update(user, user.getId());
            } else if (reqRole == SecurityRoleEnum.ORG_ADMIN) {

                // Check role.
                if (reqUser.getRole().equals("ADMIN")){
                    throw new Exception("You cannot change a users role to be higher than yours");
                }

                // Check organization
                if (reqUser.getOrganizationId() != user.getOrganizationId()) {
                    throw new Exception("You can't change the users organization.");
                }

                userDAO.update(user, user.getId());
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
