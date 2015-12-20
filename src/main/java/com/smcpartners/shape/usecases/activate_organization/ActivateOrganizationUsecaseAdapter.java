package com.smcpartners.shape.usecases.activate_organization;

import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.usecasecommon.FrameworkUsecaseAdapter;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/9/15.
 * <p>
 * Changes:<b/>
 */
public interface ActivateOrganizationUsecaseAdapter extends FrameworkUsecaseAdapter {

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
