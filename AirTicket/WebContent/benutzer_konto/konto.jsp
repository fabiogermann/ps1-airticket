<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%!String title = "Benutzerkonto";%>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title%></h1>
				<h2>Ihre Tickets</h2>
				<form action="sitz_einchecken.html" method="post">
					<table>
						<tr>
							<th>Flugnummer</th>
							<th>Abflugdatum | Zeit</th>
							<th>Ankunftsdatum | Zeit</th>
							<th>Startflughafen</th>
							<th>Zielflughafen</th>
							<th>Flugdauer</th>
							<th>Sitz</th>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<td>LH 747</td>
							<td>27.11.12 09:20</td>
							<td>27.11.12 10:35</td>
							<td>Zürich</td>
							<td>London</td>
							<td>1:15h</td>
							<td>-</td>
							<td><input type="submit" id="einchecken_K2482" name="einchecken_K2482" value="Einchecken" class="button" /></td>
							<td><input type="submit" id="stornieren_K2482" name="stornieren_K2482" value="Stornieren" class="button" /></td>
						</tr>
						<tr>
							<td>AB 8589</td>
							<td>27.11.12 10:35</td>
							<td>27.11.12 11:53</td>
							<td>Zürich</td>
							<td>London</td>
							<td>1:18h</td>
							<td>1</td>
							<td><input type="submit" id="aendern_AB5889" name="aendern_AB5889" value="Sitz ändern" class="button" /></td>
							<td><input type="submit" id="stornieren_AB5889" name="stornieren_AB5889" value="Stornieren" class="button" /></td>
						</tr>
						<tr>
							<td>AF 1115</td>
							<td>27.11.12 15:20</td>
							<td>27.11.12 16:35</td>
							<td>Zürich</td>
							<td>London</td>
							<td>1:15h</td>
							<td>43F</td>
							<td><input type="submit" id="aendern_AF1115" name="aendern_AF1115" value="Sitz ändern" class="button" /></td>
							<td><input type="submit" id="stornieren_AF1115" name="stornieren_AF1115" value="Stornieren" class="button" /></td>
						</tr>
						<tr>
							<td>SQ 345</td>
							<td>27.11.12 17:25</td>
							<td>27.11.12 18:35</td>
							<td>Zürich</td>
							<td>London</td>
							<td>1:10h</td>
							<td>-</td>
							<td><input type="submit" id="einchecken_SQ345" name="einchecken_SQ345" value="Einchecken" class="button" /></td>
							<td><input type="submit" id="stornieren_SQ345" name="stornieren_SQ345" value="Stornieren" class="button" /></td>
						</tr>
					</table>
				</form>
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>