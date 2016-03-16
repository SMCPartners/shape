package com.smcpartners.shape.usecases.add_provider;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. ADMIN and ORG_ADMIN can add a provider. If its and org admin the provider must be for their organization.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class AddProviderServiceAdapter implements AddProviderService {

    @Inject
    private Logger log;

    @EJB
    private ProviderDAO providerDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public AddProviderServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public IntEntityResponseDTO addProvider(ProviderDTO prov) throws UseCaseException {
        try {
            // Only ADMIN can add organizations
            ProviderDTO provDTO = null;

            if (SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole()) == SecurityRoleEnum.ADMIN) {
                provDTO = providerDAO.create(prov);
                return IntEntityResponseDTO.makeNew(provDTO.getId());
            } else {
                // ORG_ADMIN user
                if (requestScopedUserId.getOrgId() == prov.getOrganizationId()) {
                    provDTO = providerDAO.create(prov);
                    return IntEntityResponseDTO.makeNew(provDTO.getId());
                } else {
                    throw new IllegalAccessException();
                }
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addProvider", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
