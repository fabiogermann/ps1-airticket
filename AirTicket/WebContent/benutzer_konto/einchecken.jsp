<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="org.zhaw.airticket.database.*"%>

<%! String title = "Sitzplatz auswählen";
    String link = "";
%>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				
				<div class="detail_view">

					<%
						if (request.getParameter("ticketId") != null) {

							// #MO ticket.getFlug()
							//			     Flug flug = database.getFlug(request.getParameter("flugId"));
							//			     Ticket ticket = database.getTicket(Integer.parseInt(request.getParameter("ticketId")));

							// #MO ale DB ish fixed, aber es isch vill schneller diä info us de session z neh als extra uf db defür

							int ticketId = -1;
							try {
								ticketId = Integer.parseInt(request.getParameter("ticketId"));
							} catch (NumberFormatException e) {
								//TODO
							}

							Ticket ticket = benutzer.getTicketById(ticketId);
							Flug flug = null;
							if (ticket != null) {
								flug = ticket.getFlug();
							}

							if (flug != null) {

								//Überprüft, ob Flug dem Flug im Ticket entspricht (damit nöd afach irgend e Flugnr cha id URL gschribe werde)
								//und ob Ticket vom aktuellen Benutzer ist (damit nöd afach irgend e ID cha id URL gschribe werde)
								// funzt aber nonig.. vermuetlich bruchts defür e equals methode wo mer schribe mue?
								//     && benutzer.getTickets().containsValue(ticket)

								// #Mo: gueti ide, aber wennis gad vom benutzer holl lanet != null;

								int reihenr = -1;
								int sitznr = -1;
								try {
									reihenr = Integer.parseInt(request.getParameter("reihenr"));
									sitznr = Integer.parseInt(request.getParameter("sitznr"));
								} catch (NumberFormatException e) {
									//TODO
								}

								// #MO wür de ganzi code gad id DATABASE odr so tue das gad s sitzpaltz array bechunsch?

								Sitzplatz[][] sitzplaetze = null;
								if (ticket.getKlasse().equalsIgnoreCase("Business")) {
									sitzplaetze = flug.getFlugzeug().getBusinessSitzplaetze();
								} else if (ticket.getKlasse().equalsIgnoreCase("Economy")) {
									sitzplaetze = flug.getFlugzeug().getEconomySitzplaetze();
								}

								if (sitzplaetze != null) {
									Database database = new Database();
									ArrayList<Ticket> ticketlist = database.getTicketsByFlug(flug.getNummer());
									for (int reihe = 0; reihe < sitzplaetze.length; reihe++) {
										for (int sitz = 0; sitz < sitzplaetze[reihe].length; sitz++) {
											for (Ticket t : ticketlist) {
												if (t.getSitzreihe() - 1 == reihe && t.getSitzspalte() - 1 == sitz && t.getKlasse().equals(sitzplaetze[reihe][sitz].getKlasse())) {
													sitzplaetze[reihe][sitz].setReserviert(true);
												}
											}
										}
									}
								}
					%>

					<h2>Details zu <%= flug.getNummer() %></h2>
					<table>
						<tr>
							<th>Flugnummer</th>
							<td><%= flug.getNummer() %></td>
						</tr>
						<tr>
							<th>Abflugdatum</th>
							<td><%= ticket.getAbflugdatum(null) %></td>
						</tr>
						<tr>
							<th>Abflugzeit</th>
							<td><%= flug.getAbflugzeit(null) %></td>
						</tr>
						<tr>
							<th>Ankunftsdatum</th>
							<td><%= ticket.getAnkunftdatum(null) %></td>
						</tr>
						<tr>
							<th>Ankuntszeit</th>
							<td><%= flug.getAnkunftzeit(null) %></td>
						</tr>
						<tr>
							<th>Startflughafen</th>
							<td><%= flug.getVon().getName() %></td>
						</tr>
						<tr>
							<th>Zielflughafen</th>
							<td><%= flug.getNach().getName() %></td>
						</tr>
						<tr>
							<th>Flugdauer</th>
							<td><%= flug.getDauer(null) %>h</td>
						</tr>
					</table>
				</div>

				<div class="main_view">
					<h2>Sitzplan <%= flug.getFlugzeug().getModell() %></h2>
					<p>
						Bitte wählen Sie einen Sitzplatz durch Anklicken aus.
						<br />
						<!--
						<br />
						Bereits gebucht: <strong>   ticket.getSitzspalteAlpha()   */</strong> 
						 -->
						<br />
						Aktuell ausgewählt: <strong> <%= ticket.getSitzreihe() %><%= ticket.getSitzspalteAlpha() %></strong>
					</p>
					<!-- <input type="button" id="up" name="up" value="Nach Oben" class="button sitzplanbutton" />-->				
					
					<h3><%= ticket.getKlasse() %></h3>
					<div id="sitzplan">
					<%  
					link = "?ticketId=" + ticket.getId();
					for (int reihe = 0; reihe < sitzplaetze.length; reihe++) { %>
						  <span class="sitzreihe">
						  <% for (int sitz = 0; sitz < sitzplaetze[reihe].length; sitz++) {
						    	String klasse = "frei";
						    	String templink = link + "&reihenr=" + reihe + "&sitznr=" + sitz;
						    	if (sitzplaetze[reihe][sitz].isReserviert()) {
						    		klasse = "reserviert";
						    		templink = link;
						    	}
						    	if (reihe == reihenr && sitz == sitznr) {
						    		klasse = "aktiv";
						    	}						    	
						    	%>        	   
					           <a href="<%= templink %>" class="<%= klasse %>"><%= sitzplaetze[reihe][sitz].getSitznummer() %></a>				
					<%	    } %>
					      </span>
					<%	} %>
					</div>
					<!--<img id="sitzplanimg" alt="sitzplan" width="100" height="100" src="./css/img/footer.png">					
					<input type="button" id="down" name="down" value="Nach Unten" class="button sitzplanbutton" />-->
				</div>
                
                <% if (reihenr >= 0 && sitznr >= 0) { %>
					<form action="process_checkin.jsp" method="post">
						<input type="hidden" id="ticketId" name="ticketId" value="<%= ticket.getId() %>" /> 
						<input type="hidden" id="reihenr" name="reihenr" value="<%= reihenr %>" /> 
						<input type="hidden" id="sitznr" name="sitznr" value="<%= sitznr %>" /> 
						<% if (ticket.getSitzreihe() == 0 && ticket.getSitzspalte() == 0) { %>
						   <input type="submit" id="einchecken" name="einchecken" value="Einchecken" class="button next" />
						<% } else { %> 
						   <input type="submit" id="aendern" name="aendern" value="Sitz ändern" class="button next" />
						<% } %>
					</form>
			    <% } %>
			
		     <% } else { %>
		          <p class="error">Fehler: Falsche Ticket- und Flugangaben.</p> 
		     <% } %>
				
           <% } else { %>
               <p class="error">Fehler: Keine Ticket- und Flugangaben.</p>              
           <% } %>
              
			    <form action="konto.jsp" method="post">
			       <input type="submit" id="back" name="back" value="Zurück" class="button back" />
			    </form>
				
			  </article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
