package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Date;

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
@Entity
@Table(name = "provider_measure", schema = "", catalog = "shape")
public class ProviderMeasureEntity {
    private int id;
    private Integer numeratorValue;
    private Integer denominatorValue;
    private Date rpDate;
    private MeasureEntity measureByMeasureId;
    private ProviderEntity providerById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "numerator_value", nullable = true, insertable = true, updatable = true)
    public Integer getNumeratorValue() {
        return numeratorValue;
    }

    public void setNumeratorValue(Integer numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    @Basic
    @Column(name = "denominator_value", nullable = true, insertable = true, updatable = true)
    public Integer getDenominatorValue() {
        return denominatorValue;
    }

    public void setDenominatorValue(Integer denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rpDate", nullable = true, insertable = true, updatable = true)
    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProviderMeasureEntity that = (ProviderMeasureEntity) o;

        if (id != that.id) return false;
        if (numeratorValue != null ? !numeratorValue.equals(that.numeratorValue) : that.numeratorValue != null)
            return false;
        if (denominatorValue != null ? !denominatorValue.equals(that.denominatorValue) : that.denominatorValue != null)
            return false;
        if (rpDate != null ? !rpDate.equals(that.rpDate) : that.rpDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numeratorValue != null ? numeratorValue.hashCode() : 0);
        result = 31 * result + (denominatorValue != null ? denominatorValue.hashCode() : 0);
        result = 31 * result + (rpDate != null ? rpDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "measure_id", referencedColumnName = "id", nullable = false)
    public MeasureEntity getMeasureByMeasureId() {
        return measureByMeasureId;
    }

    public void setMeasureByMeasureId(MeasureEntity measureByMeasureId) {
        this.measureByMeasureId = measureByMeasureId;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    public ProviderEntity getProviderById() {
        return providerById;
    }

    public void setProviderById(ProviderEntity providerById) {
        this.providerById = providerById;
    }
}
