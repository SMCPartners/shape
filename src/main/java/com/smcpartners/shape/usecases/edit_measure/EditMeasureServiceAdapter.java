package com.smcpartners.shape.usecases.edit_measure;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. The ADMIN can edit a measure.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class EditMeasureServiceAdapter implements EditMeasureService {

    @Inject
    private Logger log;

    @EJB
    private MeasureDAO measureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public EditMeasureServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN})
    public BooleanValueDTO editMeasure(MeasureDTO measure) throws UseCaseException {
        try {
            measureDAO.update(measure, measure.getId());
            // Return value
            return new BooleanValueDTO(true);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "editMeasure", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
