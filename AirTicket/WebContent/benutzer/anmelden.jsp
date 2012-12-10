<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Anmelden"; %>

<body>
	<div id="seite">
		<%@include file="../include/header.jsp"%>

		<section>

			<article>
				<h1><%=title %></h1>
				<!-- <form action="../benutzer_konto/angemeldet.jsp" method="post">  -->
				<form action="j_security_check" method="post">
					<fieldset>
						<legend>Logindaten</legend>
						<label for="username1">E-Mail</label>
						<input type="email" id="username1" name="j_username" required="required" autofocus="autofocus" />
						<label for="password1">Passwort</label>
						<input type="password" id="password1" name="j_password" required="required" />
					</fieldset>
					<p>Falls sie noch kein Konto haben können sie sich hier <a href="../benutzer/registrieren.jsp" alt="Registrieren">registrieren</a>.</p>
					<input type="submit" id="submit_login1" name="submit" value="Anmelden" class="button" />
				</form>
				
			</article>
			
			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
