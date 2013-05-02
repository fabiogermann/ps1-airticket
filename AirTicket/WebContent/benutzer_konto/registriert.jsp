<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Registrieren"; %>
<%@include file="../include/head.jsp"%>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				<p>
					Vielen Dank. Sie sind nun registriert.
					<br />
					<br />
					<a href="<%=request.getContextPath()%>/benutzer_konto/konto.jsp">Zu meinem Konto</a>
					<br />
					<br />
					<a href="<%=request.getContextPath()%>/flug/finden.jsp">Einen Flug finden</a>
				<p>
		
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>