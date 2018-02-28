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
@WebServlet("/AddUser")
public class AddUserToGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddUserToGroup() {
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
		String name = request.getParameter("group_name");
		String group_id = request.getParameter("group_id");
		
		// DB Connection
		jdbcConnector jd = new jdbcConnector();
		String query_get_userid = "SELECT UserID FROM USER WHEERE Username = "+name;
		String result = jd.query(query_get_userid);
		String query_add_user = "INSERT (fk_UserID, fk_GroupID) INTO USER_IN_GROUP VALUES ("+result+", "+group_id+");";
		String query_user_already_in_table = "SELECT fk_UserID, fk_GroupID FROM USER_IN_GROUP WHERE fk_UserID = "+result+" AND fk_GroupID = "+group_id+";";
		
		if(result != null) {
			if(jd.query(query_user_already_in_table) == null){
				jd.query(query_add_user);
				response.getWriter().append("User "+name+" hinzugefügt!");
				return;
			}
			else {
				response.getWriter().append("User ist bereits in der Gruppe!");
				return;
			}
		
		}else {
			
			response.getWriter().append("User "+name+" konnte nicht gefunden werden!");
			return;
		}
	}

}
