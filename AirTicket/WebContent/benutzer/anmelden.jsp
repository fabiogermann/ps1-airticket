<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Anmelden"; %>
<%@include file="../include/head.jsp"%>

<body>
	<div id="seite">
		<%@include file="../include/header.jsp"%>

		<section>
		
			<!-- request.setAttribute("login-forward-uri", request.getRequestURI()); -->
			<% if (!request.getRequestURI().contains("regi") && !request.getRequestURI().contains("security")) {
				session.setAttribute("login-forward-uri", request.getAttribute("javax.servlet.forward.request_uri")); 
			} %>
		
			<% if (session.getAttribute("login-forward-uri") != null && ((String)session.getAttribute("login-forward-uri")).contains("best")) { %>
				<%@include file="../include/progressbar.jsp"%>
			<% } %>
			
			<article>
				<h1><%=title %></h1>
				<div class="textblock textnote">
					Als registrierter Benutzer können Sie sich mit ihren Log-in-Daten einloggen.
					<br />
					Falls Sie noch kein Benutzerkonto besitzen, können Sie sich innerhalb weniger Minuten kostenlos registrieren.
				</div>
				<!-- <form action="../benutzer_konto/angemeldet.jsp" method="post">  -->
				<form action="j_security_check" method="post">
					<fieldset>
						<legend>Logindaten</legend>
						<label for="username1">E-Mail</label>
						<input type="email" id="username1" name="j_username" required="required" autofocus="autofocus" />
						<label for="password1">Passwort</label>
						<input type="password" id="password1" name="j_password" required="required" />
						<% if (request.getParameter("failed") != null && request.getParameter("failed").equalsIgnoreCase("true")){ %>
						<% if (request.getAttribute("overrideLoginFailed") == null) { %>
							<p class="error">Sie konnten nicht angemeldet werden. Der eingegebene Benutzername oder das Passwort ist falsch.</p>
						<% } %>
						<% } %>
					</fieldset>

					<p>Falls sie noch kein Konto haben, können sie sich hier <a href="../benutzer/registrieren.jsp">registrieren</a>.</p>
					
					
					
					<input type="submit" id="submit_login1" name="submit" value="Anmelden" class="button" />
				</form>
				
			</article>
			
			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
