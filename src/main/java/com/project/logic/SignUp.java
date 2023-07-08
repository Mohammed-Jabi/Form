package com.project.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SignUp")
public class SignUp extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String Name = req.getParameter("name");
		int mob = Integer.parseInt(req.getParameter("mob"));
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");
		//String gender = req.getParameter("address");

		String path = "C:\\Users\\moham\\eclipse-workspace\\Form\\src\\main\\java\\com\\project\\utilities\\sql.properties";

		FileInputStream fis = null;
		Properties p = null;
		Connection con = null;

		String sql = "insert into userlist.list (name,pno,email,address,password,dob) values(?,?,?,?,?,?);";

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			fis = new FileInputStream(path);
			p = new Properties();
			p.load(fis);

			con = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1,req.getParameter("name"));
			pstmt.setInt(2, mob);
			pstmt.setString(3,req.getParameter("email"));
			pstmt.setString(4,req.getParameter("address"));
			pstmt.setString(5,req.getParameter("password"));
			pstmt.setString(6,req.getParameter("dob"));

			pstmt.executeUpdate();


		} 

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter out = resp.getWriter();
		out.println("Thanks for Registration " + req.getParameter("name"));




	}

}
