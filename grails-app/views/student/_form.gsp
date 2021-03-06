<%@ page import="it.lrkwz.school.library.Student" %>



<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="reader.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${readerInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'dateOfBirth', 'error')} ">
	<label for="dateOfBirth">
		<g:message code="reader.dateOfBirth.label" default="Date Of Birth" />
		
	</label>
	<g:datePicker name="dateOfBirth" precision="day"  value="${readerInstance?.dateOfBirth}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'classRoom', 'error')} ">
	<label for="classRoom">
		<g:message code="reader.classRoom.label" default="Class Room" />
		
	</label>
	<g:textField name="classRoom" value="${readerInstance?.classRoom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="reader.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${readerInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="reader.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${readerInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'school', 'error')} required">
	<label for="school">
		<g:message code="reader.school.label" default="School" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="school" name="school.id" from="${it.lrkwz.school.library.School.list()}" optionKey="id" required="" value="${readerInstance?.school?.id}" class="many-to-one"/>
</div>

