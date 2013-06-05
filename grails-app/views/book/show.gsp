
<%@ page import="it.lrkwz.school.library.Book"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:set var="entityName"
	value="${message(code: 'book.label', default: 'Book')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
<r:require module="bootstrap" />
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul class="nav nav-list">
					<li class="nav-header">
						${entityName}
					</li>
					<li><g:link class="list" action="list">
							<i class="icon-list"></i>
							<g:message code="default.list.label" args="[entityName]" />
						</g:link></li>
					<li><g:link class="create" action="create">
							<i class="icon-plus"></i>
							<g:message code="default.create.label" args="[entityName]" />
						</g:link></li>
				</ul>
			</div>
			<tc:tagCloud bean="${it.lrkwz.school.library.Book }"
				action="findByTag" />
			<table class="table table-condensed table-bordered table-hover">
				<caption>Loan history</caption>
				<tbody>
					<g:each in="${loanList }">
						<tr>
							<g:if test="${! it.isEmpty() }">
								<td><g:link controller="loan" action="show" id="${it.id}">
										<g:formatDate date="${it.loanDate}" type="date" style="SHORT" />
									</g:link></td>
								<td><g:link controller="loan" action="show" id="${it.id}">
										${it.lender }
									</g:link></td>
							</g:if>
						</tr>
					</g:each>
				</tbody>
			</table>
			<g:if test="${isAvailable }">
				<g:link class="btn btn-info" controller="loan" action="create"
					params="[bookId: bookInstance?.id]">
					<g:message code="book.borrow.label" default="Borrow this book" />
				</g:link>
			</g:if>
		</div>

		<div class="span9">

			<div class="page-header">
				<h1>
					<g:message code="default.show.label" args="[entityName]" />
				</h1>
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>

			<dl>

				<g:if test="${bookInstance?.author}">
					<dt>
						<g:message code="book.author.label" default="Author" />
					</dt>

					<dd>
						<g:fieldValue bean="${bookInstance}" field="author" />
					</dd>

				</g:if>

				<g:if test="${bookInstance?.title}">
					<dt>
						<g:message code="book.title.label" default="Title" />
					</dt>

					<dd>
						<g:fieldValue bean="${bookInstance}" field="title" />
					</dd>

				</g:if>

				<g:if test="${bookInstance?.tags }">
					<dt>
						<g:message code="book.tags.label" default="Tags" />
					</dt>
					<dd>
						<g:hiddenField name="tags" value="${bookInstance.tags.join(',')}"
							disabled="true" />
					</dd>
				</g:if>

			</dl>
			<r:script>
		    $(function() {
		      $("#tags").select2({ tags: [],
		      					tokenSeparators: [",", " "]
		      					});
		    });
			</r:script>
			<g:form>
				<g:hiddenField name="id" value="${bookInstance?.id}" />
				<div class="form-actions">
					<g:link class="btn" action="edit" id="${bookInstance?.id}">
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
