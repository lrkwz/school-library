<li
	class="<%= request.forwardURI == "${createLink(uri: '/book')}" ? 'active' : '' %>"><a
	href="${createLink(uri: '/book')}"><i
		class="icon-book icon-white"></i> Book management</a></li>
<li
	class="<%= request.forwardURI == "${createLink(uri: '/student')}" ? 'active' : '' %>"><a
	href="${createLink(uri: '/student')}"><i
		class="icon-user icon-white"></i> Students management</a></li>
<sec:ifLoggedIn>
	<li><a href="${createLink(uri: '/logout')}"><i
			class="icon-stop icon-white"></i> Logout</a></li>
</sec:ifLoggedIn>