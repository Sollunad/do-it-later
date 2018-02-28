package jdbc;

import java.io.IOException;

public class CreateUserTest {
	public static void main(String[] args) {
		jdbcConnector conn = new jdbcConnector();
		String queryString = "INSERT INTO `USER`(`Username`, `Password`) VALUES (\"Test-User\", \"Passwort-Hash\")";
		try {
			conn.query(queryString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
