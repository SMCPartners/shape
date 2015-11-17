package com.smcpartners.shape.shared.dto.shape;

import com.smcpartners.shape.frameworks.data.entitymodel.shape.OrganizationStratificationEntity;

import java.io.Serializable;
import java.util.Collection;

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
public class OrganizationDTO implements Serializable {

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
    private Integer primaryContactPhone;

    public OrganizationDTO() {
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

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getPrimaryContactEmail() {
        return primaryContactEmail;
    }

    public void setPrimaryContactEmail(String primaryContactEmail) {
        this.primaryContactEmail = primaryContactEmail;
    }

    public String getPrimaryContactRole() {
        return primaryContactRole;
    }

    public void setPrimaryContactRole(String primaryContactRole) {
        this.primaryContactRole = primaryContactRole;
    }

    public Integer getPrimaryContactPhone() {
        return primaryContactPhone;
    }

    public void setPrimaryContactPhone(Integer primaryContactPhone) {
        this.primaryContactPhone = primaryContactPhone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
