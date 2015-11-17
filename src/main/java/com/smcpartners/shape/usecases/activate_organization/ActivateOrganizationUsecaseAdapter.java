package com.smcpartners.shape.usecases.activate_organization;

import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.usecases.UseCaseException;
import com.smcpartners.shape.usecases.UsecaseAdapter;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/9/15.
 * <p>
 * Changes:<b/>
 */
public interface ActivateOrganizationUsecaseAdapter extends UsecaseAdapter {

    public static final String ORGANIZATION_ID = "organization_id";

    /**
     * Get requesting user's security role
     *
     * @return
     * @throws UseCaseException
     */
    SecurityRoleEnum getRequesterSecurityRole() throws UseCaseException;

    /**
     * Activate organization
     *
     * @throws UseCaseException
     */
    void activateOrganization(int orgId) throws UseCaseException;
}
