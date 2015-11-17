package com.smcpartners.shape.shared.dto.common;

import java.io.Serializable;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/6/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public class BooleanValueDTO implements Serializable {

    private boolean value;

    public void BooleanValueDTO() {
    }

    public BooleanValueDTO(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
