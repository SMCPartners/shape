package com.smcpartners.shape.usecases.find_all_providers;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. Return a list of providers. The ADMIN and DPH_USER can see all providers.
 * The ORG_ADMIN and REGISTERED user can only see providers associated
 * with there organization</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class FindAllProvidersServiceAdapter implements FindAllProvidersService {

    @Inject
    private Logger log;

    @EJB
    private ProviderDAO providerDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllProvidersServiceAdapter() {
    }

    /**
     * Return a list of providers. The ADMIN and DPH_USER can see all providers.
     * The ORG_ADMIN and REGISTERED user can only see providers associated
     * with there organization.
     *
     * @return
     * @throws UseCaseException
     */
    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<ProviderDTO> findAllProviders() throws UseCaseException {
        try {
            // Get the requester role from the security token
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());
            List<ProviderDTO> retLst = null;

            // ADMIN or DPH_USER get everything
            if (SecurityRoleEnum.ADMIN == reqUserRole || SecurityRoleEnum.DPH_USER == reqUserRole) {
                retLst = providerDAO.findAll();
            } else if (SecurityRoleEnum.ORG_ADMIN == reqUserRole || SecurityRoleEnum.REGISTERED == reqUserRole) {
                // ORG_ADMIN or REGISTERED get only the requester organization
                retLst = providerDAO.findAllByOrg(requestScopedUserId.getOrgId());
            }

            // Return results
            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllProviders", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}