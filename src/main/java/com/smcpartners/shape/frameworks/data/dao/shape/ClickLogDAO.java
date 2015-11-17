package com.smcpartners.shape.frameworks.data.dao.shape;

import com.smcpartners.shape.crosscutting.activitylogging.dto.ClickLogDTO;
import com.smcpartners.shape.frameworks.data.dao.CrudDAO;
import com.smcpartners.shape.frameworks.data.exceptions.DataAccessException;

import java.util.List;


/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/3/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public interface ClickLogDAO extends CrudDAO<ClickLogDTO, Integer> {

    /**
     * Find the activities by user
     *
     * @param userId
     * @return
     * @throws DataAccessException
     */
    List<ClickLogDTO> findByUser(String userId) throws DataAccessException;
}
