package com.smcpartners.shape.usecases.find_all_measures;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.common.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class FindAllMeasuresServiceAdapter implements FindAllMeasuresService {

    @Inject
    private Logger log;

    @EJB
    private MeasureDAO measureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllMeasuresServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<MeasureDTO> findAllMeasures() throws UseCaseException {
        try {
            // Anyone can select a measure
            return measureDAO.findAllMeasures();
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllMeasures", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
