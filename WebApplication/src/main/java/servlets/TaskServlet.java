package servlets;

import javax.ws.rs.PathParam;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import resources.Task;
import resources.User;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskServlet {
	
	@GET
	@Path("/{id}")
	public Task getTask(@PathParam("id") int id) {		
		return Task.getById(id);
	}
	

	@POST
	@Path("/{title}/{description}/{status}/{group}/{bearbeiter}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTask(@PathParam("title") String title, @PathParam("description") String description, @PathParam("status") String status, @PathParam("group") int group, @PathParam("status") String bearbeiter) {
		Date timestamp = new Date();
		String tsString = timestamp.toLocaleString();
		User bearbeiterUser = User.getByName(bearbeiter);
		Task task = new Task(title, description, status, bearbeiterUser, group, tsString);
		task.persist();
		return "success";
	}
	
	@PUT
	@Path("/{id}/{title}/{description}/{status}/{group}/{bearbeiter}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTask(@PathParam("id") int id, @PathParam("title") String title, @PathParam("description") String description, @PathParam("status") String status, @PathParam("group") int group, @PathParam("status") String bearbeiter) {
		Task oldTask = Task.getById(id);
		oldTask.setTitle(title);
		oldTask.setDescription(description);
		oldTask.setStatus(status);
		oldTask.setGroup(group);
		oldTask.setBearbeiter(User.getByName(bearbeiter));
		oldTask.register();
		return "success";
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTask(@PathParam("id") int id) {
		Task task = Task.getById(id);
		task.delete();
		return "sucess";
	}
}