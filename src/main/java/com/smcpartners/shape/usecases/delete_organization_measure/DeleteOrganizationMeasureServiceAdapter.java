package com.smcpartners.shape.usecases.delete_organization_measure;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. ADMIN can delete a measure for any organization. ORG_ADMIN or REGISTERED user can
 * only delte for their organization</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class DeleteOrganizationMeasureServiceAdapter implements DeleteOrganizationMeasureService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public DeleteOrganizationMeasureServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public BooleanValueDTO deleteOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException {
        try {
            SecurityRoleEnum role = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            if (SecurityRoleEnum.ADMIN == role) {
                organizationMeasureDAO.delete(org.getId());
            } else {
                // Not the ADMIN
                if (requestScopedUserId.getOrgId() == org.getOrganizationId()) {
                    organizationMeasureDAO.delete(org.getId());
                } else {
                    throw new IllegalAccessException();
                }
            }

            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "deleteOrganizationMeasure", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
