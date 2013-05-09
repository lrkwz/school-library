
<%@ page import="school.library.Loan" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'loan.label', default: 'Loan')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<r:require module="bootstrap"/>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${loanInstance?.returnDate}">
						<dt><g:message code="loan.returnDate.label" default="Return Date" /></dt>
						
							<dd> <g:formatDate format="${message(code:"default.date.format.short")}" date="${loanInstance?.returnDate}" /></dd>
						
					</g:if>
				
					<g:if test="${loanInstance?.expectedReturnDate}">
						<dt><g:message code="loan.expectedReturnDate.label" default="Expected Return Date" /></dt>
						
							<dd> <g:formatDate format="${message(code:"default.date.format.short")}" date="${loanInstance?.expectedReturnDate}" /></dd>
						
					</g:if>
				
					<g:if test="${loanInstance?.book}">
						<dt><g:message code="loan.book.label" default="Book" /></dt>
						
							<dd><g:link controller="book" action="show" id="${loanInstance?.book?.id}">${loanInstance?.book?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${loanInstance?.lender}">
						<dt><g:message code="loan.lender.label" default="Lender" /></dt>
						
							<dd><g:link controller="reader" action="show" id="${loanInstance?.lender?.id}">${loanInstance?.lender?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${loanInstance?.loanDate}">
						<dt><g:message code="loan.loanDate.label" default="Loan Date" /></dt>
						
							<dd> <g:formatDate format="${message(code:"default.date.format.short")}" date="${loanInstance?.loanDate}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${loanInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${loanInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
