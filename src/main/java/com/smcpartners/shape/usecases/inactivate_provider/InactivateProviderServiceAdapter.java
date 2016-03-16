package com.smcpartners.shape.usecases.inactivate_provider;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
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
 * 1. An ADMIN can inactivate any provider. An ORG_ADMIN can inactivate providers for their
 * organization.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class InactivateProviderServiceAdapter implements InactivateProviderService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private ProviderDAO providerDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public InactivateProviderServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO inactivateProvider(IntEntityIdRequestDTO id) throws UseCaseException {
        try {
            // Only ADMIN can inactivate any provider
            // ORG_ADMIN can inactivate org provider
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());

            if (reqRole == SecurityRoleEnum.ADMIN) {
                providerDAO.changeProviderActiveStatus(id.getEntId(), false);
            } else {
                // Is org admin
                ProviderDTO provider = providerDAO.findById(id.getEntId());

                // Organizations must match
                if (provider.getOrganizationId() == reqUser.getOrganizationId()) {
                    providerDAO.changeProviderActiveStatus(id.getEntId(), false);
                } else {
                    throw new IllegalAccessException();
                }
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganization", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
