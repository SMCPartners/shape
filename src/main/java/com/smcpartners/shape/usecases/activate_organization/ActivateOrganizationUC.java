package com.smcpartners.shape.usecases.activate_organization;


import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.utils.UCHelpers;
import com.smcpartners.shape.shared.usecasecommon.AbstractUsecase;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

/**
 * Responsible:<br/>
 * 1. Only ADMIN can activate an organization</br>
 * 2. If the user is an admin then activate the requested organization</br>
 * <p>
 * Created by johndestefano on 10/9/15.
 * <p>
 * Changes:<b/>
 */
public class ActivateOrganizationUC extends AbstractUsecase<ActivateOrganizationUsecaseAdapter> {

    public ActivateOrganizationUC(ActivateOrganizationUsecaseAdapter serviceAdapter) {
        super(serviceAdapter);
    }

    @Override
    public UsecaseResponse processRequest(UsecaseRequest request) throws UseCaseException {
        UsecaseResponse response = UCHelpers.createEmptyResponse();
        try {
            // Check users role
            int orgId = UCHelpers.getValue(request, ActivateOrganizationServiceAdapter.ORGANIZATION_ID, Integer.class);
            SecurityRoleEnum userRole = serviceAdapter.getRequesterSecurityRole();
            if (userRole == SecurityRoleEnum.ADMIN) {
                // Activate organization
                serviceAdapter.activateOrganization(orgId);
                // Set response to OK
                UCHelpers.setPositiveResponse(response, new BooleanValueDTO(true));
            } else {
                UCHelpers.setErrorResponse(response, "You are not authorized to perform this function.");
            }
        } catch (Exception e) {
            UCHelpers.setExceptionResponse(response, e);
        }
        return response;
    }
}
