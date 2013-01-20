<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Registrieren"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<article>
				<h1><%=title %></h1>
				<form action="benutzer_registriert.jsp" method="post">
					<fieldset>
						<legend>Benutzerkonto</legend>
						<label for="email">E-Mail</label>
						<input type="email" id="email" name="email" required="required" autofocus="autofocus" />

						<label for="passwort">Passwort</label>
						<input type="password" id="passwort" name="passwort" required="required" />
					</fieldset>

					<fieldset>
						<legend>Personalien</legend>
						<label for="vorname">Vorname</label>
						<input type="text" id="vorname" name="vorname" required="required" />

						<label for="name">Name</label>
						<input type="text" id="name" name="name" required="required" />

						<label for="strasse">Strasse</label>
						<input type="text" id="strasse" name="strasse" required="required" />

						<label for="ort">Ort</label>
						<input type="text" id="ort" name="ort" required="required" />

						<label for="postleitzahl">Postleitzahl</label>
						<input type="number" id="postleitzahl" name="postleitzahl" required="required" />

						<label for="telefonnummer">Telefonnummer</label>
						<input type="number" id="telefonnummer" name="telefonnummer" required="required" />

						<label for="land">Land</label>
						<input type="text" id="land" name="land" required="required" />
					</fieldset>
					
					<p>Falls bereits ein Konto haben können sie sich hier <a href="../benutzer_konto/angemeldet.jsp" alt="Anmelden">anmelden</a>.</p>
					<input type="submit" id="submit_register" name="submit_register" value="Registrieren" class="button" />
				</form>
		
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
