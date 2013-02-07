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

@WebServlet("/abmelden")
public class Abmelden extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Abmelden() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetOrPost(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetOrPost(request, response);
	}
	
	protected void doGetOrPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}

}