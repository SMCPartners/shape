package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Responsible:</br>
 * 1. Entity </br>
 * <p>
 * Created by johndestefano on 10/28/15.
 * </p>
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
    private String createdBy;
    private Date modifiedDt;
    private String modifiedBy;
    private String passwordSalt;
    private String passwordDigest;
    private boolean active;
    private boolean resetPwd;
    private boolean generatedPwd;
    private Date generatedPwdDt;
    private String questionOne;
    private String questionTwo;
    private String answerOne;
    private String answerTwo;
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
    @Column(name = "generatedPwd", columnDefinition = "TINYINT", length = 1, nullable = false, insertable = true, updatable = true)
    public boolean isGeneratedPwd() {
        return generatedPwd;
    }

    public void setGeneratedPwd(boolean generatedPwd) {
        this.generatedPwd = generatedPwd;
    }

    @Basic
    @Column(name = "generatedPwdDt", nullable = false, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getGeneratedPwdDt() {
        return generatedPwdDt;
    }

    public void setGeneratedPwdDt(Date generatedPwdDt) {
        this.generatedPwdDt = generatedPwdDt;
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

    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

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

    @Basic
    @Column(name = "question_1", nullable = true, insertable = true, updatable = true, length = 45)
    public String getQuestionOne() { return questionOne; }

    public void setQuestionOne (String questionOne) {
        this.questionOne = questionOne;
    }

    @Basic
    @Column(name = "question_2", nullable = true, insertable = true, updatable = true, length = 45)
    public String getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo (String questionTwo) {
        this.questionTwo = questionTwo;
    }

    @Basic
    @Column(name = "answer_1", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne (String answerOne) {
        this.answerOne = answerOne;
    }

    @Basic
    @Column(name = "answer_2", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
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
