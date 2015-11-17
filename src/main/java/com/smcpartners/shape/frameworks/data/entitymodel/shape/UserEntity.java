package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Collection;
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
@Table(name = "user", schema = "", catalog = "shape")
@NamedQueries({
        @NamedQuery(name = "User.findAll",
                query = "SELECT OBJECT(u) FROM UserEntity u"),
        @NamedQuery(name = "User.findByOrg",
                query = "SELECT OBJECT(u) FROM UserEntity u " +
                        "where u.organizationById = :org")
})
public class UserEntity {
    private String id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private boolean admin;
    private Date createDt;
    private String passwordSalt;
    private String passwordDigest;
    private boolean active;
    private boolean resetPwd;
    private Collection<UserProviderEntity> userProvidersById;
    private OrganizationEntity organizationById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 25)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role", nullable = false, insertable = true, updatable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "admin", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
    @Column(name = "resetPwd", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isResetPwd() {
        return resetPwd;
    }

    public void setResetPwd(boolean resetPwd) {
        this.resetPwd = resetPwd;
    }

    @Basic
    @Column(name = "passwordSalt", nullable = false, insertable = true, updatable = true, length = 100)
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Basic
    @Column(name = "passwordDigest", nullable = false, insertable = true, updatable = true, length = 100)
    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    @Basic
    @Column(name = "createDt", nullable = false, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Basic
    @Column(name = "firstName", nullable = true, insertable = true, updatable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = false, insertable = true, updatable = true, length = 75)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserProviderEntity> getUserProvidersById() {
        return userProvidersById;
    }

    public void setUserProvidersById(Collection<UserProviderEntity> userProvidersById) {
        this.userProvidersById = userProvidersById;
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
