package com.smcpartners.shape.usecases.add_provider;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.ProviderDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
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
public class AddProviderServiceAdapter implements AddProviderService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private ProviderDAO providerDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public AddProviderServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN})
    public IntEntityResponseDTO addProvider(ProviderDTO prov) throws UseCaseException {
        try {
            // Only ADMIN can add organizations
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());

            if (SecurityRoleEnum.valueOf(reqUser.getRole()) == SecurityRoleEnum.ADMIN) {
                ProviderDTO provDTO = providerDAO.create(prov);
                return IntEntityResponseDTO.makeNew(provDTO.getId());
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganization", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
