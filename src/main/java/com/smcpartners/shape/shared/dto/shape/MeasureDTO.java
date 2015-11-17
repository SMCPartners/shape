package com.smcpartners.shape.shared.dto.shape;

import java.io.Serializable;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/28/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public class MeasureDTO implements Serializable {
    private int id;
    private String name;
    private String description;
    private String nqfId;
    private String numeratorDescription;
    private String denominatorDescription;
    private String exclusionsDescription;
    private boolean wellControlledNumerator;
    private boolean selected;

    public MeasureDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNqfId() {
        return nqfId;
    }

    public void setNqfId(String nqfId) {
        this.nqfId = nqfId;
    }

    public String getNumeratorDescription() {
        return numeratorDescription;
    }

    public void setNumeratorDescription(String numeratorDescription) {
        this.numeratorDescription = numeratorDescription;
    }

    public String getDenominatorDescription() {
        return denominatorDescription;
    }

    public void setDenominatorDescription(String denominatorDescription) {
        this.denominatorDescription = denominatorDescription;
    }

    public String getExclusionsDescription() {
        return exclusionsDescription;
    }

    public void setExclusionsDescription(String exclusionsDescription) {
        this.exclusionsDescription = exclusionsDescription;
    }

    public boolean isWellControlledNumerator() {
        return wellControlledNumerator;
    }

    public void setWellControlledNumerator(boolean wellControlledNumerator) {
        this.wellControlledNumerator = wellControlledNumerator;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
