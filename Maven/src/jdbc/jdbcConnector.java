package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class jdbcConnector {
	   // JDBC driver name and database URL
	
	   static final String DB_NAME = "DB3271372";
	   static final String DB_USER = "U3271372";
	   static final String DB_PASS = "Tofuwurst42";   
	   
	   public void query(String queryString) throws IOException {
	        //Body des POST-Requests, der an das PHP-Skript geschickt werden soll
	        String body = "db=" + URLEncoder.encode(DB_NAME, "UTF-8") + "&"
	                + "user=" + URLEncoder.encode(DB_USER, "UTF-8") + "&"
	                + "pass=" + URLEncoder.encode(DB_PASS, "UTF-8") + "&"
	                + "query=" + URLEncoder.encode(queryString, "UTF-8");
	 
	        //HTTP-Verbindung mit Webserver vorbereiten
	        URL url = new URL("http://sollunad.de/do-it-later/database_api.php");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setUseCaches(false);
	        connection.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
	        connection.setRequestProperty("Content-Length", String.valueOf(body.length()));
	         
	        //Request an Webserver senden und Ergebnis ausgeben
	        try (Writer writer = new OutputStreamWriter(connection.getOutputStream())) {
	            writer.write(body);
	            writer.flush();
	            try (Scanner scanner = new Scanner(connection.getInputStream())) {
	                while (scanner.hasNextLine()) {
	                    System.out.println(scanner.nextLine());
	                }
	            }
	        }
	   }
}

