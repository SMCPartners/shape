package com.smcpartners.shape.shared.utils;

import com.smcpartners.shape.shared.constants.UseCaseConstants;
import com.smcpartners.shape.shared.dto.common.RequestResponse;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;

/**
 * Responsible:<br/>
 * 1. Various helper methods to support use cases
 * <p>
 * Created by johndestefano on 11/3/15.
 * <p>
 * <p>
 * Changes:<b/>
 * </p>
 */
public class UCHelpers {

    public static UsecaseResponse createEmptyResponse() {
        return new UsecaseResponse();
    }

    public static UsecaseResponse createResponse(String[] keys, Object... values) throws Exception {
        UsecaseResponse ucResp = new UsecaseResponse();
        loadMap(ucResp, keys, values);
        return ucResp;
    }

    public static String[] makeStringArray(String... strs) {
        if (strs == null) {
            return new String[] {};
        } else {
            return strs;
        }
    }

    public static UsecaseRequest createEmptyRequest() {
        return new UsecaseRequest();
    }

    public static UsecaseRequest createRequest(String[] keys, Object... values) throws Exception {
        UsecaseRequest ucReq = new UsecaseRequest();
        loadMap(ucReq, keys, values);
        return ucReq;
    }

    public static void setErrorResponse(UsecaseResponse response, String errorMsg) {
        response.setResponseCode(UseCaseConstants.ERROR_RESPONSE.getVal());
        response.setResponse(null);
        response.setErrMsg(errorMsg);
    }

    public static void setExceptionResponse(UsecaseResponse response, Exception errorMsg) {
        response.setResponseCode(UseCaseConstants.ERROR_RESPONSE.getVal());
        response.setErrMsg(errorMsg.getMessage());
        response.setError(errorMsg);
        response.setResponse(null);
    }

    public static void setPositiveResponse(UsecaseResponse response, Object retObj) {
        response.setResponseCode(UseCaseConstants.OK_RESPONSE.getVal());
        response.setResponse(retObj);
    }

    public static <T> T processResponse(UsecaseResponse response, Class<T> clazz) throws Exception {
        // Test for exception or error
        if (response.getErrMsg() != null) {
            throw new Exception(response.getErrMsg());
        } else if (response.getError() != null) {
            throw response.getError();
        } else if (response.isOk()) {
            return clazz.cast(response.getResponse());
        } else {
            throw new Exception("Unknown error.");
        }
    }

    public static <T> T getValue(RequestResponse resp, String key, Class<T> clazz) {
        Object val = resp.get(key);
        return clazz.cast(val);
    }

    private static void loadMap(RequestResponse reqResp, String[] keys, Object... values) throws Exception {
        if (keys.length != values.length) {
            throw new Exception("Length of keys and values must match");
        }

        for (int i=0; i<keys.length; i++) {
            reqResp.add(keys[i], values[i]);
        }
    }
}
