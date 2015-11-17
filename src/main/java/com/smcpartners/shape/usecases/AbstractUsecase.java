package com.smcpartners.shape.usecases;


import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;

/**
 * Responsible:<br/>
 * 1. Abstract class which use cases extend. <br/>
 * 2. Provides dao to the claas that implements the UsecaseSericeAdaper<br/>
 * <p>
 * Created by johndestefano on 10/9/15.
 * <p>
 * Changes:<b/>
 */
public abstract class AbstractUsecase<S extends UsecaseAdapter> {

    protected S serviceAdapter;

    public AbstractUsecase(S serviceAdapter) {
        this.serviceAdapter = serviceAdapter;
    }

    public abstract UsecaseResponse processRequest(UsecaseRequest request) throws UseCaseException;
}
