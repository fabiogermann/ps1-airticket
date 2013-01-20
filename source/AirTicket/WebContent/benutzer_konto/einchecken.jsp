<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Einchecken"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				<h1>Sitzplatz auswählen</h1>
				
				<div class="detail_view">
					<h2>Details zu LH 747</h2>
					<table>
						<tr>
							<th>Flugnummer</th>
							<td>LH 747</td>
						</tr>
						<tr>
							<th>Abflugdatum</th>
							<td>27.11.12</td>
						</tr>
						<tr>
							<th>Abflugzeit</th>
							<td>09:20</td>
						</tr>
						<tr>
							<th>Ankunftsdatum</th>
							<td>27.11.12</td>
						</tr>
						<tr>
							<th>Ankuntszeit</th>
							<td>10:35</td>
						</tr>
						<tr>
							<th>Startflughafen</th>
							<td>Zürich</td>
						</tr>
						<tr>
							<th>Zielflughafen</th>
							<td>London</td>
						</tr>
						<tr>
							<th>Flugdauer</th>
							<td>1:15h</td>
						</tr>
					</table>
				</div>

				<div class="main_view">
					<h2>Sitzplan Airbus A380</h2>
					<p>
						Bitte wählen Sie einen Sitzplatz durch Anklicken aus.
						<br />
						<!--
						<br />
						Bereits gebucht: <strong>-</strong> 
						 -->
						<br />
						Aktuell ausgewählt: <strong>5D</strong>
					</p>
					<input type="button" id="up" name="up" value="Nach Oben" class="button sitzplanbutton" />
					<img id="sitzplanimg" alt="sitzplan" width="100" height="100" src="./css/img/footer.png">
					<input type="button" id="down" name="down" value="Nach Unten" class="button sitzplanbutton" />
				</div>

				<form action="#" method="post">
					<input type="button" id="einchecken" name="einchecken" value="Einchecken" class="button next" />
					<!--  
						<input type="submit" id="aendern" name="aendern" value="Sitz ändern" />
						<input type="submit" id="stornieren" name="stornieren" value="Stornieren" />
					-->
				</form>

				<!-- <input type="button" id="back" name="back" value="Zurück" class="button back" onclick="history.back()" /> -->
				<form action="benutzer_konto.html" method="post">
					<input type="submit" id="back" name="back" value="Zurück" class="button back" />
				</form>
				
				</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
