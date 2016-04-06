package com.smcpartners.shape.shared.dto.shape.request;

/**
 * Responsible: Sent when the user initiates a password reset<br/>
 * 1. <br/>
 * <p>
 * Created by johndestefano on 4/5/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
public class UserPasswordResetRequestDTO extends AbstractBaseRequestDTO {
    private String questionAnswer;
    private String newPassword;
    private String oldPassword;

    public UserPasswordResetRequestDTO() {
    }

    public UserPasswordResetRequestDTO(String userId, String questionAnswer, String newPassword, String oldPassword) {
        this.questionAnswer = questionAnswer;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.setUserId(userId);
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
