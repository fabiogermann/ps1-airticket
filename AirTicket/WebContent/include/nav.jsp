<nav>
	<ul>
		<li><a href="<%=request.getContextPath()%>/flug/finden.jsp" >Flug finden</a></li>
		<% if (request.getUserPrincipal() == null) { %>
			<li><a href="<%=request.getContextPath()%>/benutzer_konto/angemeldet.jsp" >Anmelden</a></li>
		<% } else { %>
			<li><a href="<%=request.getContextPath()%>/abmelden">Abmelden</a></li>
		<% } %>
		<li><a href="<%=request.getContextPath()%>/benutzer/registrieren.jsp">Registrieren</a></li>
		<li><a href="<%=request.getContextPath()%>/benutzer_konto/konto.jsp">Mein Konto</a></li>
	</ul>
</nav>