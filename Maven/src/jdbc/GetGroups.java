package jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 * Servlet implementation class GetGroups
 */
@WebServlet("/GetGroups")
public class GetGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGroups() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		jdbcConnector jd = new jdbcConnector();
		String userID = request.getParameter("uid");
		String query = "SELECT fk_GroupID FROM USER_IN_GROUP WHERE fk_UserID="+userID;
		String res = jd.query(query);
		res = jd.query(query);
		res = res.replace("[", "");
		res = res.replace("]", "");
		res = res.replaceAll(" ", "");
		JSONObject jo = null;
		try {
			jo = new JSONObject(res);
			res = jo.getString("fk_GroupID");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		jo = null;
		if(res != null) {
			query = "SELECT GroupName FROM `GROUP` WHERE GroupID="+res;
			res = jd.query(query);
			res = res.replace("[", "");
			res = res.replace("]", "");
			res = res.replaceAll(" ", "");
			System.out.println(res);
			try {
				jo = new JSONObject(res);
				res = jo.getString("GroupName");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			response.getWriter().append(res);
			return;
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

/*
 * TODO: res zu JSON-Objekt machen und Wert für Gruppen-IDs auslesen
 * TODO: Alle Gruppennamen holen mit den Gruppen-IDs
 * TODO: Gruppennamen in HTML einbetten
 */
