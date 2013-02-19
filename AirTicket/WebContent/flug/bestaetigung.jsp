<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Flugbest�tigung"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1>Ihre Flugbuchung | Best�tigung</h1>
				<div id="flugbestaetigung">
					<h2>Buchungsbest�tigung</h2>
					<p>
						Ihr Flug wurde erfolgreich f�r sie gebucht. Das Ticket ist ab sofort in ihrem Konto verf�gbar.
						<br />
						<br />
						<a href="benutzer_konto.html" alt="Mein Konto">Zu meinem Konto</a>						
						<br />
						<br />
						<br />
						Vielen Dank, dass sie AirTicket nutzen.
					</p>
				</div>

			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>