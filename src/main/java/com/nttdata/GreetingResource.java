package com.nttdata;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello-resteasy")
public class GreetingResource {

    @Inject
    Logger LOG;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("public String hello() was called.");
        return "Hello RESTEasy";
    }
}