package com.smcpartners.shape.usecases.show_aggregate_comparison;

/**
 * Created by bryanhokanson on 12/18/15.
 */

import com.smcpartners.shape.frameworks.data.entitymodel.shape.UserEntity;
import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;


import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;



import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bryanhokanson on 12/14/15.
 */
@RequestScoped
public class ShowAggregateComparisonServiceAdapter implements ShowAggregateComparisonService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @EJB
    private UserDAO userDAO;

    @EJB
    private MeasureDAO mDAO;

    @EJB
    private OrganizationDAO oDAO;

    @EJB
    private OrganizationMeasureDAO orgMDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public ShowAggregateComparisonServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<List<Object>> showAggregateComparison(@PathParam("measureId") int measureId,
                                                          @PathParam("year") int year) throws UseCaseException {
        try {

            List<List<Object>> retList = new ArrayList<>();

            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());

            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());

            OrganizationDTO orgDTO = oDAO.findById(user.getOrganizationId());

            List<OrganizationMeasureDTO> orgMList = orgMDAO.findOrgMeasureByMeasureIdAndYear(measureId, year);

            List<Object> headerList = new ArrayList<>();
            List<Object> aggregateList = new ArrayList<>();

            int[] totalAgg = aggregateTotal(orgMList);

            headerList.add("Organization");
            headerList.add("Denominator");
            headerList.add("Numerator");

            aggregateList.add("Aggregate");
            aggregateList.add(totalAgg[0]);
            aggregateList.add(totalAgg[1]);

            retList.add(headerList);
            retList.add(aggregateList);


            if (SecurityRoleEnum.ORG_ADMIN == reqUserRole || SecurityRoleEnum.REGISTERED == reqUserRole) {

                List<Object> orgList = new ArrayList<>();

                orgList.add(orgDTO.getName());
                orgList.add(orgMList.get(0).getDenominatorValue());
                orgList.add(orgMList.get(0).getNumeratorValue());

                retList.add(orgList);

            } else {

                if (orgMList != null) {
                    for (OrganizationMeasureDTO om : orgMList) {
                        OrganizationDTO oDTO = oDAO.findById(om.getOrganizationId());
                        List<Object> orgList = new ArrayList<>();

                        orgList.add(oDTO.getName());
                        orgList.add(om.getDenominatorValue());
                        orgList.add(om.getNumeratorValue());

                        retList.add(orgList);
                    }
                }
            }
            return retList;

        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "showAggregateComparison", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    public int[] aggregateTotal(List<OrganizationMeasureDTO> orgMList) {
        int denSum = 0;
        int numSum = 0;
        int[] totals = new int[2];
        for (OrganizationMeasureDTO om : orgMList) {
            denSum += om.getDenominatorValue();
            numSum += om.getNumeratorValue();
        }
        totals[0] = denSum;
        totals[1] = numSum;
        return totals;
    }
}