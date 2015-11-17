package com.smcpartners.shape.usecases.find_user_by_id;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.UseCaseException;

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
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public UserDTO findUser(String targetUserId) throws UseCaseException {
        try {
            // The ADMIN can see anyone
            // ORG_ADMIN can only see users in their ORG
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            UserDTO u = null;
            if (SecurityRoleEnum.ADMIN == reqUserRole) {
                u = userDAO.findById(targetUserId);
            } else {
                UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
                UserDTO targetUser = userDAO.findById(targetUserId);

                if (targetUser.getOrganizationId() == reqUser.getOrganizationId()) {
                    u = targetUser;
                }

            }
            return u;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
