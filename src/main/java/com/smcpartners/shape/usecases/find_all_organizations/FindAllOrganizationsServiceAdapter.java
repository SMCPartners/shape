package com.smcpartners.shape.usecases.find_all_organizations;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

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
public class FindAllOrganizationsServiceAdapter implements FindAllOrganizationsService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private OrganizationDAO organizationDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindAllOrganizationsServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<OrganizationDTO> findAllOrganizations() throws UseCaseException {
        try {
            List<OrganizationDTO> retLst = new ArrayList<>();

            // ADMIN role can see all organizations
            // ORG_ADMIN can see only their organization
            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(user.getRole());

            if (SecurityRoleEnum.ADMIN == reqUserRole || SecurityRoleEnum.DPH_USER == reqUserRole) {
                List<OrganizationDTO> lst = organizationDAO.findAll();
                lst.forEach(l -> {
                    OrganizationDTO dto = new OrganizationDTO();
                    dto.setId(l.getId());
                    dto.setName(l.getName());
                    dto.setActive(l.isActive());
                    dto.setAddressStreet(l.getAddressStreet());
                    dto.setAddressCity(l.getAddressCity());
                    dto.setAddressState(l.getAddressState());
                    dto.setAddressZip(l.getAddressZip());
                    dto.setPhone(l.getPhone());
                    dto.setPrimaryContactPhone(l.getPrimaryContactPhone());
                    dto.setPrimaryContactName(l.getPrimaryContactName());
                    dto.setPrimaryContactEmail(l.getPrimaryContactEmail());
                    dto.setPrimaryContactRole(l.getPrimaryContactRole());
                    retLst.add(dto);

                });
            } else if (SecurityRoleEnum.ORG_ADMIN == reqUserRole || SecurityRoleEnum.REGISTERED == reqUserRole){
                // Find the user
                UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());

                // Find the organization
                OrganizationDTO orgDTO = organizationDAO.findById(reqUser.getOrganizationId());

                // Add to lst
                OrganizationDTO dto = new OrganizationDTO();
                dto.setId(orgDTO.getId());
                dto.setName(orgDTO.getName());
                dto.setActive(orgDTO.isActive());
                dto.setAddressStreet(orgDTO.getAddressStreet());
                dto.setAddressCity(orgDTO.getAddressCity());
                dto.setAddressState(orgDTO.getAddressState());
                dto.setAddressZip(orgDTO.getAddressZip());
                dto.setPhone(orgDTO.getPhone());
                dto.setPrimaryContactPhone(orgDTO.getPrimaryContactPhone());
                dto.setPrimaryContactName(orgDTO.getPrimaryContactName());
                dto.setPrimaryContactEmail(orgDTO.getPrimaryContactEmail());
                dto.setPrimaryContactRole(orgDTO.getPrimaryContactRole());
                retLst.add(dto);
            }

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationNames", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
