package com.smcpartners.shape.crosscutting.webfilters.rest;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Responsible:<br/>
 * 1. Filters http responses. Used for debugging.
 * <p>
 * Created by johndestefano on 10/15/15.
 * <p>
 * Changes:<b/>
 */
@PreMatching
@Priority(value = 1)
@Provider
public class ResponseContainerFilter implements ContainerResponseFilter {

    public ResponseContainerFilter() {
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    }
}
