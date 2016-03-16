package com.smcpartners.shape.usecases.add_organization_stratification;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationStratificationDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
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
public class AddOrganizationStratificationServiceAdapter implements AddOrganizationStratificationService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationStratificationDAO organizationStratificationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public AddOrganizationStratificationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public IntEntityResponseDTO addOrganizationStratification(OrganizationStratificationDTO org) throws UseCaseException {
        try {
            // ADMIN can add for any organizations
            // ORG_ADMIN and REGISTERED can only add for their organizations
            // The date for the report is now

            // Users role
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());
            Date now = new Date();
            OrganizationStratificationDTO orgDTO = null;

            if (reqRole == SecurityRoleEnum.ADMIN) {
                org.setRpDate(now);
                orgDTO = organizationStratificationDAO.create(org);
            } else {
                // Does the orgs organization match the requesting users organization
                if (requestScopedUserId.getOrgId() == org.getOrganizationId()) {
                    org.setRpDate(now);
                    orgDTO = organizationStratificationDAO.create(org);
                } else {
                    throw new IllegalAccessException();
                }
            }

            return IntEntityResponseDTO.makeNew(orgDTO.getId());
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganizationStratification", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
