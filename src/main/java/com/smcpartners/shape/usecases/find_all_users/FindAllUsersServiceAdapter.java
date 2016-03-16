package com.smcpartners.shape.usecases.find_all_users;


import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. The ADMIN role can see everybody. The ORG_ADMIN can see others in their ORG
 * Everyone else gets nothing
 * <p>
 * Created by johndestefano on 9/11/15.
 * <p>
 * Changes:<b/>
 * </p>
 */
@RequestScoped
public class FindAllUsersServiceAdapter implements FindAllUsersService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

     @Inject
    private RequestScopedUserId requestScopedUserId;

    /**
     * Default constructor
     */
    public FindAllUsersServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN})
    public List<UserDTO> findAllUser() throws UseCaseException {
        try {
            List<UserDTO> lst = null;

            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            // The ADMIN role can see everybody
            // The ORG_ADMIN can see others in their ORG
            // Everyone else gets nothing
            if (reqUserRole == SecurityRoleEnum.ADMIN || reqUserRole == SecurityRoleEnum.DPH_USER) {
                lst = userDAO.findAll();
            } else if (reqUserRole == SecurityRoleEnum.ORG_ADMIN) {
                // Find the user
                lst = userDAO.findByOrg(requestScopedUserId.getOrgId());
            }
            return lst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllUser", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
