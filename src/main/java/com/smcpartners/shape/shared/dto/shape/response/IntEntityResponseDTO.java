package com.smcpartners.shape.shared.dto.shape.response;

/**
 * Responsible:<br/>
 * 1. DTO
 * <p>
 * Created by johndestefano on 11/5/15.
 * <p>
 * Changes:<b/>
 */
public class IntEntityResponseDTO {

    private int entId;

    public IntEntityResponseDTO() {
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public static IntEntityResponseDTO makeNew(int id) {
        IntEntityResponseDTO dto = new IntEntityResponseDTO();
        dto.setEntId(id);
        return dto;
    }
}
