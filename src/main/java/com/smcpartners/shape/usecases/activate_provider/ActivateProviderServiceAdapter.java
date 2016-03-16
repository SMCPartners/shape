package com.smcpartners.shape.usecases.activate_provider;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.request.IntEntityIdRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. ADMIN and ORG_ADMIN can activate a provider but ORG_ADMIN only for their organization
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class ActivateProviderServiceAdapter implements ActivateProviderService {

    @Inject
    private Logger log;

    @EJB
    private ProviderDAO providerDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public ActivateProviderServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO activateProvider(IntEntityIdRequestDTO id) throws UseCaseException {
        try {
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            // ADMIN
            if (reqRole == SecurityRoleEnum.ADMIN) {
                providerDAO.changeProviderActiveStatus(id.getEntId(), true);
            } else {
                // For ORG_ADMIN
                // Find the provider to see if they are in the requester organization
                ProviderDTO provider = providerDAO.findById(id.getEntId());

                if (provider.getOrganizationId() == requestScopedUserId.getOrgId()) {
                    providerDAO.changeProviderActiveStatus(id.getEntId(), true);
                } else {
                    throw new IllegalAccessException();
                }
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "activateProvider", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
