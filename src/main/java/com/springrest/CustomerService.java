package com.springrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/services")
public interface CustomerService {
	
	public static final String MEDIATYPE_CHARSET = MediaType.APPLICATION_JSON + ";charset=utf-8";

    @GET
    @Path("/sayHello")
    @Produces(MEDIATYPE_CHARSET)
    public String sayHello(@QueryParam("name") String name);
}

