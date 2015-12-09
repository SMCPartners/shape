package com.smcpartners.shape.usecases.find_all_organization_measures_by_organization;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.common.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class FindAllOrganizationMeasuresByOrganizationServiceAdapter implements FindAllOrganizationMeasuresByOrganizationService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllOrganizationMeasuresByOrganizationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<OrganizationMeasureDTO> findAllOrganizationMeasuresByOrg(@PathParam("orgId") int orgId) throws UseCaseException {
        try {
            // Admin can see all
            // Other only see their organization

            // Get user and find security role
            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(user.getRole());

            // Get users org id
            int userOrg = user.getOrganizationId();

            if (reqRole == SecurityRoleEnum.ADMIN ||
                    (orgId == userOrg && (reqRole == SecurityRoleEnum.ORG_ADMIN || reqRole == SecurityRoleEnum.REGISTERED))) {
                return organizationMeasureDAO.findAllOrganizationMeasureByOrgId(orgId);
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationMeasuresByOrg", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
