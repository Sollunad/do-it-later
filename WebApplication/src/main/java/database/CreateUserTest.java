package database;

import java.io.IOException;

public class CreateUserTest {
	public static void main(String[] args) {
		jdbcConnector conn = new jdbcConnector();
		//String queryString = "INSERT INTO `USER`(`Username`, `Password`) VALUES (\"Test-User\", \"Passwort-Hash\")";
		String queryString = "SELECT * FROM `USER`";
		try {
			String jsonReturn = conn.query(queryString);
			System.out.println(jsonReturn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
