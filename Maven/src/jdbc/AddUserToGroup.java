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
		jdbcConnector jd = new jdbcConnector();
		String res = "";
		String uid = request.getParameter("uid");
		String gid = request.getParameter("gid");
		String query = "SELECT fk_UserID, fk_GroupID FROM USER_IN_GROUP WHERE fk_UserID="+uid+" AND fk_GroupID="+gid;
		//String query = "INSERT INTO `USER_IN_GROUP` VALUES ('"+uid+"', '"+gid+"');";
		if(uid == null || gid == null) {
			response.getWriter().append("Ups, etwas ist wohl schief gelaufen. Probiere es später erneut!");
			return;
		}
		if(jd != null)
			res = jd.query(query);
			System.out.println(res);
		if(res == "null" && jd != null) {
			query = "INSERT INTO `USER_IN_GROUP` VALUES ('"+uid+"', '"+gid+"');";
			res = jd.query(query); 
			if(res != null) {
				response.getWriter().append(res);
				return;
			}
			else {
				response.getWriter().append("Fehler! Mitglied konnte nicht hinzugefügt werden.");
				return;
			}
		}else {
			response.getWriter().append("User ist bereits in der Gruppe!");
			return;
		}
		
	}
}

/*
 * TODO: Prüfen, ob Kombination in Tabelle bereits vorhanden => Wenn ja, Meldung ausgeben
 */
