package com.smcpartners.shape.usecases.edit_organization;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.common.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class EditOrganizationServiceAdapter implements EditOrganizationService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationDAO organizationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public EditOrganizationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO editOrganization(OrganizationDTO org) throws UseCaseException {
        try {
            // Only ADMIN or ORG_ADMIN can edi organizations
            // ORG_ADMIN can only edit there organization
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());
            if (reqRole == SecurityRoleEnum.ADMIN ||
                    (reqRole == SecurityRoleEnum.ORG_ADMIN && reqUser.getOrganizationId() == org.getId())) {
                organizationDAO.update(org, org.getId());
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editOrganization", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
