package org.zhaw.airticket.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zhaw.airticket.database.Database;
import org.zhaw.airticket.model.Flug;
import org.zhaw.airticket.util.Errors;

@WebServlet("/suchen")
public class Suchen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/flug/auswaehlen.jsp";

    public Suchen() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + VIEW);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Flug> hinfluege = new ArrayList<Flug>();
	   	ArrayList<Flug> rueckfluege = new ArrayList<Flug>(); 
	   	Database database = new Database();
		Errors errors = new Errors();
		Date today = new Date();
	   	String dep = request.getParameter("departure_code");
	   	String dest = request.getParameter("destination_code");
	   	String dateDepS = request.getParameter("date_flight");
	   	String dateRetS = request.getParameter("date_returnflight");
		int tol = Integer.parseInt(request.getParameter("date_tolerance"));
		Date dateDep = null;
		Date dateRet = null;
		DateFormat airFormat = new SimpleDateFormat("dd.MM.yyyy");
		DateFormat dbFormat  = new SimpleDateFormat("yyyy-MM-dd");
		
		if (dateDepS != "" | dateRetS != ""){
			try {
	            dateDep = airFormat.parse(dateDepS);
	            dateRet = airFormat.parse(dateRetS);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			if (dateDep.before(today)){
				errors.putMsg("date_flight", Errors.TYPE_DATUM_PAST);
			}
			if (dateRet.before(today)){
				errors.putMsg("date_returnflight", Errors.TYPE_DATUM_PAST);
			}
			if (dateRet.before(dateDep)){
				errors.putMsg("date_returnflight", Errors.TYPE_DATUM_MIX);
			}
		} else {
			errors.putMsg("date_flight", Errors.TYPE_DATUM_SET);
			errors.putMsg("date_returnflight", Errors.TYPE_DATUM_SET);
		}

		if(database.getFlughafen(dep).getCode().equals("ER7") && !database.getFlughafen(request.getParameter("departure")).getCode().equals("ER7")){
			dep = request.getParameter("departure");
		}
		if (database.getFlughafen(dep).getCode().equals("ER7")){
			errors.putMsg("departure", Errors.TYPE_FLUGHAFEN);
		}
		
		if(database.getFlughafen(dest).getCode().equals("ER7") && !database.getFlughafen(request.getParameter("destination")).getCode().equals("ER7")){
			dest = request.getParameter("destination");
		}
		if (database.getFlughafen(dest).getCode().equals("ER7")){
			errors.putMsg("destination", Errors.TYPE_FLUGHAFEN);
		}

		if(errors.isEmpty()){
			hinfluege = database.lookupFlug(database.getFlughafen(dep), database.getFlughafen(dest), dbFormat.format(dateDep), tol);
		   	rueckfluege = database.lookupFlug(database.getFlughafen(dest), database.getFlughafen(dep), dbFormat.format(dateRet), tol);
		   	request.setAttribute("hinFlug", hinfluege);
		   	request.setAttribute("rueckFlug", rueckfluege);
		   	getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		} else {
			String backview = "/flug/finden.jsp";
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher(backview).forward(request, response);
		}
	   	
	}

}
