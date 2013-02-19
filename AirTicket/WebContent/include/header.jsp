<%@ page import="java.util.* ,org.zhaw.airticket.model.*,org.zhaw.airticket.util.*" %>
<header>

	<head>
		<meta charset="UTF-8" />
		<title>Air Ticket | <%=title %></title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
	</head>
	<!-- IF DEBUG = TRUE  -->
	<p style="color: blue; background-color: white; overflow: scroll;">
	<% if(request.getUserPrincipal() != null) { %>
		Principal: <%=request.getUserPrincipal() %>
		<br/>
	<% } %>
	
 	<% 
 	Benutzer benutzer_debug =  (Benutzer)session.getAttribute("Benutzer"); 
	if(benutzer_debug != null) {	
		 %><%=benutzer_debug %><% 
			Map<Integer, Ticket> tickets = benutzer_debug.getTickets(); 
			for (Ticket ticket : tickets.values()){
				 %>
				 <br/> <%=ticket %>
				 <br/> --)<%=ticket.getFlug() %>
				 <br/> -- --)<%=ticket.getFlug().getVon() %>
				 <br/> -- --)<%=ticket.getFlug().getNach() %>
				 <br/> -- --)<%=ticket.getFlug().getFlugzeug() %>
				 <% 
			}
		%>
		<br/>
	<% } %>
	</p>
	
	<h1>AirTicket</a></h1>
	<div id="login">
	<% if(request.getUserPrincipal() == null) { %>
		<form method="POST" action="<%=request.getContextPath()%>/benutzer_konto/angemeldet.jsp">
			<fieldset>
				<label for="email">E-Mail</label>
				<input type="email" id="email" name="j_username" required="required" />
				<label for="passwort">Passwort</label>
				<input type="password" id="passwort" name="j_password" required="required" />
				<input type="submit" id="submit_login" name="login" value="Anmelden" class="button" />
			</fieldset>
		</form>
	<% } else { %>
		<a href="<%=request.getContextPath()%>/benutzer_konto/konto.jsp"><%=request.getUserPrincipal().getName() %></a>
	<% } %>
	</div>
	
	<% 
		Errors errors = new Errors();
		Object errorsObj = request.getAttribute("errors"); 
	    if (errorsObj != null && errorsObj instanceof Errors){
	    	errors = (Errors) errorsObj;
	    }
	%>
	
</header>