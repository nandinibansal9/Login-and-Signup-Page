package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class database {
static Connection conn = null;
	
	static {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			
			if(!conn.isClosed()) {
				System.out.println("Connection established");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Connection not established");
			e.printStackTrace();
		}
	}
	
	public static Connection getDBConnection() {	
		return conn;
	}
}
