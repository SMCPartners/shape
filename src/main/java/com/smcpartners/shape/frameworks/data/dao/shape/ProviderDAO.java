package com.smcpartners.shape.frameworks.data.dao.shape;

import com.smcpartners.shape.frameworks.data.dao.CrudDAO;
import com.smcpartners.shape.frameworks.data.exceptions.DataAccessException;
import com.smcpartners.shape.shared.dto.shape.ProviderDTO;

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
public interface ProviderDAO extends CrudDAO<ProviderDTO, Integer> {

    /**
     * Find all providers
     *
     * @return
     * @throws DataAccessException
     */
    List<ProviderDTO> findAll() throws DataAccessException;

    /**
     * Find all providers by organization
     *
     * @return
     * @throws DataAccessException
     */
    List<ProviderDTO> findAllByOrg(int orgId) throws DataAccessException;

    /**
     * Inactivate the organization
     *
     * @param id
     * @throws DataAccessException
     */
    void changeProviderActiveStatus(int id, boolean status) throws DataAccessException;
}
