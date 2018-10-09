

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 
 * 
 * *
 * Servlet implementation class ProcessLogin
 */
@WebServlet("/ProcessLogin")
public class ProcessLogin extends HttpServlet {
	//before
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/abcd";
	   //127.0.0.1

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "admin";
	   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("uname");
		String password = request.getParameter("upwd");
		
		if(login_valid(username, password))
		{
			pw.println("Welcome User");
		}
		else
		{
			pw.println("Invalid Username/Password. Get Lsot!!!");
		}
		
		pw.close();
	}
	


private boolean login_valid(String u_name, String u_pwd) {
	Connection conn = null;
	Statement stmt = null;
	try{	System.out.println("Loaded123");

	   //STEP 2: Register and Load JDBC driver
	   Class.forName("com.mysql.jdbc.Driver");
	System.out.println("Loaded");
	   //STEP 3: Open a connection to database server
	   System.out.println("Connecting to database...");
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
	   System.out.println("Connected to database...");
	   //STEP 4: Execute a query
	   System.out.println("Creating statement...");
	   stmt = conn.createStatement();
	  
	   String sql; 
	   sql = "SELECT uname FROM emptable where uname='"+u_name+"' and upwd='"+u_pwd+"'";
	   
	   ResultSet rs = stmt.executeQuery(sql);
	   System.out.println(rs);
	   System.out.println("After query");
	   //STEP 5: Extract data from result set
	   while(rs.next()){
		   
	     return true;
	   }      //STEP 6: Clean-up environment
	  
	   stmt.close();
	   conn.close();
	   
	   return false;
	  
	}catch(SQLException se){
	   //Handle errors for JDBC
	   se.printStackTrace();
	}catch(Exception e){
	   //Handle errors for Class.forName
	   e.printStackTrace();
	}
	System.out.println("Done...");
	
	return false;
}//end main

} 