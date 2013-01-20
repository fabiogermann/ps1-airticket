<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%! String title = "Flug ausw�hlen"; %>

<body>
	<div id="seite">

		<%@include file="../include/header.jsp"%>

		<section>

			<%@include file="../include/progressbar.jsp"%>

			<article>
				<h1><%=title %></h1>
				<form action="buchen.jsp" method="post">
					<div id="hinflug">
						<h2>Hinflug</h2>
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
							<tr>
								<td>K2 482</td>
								<td>27.11.12 | 09:20</td>
								<td>27.11.12 | 10:35</td>
								<td>1:15h</td>
								<td>Z�rich</td>
								<td>London</td>
								<td>Business: 44 <br /> Economy: 122
								</td>
								<td><input type="radio" id="radio_flug_h1_e" name="flug_auswaehlen_h" checked="checked" value="Flug_h1_e" /> <label for="radio_flug_h1_e">250 CHF</label></td>
								<td><input type="radio" id="radio_flug_h1_b" name="flug_auswaehlen_h" value="Flug_h1_b" /> <label for="radio_flug_h1_b">500 CHF</label></td>
							</tr>
							<tr>
								<td>AB 8589</td>
								<td>27.11.12 | 10:35</td>
								<td>27.11.12 | 11:53</td>
								<td>1.18:h</td>
								<td>Z�rich</td>
								<td>London</td>
								<td>Business: 12 <br /> Economy: 196
								</td>
								<td><input type="radio" id="radio_flug_h2_e" name="flug_auswaehlen_h" value="Flug_h2_e" /> <label for="radio_flug_h2_e">220 CHF</label></td>
								<td><input type="radio" id="radio_flug_h2_b" name="flug_auswaehlen_h" value="Flug_h2_b" /> <label for="radio_flug_h2_b">535 CHF</label></td>
							</tr>
							<tr>
								<td>AF 1115</td>
								<td>27.11.12 | 15:20</td>
								<td>27.11.12 | 16:35</td>
								<td>1:15h</td>
								<td>Z�rich</td>
								<td>London</td>
								<td>Business: 38 <br /> Economy: 96
								</td>
								<td><input type="radio" id="radio_flug_h3_e" name="flug_auswaehlen_h" value="Flug_h3_e" /> <label for="radio_flug_h3_e">285 CHF</label></td>
								<td><input type="radio" id="radio_flug_h3_b" name="flug_auswaehlen_h" value="Flug_h3_b" /> <label for="radio_flug_h3_b">600 CHF</label></td>
							</tr>
							<!-- 
							<tr>
								<td>SQ 345</td>
								<td>27.11.12 | 17:25</td>
								<td>27.11.11 | 18:35</td>
								<td>1:10h</td>
								<td>Z�rich</td>
								<td>London</td>
								<td>Business: 11 <br/> Economy: 150</td>
								<td><input type="radio" id="radio_flug_h4_e" name="flug_auswaehlen_h" value="Flug_h4_e" /> <label for="radio_flug_h4_e">244 CHF</label></td>
								<td><input type="radio" id="radio_flug_h4_b" name="flug_auswaehlen_h" value="Flug_h4_b" /> <label for="radio_flug_h4_b">566 CHF</label>
								</td>
							</tr>
							-->
						</table>
					</div>
					
					<div id="rueckflug">
						<h2>R�ckflug</h2>
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
							<tr>
								<td>K2 498</td>
								<td>29.11.12 | 10:20</td>
								<td>29.11.12 | 11:35</td>
								<td>1:15h</td>
								<td>London</td>
								<td>Z�rich</td>
								<td>Business: 22 <br /> Economy: 210
								</td>
								<td><input type="radio" id="radio_flug_r1_e" name="flug_auswaehlen_r" value="Flug_r1_e" /> <label for="radio_flug_r1_e">250 CHF</label></td>
								<td><input type="radio" id="radio_flug_r1_b" name="flug_auswaehlen_r" value="Flug_r1_b" /> <label for="radio_flug_r1_b">545 CHF</label></td>
							</tr>
							<tr>
								<td>AB 41258</td>
								<td>30.11.12 | 11:35</td>
								<td>30.11.12 | 12:53</td>
								<td>1:18h</td>
								<td>London</td>
								<td>Z�rich</td>
								<td>Business: 8 <br /> Economy: 120
								</td>
								<td><input type="radio" id="radio_flug_r2_e" name="flug_auswaehlen_r" value="Flug_r2_e" /> <label for="radio_flug_r2_e">266 CHF</label></td>
								<td><input type="radio" id="radio_flug_r2_b" name="flug_auswaehlen_r" checked="checked" value="Flug_r2_b" /> <label for="radio_flug_r2_b">375 CHF</label></td>
							</tr>
							<tr>
								<td>AF 1145</td>
								<td>30.11.12 | 16:20</td>
								<td>30.11.12 | 17:35</td>
								<td>1:15h</td>
								<td>London</td>
								<td>Z�rich</td>
								<td>Business: 36 <br /> Economy: 116
								</td>
								<td><input type="radio" id="radio_flug_r3_e" name="flug_auswaehlen_r" value="Flug_r3_e" /> <label for="radio_flug_r3_e">227 CHF</label></td>
								<td><input type="radio" id="radio_flug_r3_b" name="flug_auswaehlen_r" value="Flug_r3_b" /> <label for="radio_flug_r3_b">561 CHF</label></td>
							</tr>
							<!-- 
							<tr>
								<td>SQ 654</td>
								<td>01.12.12 | 18:25</td>
								<td>01.12.12 | 19:35</td>
								<td>1:15h</td>
								<td>London</td>
								<td>Z�rich</td>
								<td>Business: 27 <br/> Economy: 140</td>
								<td><input type="radio" id="radio_flug_r4_e" name="flug_auswaehlen_r" value="Flug_r4_e" /> <label for="radio_flug_r4_e">199 CHF</label></td>
								<td><input type="radio" id="radio_flug_r4_b" name="flug_auswaehlen_r" value="Flug_r4_b" /> <label for="radio_flug_r4_b">435 CHF</label></td>
							</tr>
							-->
						</table>
					</div>

					<input type="submit" id="submit_auswahl" name="submit_auswahl" value="Ausw�hlen" class="button next" />
				</form>
				
				<!-- <input type="button" id="back" name="back" value="Zur�ck" class="button back" onclick="history.back()" /> -->
				<form action="index.html" method="post">
					<input type="submit" id="back" name="back" value="Zur�ck" class="button back" />
				</form>

			</article>

			<%@include file="../include/nav.jsp"%>

		</section>

		<%@include file="../include/footer.jsp"%>

	</div>
</body>
</html>