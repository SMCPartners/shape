package com.smcpartners.shape.usecases.add_organization_measure;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
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
 * 1. ADMIN can add for any organization measure.
 * ORG_ADMIN and REGISTERED can only add for their organizations<br/>
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class AddOrganizationMeasureServiceAdapter implements AddOrganizationMeasureService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public AddOrganizationMeasureServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public IntEntityResponseDTO addOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException {
        try {
            // ADMIN can add for any organization organizations
            // ORG_ADMIN and REGISTERED can only add for their organizations
            // The date for the report is now

            // Users role
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());
            Date now = new Date();
            OrganizationMeasureDTO orgDTO = null;

            // ADMIN role
            if (reqRole == SecurityRoleEnum.ADMIN) {
                org.setRpDate(now);
                orgDTO = organizationMeasureDAO.create(org);
                return IntEntityResponseDTO.makeNew(orgDTO.getId());
            } else {
                // For other users only allow add for their organization
                if (org.getOrganizationId() == requestScopedUserId.getOrgId()) {
                    org.setRpDate(now);
                    orgDTO = organizationMeasureDAO.create(org);
                    return IntEntityResponseDTO.makeNew(orgDTO.getId());
                } else {
                    throw new IllegalAccessException();
                }
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganizationMeasure", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
