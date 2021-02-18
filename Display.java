package test;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Display extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter pw = res.getWriter();
		String uname = req.getParameter("username");
		Connection conn = database.getDBConnection();
        pw.println("<h1>Welcome " + uname + "</h1>");
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from user where username = ?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String uName = rs.getString(1);
				String email=rs.getString(2);
				int age = rs.getInt(4);
				String occ = rs.getString(5);
				String gen = rs.getString(6);
				int sal = rs.getInt(7);
				String str = "Username: " +  uName + "<br> Email: "+email+"<br> Age: " + age + "<br> Occupation: " + occ +"<br> Gender: " + gen +  "<br> Salary: " + sal;
				pw.println(str);
			}
			else {
				pw.println("No data found");
			}			
			
		} catch (SQLException e) {
			System.out.println("Exception in Display class");
			e.printStackTrace();
		}
		
		
	}
}
