package com.smcpartners.shape.usecases.show_aggregate_comparison;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:</br>
 * 1. Get measure comparison data. ADMIN and DPH_USER can see all organizations.
 * Other users can only see their organization.</br>
 * <p>
 * Created by johndestefano on 3/16/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@RequestScoped
public class ShowAggregateComparisonServiceAdapter implements ShowAggregateComparisonService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private MeasureDAO mDAO;

    @EJB
    private OrganizationDAO oDAO;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public ShowAggregateComparisonServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<List<Object>> showAggregateComparison(@PathParam("measureId") int measureId,
                                                          @PathParam("year") int year) throws UseCaseException {
        try {

            // Get requesting users role
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getSecurityRole());


            List<OrganizationMeasureDTO> orgMList = null;
            List<List<Object>> retLst = null;

            // If admin or dph user they can see any organization
            if (SecurityRoleEnum.ADMIN == reqUserRole || SecurityRoleEnum.DPH_USER == reqUserRole) {
                orgMList = organizationMeasureDAO.findOrgMeasureByMeasureIdAndYear(measureId, year);
                retLst = createReturnLst(orgMList);

                // Add in organizations
                if (orgMList != null) {
                    for (OrganizationMeasureDTO om : orgMList) {
                        OrganizationDTO oDTO = oDAO.findById(om.getOrganizationId());
                        List<Object> orgList = new ArrayList<>();
                        orgList.add(oDTO.getName());
                        orgList.add(om.getDenominatorValue());
                        orgList.add(om.getNumeratorValue());
                        retLst.add(orgList);
                    }
                }

                // Return list
                return retLst;
            } else {
                //Either ORG_ADMIN or REGISTERED user. They can only see their organization
                UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());

                // Look up data
                orgMList = organizationMeasureDAO.findOrgMeasureByMeasureIdAndYearAndOrg(measureId, year, user.getOrganizationId());
                retLst = createReturnLst(orgMList);

                if (orgMList != null && orgMList.size() > 0) {
                    List<Object> orgList = new ArrayList<>();
                    orgList.add(user.getOrganizationName());
                    orgList.add(orgMList.get(0).getDenominatorValue());
                    orgList.add(orgMList.get(0).getNumeratorValue());
                    retLst.add(orgList);
                }

                // Return list
                return retLst;
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "showAggregateComparison", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    /**
     * Set up the return list for later population
     *
     * @param orgMList
     * @return
     */
    public List<List<Object>> createReturnLst(List<OrganizationMeasureDTO> orgMList) {
        // Result list
        List<List<Object>> retList = new ArrayList<>();
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

        // Return the list
        return retList;
    }

    /**
     * Sum up numerator and denominator values
     *
     * @param orgMList
     * @return
     */
    public int[] aggregateTotal(List<OrganizationMeasureDTO> orgMList) {
        int denSum = 0;
        int numSum = 0;
        int[] totals = new int[2];

        // Process list
        if (orgMList != null && orgMList.size() > 0) {
            for (OrganizationMeasureDTO om : orgMList) {
                if (om.getDenominatorValue() != null && om.getNumeratorValue() != null) {
                    denSum += om.getDenominatorValue();
                    numSum += om.getNumeratorValue();
                }
            }
        }

        // Return values
        totals[0] = denSum;
        totals[1] = numSum;
        return totals;
    }
}