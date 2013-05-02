<%@ page import="java.util.* ,org.zhaw.airticket.model.*,org.zhaw.airticket.util.*" %>
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<header>
	<h1>AirTicket</h1>
	<div id="login">
	<% if(request.getUserPrincipal() == null) { %>
		<form method="POST" action="<%=request.getContextPath()%>/benutzer_konto/angemeldet.jsp">
		<!--  <form action="j_security_check" method="post"> -->
			<fieldset>
			<!--  
				<label for="email">E-Mail</label>
				<input type="email" id="email" name="j_username" required="required" />
				<label for="passwort">Passwort</label>
				<input type="password" id="passwort" name="j_password" required="required" />
				<input type="submit" id="submit_login" name="login" value="Anmelden" class="button" />
			-->
			</fieldset>
		</form>
	<% } else { %>
		<!--  <a href="  request.getContextPath()  /abmelden" class="button">Abmelden</a>  -->
		<form action="<%=request.getContextPath()%>/abmelden">
			<a href="<%=request.getContextPath()%>/benutzer_konto/konto.jsp"><%=request.getUserPrincipal().getName() %></a>
			<input type="submit" id="submit_logout" value="Abmelden" class="button" >
		</form>
	<% } %>
	</div>
	
	<% 
		Errors errors = new Errors();
		Object errorsObj = request.getAttribute("errors"); 
	    if (errorsObj != null && errorsObj instanceof Errors){
	    	errors = (Errors) errorsObj;
	    }
	    
	    Benutzer benutzer =  (Benutzer)session.getAttribute("Benutzer"); 
	%>
	
</header>