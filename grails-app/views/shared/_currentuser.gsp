
<sec:ifLoggedIn>
	<div style="display: inline-block" class="pull-right">
		<ul class="nav pull-right">
			<li class="divider-vertical"></li>
			<li><p class="navbar-text">
					Hello
					<sec:loggedInUserInfo field="username" />
					</a>
				</p></li>
			<li><a href="${createLink(uri: '/logout')}"><i
					class="icon-stop icon-white"></i> Logout</a></li>
		</ul>
	</div>
</sec:ifLoggedIn>
