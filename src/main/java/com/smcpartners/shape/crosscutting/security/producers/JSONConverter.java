package com.smcpartners.shape.crosscutting.security.producers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Responsible:<br/>
 * 1. <br/>
 * <p>
 * Created by johndestefano on 3/16/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
@ApplicationScoped
public class JSONConverter {

    private ObjectMapper om;

    public JSONConverter() {
    }

    @PostConstruct
    protected void postConstruct() {
        om = new ObjectMapper();
    }

    public ObjectMapper getMapper() {
        return om;
    }
}
