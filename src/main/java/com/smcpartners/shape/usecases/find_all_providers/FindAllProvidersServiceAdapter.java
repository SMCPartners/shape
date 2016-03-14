package com.smcpartners.shape.usecases.find_all_providers;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bhokanson on 12/4/2015.
 */
@RequestScoped
public class FindAllProvidersServiceAdapter implements FindAllProvidersService {

    @Inject
    private Logger log;

    @EJB
    private ProviderDAO providerDAO;

    @EJB
    private UserDAO userDAO;

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
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<ProviderDTO> findAllProviders() throws UseCaseException {
        try {
            // Get the requester role from the security token
            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(user.getRole());
            List<ProviderDTO> retLst = null;

            // ADMIN or DPH_USER
            if (SecurityRoleEnum.ADMIN == reqUserRole || SecurityRoleEnum.DPH_USER == reqUserRole) {
                // ADMIN or DPH_USER get everything
                retLst = providerDAO.findAll();
            } else if (SecurityRoleEnum.ORG_ADMIN == reqUserRole || SecurityRoleEnum.REGISTERED == reqUserRole) {
                // ORG_ADMIN or REGISTERED get only the requester organization
                retLst = providerDAO.findAllByOrg(user.getOrganizationId());
            }

            // Return results
            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllProviders", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}