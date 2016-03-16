package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Date;

/**
 * Responsible:</br>
 * 1.  Entity</br>
 * <p>
 * Created by johndestefano on 10/28/15.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Entity
@Table(name = "provider", schema = "", catalog = "shape")
@NamedQueries({
        @NamedQuery(name = "Provider.findAll",
                query = "SELECT OBJECT(p) FROM ProviderEntity p"),
        @NamedQuery(name = "Provider.findByOrg",
                query = "SELECT OBJECT(p) FROM ProviderEntity p " +
                        "WHERE p.organizationById = :org")
})
public class ProviderEntity {
    private int id;
    private boolean active;
    private String name;
    private Integer npi;
    private String createdBy;
    private Date modifiedDt;
    private String modifiedBy;
    private OrganizationEntity organizationById;

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
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "active", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "npi", nullable = true, insertable = true, updatable = true)
    public Integer getNpi() {
        return npi;
    }

    public void setNpi(Integer npi) {
        this.npi = npi;
    }

    @Basic
    @Column(name = "createdBy", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "modifiedDt", nullable = false, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifiedDt() {
        return modifiedDt;
    }

    public void setModifiedDt(Date modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    @Basic
    @Column(name = "modifiedBy", nullable = true, insertable = true, updatable = true, length = 45)
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProviderEntity that = (ProviderEntity) o;

        if (id != that.id) return false;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (npi != null ? !npi.equals(that.npi) : that.npi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (npi != null ? npi.hashCode() : 0);
        return result;
    }

    @OneToOne()
    @JoinColumn(name="organizationId", referencedColumnName = "id", nullable = false)
    public OrganizationEntity getOrganizationById() {
        return organizationById;
    }

    public void setOrganizationById(OrganizationEntity organizationById) {
        this.organizationById = organizationById;
    }

}
