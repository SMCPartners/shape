package com.smcpartners.shape.usecases.get_measure_years;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. Any user can find the years a measure has been recorded</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class GetMeasureYearsServiceAdapter implements GetMeasureYearsService {

    @Inject
    private Logger log;

    @EJB
    private MeasureDAO measureDAO;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public GetMeasureYearsServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED,
            SecurityRoleEnum.DPH_USER})
    public List<Integer> getMeasureYears(int orgId, int measureId) throws UseCaseException {
        try {

            List<OrganizationMeasureDTO> measureList = organizationMeasureDAO.findAllOrganizationMeasureByOrgId(orgId);
            List<Integer> retLst = new ArrayList<>();

            if (measureList != null) {
                for (OrganizationMeasureDTO mL : measureList) {
                    if (mL.getReportPeriodYear() != null && mL.getMeasureId() == measureId) {
                        retLst.add(mL.getReportPeriodYear());
                    }
                }
            }

            Set duplicateRemove = new HashSet<>(retLst);
            retLst.clear();
            if (duplicateRemove.size() > 0) {
                retLst.addAll(duplicateRemove);
            }
            Collections.sort(retLst);
            Collections.reverse(retLst);

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "getMeasureYears", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
