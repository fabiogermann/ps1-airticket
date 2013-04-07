<nav>
	<ul>
		<li><a href="<%=request.getContextPath()%>/flug/finden.jsp" <% if (request.getRequestURI().contains("flug")){ %> class="active" <% } %> >Flug finden</a></li>
		<% if (request.getUserPrincipal() == null) { %>
		<li><a href="<%=request.getContextPath()%>/benutzer_konto/angemeldet.jsp"  <% if (request.getRequestURI().contains("anmeld")){ %> class="active" <% } %> >Anmelden</a></li>
		<% } else { %>
		<li><a href="<%=request.getContextPath()%>/abmelden">Abmelden</a></li>
		<% } %>
		<li><a href="<%=request.getContextPath()%>/benutzer/registrieren.jsp" <% if (request.getRequestURI().contains("regist")){ %> class="active" <% } %>>Registrieren</a></li>
		<li><a href="<%=request.getContextPath()%>/benutzer_konto/konto.jsp" <% if (request.getRequestURI().contains("konto.jsp")){ %> class="active" <% } %> >Mein Konto</a></li>
	</ul>
</nav>