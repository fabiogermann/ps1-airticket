package org.zhaw.airticket.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.zhaw.airticket.database.Database;
import org.zhaw.airticket.model.Benutzer;
import org.zhaw.airticket.util.Crypto;
import org.zhaw.airticket.util.Errors;

@WebServlet("/registrieren")
public class Registrieren extends HttpServlet {

	private Database db = new Database();
	private static final long serialVersionUID = 1L;

	public Registrieren() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = "";
		Errors errors = new Errors();
		
		String email = request.getParameter("email");
		String passwort = request.getParameter("passwort");
		String vorname = request.getParameter("vorname");
		String name = request.getParameter("name");
		String strasse = request.getParameter("strasse");
		String ort = request.getParameter("ort");
		String postleitzahl = request.getParameter("postleitzahl");
		String telefonnummer = request.getParameter("telefonnummer");
		String land = request.getParameter("land");

		if (!EmailValidator.getInstance().isValid(email)) {
			errors.putMsg("email", Errors.TYPE_EMAIL);
		}

		if (IntegerValidator.getInstance().validate(postleitzahl) == null) {
			errors.putMsg("postleitzahl", Errors.TYPE_NUMBER);
		}

		if (LongValidator.getInstance().validate(telefonnummer) == null) {
			errors.putMsg("telefonnummer", Errors.TYPE_NUMBER);
		}
		
		if (errors.isEmpty()){
			view = "/benutzer_konto/registriert.jsp";
			Benutzer benutzer = new Benutzer(email, Crypto.hashMD5(passwort), vorname, name, strasse, ort, postleitzahl, telefonnummer, land);
			System.out.println(email + " " + passwort + " " + vorname + " " + name + " " + strasse + " " + ort + " " + postleitzahl + " " + telefonnummer + " " + land);
			
			try {
				db.addBenutzer(benutzer);
			} catch (SQLException e) {
				if (e.getMessage().contains("duplicate key")){
					errors.putMsg("general", "Benutzer bereits vorhanden");
				} else {
					//TODO
					errors.putMsg("general", e.getMessage());
				}
			}
		} 
		
		if (!errors.isEmpty()){
			view = "/benutzer/registrieren.jsp";
			request.setAttribute("errors", errors);
		}
		getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/benutzer/registrieren.jsp");
	}

}