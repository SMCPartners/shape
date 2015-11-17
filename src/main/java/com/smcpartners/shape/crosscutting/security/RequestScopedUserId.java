package com.smcpartners.shape.crosscutting.security;

import javax.enterprise.context.RequestScoped;

/**
 * Request scoped object to hold userid
 * <p>
 * Created by johndestefano on 9/19/15.
 */
@RequestScoped
public class RequestScopedUserId {

    private String requestUserId;
    private String securityRole;


    public RequestScopedUserId() {
    }

    public RequestScopedUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }
}
