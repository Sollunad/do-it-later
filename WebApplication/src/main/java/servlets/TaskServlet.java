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

import resources.Task;
import resources.TaskStatus;
import resources.Validation;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskServlet {
	
	@GET
	@Path("/{id}")
	public Task getTask(@PathParam("id") int id) {		
		return Task.getById(id);
	}
	

	@POST
	@Path("/{title}/{description}/{status}/{group}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTask(@FormParam("title") String title, @FormParam("description") String description, @FormParam("status") TaskStatus status, @FormParam("group") int group) {
		Task task = new Task(title, description, status, group);
		task.persist();
		return "success";
	}
	
	@PUT
	@Path("/{id}/{title}/{description}/{status}/{group}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTask(@FormParam("id") int id, @FormParam("title") String title, @FormParam("description") String description, @FormParam("status") TaskStatus status, @FormParam("group") int group) {
		Task task = new Task(id, title, description, status, group);
		task.register();
		return "success";
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTask(@FormParam("id") int id) {
		Task task = Task.getById(id);
		task.delete();
		return "sucess";
	}
}