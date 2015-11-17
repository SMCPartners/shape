package com.smcpartners.shape.frameworks.data.dao.shape;

import com.smcpartners.shape.frameworks.data.dao.CrudDAO;
import com.smcpartners.shape.frameworks.data.exceptions.DataAccessException;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;

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
public interface MeasureDAO extends CrudDAO<MeasureDTO, Integer> {

    /**
     * Change the select status of the measure
     *
     * @param id
     * @param status
     * @throws DataAccessException
     */
    void changeMeasureSelectStatus(int id, boolean status) throws DataAccessException;


    /**
     * Return all measures
     *
     * @return
     * @throws DataAccessException
     */
    List<MeasureDTO> findAllMeasures() throws DataAccessException;
}
