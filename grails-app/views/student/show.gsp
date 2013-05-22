
<%@ page import="it.lrkwz.school.library.Student" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'reader.label', default: 'Reader')}" />
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
				
					<g:if test="${readerInstance?.email}">
						<dt><g:message code="reader.email.label" default="Email" /></dt>
						
							<dd><g:fieldValue bean="${readerInstance}" field="email"/></dd>
						
					</g:if>
				
					<g:if test="${readerInstance?.dateOfBirth}">
						<dt><g:message code="reader.dateOfBirth.label" default="Date Of Birth" /></dt>
						
							<dd> <g:formatDate format="${message(code:"default.date.format.short")}" date="${readerInstance?.dateOfBirth}" /></dd>
						
					</g:if>
				
					<g:if test="${readerInstance?.classRoom}">
						<dt><g:message code="reader.classRoom.label" default="Class Room" /></dt>
						
							<dd><g:fieldValue bean="${readerInstance}" field="classRoom"/></dd>
						
					</g:if>
				
					<g:if test="${readerInstance?.firstName}">
						<dt><g:message code="reader.firstName.label" default="First Name" /></dt>
						
							<dd><g:fieldValue bean="${readerInstance}" field="firstName"/></dd>
						
					</g:if>
				
					<g:if test="${readerInstance?.lastName}">
						<dt><g:message code="reader.lastName.label" default="Last Name" /></dt>
						
							<dd><g:fieldValue bean="${readerInstance}" field="lastName"/></dd>
						
					</g:if>
				
					<g:if test="${readerInstance?.school}">
						<dt><g:message code="reader.school.label" default="School" /></dt>
						
							<dd><g:link controller="school" action="show" id="${readerInstance?.school?.id}">${readerInstance?.school?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${readerInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${readerInstance?.id}">
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
