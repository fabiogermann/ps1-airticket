<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Flug buchen"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1>Ihre Flugauswahl | Detailinformationen</h1>

				<div id="flugdetails">
					<h2>Ihr Hinflug</h2>
					<table>
						<tr>
							<th>Flugnummer</th>
							<th>Abflugdatum | Zeit</th>
							<th>Ankunftsdatum | Zeit</th>
							<th>Dauer</th>
							<th>Startflughafen</th>
							<th>Zielflughafen</th>
							<th>Klasse</th>
							<th>Ticketpreis</th>
						</tr>
						<tr>
							<td>K2 482</td>
							<td>27.11.12 | 09:20</td>
							<td>27.11.12 | 10:35</td>
							<td>1:15h</td>
							<td>Zürich</td>
							<td>London</td>
							<td>Economy</td>
							<td>250 CHF</td>
						</tr>
					</table>
					
					<h2>Ihr Rückflug</h2>
					<table>
						<tr>
							<th>Flugnummer</th>
							<th>Abflugdatum | Zeit</th>
							<th>Ankunftsdatum | Zeit</th>
							<th>Dauer</th>
							<th>Startflughafen</th>
							<th>Zielflughafen</th>
							<th>Klasse</th>
							<th>Ticketpreis</th>
						</tr>
						<tr>
							<td>AB 41258</td>
							<td>30.11.12 | 11:35</td>
							<td>30.11.12 | 12:53</td>
							<td>1:18h</td>
							<td>London</td>
							<td>Zürich</td>
							<td>Business</td>
							<td>375 CHF</td>
						</tr>
					</table>
					
					<h2>Total</h2>
					<div class="total">
						<strong>Zürich - London - Zürich
							<span class="total_value">625 CHF</span>
						</strong>
					</div>
					
					<form action="bestaetigung.jsp" method="post">
						<input type="submit" id="submit_buchen" name="submit_buchen" value="Flug buchen" class="button next" />
					</form>
					
					<!-- <input type="button" id="back" name="back" value="Zurück" class="button back" onclick="history.back()" /> -->
					<form action="auswaehlen.jsp" method="post">
						<input type="submit" id="back" name="back" value="Zurück" class="button back" />
					</form>
					
				</div>
	
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>