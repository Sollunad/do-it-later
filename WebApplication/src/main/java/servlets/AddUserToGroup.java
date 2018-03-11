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
public class AddUserToGroup {
    public AddUserToGroup() {
        // TODO Auto-generated constructor stub
    }

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
	public void addUser(@FormParam("name") String userName, @FormParam("board") int boardId) {
		
		User user = new User(userName);
		Board b = new Board(boardId);
		ArrayList<Board> list = (ArrayList<Board>) Board.getAllBoardsFromUser(user);
		
		
		for(Board i : list) {
			if(i.getId() == boardId) {
				return;
			}
			else {
				b.add(user);
				return;
			}
		}
		
		
	}
	
	
}
