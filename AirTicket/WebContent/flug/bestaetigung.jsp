<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="org.zhaw.airticket.database.*"%>

<%! String title = "Flugbest�tigung"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
			   <%  
                 if (benutzer != null) {  
                     Database database = new Database();
                     
					if (session.getAttribute("hinflugTicket") != null | session.getAttribute("rueckflugTicket") != null) 
					{
						session.removeAttribute("Benutzer");                              	 
						
						%>
						<h1>Ihre Flugbuchung | Best�tigung</h1>
						<div id="flugbestaetigung">
							<h2>Buchungsbest�tigung</h2>
							<p class="meldung">Ihr Flug wurde erfolgreich f�r sie gebucht. Das Ticket ist ab sofort in ihrem Konto verf�gbar.</p>
							<p><a href="<%=request.getContextPath() %>/benutzer_konto/konto.jsp">> Zu meinem Konto</a>						
							   <br />
							   <br />
							   Vielen Dank, dass sie AirTicket nutzen.
							</p>
						</div>
						<%    
					} 
					else {   
						response.sendRedirect(request.getContextPath()+"/flug/finden.jsp");
					}
					
					if(session.getAttribute("hinflugTicket") != null)
                  	 {
						Ticket hinflugTicket = (Ticket) session.getAttribute("hinflugTicket");
						session.removeAttribute("hinflugTicket");
						database.addTicket(hinflugTicket, benutzer);       

                  	 }
					
                    if(session.getAttribute("rueckflugTicket") != null)
                    {
						Ticket rueckflugTicket = (Ticket) session.getAttribute("rueckflugTicket");
						session.removeAttribute("rueckflugTicket");
						database.addTicket(rueckflugTicket, benutzer);  
                    }
                 }
			  %>

			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>