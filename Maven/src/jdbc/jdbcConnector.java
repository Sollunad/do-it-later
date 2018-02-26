package jdbc;

import java.sql.*;

public class jdbcConnector {
	   // JDBC driver name and database URL
	
	   /*static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://rdbms.strato.de/DB3271372";
	   static final String USER = "U3271372";
	   static final String PASS = "Tofuwurst42";*/
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/doitlater";
	   static final String USER = "root";
	   static final String PASS = "";
	   
	   public ResultSet query(String queryString) {	   
		   Connection conn = null;
		   Statement stmt = null;
		   try {
		      Class.forName(JDBC_DRIVER);
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(queryString);
	
		      rs.close();
		      stmt.close();
		      conn.close();
		      return rs;
		   } catch(SQLException se) {
		      //Handle errors for JDBC
		      se.printStackTrace();
		   } catch(Exception e) {
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   } finally {
		      //finally block used to close resources
		      try {
		         if(stmt!=null) stmt.close();
		      } catch(SQLException se){ }
		      try {
		         if(conn!=null) conn.close();
		      } catch(SQLException se){ }
		   }
		   return null;
	   }
}

