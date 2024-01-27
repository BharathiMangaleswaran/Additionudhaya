package com.servlet.register;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Joining")

public class Gen extends HttpServlet{
	private static final String INSERT_QUERY="INSERT INTO HARI(VALUE1,VALUE2,TOTALVALUE) VALUES(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		int number1=Integer.parseInt(req.getParameter("num1"));
		int number2=Integer.parseInt(req.getParameter("num2"));
		
		int sum=number1+number2;
		pw.println("SUM OF TWO NUMBER:"  +sum);
		
		//System.out.println("1st no:" +number1);
		//System.out.println("2nd no:"+number2);
		//System.out.println("Solution:"+sum);
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///hari","root","Udhaya23");
			PreparedStatement ps=con.prepareStatement(INSERT_QUERY);){
			ps.setInt(1, number1);
			ps.setInt(2, number2);
			ps.setInt(3, sum);
			
			int count=ps.executeUpdate();
			if(count==0) {
				pw.println("record not submit");
			}else {
				pw.println("record stored into database");
			}
		
		} catch (SQLException se) {
				pw.println(se.getMessage());
				se.printStackTrace();
			}catch (Exception e) {
				pw.println(e.getMessage());
				e.printStackTrace();
				
			}
		
		
		pw.close();
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
