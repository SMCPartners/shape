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
@Table(name = "organization", schema = "", catalog = "shape")
@NamedQueries({
        @NamedQuery(name = "Organization.findAll",
                query = "SELECT OBJECT(o) FROM OrganizationEntity o")
})
public class OrganizationEntity {
    private int id;
    private String name;
    private boolean active;
    private String addressStreet;
    private String addressState;
    private String addressCity;
    private String addressZip;
    private String phone;
    private String primaryContactName;
    private String primaryContactEmail;
    private String primaryContactRole;
    private String primaryContactPhone;
    private String createdBy;
    private Date modifiedDt;
    private String modifiedBy;

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
    @Column(name = "active", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "address_street", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    @Basic
    @Column(name = "address_state", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    @Basic
    @Column(name = "address_city", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    @Basic
    @Column(name = "address_zip", nullable = true, insertable = true, updatable = true)
    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    @Basic
    @Column(name = "phone", nullable = true, insertable = true, updatable = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "primary_contact_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    @Basic
    @Column(name = "primary_contact_email", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPrimaryContactEmail() {
        return primaryContactEmail;
    }

    public void setPrimaryContactEmail(String primaryContactEmail) {
        this.primaryContactEmail = primaryContactEmail;
    }

    @Basic
    @Column(name = "primary_contact_role", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPrimaryContactRole() {
        return primaryContactRole;
    }

    public void setPrimaryContactRole(String primaryContactRole) {
        this.primaryContactRole = primaryContactRole;
    }

    @Basic
    @Column(name = "primary_contact_phone", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPrimaryContactPhone() {
        return primaryContactPhone;
    }

    public void setPrimaryContactPhone(String primaryContactPhone) {
        this.primaryContactPhone = primaryContactPhone;
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

        OrganizationEntity that = (OrganizationEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (addressStreet != null ? !addressStreet.equals(that.addressStreet) : that.addressStreet != null)
            return false;
        if (addressState != null ? !addressState.equals(that.addressState) : that.addressState != null) return false;
        if (addressCity != null ? !addressCity.equals(that.addressCity) : that.addressCity != null) return false;
        if (addressZip != null ? !addressZip.equals(that.addressZip) : that.addressZip != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (primaryContactName != null ? !primaryContactName.equals(that.primaryContactName) : that.primaryContactName != null)
            return false;
        if (primaryContactEmail != null ? !primaryContactEmail.equals(that.primaryContactEmail) : that.primaryContactEmail != null)
            return false;
        if (primaryContactRole != null ? !primaryContactRole.equals(that.primaryContactRole) : that.primaryContactRole != null)
            return false;
        if (primaryContactPhone != null ? !primaryContactPhone.equals(that.primaryContactPhone) : that.primaryContactPhone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (addressStreet != null ? addressStreet.hashCode() : 0);
        result = 31 * result + (addressState != null ? addressState.hashCode() : 0);
        result = 31 * result + (addressCity != null ? addressCity.hashCode() : 0);
        result = 31 * result + (addressZip != null ? addressZip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (primaryContactName != null ? primaryContactName.hashCode() : 0);
        result = 31 * result + (primaryContactEmail != null ? primaryContactEmail.hashCode() : 0);
        result = 31 * result + (primaryContactRole != null ? primaryContactRole.hashCode() : 0);
        result = 31 * result + (primaryContactPhone != null ? primaryContactPhone.hashCode() : 0);
        return result;
    }
}
