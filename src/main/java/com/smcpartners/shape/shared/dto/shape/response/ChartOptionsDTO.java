package com.smcpartners.shape.shared.dto.shape.response;

/**
 * Responsible:</br>
 * 1. DTO</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public class ChartOptionsDTO {
    private String pieHole;
    private String pieSliceText;

    public String getPieHole() {
        return pieHole;
    }

    public void setPieHole(String pieHole) {
        this.pieHole = pieHole;
    }

    public String getPieSliceText() {
        return pieSliceText;
    }

    public void setPieSliceText(String pieSliceText) {
        this.pieSliceText = pieSliceText;
    }
}
