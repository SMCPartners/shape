package com.smcpartners.shape.shared.dto.shape;

import java.io.Serializable;
import java.util.Date;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 9/11/15.
 * <p>
 * Changes:<b/>
 */
public class UserDTO implements Serializable {
    private String id;
    private String role;
    private boolean admin;
    private Date createDt;
    private String password;
    private boolean active;
    private boolean resetPwd;
    private int organizationId;
    private String organizationName;
    private String firstName;
    private String lastName;
    private String email;
    private String questionOne;
    private String questionTwo;
    private String answerOne;
    private String answerTwo;

    /**
     * Constructor
     */
    public UserDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isResetPwd() {
        return resetPwd;
    }

    public void setResetPwd(boolean resetPwd) {
        this.resetPwd = resetPwd;
    }

    public String getOrganizationName() { return organizationName; }

    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public String getQuestionOne() { return questionOne; }

    public void setQuestionOne(String questionOne) { this.questionOne = questionOne; }

    public String getQuestionTwo() { return questionTwo; }

    public void setQuestionTwo(String questionTwo) { this.questionTwo = questionTwo; }

    public String getAnswerOne() { return answerOne; }

    public void setAnswerOne(String answerOne) { this.answerOne = answerOne; }

    public String getAnswerTwo() { return answerTwo; }

    public void setAnswerTwo(String answerTwo) { this.answerTwo = answerTwo; }
}
