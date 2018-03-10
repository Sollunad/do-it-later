package servlets;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import resources.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateGroup
 */
//@WebServlet("/AddUser")
@Path("/AddUser")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Parameter
		jdbcConnector jd = new jdbcConnector();
		JSONObject jo = null;
		String res = null;
		String uid_json = null;
		String uid = null;
		String uname = request.getParameter("uname");
		String gid = request.getParameter("gid");
		String query_get_uid = "SELECT UserID FROM USER WHERE Username="+uname;
		System.out.println(gid);
		if(jd == null || uname == "" || gid == "") {
			response.getWriter().append("Etwas ist schiefgelaufen, versuche es sp�ter erneut!");
			return;
		}
		
		uid_json = jd.query(query_get_uid);
		uid_json = uid_json.replace("[", "");
		uid_json = uid_json.replace("]", "");
		uid_json = uid_json.replace(" ", "");
		try {
			 jo = new JSONObject(uid_json);
			uid = jo.getString("UserID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		String query = "SELECT fk_UserID, fk_GroupID FROM USER_IN_GROUP WHERE fk_UserID="+uid+" AND fk_GroupID="+gid;
		res = jd.query(query);
		if(res == null || res == "null" || res == "") {
			query = "INSERT INTO `USER_IN_GROUP` VALUES ('"+uid+"', '"+gid+"');";
			res = jd.query(query); 
			if(res != null) {
				response.getWriter().append(res);
				return;
			}
			else {
				response.getWriter().append("Fehler! Mitglied konnte nicht hinzugef�gt werden.");
				return;
			}
		}else {
			response.getWriter().append("User ist bereits in der Gruppe!");
			return;
		}
		
	}*/
	
	@POST
	public void addUser(@FormParam("uname") String uname, @FormParam("gid") int gid) {
		
		User user = new User(uname);
		Board b = new Board(gid);
		ArrayList<Board> list = (ArrayList<Board>) Board.getAllBoardsFromUser(user);
		
		
		for(Board i : list) {
			if(i.getId() == gid) {
				return;
			}
			else {
				b.add(user);
				return;
			}
		}
		
		
	}
	
	
}
