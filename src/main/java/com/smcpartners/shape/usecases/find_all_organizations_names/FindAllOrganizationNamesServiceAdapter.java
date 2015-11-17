package com.smcpartners.shape.usecases.find_all_organizations_names;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.response.OrganizationNameResponseDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
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
public class FindAllOrganizationNamesServiceAdapter implements FindAllOrganizationNamesService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationDAO organizationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllOrganizationNamesServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public List<OrganizationNameResponseDTO> findAllOrganizationNames() throws UseCaseException {
        try {
            List<OrganizationNameResponseDTO> retLst = new ArrayList<>();

            // ADMIN role can see all organizations
            // ORG_ADMIN can see only their organization
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(requestScopedUserId.getRequestUserId());

            if (SecurityRoleEnum.ADMIN == reqUserRole) {
                List<OrganizationDTO> lst = organizationDAO.findAll();
                lst.forEach(l -> {
                    OrganizationNameResponseDTO dto = new OrganizationNameResponseDTO();
                    dto.setId(l.getId());
                    dto.setName(l.getName());
                    dto.setActive(l.isActive());
                    retLst.add(dto);
                });
            } else if (SecurityRoleEnum.ORG_ADMIN == reqUserRole){
                // Find the user
                UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());

                // Find the organization
                OrganizationDTO orgDTO = organizationDAO.findById(reqUser.getOrganizationId());

                // Add to lst
                OrganizationNameResponseDTO dto = new OrganizationNameResponseDTO();
                dto.setId(orgDTO.getId());
                dto.setName(orgDTO.getName());
                dto.setActive(orgDTO.isActive());
                retLst.add(dto);
            }

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationNames", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
