<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="org.zhaw.airticket.database.*"%>
<%@ page import="org.zhaw.airticket.model.Flug" %>

<%! String title = "Flug buchen"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1>Ihre Flugauswahl | Detailinformationen</h1>
				<div id="flugdetails">
				<% Database database = new Database();
				//Object hinflug = new Object();
				//Object rueckflug = new Object();
				String rueckflugKlasse = new String();
				String hinflugKlasse = new String();
				Integer rueckflugPreis = 0;
				Integer hinflugPreis = 0;
				String hVon = new String();
				String hNach = new String();
				String rVon = new String();
				String rNach = new String();
				
				
				  	if (request.getParameter("flug_auswaehlen_h") != null)
				  		{
				  			String[] hinflugId = request.getParameter("flug_auswaehlen_h").split("_");
				  			Flug hinflug = database.getFlug(hinflugId[0]);
				  			hinflug.setGeplant(hinflugId[2]);
							hinflugKlasse = null;
							if (hinflugId[1].equals("e")) {
							 hinflugKlasse = "Economy";
							 hinflugPreis = hinflug.getEconomyPreis();
							}
							else if (hinflugId[1].equals("b")) {
							 hinflugKlasse = "Business";
							 hinflugPreis = hinflug.getBusinessPreis();
							}
							Ticket hinflugTicket = new Ticket(0, new Date(), 0, 0, hinflugKlasse, hinflug);
							session.setAttribute("hinflugTicket", hinflugTicket);
							hVon = hinflug.getVon().getStadt();
							hNach = hinflug.getNach().getStadt();
					%>
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
	                            <td><%= hinflug.getNummer() %></td>
	                            <td><%= hinflug.getGeplant() %> | <%= hinflug.getAbflugzeit(null) %></td>
	                            <td><%= hinflug.getGeplant() %> | <%= hinflug.getAnkunftzeit(null) %></td>
	                            <td><%= hinflug.getDauer(null) %>h</td>
	                            <td><%= hinflug.getVon().getStadt() %></td>
	                            <td><%= hinflug.getNach().getStadt() %></td>
								<td><%= hinflugKlasse %></td>
								<td><%= hinflugPreis %> CHF</td>
							</tr>
						</table>
					<% } %>
					<% if (request.getParameter("flug_auswaehlen_r") != null) 
						{
							String[] rueckflugId = request.getParameter("flug_auswaehlen_r").split("_");
							Flug rueckflug = database.getFlug(rueckflugId[0]);
							rueckflug.setGeplant(rueckflugId[2]);
							rueckflugKlasse = null;
							if (rueckflugId[1].equals("e")) {
							    rueckflugKlasse = "Economy";
							    rueckflugPreis = rueckflug.getEconomyPreis();
							}
							else if (rueckflugId[1].equals("b")) {
							    rueckflugKlasse = "Business";
							    rueckflugPreis = rueckflug.getBusinessPreis();
							}
							Ticket rueckflugTicket = new Ticket(0, new Date(), 0, 0, rueckflugKlasse, rueckflug);
							session.setAttribute("rueckflugTicket", rueckflugTicket);
							rVon = rueckflug.getVon().getStadt();
							rNach = rueckflug.getNach().getStadt();
						%>
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
							    <td><%= rueckflug.getNummer() %></td>
	                            <td><%= rueckflug.getGeplant() %> | <%= rueckflug.getAbflugzeit(null) %></td>
	                            <td><%= rueckflug.getGeplant() %> | <%= rueckflug.getAnkunftzeit(null) %></td>
	                            <td><%= rueckflug.getDauer(null) %>h</td>
	                            <td><%= rueckflug.getVon().getStadt() %></td>
	                            <td><%= rueckflug.getNach().getStadt() %></td>
	                            <td><%= rueckflugKlasse %></td>
	                            <td><%= rueckflugPreis %> CHF</td>
	                        </tr>
						</table>
					<% } %>
					
					<h2>Total</h2>
					<div class="total">
						<%= hVon %> - <%= hNach %> - <%= rNach %>
							<span class="total_value"><%= hinflugPreis + rueckflugPreis %> CHF</span>
						
					</div>
					
					<form action="bestaetigung.jsp" method="post">
						<input type="submit" id="submit_buchen" name="submit_buchen" value="Flug buchen" class="button next" />
					</form>
					
					<form action="finden.jsp" method="post">
						<input type="submit" id="back" name="back" value="Abbrechen" class="button back" />
					</form>
					
				</div>
	
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>