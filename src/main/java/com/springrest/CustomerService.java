package com.springrest;
/**
 * to do CRUD operations on User document/table - entity in general. 
 */
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.vo.User;

@Path("/services")
@RestController
public interface CustomerService {

	public static final String MEDIATYPE_CHARSET = MediaType.APPLICATION_JSON + ";charset=utf-8";

	/**
	 * sayHello is a test resource path - to just know whether service is up or not
	 * @param name
	 * @return
	 */
	@GET
	@Path("/sayHello")
	@Produces(MEDIATYPE_CHARSET)
	@Cacheable(value = "customerServiceCache", key = "#name")
	public String sayHello(@QueryParam("name") String name);

	@GET
	@Path("/getUser")
	@Produces(MEDIATYPE_CHARSET)
	public User getUser(@QueryParam("id") Long id);
	
	@GET
	@Path("/getUsers")
	@Produces(MEDIATYPE_CHARSET)
	public List<User> getUsers();

	@DELETE
	@Path("/deleteUser")
	@Produces(MEDIATYPE_CHARSET)
	public Boolean deleteUser(@QueryParam("id") Long id);

	@PUT
	@Path("/upsertUser")
	@Produces(MEDIATYPE_CHARSET)
	public void upsertUser(@QueryParam("id")Long id, @QueryParam("firstName")String firstName, @QueryParam("lastName")String lastName, @QueryParam("location")String location);

	/**
	 * to get cache details, TO-DO : need to work more on it.
	 * @return
	 */
	@GET
	@Path("/statistics")
	@Produces(MEDIATYPE_CHARSET)
	public String getCacheStatistics();

}