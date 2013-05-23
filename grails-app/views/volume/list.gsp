
<%@ page import="it.lrkwz.school.library.Volume" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'volume.label', default: 'Volume')}" />
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
						
							<g:sortableColumn property="code" title="${message(code: 'volume.code.label', default: 'Code')}" />
						
							<g:sortableColumn property="isAvailable" title="${message(code: 'volume.isAvailable.label', default: 'Is Available')}" />
						
							<g:sortableColumn property="publishedOn" title="${message(code: 'volume.publishedOn.label', default: 'Published On')}" />
						
							<g:sortableColumn property="publisher" title="${message(code: 'volume.publisher.label', default: 'Publisher')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${volumeInstanceList}" status="i" var="volumeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						<td><g:link action="show" id="${volumeInstance.id}">${fieldValue(bean: volumeInstance, field: "code")}</g:link></td>
					
							<td><g:formatBoolean boolean="${volumeInstance.isAvailable}" /></td>
						
							<td><g:formatDate date="${volumeInstance.publishedOn}" /></td>
						
							<td>${fieldValue(bean: volumeInstance, field: "publisher")}</td>
						
							<td class="link">
								<g:link action="show" id="${volumeInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${volumeInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
