package com.smcpartners.shape.usecases.edit_organization_stratification;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationStratificationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. ADMIN can add for any organizations. ORG_ADMIN and REGISTERED can only add for their organizations
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class EditOrganizationStratificationServiceAdapter implements EditOrganizationStratificationService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationStratificationDAO organizationStratificationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public EditOrganizationStratificationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public IntEntityResponseDTO editOrganizationStratification(OrganizationStratificationDTO org) throws UseCaseException {
        try {
            // ADMIN can add for any organizations
            // ORG_ADMIN and REGISTERED can only add for their organizations
            // The date for the report is now
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());

            // Get Users organization
            int orgId = reqUser.getOrganizationId();

            // Users role
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());
            Date now = new Date();

            if (reqRole == SecurityRoleEnum.ADMIN) {
                org.setRpDate(now);
                OrganizationStratificationDTO orgDTO = organizationStratificationDAO.update(org, org.getId());
                return IntEntityResponseDTO.makeNew(orgDTO.getId());
            } else {
                // Not the admin
                UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());

                if (user.getOrganizationId() == org.getOrganizationId()) {
                    org.setRpDate(now);
                    OrganizationStratificationDTO orgDTO = organizationStratificationDAO.update(org, org.getId());
                    return IntEntityResponseDTO.makeNew(orgDTO.getId());
                } else {
                    throw new IllegalAccessException();
                }
            }

        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganizationStratification", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
