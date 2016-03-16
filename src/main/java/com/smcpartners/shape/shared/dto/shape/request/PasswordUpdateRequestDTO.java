package com.smcpartners.shape.shared.dto.shape.request;

/**
 * Responsible:<br/>
 * 1. DTO
 * <p>
 * Created by johndestefano on 12/21/15.
 * <p>
 * Changes:<b/>
 */
public class PasswordUpdateRequestDTO extends AbstractBaseRequestDTO {

    /**
     * New password
     */
    private String password;

    /**
     * User question
     */
    private String question;

    /**
     * User answer for above question
     */
    private String answer;

    public PasswordUpdateRequestDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
