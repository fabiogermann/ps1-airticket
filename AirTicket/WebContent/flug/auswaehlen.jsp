<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:useBean id="flugFinden" class="org.zhaw.airticket.model.FlugFinden" scope="session" />
<jsp:setProperty name="flugFinden" property="*" />
<%@ page import="org.zhaw.airticket.database.*"%>
<%@ page import="java.text.*" %>
<%! String title = "Flug ausw�hlen"; %>
<%@include file="../include/head.jsp"%>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				<div class="textblock">
					Sie haben den passenden Flug f�r sich gefunden? 
					 <br/>
					W�hlen Sie ihn durch anklicken aus und sie gelangen auf die Best�tigungsseite.
				</div>
				
				<% 	ArrayList<Flug> hinfluege = new ArrayList<Flug>();
				   	ArrayList<Flug> rueckfluege = new ArrayList<Flug>(); 
				   	Database database = new Database();
				   	hinfluege = (ArrayList<Flug>) request.getAttribute("hinFlug");
				   	rueckfluege = (ArrayList<Flug>) request.getAttribute("rueckFlug");
				%>
				
				<form action="<%=request.getContextPath() %>/flug/buchen.jsp" method="post">
					<div id="hinflug">
						<h2>Hinflug</h2>
						
	                   <% if (hinfluege.size() != 0) { %>
	 						
						<table>
						   <tr>
                                <th>Flugnummer</th>
                                <th>Abflugdatum | Zeit</th>
                                <th>Ankunftsdatum | Zeit</th>
                                <th>Dauer</th>
                                <th>Startflughafen</th>
                                <th>Zielflughafen</th>
                                <th>Verf�gbarkeit</th>
                                <th>Economy</th>
                                <th>Business</th>
                            </tr>
                            
						    <% for (Flug flug : hinfluege) { %>						

							<tr>
								<td><%= flug.getNummer() %></td>
								<td><%= flug.getAbflugdatum(null) %> | <%= flug.getAbflugzeit(null) %></td>
								<td><%= flug.getAnkunftdatum(null) %> | <%= flug.getAnkunftzeit(null) %></td>
								<td><%= flug.getDauer(null) %></td>
								<td><%= flug.getVon().getStadt() %></td>
								<td><%= flug.getNach().getStadt() %></td>
								<td>Business: <%= database.freiePlaetzeBus(flug.getNummer(), flug.getGeplantDate(null)) %> <br /> Economy: <%= database.freiePlaetzeEco(flug.getNummer(), flug.getGeplantDate(null)) %></td>
								<td><input type="radio" id="radio_<%= flug.getNummer() %>_e_<%= flug.getGeplant() %>" name="flug_auswaehlen_h" checked="checked" value="<%= flug.getNummer() %>_e_<%= flug.getGeplant() %>" /> <label for="radio_<%= flug.getNummer() %>_e_<%= flug.getGeplant() %>"><%= flug.getEconomyPreis() %> CHF</label></td>
								<td><input type="radio" id="radio_<%= flug.getNummer() %>_b_<%= flug.getGeplant() %>" name="flug_auswaehlen_h" value="<%= flug.getNummer() %>_b_<%= flug.getGeplant() %>" /> <label for="radio_<%= flug.getNummer() %>_b_<%= flug.getGeplant() %>"><%= flug.getBusinessPreis() %> CHF</label></td>
							</tr>
							
					      <% } %>
						    
						  </table>
					        
			           <%} else { %>
                        
                            <p class="error">Es konnten keine Hinfl�ge gefunden werden. Bitte �ndern Sie Ihre Suchkriterien.</p>
                            
                    <% } %>

					</div>
					
					<div id="rueckflug">
						<h2>R�ckflug</h2>
						
						<% if (rueckfluege.size() != 0) { %>
						
						<table>
							<tr>
								<th>Flugnummer</th>
								<th>Abflugdatum | Zeit</th>
								<th>Ankunftsdatum | Zeit</th>
								<th>Dauer</th>
								<th>Startflughafen</th>
								<th>Zielflughafen</th>
								<th>Verf�gbarkeit</th>
								<th>Economy</th>
								<th>Business</th>
							</tr>
							
						    <% for (Flug flug : rueckfluege) {   
						   
                            %>
                            <tr>
                                <td><%= flug.getNummer() %></td>
								<td><%= flug.getAbflugdatum(null) %> | <%= flug.getAbflugzeit(null) %></td>
								<td><%= flug.getAnkunftdatum(null) %> | <%= flug.getAnkunftzeit(null) %></td>
                                <td><%= flug.getDauer(null) %></td>
                                <td><%= flug.getVon().getStadt() %></td>
                                <td><%= flug.getNach().getStadt() %></td>
                                <td>Business: <%= database.freiePlaetzeBus(flug.getNummer(), flug.getGeplantDate(null)) %> <br /> Economy: <%= database.freiePlaetzeEco(flug.getNummer(), flug.getGeplantDate(null)) %></td>
                                <td><input type="radio" id="radio_<%= flug.getNummer() %>_e_<%= flug.getGeplant() %>" name="flug_auswaehlen_r" checked="checked" value="<%= flug.getNummer() %>_e_<%= flug.getGeplant() %>" /> <label for="radio_<%= flug.getNummer() %>_e_<%= flug.getGeplant() %>"><%= flug.getEconomyPreis() %> CHF</label></td>
                                <td><input type="radio" id="radio_<%= flug.getNummer() %>_b_<%= flug.getGeplant() %>" name="flug_auswaehlen_r" value="<%= flug.getNummer() %>_b_<%= flug.getGeplant() %>" /> <label for="radio_<%= flug.getNummer() %>_b_<%= flug.getGeplant() %>"><%= flug.getBusinessPreis() %> CHF</label></td>
                            </tr>
                            
                          <% } %>
                            
                          </table>
                            
                       <%} else { %>
                        
                            <p class="error">Es konnten keine R�ckfl�ge gefunden werden. Bitte �ndern Sie Ihre Suchkriterien.</p>
                            
                    <% } %>
                            
					</div>
					<% if (hinfluege.size() != 0 && rueckfluege.size() != 0 ) { %>
                    <p class="hinweis">Es wurden total <%= hinfluege.size() + rueckfluege.size()%> Fl�ge gefunden.</p>
                    <input type="submit" id="submit_auswahl" name="submit_auswahl" value="Ausw�hlen" class="button next" />	
                    <% } %>				
				</form>
				
				<!-- <input type="button" id="back" name="back" value="Zur�ck" class="button back" onclick="history.back()" /> -->
				<form action="<%=request.getContextPath() %>/flug/finden.jsp" method="post">
					<input type="submit" id="back" name="back" value="Zur�ck" class="button back" />
				</form>

			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>