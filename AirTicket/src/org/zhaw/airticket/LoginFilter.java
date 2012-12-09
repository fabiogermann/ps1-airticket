package org.zhaw.airticket;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	protected FilterConfig filterConfig;

	/**
	 * init() : Beim Instanzieren des Filters wird die Methode init()
	 * aufgerufen. Dieser Filter wird instanziert, wenn j_security_check das
	 * erste Mal für die Anwendung aufgerufen wird (wenn auf ein geschütztes
	 * Servlet in der Anwendung zugegriffen wird).
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * destroy() : Die Methode destroy() wird aufgerufen, wenn der Filter
	 * inakiviert wird.
	 */
	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * doFilter() : Die Methode doFilter() wird vor dem Servlet, dem dieser
	 * Filter zugeordnet wird, aufgerufen. Da dieser Filter j_security_check
	 * zugeordnet wird, wird diese Methode vor dem Ausführen der Aktion
	 * j_security_check aufgerufen.
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// pre login action

		// get username
		String username = req.getParameter("j_username");
		String password = req.getParameter("j_password");
		
		System.out.println("@Mo j_username: " + username );
		System.out.println("@Mo j_password: " + password );

		// Nächsten Filter in der Kette aufrufen: Benutzer mit j_security_check
		// authentifizieren
		
		chain.doFilter(request, response);
		
		// post login action

	}

}