<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String title = "Impressum";%>
<%@include file="../include/head.jsp"%>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<article>
				<h1><%=title%></h1>
				<div>
					<p>
						Diese Website entstand im Rahmen der Projektschiene PS1 &amp; 2 an der ZHAW in Winterthur.
						<br />
						Unser Ziel war es, eine einfache Web-Applikation zu entwickeln, die es den Kunden ermöglicht einfach und ein Ticket für einen Flug zu erwerben.
					</p>
					<h2>Entwickler</h2>
					<table>
						<tr>
							<th>Claudio Federer</th>
							<td>DB-Managment</td>
						</tr>
						<tr>
							<th>Fabio Germann</th>
							<td>Server &amp; Infrastruktur</td>
						</tr>
						<tr>
							<th>Alessandro Monsorno</th>
							<td>Programm und Design</td>
						</tr>
						<tr>
							<th>Moritz Zollinger</th>
							<td>Chefprogrammierer</td>
						</tr>
					</table>
				</div>
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
