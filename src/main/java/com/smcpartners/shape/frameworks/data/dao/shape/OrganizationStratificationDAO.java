package com.smcpartners.shape.frameworks.data.dao.shape;

import com.smcpartners.shape.frameworks.data.dao.CrudDAO;
import com.smcpartners.shape.frameworks.data.exceptions.DataAccessException;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;

import java.util.List;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/29/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public interface OrganizationStratificationDAO extends CrudDAO<OrganizationStratificationDTO, Integer> {

    /**
     * @return
     * @throws DataAccessException
     */
    List<OrganizationStratificationDTO> findAllOrganizationStratification() throws DataAccessException;

    /**
     * @return
     * @throws DataAccessException
     */
    List<OrganizationStratificationDTO> findAllOrganizationStratificationByOrgId(int orgId) throws DataAccessException;
}
