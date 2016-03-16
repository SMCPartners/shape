package com.smcpartners.shape.crosscutting.email;

import java.io.Serializable;

/**
 * Responsible:</br>
 * 1. Holds data for mail messages.</br>
 * <p>
 * Created by johndestefano on 10/6/15.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public class MailDTO implements Serializable {

    /**
     * How the email should be sent to
     */
    private String toEmail;

    /**
     * Email subject
     */
    private String subject;

    /**
     * Message body of email
     */
    private String message;

    /**
     * Default constructor
     */
    public MailDTO() {
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
