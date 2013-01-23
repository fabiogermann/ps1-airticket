<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Flug finden"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				<form action="auswaehlen.jsp" method="post">

					<fieldset>
						<legend>Ort</legend>
						<label for="departure">Von</label>
						<input type="text" id="departure" name="departure" placeholder="z.B. Zürich" required="required" autofocus="autofocus" />
						<label for="destination">Nach</label>
						<input type="text" id="destination" name="destination" placeholder="z.B. New York" required="required" />
					</fieldset>
					<fieldset>
						<legend>Datum</legend>
						<label for="date_flight">Hinflug</label>
						<input type="date" id="date_flight" name="date_flight" required="required" />
						<label for="date_returnflight">Rückflug</label>
						<input type="date" id="date_returnflight" name="date_returnflight" required="required" />
						<label for="date_tolerance">+/- Tage</label>
						<select id="date_tolerance" name="date_tolerance">
							<option label="0" value="0" selected="selected">0</option>
							<option label="1" value="1">1</option>
							<option label="2" value="2">2</option>
							<option label="3" value="3">3</option>
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
