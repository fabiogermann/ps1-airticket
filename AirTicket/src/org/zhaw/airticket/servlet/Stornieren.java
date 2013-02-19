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
import org.zhaw.airticket.model.Ticket;
import org.zhaw.airticket.util.Crypto;
import org.zhaw.airticket.util.Errors;

@WebServlet("/stornieren")
public class Stornieren extends HttpServlet {

	private Database db = new Database();
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/benutzer_konto/konto.jsp";
	private static final String ERROR_MSG = "Ticket konnte nicht Storniert werden";

	public Stornieren() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Errors errors = new Errors();
		Benutzer benutzer = (Benutzer) request.getSession().getAttribute("Benutzer");

		String ticketId = request.getParameter("ticketId");
		Integer ticketIdInt = IntegerValidator.getInstance().validate(ticketId);

		if (ticketIdInt != null && ticketIdInt != 0) {
			
			try {
				
				Ticket ticket = benutzer.removeTicket(ticketIdInt);
				if (ticket == null){
					errors.putMsg("general",ERROR_MSG);
				} else {
					if (!db.removeTicket(ticket.getId())){
						errors.putMsg("general",ERROR_MSG);
					}
				}
				
			} catch (SQLException e) {
				// TODO
				errors.putMsg("general", e.getMessage());
			}
			
		} else {
			errors.putMsg("ticketId", Errors.TYPE_NUMBER);
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}

		getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + VIEW);
	}

}