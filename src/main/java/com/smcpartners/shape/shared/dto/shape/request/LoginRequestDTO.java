package com.smcpartners.shape.shared.dto.shape.request;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/21/15.
 * <p>
 * Changes:<b/>
 */
public class LoginRequestDTO extends AbstractBaseRequestDTO {

    private String password;

    public LoginRequestDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
