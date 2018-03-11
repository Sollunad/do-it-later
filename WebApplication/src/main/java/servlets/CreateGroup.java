package servlets;

import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import resources.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Path("/CreateGroup")
public class CreateGroup {
	private static final long serialVersionUID = 1L;

    public CreateGroup() {
        // TODO Auto-generated constructor stub
    }

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Parameter
		String groupname = request.getParameter("gname");
		String userID = request.getParameter("uid");
		
		// DB Connection
		jdbcConnector jd = new jdbcConnector();
		String query = "INSERT INTO `GROUP` (GroupName, fk_GroupAdmin) VALUES ('"+groupname+"', '"+userID+"');";
		
		String res = jd.query(query);
		if(res != null) {
			response.getWriter().append("Gruppe "+groupname+" wurde erstellt!");
			return;
		}
		else {
			response.getWriter().append("Fehler beim Erstellen der Gruppe");
			return;
		}
	}*/
    
    @POST
    public void createGroup(@FormParam("name") String name, @FormParam("admin") String admin) {
    	
    	Board b = new Board(name, admin);
    	b.persist();
    	return;
    }
    
    
}