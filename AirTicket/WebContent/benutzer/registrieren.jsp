<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">  
<jsp:useBean id="benutzerBean" class="org.zhaw.airticket.model.Benutzer" scope="request" />
<jsp:setProperty name="benutzerBean" property="*" />
<%! String title = "Registrieren"; %>

<body>
	<div id="seite">

		<%@include file="/include/header.jsp"%>

		<section>

			<% if (session.getAttribute("login-forward-uri") != null && ((String)session.getAttribute("login-forward-uri")).contains("best")) { %>
				<%@include file="../include/progressbar.jsp"%>
			<% } %>

			<article>
				<h1><%=title %></h1>
				<!-- <form action="benutzer_registriert.jsp" method="post"> -->
				<form action="<%=request.getContextPath()%>/registrieren" method="post">
					
					<fieldset>
						<legend>Benutzerkonto</legend>
						<label for="email">E-Mail</label>
						<input type="email" id="email" name="email" required="required" autofocus="autofocus" value="${benutzerBean.email}" />
						<%if (!errors.getMsg("email").equals("")) {%>
						<p class="error"><%=errors.getMsg("email") %> </p>
						<% } %>
						<label for="passwort">Passwort</label>
						<input type="password" id="passwort" name="passwort" required="required" />
						<%if (!errors.getMsg("general").equals("")) {%>
						<p class="error"><%=errors.getMsg("general") %> </p>
						<% } %>
					</fieldset>
					

					<fieldset>
						<legend>Personalien</legend>
						<label for="vorname">Vorname</label>
						<input type="text" id="vorname" name="vorname" required="required" value="${benutzerBean.vorname}" />

						<label for="name">Name</label>
						<input type="text" id="name" name="name" required="required" value="${benutzerBean.name}" />

						<label for="strasse">Strasse</label>
						<input type="text" id="strasse" name="strasse" required="required" value="${benutzerBean.strasse}" />

						<label for="ort">Ort</label>
						<input type="text" id="ort" name="ort" required="required" value="${benutzerBean.ort}" />

						<label for="postleitzahl">Postleitzahl</label>
						<input type="number" id="postleitzahl" name="postleitzahl" required="required" value="${benutzerBean.postleitzahl}" />
						<%if (!errors.getMsg("postleitzahl").equals("")) {%>
						<p class="error"><%=errors.getMsg("postleitzahl") %> </p>
						<% } %>
						
						<label for="telefonnummer">Telefonnummer</label>
						<input type="number" id="telefonnummer" name="telefonnummer" required="required" value="${benutzerBean.telefonnummer}" />
						<%if (!errors.getMsg("telefonnummer").equals("")) {%>
						<p class="error"><%=errors.getMsg("telefonnummer") %> </p>
						<% } %>

						<label for="land">Land</label>
						<input type="text" id="land" name="land" required="required" value="${benutzerBean.land}" />
					</fieldset>
					
					<p>Falls bereits ein Konto haben können sie sich hier <a href="../benutzer_konto/angemeldet.jsp" alt="Anmelden">anmelden</a>.</p>
					<input type="submit" id="submit_register" name="submit_register" value="Registrieren" class="button" />
				</form>
		
			</article>

			<%@include file="/include/nav.jsp"%>

		</section>

		<%@include file="/include/footer.jsp"%>

	</div>
</body>
</html>
