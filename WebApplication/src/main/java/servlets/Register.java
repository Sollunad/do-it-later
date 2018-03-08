package servlets;

import javax.ws.rs.PathParam;
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
import resources.Validation;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
public class Register {
	
	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name) {		
		return User.getByName(name);
	}
	

	@POST
	//@Path("/{name}/{password}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String registerUser(@FormParam("name") String name, @FormParam("password") String password) {
		if (password.length()>5) {
			if (Validation.checkPasswordForHardness(password)) {
				User user = new User(name);
				String hash = DigestUtils.sha256Hex(password);
				user.setPassword(hash);
				user.persist();
				return "success";
			}
			return "Passwort muss eine Zahl und ein Sonderzeichen enthalten!";
		}
		return "Passwort muss mindestens 6 Zeichen lang sein!";
	}
	
	@PUT
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(@FormParam("name") String name, @FormParam("password") String password) {
		if (password.length()>5) {
			if (Validation.checkPasswordForHardness(password)) {
				User user = new User(name);
				String hash = DigestUtils.sha256Hex(password);
				user.setPassword(hash);
				user.register();
				return "success";
			}
			return "Passwort muss eine Zahl und ein Sonderzeichen enthalten!";
		}
		return "Passwort muss mindestens 6 Zeichen lang sein!";
	}
	
	@DELETE
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@FormParam("name") String name, @FormParam("password") String password) {
		User user = new User(name);
		String hash = DigestUtils.sha256Hex(password);
		user.setPassword(hash);
		user.delete();
		return "sucess";
	}
}