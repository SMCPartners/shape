package com.smcpartners.shape.shared.dto.shape.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryanhokanson on 12/14/15.
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
