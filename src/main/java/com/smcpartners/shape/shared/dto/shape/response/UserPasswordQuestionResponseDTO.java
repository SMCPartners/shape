package com.smcpartners.shape.shared.dto.shape.response;

import com.smcpartners.shape.shared.dto.shape.request.AbstractBaseRequestDTO;

/**
 * Responsible: Returns a password question for a user that the
 * user previously answered<br/>
 * 1. <br/>
 * <p>
 * Created by johndestefano on 4/5/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
public class UserPasswordQuestionResponseDTO extends AbstractBaseRequestDTO {

    private String passwordQuestion;

    public UserPasswordQuestionResponseDTO() {
    }

    public UserPasswordQuestionResponseDTO(String userId, String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
        this.setUserId(userId);
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
    }
}
