package com.smcpartners.shape.shared.dto.shape.request;

import java.io.Serializable;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/4/15.
 * <p>
 * Changes:<b/>
 */
public class IntEntityIdRequestDTO implements Serializable {
    private int entId;

    public IntEntityIdRequestDTO() {
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }
}
