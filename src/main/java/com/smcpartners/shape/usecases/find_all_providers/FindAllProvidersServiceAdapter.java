package com.smcpartners.shape.usecases.find_all_providers;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
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
 * Created by bhokanson on 12/4/2015.
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

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<ProviderDTO> findAllProviders() throws UseCaseException {
        try {
            // Anyone can select a measure
            return providerDAO.findAll();
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllMeasures", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}