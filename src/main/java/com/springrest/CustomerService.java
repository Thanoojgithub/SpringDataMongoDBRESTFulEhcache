package com.springrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.ResponseBody;


@Path("/services")
public interface CustomerService {
	
	public static final String MEDIATYPE_CHARSET = MediaType.APPLICATION_JSON + ";charset=utf-8";

    /*@GET
    @Async
    @Path("/sayHello")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#name")
    public @ResponseBody Future<String> sayHello(@QueryParam("name") String name);
    */
    
	@GET
    @Path("/sayHello")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#name")
    public @ResponseBody String sayHello(@QueryParam("name") String name);
	
    @GET
    @Path("/sayBye")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#name")
    public @ResponseBody String sayBye(@QueryParam("name") String name);
    
    @GET
    @Path("/beInTouch")
    @Produces(MEDIATYPE_CHARSET)
    @Cacheable(value = "customerServiceCache", key = "#email")
    public @ResponseBody String beInTouch(@QueryParam("email") String email);
    
    @GET
    @Path("/statistics")
    public void getCacheStatistics();
    
    
}