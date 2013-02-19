package org.zhaw.airticket.debug;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/viewdb")
public class ViewTestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewTestDB() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doIt(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doIt(request, response);
	}
	
	protected void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		Statement stmt = null;
		Connection conn = null;
		try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/airdb");
				conn = ds.getConnection();
			 stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (NamingException e) {
			e.printStackTrace();
			return;
		}
		
		try {	
			ResultSet rs = stmt.executeQuery("SELECT * FROM Benutzer");
			
			System.out.println("");
			System.out.println("SELECT * FROM Benutzer");
			System.out.println("email passwort");
			while (rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9));
			} 
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			ResultSet rs =  stmt.executeQuery("SELECT * FROM Rolle");
			System.out.println("");
			System.out.println("SELECT * FROM Rolle");
			System.out.println("email rollen");
			while (rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			ResultSet rs =  stmt.executeQuery("SELECT * FROM Ticket");
			System.out.println("");
			System.out.println("SELECT * FROM Ticket");
			System.out.println("id, datum, sitzreihe, sitzspalte, klasse, flug_fs, benutzer_fs");
			while (rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
				
	}

}
