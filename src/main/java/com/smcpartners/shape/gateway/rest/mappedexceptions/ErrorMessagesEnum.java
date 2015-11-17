package com.smcpartners.shape.gateway.rest.mappedexceptions;

/**
 * Responsible:</br>
 * 1. </br
 * <p>
 * <p>
 * Created by johndestefano on 9/28/15.
 * </p>
 * <p>
 * <p>
 * Changes:<br>
 * 1.
 * </p>
 */
public enum ErrorMessagesEnum {

    Access_ERR(1, 0, "Bad userId or password"),
    InactiveUser_ERR(3, 0, "Your account has been inactivated. Contact the program administrator."),
    IllegalMethodSignatureRequiresActiveUser_ERR(4, 0, "The resource you are calling requires a different signature."),
    Bad_Credentials(5, 0, "Bad userId or password"),
    ActivityLog_ERR(6, 0, "Error logging activity."),
    SecureRequireActiveLogAvtivity_ERR(7, 0, "Error");

    private int code;

    private int httpCode;

    private String msg;

    ErrorMessagesEnum(int code, int httpCode, String msg) {
        this.code = code;
        this.httpCode = httpCode;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
