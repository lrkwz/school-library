<%@ page import="it.lrkwz.school.library.School" %>



<div class="fieldcontain ${hasErrors(bean: schoolInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="school.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${schoolInstance?.name}"/>
</div>

