package com.smcpartners.shape.usecases.activate_organization;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.IntEntityIdRequestDTO;
import com.smcpartners.shape.shared.utils.UCHelpers;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
public class ActivateOrganizationServiceAdapter implements ActivateOrganizationService, ActivateOrganizationUsecaseAdapter {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationDAO organizationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    private ActivateOrganizationUC activateOrganizationUC;


    public ActivateOrganizationServiceAdapter() {
    }

    @PostConstruct
    protected void postConstruct() {
        this.activateOrganizationUC = new ActivateOrganizationUC(this);
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN})
    public BooleanValueDTO activateOrganization(IntEntityIdRequestDTO id) throws UseCaseException {
        try {
            // Request needs the organization id
            UsecaseRequest request = UCHelpers.createRequest(
                    UCHelpers.makeStringArray(ActivateOrganizationUsecaseAdapter.ORGANIZATION_ID),
                    Integer.valueOf(id.getEntId()));
            UsecaseResponse resp = this.activateOrganizationUC.processRequest(request);
            return UCHelpers.processResponse(resp, BooleanValueDTO.class);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "activateOrganization", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public SecurityRoleEnum getRequesterSecurityRole() throws UseCaseException {
        try {
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());
            return reqRole;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "getRequesterSecurityRole", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    @Override
    public void activateOrganization(int orgId) throws UseCaseException {
        try {
            organizationDAO.changeOrganizationActiveStatus(orgId, true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "activateOrganization", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
