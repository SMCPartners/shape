package com.smcpartners.shape.usecases.find_measure_by_id;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bryanhokanson on 3/2/16.
 */
@RequestScoped
public class FindMeasureByIdServiceAdapter implements FindMeasureByIdService {

    @Inject
    private Logger log;

    @EJB
    private MeasureDAO measureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindMeasureByIdServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public MeasureDTO findMeasureById(int measureId) throws UseCaseException {
        try {

            return measureDAO.findById(measureId);

        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findMeasureById", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}