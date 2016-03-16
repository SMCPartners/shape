package com.smcpartners.shape.usecases.find_all_organization_measures_by_organization;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.usecasecommon.IllegalAccessException;
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
 * Responsible:<br/>
 * 1. ADMIN and DPH_USER can see measures for any organization. Other users can only see measures
 * for their organization.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class FindAllOrganizationMeasuresByOrganizationServiceAdapter implements FindAllOrganizationMeasuresByOrganizationService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllOrganizationMeasuresByOrganizationServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED, SecurityRoleEnum.DPH_USER})
    public List<OrganizationMeasureDTO> findAllOrganizationMeasuresByOrg(@PathParam("orgId") int orgId) throws UseCaseException {
        try {
            // Admin can see all
            // Other only see their organization
            // Get user and find security role
            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(user.getRole());

            if (reqRole == SecurityRoleEnum.ADMIN || reqRole == SecurityRoleEnum.DPH_USER) {
                return getRetLst(organizationMeasureDAO.findAllOrganizationMeasureByOrgId(orgId));
            } else {
                // Not admin or dph so org ids must match
                if (user.getOrganizationId() == orgId) {
                    return getRetLst(organizationMeasureDAO.findAllOrganizationMeasureByOrgId(orgId));
                } else {
                    throw new IllegalAccessException();
                }
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationMeasuresByOrg", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    private List<OrganizationMeasureDTO> getRetLst(List<OrganizationMeasureDTO> orgMList) {
        List<OrganizationMeasureDTO> retList = new ArrayList<>();
        if (orgMList != null) {
            int reportPeriod = 0;
            for (OrganizationMeasureDTO om : orgMList) {
                if (reportPeriod < om.getReportPeriodYear()) {
                    reportPeriod = om.getReportPeriodYear();
                }
            }

            for (OrganizationMeasureDTO omg: orgMList) {
                if (omg.getReportPeriodYear() == reportPeriod) {
                    retList.add(omg);
                }
            }
        }
        return retList;
    }
}
