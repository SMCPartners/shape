package com.smcpartners.shape.usecases.edit_organization_stratification;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationStratificationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
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
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
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

            if (reqRole == SecurityRoleEnum.ADMIN ||
                    (orgId == org.getId() && (reqRole == SecurityRoleEnum.ORG_ADMIN || reqRole == SecurityRoleEnum.REGISTERED))) {
                Date now = new Date();
                org.setRpDate(now);
                OrganizationStratificationDTO orgDTO = organizationStratificationDAO.update(org, orgId);
                return IntEntityResponseDTO.makeNew(orgDTO.getId());
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganizationStratification", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
