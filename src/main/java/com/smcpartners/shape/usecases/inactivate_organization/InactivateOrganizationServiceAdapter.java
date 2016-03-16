package com.smcpartners.shape.usecases.inactivate_organization;

import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.request.IntEntityIdRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. Only the ADMIN can inactivate an organization
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class InactivateOrganizationServiceAdapter implements InactivateOrganizationService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationDAO organizationDAO;

    public InactivateOrganizationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN})
    public BooleanValueDTO inactivateOrganization(IntEntityIdRequestDTO id) throws UseCaseException {
        try {
            // Only ADMIN can inactivate there organization
            organizationDAO.changeOrganizationActiveStatus(id.getEntId(), false);

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "inactivateOrganization", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
