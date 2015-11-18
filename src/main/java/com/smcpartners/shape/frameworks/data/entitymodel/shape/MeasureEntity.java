package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;

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
@Table(name = "measure", schema = "", catalog = "shape")
@NamedQueries({
        @NamedQuery(name = "Measure.findAll",
                query = "SELECT OBJECT(m) FROM MeasureEntity m")
})
public class MeasureEntity {
    private int id;
    private String name;
    private String description;
    private String nqfId;
    private String numeratorDescription;
    private String denominatorDescription;
    private String exclusionsDescription;
    private boolean wellControlledNumerator;
    private boolean selected;
    private boolean active;

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
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Description", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "nqf_id", nullable = true, insertable = true, updatable = true, length = 75)
    public String getNqfId() {
        return nqfId;
    }

    public void setNqfId(String nqfId) {
        this.nqfId = nqfId;
    }

    @Basic
    @Column(name = "numerator_description", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNumeratorDescription() {
        return numeratorDescription;
    }

    public void setNumeratorDescription(String numeratorDescription) {
        this.numeratorDescription = numeratorDescription;
    }

    @Basic
    @Column(name = "denominator_description", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDenominatorDescription() {
        return denominatorDescription;
    }

    public void setDenominatorDescription(String denominatorDescription) {
        this.denominatorDescription = denominatorDescription;
    }

    @Basic
    @Column(name = "exclusions_description", nullable = true, insertable = true, updatable = true, length = 255)
    public String getExclusionsDescription() {
        return exclusionsDescription;
    }

    public void setExclusionsDescription(String exclusionsDescription) {
        this.exclusionsDescription = exclusionsDescription;
    }

    @Basic
    @Column(name = "well_controlled_numerator", columnDefinition = "TINYINT", length = 1, nullable = true, insertable = true, updatable = true)
    public boolean isWellControlledNumerator() {
        return wellControlledNumerator;
    }

    public void setWellControlledNumerator(boolean wellControlledNumerator) {
        this.wellControlledNumerator = wellControlledNumerator;
    }

    @Basic
    @Column(name = "selected", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Basic
    @Column(name = "active", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasureEntity that = (MeasureEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (nqfId != null ? !nqfId.equals(that.nqfId) : that.nqfId != null) return false;
        if (numeratorDescription != null ? !numeratorDescription.equals(that.numeratorDescription) : that.numeratorDescription != null)
            return false;
        if (denominatorDescription != null ? !denominatorDescription.equals(that.denominatorDescription) : that.denominatorDescription != null)
            return false;
        if (exclusionsDescription != null ? !exclusionsDescription.equals(that.exclusionsDescription) : that.exclusionsDescription != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (nqfId != null ? nqfId.hashCode() : 0);
        result = 31 * result + (numeratorDescription != null ? numeratorDescription.hashCode() : 0);
        result = 31 * result + (denominatorDescription != null ? denominatorDescription.hashCode() : 0);
        result = 31 * result + (exclusionsDescription != null ? exclusionsDescription.hashCode() : 0);
        return result;
    }
}
