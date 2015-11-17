package com.smcpartners.shape.usecases.find_all_organization_stratifications;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationStratificationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.UseCaseException;

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
public class FindAllOrganizationStratificationsServiceAdapter implements FindAllOrganizationStratificationsService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationStratificationDAO organizationStratificationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllOrganizationStratificationsServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN})
    public List<OrganizationStratificationDTO> findAllOrganizationStratifications() throws UseCaseException {
        try {
            // Admin can see all
            // Others only see their organization
            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(user.getRole());

            if (reqRole == SecurityRoleEnum.ADMIN) {
                return organizationStratificationDAO.findAllOrganizationStratification();
            } else {
                throw new Exception("You are not authorized to perform this function.");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationStratifications", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
