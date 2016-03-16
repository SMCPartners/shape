package com.smcpartners.shape.usecases.find_user_by_id;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
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
 * 1. ADMIN and DPH_USER can find any user. ORG_ADMIN and REGISTERED user can only find users in their organization.</br
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
@RequestScoped
public class FindUserByIdServiceAdapter implements FindUserByIdService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    /**
     * Default constructor
     */
    public FindUserByIdServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED,
                                        SecurityRoleEnum.DPH_USER})
    public UserDTO findUser(String targetUserId) throws UseCaseException {
        try {
            // The ADMIN can see anyone
            // ORG_ADMIN can only see users in their ORG
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            if (SecurityRoleEnum.ADMIN == reqUserRole || SecurityRoleEnum.DPH_USER == reqUserRole) {
                return userDAO.findById(targetUserId);
            } else {
                UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
                UserDTO targetUser = userDAO.findById(targetUserId);

                if (targetUser.getOrganizationId() == reqUser.getOrganizationId()) {
                    return targetUser;
                } else {
                    throw new IllegalAccessException();
                }
            }

        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
