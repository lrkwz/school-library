
<%@ page import="it.lrkwz.school.library.Volume" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'volume.label', default: 'Volume')}" />
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
				
					<g:if test="${volumeInstance?.code}">
						<dt><g:message code="volume.code.label" default="Code" /></dt>
						
							<dd><g:fieldValue bean="${volumeInstance}" field="code"/></dd>
						
					</g:if>
				
					<g:if test="${volumeInstance?.isAvailable}">
						<dt><g:message code="volume.isAvailable.label" default="Is Available" /></dt>
						
							<dd><g:formatBoolean boolean="${volumeInstance?.isAvailable}" /></dd>
						
					</g:if>
				
					<g:if test="${volumeInstance?.publishedOn}">
						<dt><g:message code="volume.publishedOn.label" default="Published On" /></dt>
						
							<dd> <g:formatDate format="${message(code:"default.date.format.short")}" date="${volumeInstance?.publishedOn}" /></dd>
						
					</g:if>
				
					<g:if test="${volumeInstance?.publisher}">
						<dt><g:message code="volume.publisher.label" default="Publisher" /></dt>
						
							<dd><g:fieldValue bean="${volumeInstance}" field="publisher"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${volumeInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${volumeInstance?.id}">
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
