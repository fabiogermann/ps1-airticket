<div id="progressbar">
	<ul>
		<li <% if (request.getServletPath().contains("finden") ){ %> class="active" <% } %>><p>1. Flug finden</p></li>
		<li <% if (request.getServletPath().contains("auswaehlenp") ){ %> class="active" <% } %>><p>2. Flug ausw�hlen</p></li>
		<li <% if (request.getServletPath().contains("buchen") ){ %> class="active" <% } %>><p>3. Flug buchen</p></li>
		<li <% if (request.getServletPath().contains("bestaetigung") ){ %> class="active" <% } %>><p>4. Flugbest�tigung</p></li>
	</ul>
</div>