import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class Validate {
	public static Boolean checkUser(String name, String password) {
		boolean result = false;
		try {
			String hashedPW = DigestUtils.sha256Hex(password);  
			
			//DB Stuff
			Connection connection = null;
			try (PreparedStatement ps = connection.prepareStatement("SELECT password FROM user WHERE name=? AND password=?")) {
				ps.setString(1, name);
				ps.setString(2, hashedPW);
				ResultSet rs = ps.executeQuery();
				while (rs.next())
					result = true;
			} catch (SQLException e) {
				e.printStackTrace();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
