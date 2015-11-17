package com.smcpartners.shape.shared.dto.shape;

import java.io.Serializable;
import java.util.Date;

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
public class ProviderMeasureResponseDTO implements Serializable {
    private int id;
    private int numeratorValue;
    private int denominatorValue;
    private Date rpDate;
    private int measureByMeasureId;
    private int providerId;

    public ProviderMeasureResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeratorValue() {
        return numeratorValue;
    }

    public void setNumeratorValue(int numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    public int getDenominatorValue() {
        return denominatorValue;
    }

    public void setDenominatorValue(int denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    public int getMeasureByMeasureId() {
        return measureByMeasureId;
    }

    public void setMeasureByMeasureId(int measureByMeasureId) {
        this.measureByMeasureId = measureByMeasureId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
}
