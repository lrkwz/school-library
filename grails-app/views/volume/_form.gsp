<%@ page import="it.lrkwz.school.library.Volume" %>



<div class="fieldcontain ${hasErrors(bean: volumeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="volume.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${volumeInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: volumeInstance, field: 'isAvailable', 'error')} ">
	<label for="isAvailable">
		<g:message code="volume.isAvailable.label" default="Is Available" />
		
	</label>
	<g:checkBox name="isAvailable" value="${volumeInstance?.isAvailable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: volumeInstance, field: 'publishedOn', 'error')} required">
	<label for="publishedOn">
		<g:message code="volume.publishedOn.label" default="Published On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="publishedOn" precision="day"  value="${volumeInstance?.publishedOn}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: volumeInstance, field: 'publisher', 'error')} ">
	<label for="publisher">
		<g:message code="volume.publisher.label" default="Publisher" />
		
	</label>
	<g:textField name="publisher" value="${volumeInstance?.publisher}"/>
</div>

