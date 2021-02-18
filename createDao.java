package test;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class createDao extends HttpServlet{
	final  static Connection con=database.getDBConnection();
	PreparedStatement ps = null;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		try {
		ps=con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
		 ps.setString(1,req.getParameter("username"));
		 ps.setString(2,req.getParameter("email"));
		 ps.setString(3,req.getParameter("password"));
		 ps.setInt(4,Integer.parseInt(req.getParameter("age")));
		 ps.setString(5,req.getParameter("occupation"));
		 ps.setString(6,req.getParameter("gender"));
		 ps.setInt(7,Integer.parseInt(req.getParameter("salary")));	
		
		 ps.executeUpdate();
		 res.sendRedirect("home.html");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
}
