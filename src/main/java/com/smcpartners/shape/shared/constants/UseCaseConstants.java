package com.smcpartners.shape.shared.constants;

/**
 * Responsible:<br/>
 * 1. Constants used to support use cases
 * <p>
 * Created by johndestefano on 11/13/15.
 * <p>
 * Changes:<b/>
 */
public enum UseCaseConstants {
    USER_ID("user_id"), USER_CASE_RESPONSE("user_case_response"),
    OK_RESPONSE("ok_response"), ERROR_RESPONSE("error_response");

    private String val;

    UseCaseConstants(String val){
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
