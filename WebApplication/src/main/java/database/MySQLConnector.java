package database;

import java.sql.*;

public class MySQLConnector {
	public static Connection connection = null;
	public static final String DB_URL = "jdbc:mysql://localhost/doitlater";
	public static final String USER = "webapp";
	public static final String PASS = "qweruiop";
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			/*try {
				Class.forName("com.example.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}*/
			connection = DriverManager.getConnection(MySQLConnector.DB_URL, MySQLConnector.USER,
					MySQLConnector.PASS);
		}
		return connection;
	}
}

