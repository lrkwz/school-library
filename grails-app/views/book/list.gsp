
<%@ page import="school.library.Book" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<r:require module="bootstrap"/>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="author" title="${message(code: 'book.author.label', default: 'Author')}" />
						
							<g:sortableColumn property="code" title="${message(code: 'book.code.label', default: 'Code')}" />
						
							<g:sortableColumn property="publisher" title="${message(code: 'book.publisher.label', default: 'Publisher')}" />
						
							<g:sortableColumn property="title" title="${message(code: 'book.title.label', default: 'Title')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${bookInstanceList}" var="bookInstance">
						<tr>
						
							<td>${fieldValue(bean: bookInstance, field: "author")}</td>
						
							<td>${fieldValue(bean: bookInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: bookInstance, field: "publisher")}</td>
						
							<td>${fieldValue(bean: bookInstance, field: "title")}</td>
						
							<td class="link">
								<g:link action="show" id="${bookInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${bookInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
