package com.smcpartners.shape.usecases.create_user_account;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.CreateUserRequestDTO;
import com.smcpartners.shape.shared.dto.shape.response.CreateUserResponseDTO;
import com.smcpartners.shape.shared.utils.RandomPasswordGenerator;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.usecases.UseCaseException;

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
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class AuthCreateUserAccountServiceAdapter implements AuthCreateUserAccountService {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;


    public AuthCreateUserAccountServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN})
    public CreateUserResponseDTO createUserAccount(CreateUserRequestDTO dto) throws UseCaseException {
        try {
            // Check account id to make sure its not in use
            BooleanValueDTO checkRetDTO = userDAO.checkUserId(dto.getId());

            // Get the user trying to do the create
            UserDTO reqUser = userDAO.findById(requestScopedUserId.getRequestUserId());

            if (!checkRetDTO.isValue()) {
                // Load this object
                UserDTO nDTO = new UserDTO();

                // Check the role being set
                // To set the ADMIN role you must be an ADMIN
                // To set the ORG_ADMIN role you must be and ADMIN or
                // An ORG_ADMIN and the new user account must have the
                // same org id and the user creating the account
                SecurityRoleEnum reqUserRole = SecurityRoleEnum.valueOf(reqUser.getRole());
                int reqUserOrgId = reqUser.getOrganizationId();
                int targetUserOrgId = dto.getOrganizationId();
                SecurityRoleEnum targetUserRole = SecurityRoleEnum.valueOf(dto.getRole());
                if (reqUserRole == SecurityRoleEnum.ADMIN) {
                    nDTO.setRole(dto.getRole());
                    nDTO.setAdmin(true);
                } else if (targetUserOrgId == reqUserOrgId && targetUserRole != SecurityRoleEnum.ADMIN) {
                    nDTO.setRole(dto.getRole());
                    nDTO.setAdmin(false);
                } else {
                    throw new Exception("You don't have the authority to create a user with this role.");
                }

                // Creating a new user sets the password to a random generated one
                String pWd = RandomPasswordGenerator.generateApplicationDefaultPwd();

                // Set the UserDTO
                // New account must reset password
                // New account is active by default
                // New account has fake password
                nDTO.setResetPwd(true);
                nDTO.setActive(true);
                nDTO.setCreateDt(new Date());
                nDTO.setEmail(dto.getEmail());
                nDTO.setFirstName(nDTO.getFirstName());
                nDTO.setLastName(dto.getLastName());
                nDTO.setOrganizationId(dto.getOrganizationId());
                nDTO.setId(dto.getId());
                nDTO.setPassword(pWd);
                UserDTO respDTO = userDAO.create(nDTO);

                // Generate return
                CreateUserResponseDTO createUserRespDTO = new CreateUserResponseDTO();
                createUserRespDTO.setId(respDTO.getId());
                return createUserRespDTO;
            } else {
                throw new Exception("User id is in use.");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "createUserAccount", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
