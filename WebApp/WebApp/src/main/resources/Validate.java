import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class Validate {
	/**
	 * Login Method. Returns true, if Access is granted. Returns false, if not.
	 */
	public static Boolean checkUser(String name, String password) {
		boolean result = false;
		String hashedPW = DigestUtils.sha256Hex(password);
		Connection connection = null;
		try (PreparedStatement ps = connection
				.prepareStatement("SELECT password FROM user WHERE name=? AND password=?;")) {
			ps.setString(1, name);
			ps.setString(2, hashedPW);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Checks User-Existence. Returns true, if User already exists. Returns false, if not.
	 */
	public static Boolean userExists(String name) {
		boolean result = false;
		// DB Stuff
		Connection connection = null;
		try (PreparedStatement ps = connection.prepareStatement("SELECT password FROM user WHERE name=?;")) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
