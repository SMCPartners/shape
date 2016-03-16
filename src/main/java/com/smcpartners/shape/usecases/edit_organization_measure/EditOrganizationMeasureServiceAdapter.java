package com.smcpartners.shape.usecases.edit_organization_measure;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. Only ADMIN or ORG_ADMIN can edit organization measures. ORG_ADMIN and REGISTERED user
 * can only edit their organization.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class EditOrganizationMeasureServiceAdapter implements EditOrganizationMeasureService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public EditOrganizationMeasureServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public BooleanValueDTO editOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException {
        try {
            // Only ADMIN or ORG_ADMIN can edit organization measures
            // ORG_ADMIN can only edit there organization
            SecurityRoleEnum role = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            if (SecurityRoleEnum.ADMIN == role ) {
                organizationMeasureDAO.update(org, org.getId());
            } else {
                // Not the ADMIN
                UserDTO userDTO = userDAO.findById(requestScopedUserId.getRequestUserId());

                if (userDTO.getOrganizationId() == org.getOrganizationId()) {
                    organizationMeasureDAO.update(org, org.getId());
                } else {
                    throw new IllegalAccessException();
                }
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editOrganizationMeasure", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
