package com.smcpartners.shape.crosscutting.security.producers;

import com.smcpartners.shape.crosscutting.security.ServerUserToken;
import com.smcpartners.shape.crosscutting.security.annotations.AppServerUser;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

/**
 * Responsibilities:<br/>
 * 1. Produces the session scoped AppServerUser object<br/>
 *
 * @author John DeStefano
 * @version 1.0
 * @since May 26, 2013
 */
public class AppUserProducer {

    public AppUserProducer() {
    }

    @Produces
    @SessionScoped
    @AppServerUser
    public ServerUserToken getCurrentUser(ServerUserToken user) {
        return user;
    }
}
