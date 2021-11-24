package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.mysql.cj.jdbc.Driver;

public class DBUtil {
	private static String host = "localhost";
	private static String database = "jspxservlet";
	private static String user = "root";
	private static String password = "vinicius";
	private static String url = "jdbc:mysql://" + host + ":3306/" + database;
		
	public static Connection getConnection() throws SQLException, ClassNotFoundException {		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, password);
		} catch(Exception ex) {
			System.out.println("Error when getConnection: " + ex.getMessage());
		}
		
		if (connection == null) System.out.println("Cannot connect to database!");
		
		return connection;
	}
}
