package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import database.MySQLConnector;

class MySQLTest {

	/*void test() {
		Connection connection;
		String sql = "INSERT INTO user (name, password) VALUES (?, ?);";
		try {
			connection = java.sql.DriverManager.getConnection(MySQLConnector.DB_URL, MySQLConnector.USER,
					MySQLConnector.PASS);
			PreparedStatement s = connection.prepareStatement(sql);
			s.setString(1, "Vladimir");
			String hash = DigestUtils.sha256Hex("1338");
			s.setString(2, hash);
			s.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			fail("denied!");			
		}
	}*/

}
