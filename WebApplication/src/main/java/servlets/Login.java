package servlets;

import java.util.Random;

import javax.ws.rs.PathParam;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;

import resources.User;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
public class Login {

	@Context
	private ServletContext context;

	@GET
	@Path("/{name}")
	public boolean getCustomer(@PathParam("name") String name) {
		Random random = new Random();
		return random.nextBoolean(); // ;)
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public boolean postLogin(@FormParam("name") String name, @FormParam("password") String password) {
		return check(name, password);
	}

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public boolean putLogin(@FormParam("name") String name, @FormParam("password") String password) {
		return check(name, password);
	}

	@DELETE
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public boolean deleteCustomer(@FormParam("name") String name, @FormParam("password") String password) {
		Random random = new Random();
		return random.nextBoolean(); // ;)
	}
	
	public boolean check(String name, String password) {
		User user = new User(name);
		String hash = DigestUtils.sha256Hex(password);
		user.setPassword(hash);
		return user.exists();
	}
}