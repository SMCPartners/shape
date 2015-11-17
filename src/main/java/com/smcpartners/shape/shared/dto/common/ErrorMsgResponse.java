package com.smcpartners.shape.shared.dto.common;

import java.io.Serializable;

/**
 * Created by johndestefano on 9/28/15.
 */
public class ErrorMsgResponse implements Serializable {

    private int errCode;
    private int httpResponseCode;
    private String errMsg;

    public ErrorMsgResponse() {
    }

    public ErrorMsgResponse(int errCode, int httpResponseCode, String errMsg) {
        this.errCode = errCode;
        this.httpResponseCode = httpResponseCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(int httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }
}
