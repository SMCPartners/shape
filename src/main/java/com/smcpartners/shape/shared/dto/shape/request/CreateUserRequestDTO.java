package com.smcpartners.shape.shared.dto.shape.request;

import java.io.Serializable;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 11/2/15.
 * <p>
 * Changes:<b/>
 */
public class CreateUserRequestDTO implements Serializable {
    private String id;
    private String role;
    private String password;
    private String newPassword;
    private int organizationId;
    private String firstName;
    private String lastName;
    private String email;
    private String questionOne;
    private String questionTwo;
    private String answerOne;
    private String answerTwo;

    public CreateUserRequestDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getNewPassword() { return newPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    public String getQuestionOne() { return questionOne; }

    public void setQuestionOne(String questionOne) { this.questionOne = questionOne; }

    public String getQuestionTwo() { return questionTwo; }

    public void setQuestionTwo(String questionTwo) { this.questionTwo = questionTwo; }

    public String getAnswerOne() { return answerOne; }

    public void setAnswerOne(String answerOne) { this.answerOne = answerOne; }

    public String getAnswerTwo() { return answerTwo; }

    public void setAnswerTwo(String answerTwo) { this.answerTwo = answerTwo; }
}
