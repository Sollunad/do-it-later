package jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateGroup() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Parameter
		String groupname = request.getParameter("group_name");
		String username = request.getParameter("user_name");
		
		// DB Connection
		jdbcConnector jd = new jdbcConnector();
		String query_get_userid = "SELECT UserID FROM USER WHERE UserID = "+username+";";
		String query_add_group = "INSERT INTO GROUP (GroupName, fk_GroupAdmin) VALUES ("+groupname+", "+username+");";
		String userid = jd.query(query_get_userid);
		
		if(userid != null) {
			if(jd.query(query_add_group) == null) {
				response.getWriter().append("Gruppe konnte nicht hinzugefügt werden");
				return;
			}
			else {
				response.getWriter().append("Gruppe "+groupname+" hinzugefügt");
				return;
			}
		}
		else {
			response.getWriter().append("User konnte nicht eindeutig identifiziert werden");
			return;
		}
	}
}
