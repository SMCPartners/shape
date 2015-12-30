package com.smcpartners.shape.usecases.add_organization_measure;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.IntEntityResponseDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
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
public class AddOrganizationMeasureServiceAdapter implements AddOrganizationMeasureService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public AddOrganizationMeasureServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public IntEntityResponseDTO addOrganizationMeasure(OrganizationMeasureDTO org) throws UseCaseException {
        try {
            // ADMIN can add for any organization organizations
            // ORG_ADMIN and REGISTERED can only add for their organizations
            // The date for the report is now
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());

            // Get Users organization
            int orgId = reqUser.getOrganizationId();

            // Users role
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(reqUser.getRole());

            if (reqRole == SecurityRoleEnum.ADMIN ||
                    ((reqRole == SecurityRoleEnum.ORG_ADMIN || reqRole == SecurityRoleEnum.REGISTERED))) {
                Date now = new Date();
                org.setRpDate(now);
                OrganizationMeasureDTO orgDTO = organizationMeasureDAO.create(org);
                return IntEntityResponseDTO.makeNew(orgDTO.getId());
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "addOrganizationMeasure", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
