<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:useBean id="flugFinden" class="org.zhaw.airticket.model.FlugFinden" scope="session" />
<%! String title = "Flug finden"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>
		<style>
			.ui-autocomplete {
			max-height: 300px;
			overflow-y: auto;
			overflow-x: hidden;
			}
		</style>
		<script type="text/javascript">
			<% 
				Autocomplete auto = new Autocomplete();
			%>
			<%=auto.setFllughafenAutocomplete("departure")%>
			<%=auto.setFllughafenAutocomplete("destination")%>
			<%=auto.setFlugnummerAutocomplete()%>
			<%=auto.setDatepickerDependent("date_flight", "date_returnflight")%>
		</script>
		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				<form action="<%=request.getContextPath()%>/suchen" method="post">
					<fieldset>
						<legend>Destination</legend>
						<label for="departure">Von</label>
						<input type="text" id="departure" name="departure" placeholder="z.B. Z�rich" required="required" autofocus="autofocus" value="${flugFinden.departure}" />
						<input type="hidden" id="departure_code" name="departure_code" value="${flugFinden.departure_code}" />
						<%if (!errors.getMsg("departure").equals("")) {%>
						<p class="error"><%=errors.getMsg("departure") %> </p>
						<% } %>
						<label for="destination">Nach</label>
						<input type="text" id="destination" name="destination" placeholder="z.B. New York" required="required" value="${flugFinden.destination}" />
						<input type="hidden" id="destination_code" name="destination_code" value="${flugFinden.destination_code}" />
						<%if (!errors.getMsg("destination").equals("")) {%>
						<p class="error"><%=errors.getMsg("destination") %> </p>
						<% } %>
					</fieldset>
                    <fieldset>
                        <legend>Flugnummer</legend>
                        <label for="flightnr">Flugnummer</label>
                        <input type="text" id="flightnr" name="flightnr" placeholder="z.B. LX1567" value="${flugFinden.flightnr}" />
                    	<input type="hidden" id="flightnr_code" name="flightnr_code" value="${flugFinden.flightnr_code}" />
                    </fieldset>
					<fieldset>
						<legend>Datum</legend>
						<label for="date_flight">Hinflug</label>
						<input type="date" id="date_flight" name="date_flight" placeholder="z.B. 21.12.2012" required="required" value="${flugFinden.date_flight}" />
						<%if (!errors.getMsg("date_flight").equals("")) {%>
						<p class="error"><%=errors.getMsg("date_flight") %> </p>
						<% } %>
						<label for="date_returnflight">R�ckflug</label>
						<input type="date" id="date_returnflight" name="date_returnflight" placeholder="z.B. 31.12.2012" required="required" value="${flugFinden.date_returnflight}" />
						<%if (!errors.getMsg("date_returnflight").equals("")) {%>
						<p class="error"><%=errors.getMsg("date_returnflight") %> </p>
						<% } %>
						<label for="date_tolerance">+/- Tage</label>
						<select id="date_tolerance" name="date_tolerance">
							<option label="0" value="0" <% if(flugFinden.getDate_tolerance().equals("0")) {  %> selected="selected" <% } %> >0</option> 
							<option label="1" value="1" <% if(flugFinden.getDate_tolerance().equals("1")) {  %> selected="selected" <% } %> >1</option>
							<option label="2" value="2" <% if(flugFinden.getDate_tolerance().equals("2")) {  %> selected="selected" <% } %> >2</option>
							<option label="3" value="3" <% if(flugFinden.getDate_tolerance().equals("3")) {  %> selected="selected" <% } %> >3</option>
						</select>
					</fieldset>
					<input type="submit" id="submit_search" name="submit_search" value="Suchen" class="button" />
				</form>
			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>
