package org.zhaw.airticket.servlet;

import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zhaw.airticket.database.Database;
import org.zhaw.airticket.model.Benutzer;

public class AirFilter implements Filter {

	private Database db = new Database();
	protected FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse res = (HttpServletResponse) response;

		if (req.getSession().getAttribute("Benutzer") == null) {

			Principal principal = req.getUserPrincipal();
			if (principal != null){
				String email = principal.getName();
				if (email != null) {
					Benutzer benutzer = db.getBenutzer(email);
					req.getSession().setAttribute("Benutzer", benutzer);
					System.out.println("Filter: Set Benutzer = " + benutzer);
				}
			}
		}

		chain.doFilter(request, response);
	}

}