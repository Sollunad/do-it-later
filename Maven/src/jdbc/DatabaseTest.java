package jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseTest
 */
@WebServlet("/DatabaseTest")
public class DatabaseTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseTest() {
        super();
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
		jdbcConnector jd = new jdbcConnector();
		String res = "";
		String uid = request.getParameter("uid");
		String gid = request.getParameter("gid");
		//String query = "SELECT * FROM `GROUP`";
		String query = "INSERT INTO `USER_IN_GROUP` VALUES ('"+uid+"', '"+gid+"');";
		//String query = "SELECT * FROM USER";
		//String query = "INSERT INTO `GROUP` VALUES (1, 'Testuser', 1);";
		//String query = "SELECT * FROM USER_IN_GROUP";
		res = jd.query(query);
		if(res != null) {
			response.getWriter().append(res);
			return;
		}
		else {
			response.getWriter().append("Fehler");
			return;
		}
		
		
	}

}
