<header>

	<head>
		<meta charset="UTF-8" />
		<title>Air Ticket | <%=title%></title>
		<link rel="stylesheet" href="../css/style.css" />
	</head>

	<h1>AirTicket</h1>
	<div id="login">
		<form method="POST" action="../benutzer_konto/angemeldet.jsp">
			<fieldset>
				<label for="email">E-Mail</label>
				<input type="email" id="email" name="j_username" required="required" />
				<label for="passwort">Passwort</label>
				<input type="password" id="passwort" name="j_password" required="required" />
				<input type="submit" id="submit_login" name="login" value="Anmelden" class="button" />
			</fieldset>
		</form>
	</div>
</header>