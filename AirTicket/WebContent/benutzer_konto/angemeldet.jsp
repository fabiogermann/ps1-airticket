<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Angemeldet"; %>

<body>
	<div id="seite">
		<%@include file="../include/header.jsp"%>

		<section>

			<article>
				<h1><%=title %></h1>
				<p>
					Sie sind nun Angemeldet.
					<br />
					<br />
					<a href="benutzer_konto.html" alt="Mein Konto">Zu meinem Konto</a>
					<br />
					<br />
					<a href="index.html" alt="Flug finden">Einen Flug finden</a>
				</p>
					
			</article>
			
			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
