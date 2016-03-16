package com.smcpartners.shape.usecases.find_all_organization_stratifications_by_organization;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationStratificationDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. ADMIN and DPH_USER can see all. Others can see only their organzations
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class FindAllOrganizationStratificationsByOrganizationServiceAdapter implements FindAllOrganizationStratificationsByOrganizationService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationStratificationDAO organizationStratificationDAO;

   @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllOrganizationStratificationsByOrganizationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<OrganizationStratificationDTO> findAllOrganizationStratificationsByOrg(int orgId)throws UseCaseException {
        try {
            // Admin can see all
            // Other only see their organization

            // Get user and find security role
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            if (reqRole == SecurityRoleEnum.ADMIN || reqRole == SecurityRoleEnum.DPH_USER ||
                    (orgId == requestScopedUserId.getOrgId() && (reqRole == SecurityRoleEnum.ORG_ADMIN ||
                            reqRole == SecurityRoleEnum.REGISTERED ))) {
                return organizationStratificationDAO.findAllOrganizationStratificationByOrgId(orgId);
            } else {
                throw new IllegalAccessException();
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationMeasuresByOrg", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
