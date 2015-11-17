package com.smcpartners.shape.crosscutting.webfilters.rest;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 10/15/15.
 * <p>
 * Changes:<b/>
 */
@PreMatching
@Priority(value = 1)
@Provider
public class RequestContainerFilter implements ContainerRequestFilter {

    @Inject
    private Logger log;

    public RequestContainerFilter() {
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //BufferedInputStream bos = new BufferedInputStream(requestContext.getEntityStream());
        //bos.mark(0);
        //String contents = this.convertStreamToString(bos, false);
        //bos.reset();
        //bos.close();
        //log.log(Level.INFO, contents);
    }

    private String convertStreamToString(InputStream is, boolean doClose)
            throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                if (doClose) {
                    is.close();
                }
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
