
<%@ page import="it.lrkwz.school.library.Loan" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'loan.label', default: 'Loan')}" />
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
						
							<g:sortableColumn property="returnDate" title="${message(code: 'loan.returnDate.label', default: 'Return Date')}" />
						
							<g:sortableColumn property="expectedReturnDate" title="${message(code: 'loan.expectedReturnDate.label', default: 'Expected Return Date')}" />
						
							<th class="header"><g:message code="loan.book.label" default="Book" /></th>
						
							<th class="header"><g:message code="loan.lender.label" default="Lender" /></th>
						
							<g:sortableColumn property="loanDate" title="${message(code: 'loan.loanDate.label', default: 'Loan Date')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${loanInstanceList}" var="loanInstance">
						<tr>
						
							<td><g:formatDate date="${loanInstance.returnDate}" /></td>
						
							<td><g:formatDate date="${loanInstance.expectedReturnDate}" /></td>
						
							<td>${fieldValue(bean: loanInstance, field: "book")}</td>
						
							<td>${fieldValue(bean: loanInstance, field: "lender")}</td>
						
							<td><g:formatDate date="${loanInstance.loanDate}" /></td>
						
							<td class="link">
								<g:link action="show" id="${loanInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${loanInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
