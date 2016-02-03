package com.smcpartners.shape.usecases.delete_organization_measure;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bryanhokanson on 12/21/15.
 */
@RequestScoped
public class DeleteOrganizationMeasureServiceAdapter implements DeleteOrganizationMeasureService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public DeleteOrganizationMeasureServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public BooleanValueDTO deleteOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException {
        try {
                organizationMeasureDAO.delete(org.getId());
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editOrganizationMeasure", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
