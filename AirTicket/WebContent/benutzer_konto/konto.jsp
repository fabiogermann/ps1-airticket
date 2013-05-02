<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Benutzerkonto";%>
<%@include file="../include/head.jsp"%>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<article>
				<h1><%=title%></h1>
				<h2>Ihre Tickets</h2>
	
					
				<% if(benutzer != null && benutzer.getTickets().size() > 0 ) {	%>
					
					<table>
					
					<tr>
						<th>Flugnummer</th>
						<th>Abflugdatum <br/> Zeit</th>
						<th>Ankunftsdatum <br/> Zeit</th>
						<th>Startflughafen</th>
						<th>Zielflughafen</th>
						<th>Flugdauer <br/> Klasse</th>
						<th>Sitz</th>
						<th></th>
						<th></th>
					</tr>
					
					<%
						Map<Integer, Ticket> tickets = benutzer.getTickets(); 
						for (Ticket ticket : tickets.values()){
					%>
						<tr>
							<td><%=ticket.getFlug().getNummer() %></td>
							<td><%=ticket.getAbflugdatum(null) %><br/><%=ticket.getFlug().getAbflugzeit(null) %></td>
							<td><%=ticket.getAnkunftdatum(null) %><br/><%=ticket.getFlug().getAnkunftzeit(null) %></td>
							<td><%=ticket.getFlug().getVon().getName() %> (<%=ticket.getFlug().getVon().getCode() %>)<br/><%=ticket.getFlug().getVon().getStadt() %> <%=ticket.getFlug().getVon().getLand() %></td>
							<td><%=ticket.getFlug().getNach().getName() %> (<%=ticket.getFlug().getNach().getCode() %>)<br/><%=ticket.getFlug().getNach().getStadt() %> <%=ticket.getFlug().getNach().getLand() %></td>
							<td><%=ticket.getFlug().getDauer() %><br/><%=ticket.getKlasse() %></td>
							<td> 
							<% if (ticket.getSitzreihe() > 0){ %>
								<%=ticket.getSitzreihe() %><%=ticket.getSitzspalteAlpha() %>
							<% } else { %> - <% } %>	
							</td>
							<td>
								<form action="<%=request.getContextPath()%>/benutzer_konto/einchecken.jsp" method="post">
									<% if (ticket.getSitzreihe() != 0){ %>
										<input type="submit" id="aendern_<%=ticket.getId() %>" name="aendern" value="Ändern" class="button" />
									<% } else { %>
										<input type="submit" id="einchecken_<%=ticket.getId() %>" name="einchecken" value="Einchecken" class="button" />
									<% } %>	
									<input type="hidden" name="ticketId" value="<%=ticket.getId() %>" />
									<input type="hidden" name="flugId" value="<%=ticket.getFlug().getNummer() %>" />
								</form>	
							</td>
							<td>
								<form action="<%=request.getContextPath()%>/stornieren" method="post">
									<input type="submit" id="stornieren_<%=ticket.getId() %>" name="stornieren" value="Stornieren" class="button" />
									<input type="hidden" name="ticketId" value="<%=ticket.getId() %>" />
									<input type="hidden" name="flugId" value="<%=ticket.getFlug().getNummer() %>" />
								</form>	
							</td>
						</tr>
						<% } %>
						</table>
					<% } else { %>
						<p class="hinweis">Sie haben zur Zeit noch keine Tickets.</p>
						<p><a href="<%=request.getContextPath()%>/flug/finden.jsp"> Erwerben sie ein Flugticket</a>
						</p>
					<% } %>
					
	
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>