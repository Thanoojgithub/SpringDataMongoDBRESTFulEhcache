package com.springrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.cache.annotation.Cacheable;


@Path("/services")
public interface CustomerService {
	
	public static final String MEDIATYPE_CHARSET = MediaType.APPLICATION_JSON + ";charset=utf-8";

    @GET
    @Path("/sayHello")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#name")
    public String sayHello(@QueryParam("name") String name);
    
    @GET
    @Path("/sayBye")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#name")
    public String sayBye(@QueryParam("name") String name);
    
    @GET
    @Path("/beInTouch")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#email")
    public String beInTouch(@QueryParam("email") String email);
}