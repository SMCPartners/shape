package com.smcpartners.shape.shared.dto.shape.response;

import java.io.Serializable;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
public class CreateUserResponseDTO implements Serializable {

    private String id;

    public CreateUserResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
