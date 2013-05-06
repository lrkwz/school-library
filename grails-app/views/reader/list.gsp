
<%@ page import="school.library.Reader" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'reader.label', default: 'Reader')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
						
							<g:sortableColumn property="email" title="${message(code: 'reader.email.label', default: 'Email')}" />
						
							<g:sortableColumn property="birthday" title="${message(code: 'reader.birthday.label', default: 'Birthday')}" />
						
							<g:sortableColumn property="classRoom" title="${message(code: 'reader.classRoom.label', default: 'Class Room')}" />
						
							<g:sortableColumn property="firstName" title="${message(code: 'reader.firstName.label', default: 'First Name')}" />
						
							<g:sortableColumn property="lastName" title="${message(code: 'reader.lastName.label', default: 'Last Name')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${readerInstanceList}" var="readerInstance">
						<tr>
						
							<td>${fieldValue(bean: readerInstance, field: "email")}</td>
						
							<td><g:formatDate date="${readerInstance.birthday}" /></td>
						
							<td>${fieldValue(bean: readerInstance, field: "classRoom")}</td>
						
							<td>${fieldValue(bean: readerInstance, field: "firstName")}</td>
						
							<td>${fieldValue(bean: readerInstance, field: "lastName")}</td>
						
							<td class="link">
								<g:link action="show" id="${readerInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${readerInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
