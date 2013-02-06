<%@ page import="java.util.* ,org.zhaw.airticket.model.*" %>
<header>

	<head>
		<meta charset="UTF-8" />
		<title>Air Ticket | <%=title %></title>
		<link rel="stylesheet" href="../css/style.css" />
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
	
	<h1>AirTicket</h1>
	<div id="login">
	<% if(request.getUserPrincipal() == null) { %>
		<form method="POST" action="../benutzer_konto/angemeldet.jsp">
			<fieldset>
				<label for="email">E-Mail</label>
				<input type="email" id="email" name="j_username" required="required" />
				<label for="passwort">Passwort</label>
				<input type="password" id="passwort" name="j_password" required="required" />
				<input type="submit" id="submit_login" name="login" value="Anmelden" class="button" />
			</fieldset>
		</form>
	<% } else { %>
		<a href="../benutzer_konto/konto.jsp"><%=request.getUserPrincipal().getName() %></a>
	<% } %>
	</div>
</header>