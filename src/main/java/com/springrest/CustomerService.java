package com.springrest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springrest.vo.User;

@Path("/services")
public interface CustomerService {

	public static final String MEDIATYPE_CHARSET = MediaType.APPLICATION_JSON + ";charset=utf-8";

	@GET
	@Path("/sayHello")
	@Produces(MEDIATYPE_CHARSET)
	@Cacheable(value = "customerServiceCache", key = "#name")
	public @ResponseBody String sayHello(@QueryParam("name") String name);

	@GET
	@Path("/getUser")
	@Produces(MEDIATYPE_CHARSET)
	public @ResponseBody User getUser(@QueryParam("id") String id);

	@DELETE
	@Path("/deleteUser")
	@Produces(MEDIATYPE_CHARSET)
	public @ResponseBody User deleteUser(@QueryParam("id") String id);

	@PUT
	@Path("/upsertUser")
	@Produces(MEDIATYPE_CHARSET)
	public @ResponseBody User upsertUser(@QueryParam("id")String id, @QueryParam("firstName")String firstName, @QueryParam("lastName")String lastName, @QueryParam("location")String location);

	@GET
	@Path("/statistics")
	public void getCacheStatistics();

}