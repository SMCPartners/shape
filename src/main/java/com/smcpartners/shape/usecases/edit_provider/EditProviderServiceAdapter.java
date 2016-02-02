package com.smcpartners.shape.usecases.edit_provider;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

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
public class EditProviderServiceAdapter implements EditProviderService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private ProviderDAO providerDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public EditProviderServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO editProvider(ProviderDTO prov) throws UseCaseException {
        try {
            // Only ADMIN or ORG_ADMIN can edit provider
            // ORG_ADMIN can only edit there organization
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());
            ProviderDTO pDTO = providerDAO.findById(prov.getId());

            if (reqRole == SecurityRoleEnum.ADMIN ||
                    (reqRole == SecurityRoleEnum.ORG_ADMIN && reqUser.getOrganizationId() == pDTO.getId())) {
                providerDAO.update(prov, prov.getId());
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }

            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editProvider", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
