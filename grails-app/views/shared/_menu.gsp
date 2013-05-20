<li
	class="<%= request.forwardURI == "${createLink(uri: '/book')}" ? 'active' : '' %>"><a
	href="${createLink(uri: '/book')}"><i
		class="icon-plus-sign icon-white"></i> Book management</a></li>
<li
	class="<%= request.forwardURI == "${createLink(uri: '/reader')}" ? 'active' : '' %>"><a
	href="${createLink(uri: '/reader')}"><i
		class="icon-plus-sign icon-white"></i> Students management</a></li>
		