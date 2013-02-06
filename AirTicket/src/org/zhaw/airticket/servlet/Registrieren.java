package org.zhaw.airticket.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zhaw.airticket.database.Database;
import org.zhaw.airticket.model.Benutzer;

@WebServlet("/registrieren")
public class Registrieren extends HttpServlet {
	
	private Database db = new Database();
	private static final long serialVersionUID = 1L;

	public Registrieren() {
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String email = request.getParameter("email");
		 String passwort = request.getParameter("passwort");
		 String vorname = request.getParameter("vorname");
		 String name = request.getParameter("name");
		 String strasse = request.getParameter("strasse");
		 String ort = request.getParameter("ort");
		 int postleitzahl = Integer.parseInt(request.getParameter("postleitzahl")); //TODO
		 long telefonnummer = Long.parseLong(request.getParameter("telefonnummer")); //TODO
		 String land = request.getParameter("land");
		 
		 Benutzer benutzer = new Benutzer(email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land);
		
		 System.out.println(email+" "+passwort+" "+vorname+" "+ name+" "+ strasse+" "+ ort+" "+ postleitzahl+" "+ telefonnummer+" "+ land);
		 
		 db.addBenutzer(benutzer);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}