package test;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uname = req.getParameter("username");
		String pwd = req.getParameter("password");
		
		Connection conn = database.getDBConnection();
		PrintWriter pw = res.getWriter();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from user where username = ?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				String u = rs.getString("username");
				String p = rs.getString("password");
				if(u.equals(uname)){
					if(p.equals(pwd)) {
						//System.out.println("Data found");
						RequestDispatcher rd = req.getRequestDispatcher("pulkit2");
						rd.forward(req, res);
						return;
					}else {
						pw.print("Incorrect Password.\n\n");
						RequestDispatcher rd = req.getRequestDispatcher("home.html");	
						rd.include(req, res);
					}
				}else{
					pw.print("Username does not exist.\n\n");	
					RequestDispatcher rd = req.getRequestDispatcher("home.html");	
					rd.include(req, res);
				}
			}else {
				pw.print("No data found");
				RequestDispatcher rd = req.getRequestDispatcher("home.html");	
				rd.include(req, res);
			}
			
	} catch (SQLException e) {
			System.out.println("Exception in Login class");
			e.printStackTrace();
		}
	}
}
