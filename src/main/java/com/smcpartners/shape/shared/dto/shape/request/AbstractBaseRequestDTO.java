package com.smcpartners.shape.shared.dto.shape.request;

import java.io.Serializable;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/21/15.
 * <p>
 * Changes:<b/>
 */
public abstract class AbstractBaseRequestDTO implements Serializable {

    private String userId;

    public AbstractBaseRequestDTO() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
