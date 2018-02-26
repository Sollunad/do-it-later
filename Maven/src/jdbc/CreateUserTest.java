package jdbc;

public class CreateUserTest {
	public static void main(String[] args) {
		jdbcConnector conn = new jdbcConnector();
		String queryString = "INSERT INTO `USER`(`Username`, `Password`) VALUES (`Cooler Nutzername`, `Passwort-Hash`)";
		conn.query(queryString);
	}
}
