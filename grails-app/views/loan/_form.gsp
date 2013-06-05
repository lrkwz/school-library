<%@ page import="it.lrkwz.school.library.Loan" %>


<div class="fieldcontain ${hasErrors(bean: loanInstance, field: 'lender', 'error')} required">
	<label for="lender">
		<g:message code="loan.lender.label" default="Lender" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lender" name="lender.id" from="${it.lrkwz.school.library.Student.list()}" optionKey="id" required="" value="${loanInstance?.lender?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loanInstance, field: 'returnDate', 'error')} ">
	<label for="returnDate">
		<g:message code="loan.returnDate.label" default="Return Date" />
		
	</label>
	<g:datePicker name="returnDate" precision="day"  value="${loanInstance?.returnDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: loanInstance, field: 'expectedReturnDate', 'error')} required">
	<label for="expectedReturnDate">
		<g:message code="loan.expectedReturnDate.label" default="Expected Return Date" />
		<span class="required-indicator">*</span>
	</label>
	${loanInstance?.expectedReturnDate?.toString()}
</div>

<div class="fieldcontain ${hasErrors(bean: loanInstance, field: 'book', 'error')} required">
	<label for="book">
		<g:message code="loan.book.label" default="Book" />lrkwz: ${bookInstance}
		<span class="required-indicator">*</span>
	</label>
	<g:select id="book" name="book.id" from="${bookInstance.getAvailableVolumes()}" optionKey="id" required="" value="${loanInstance?.book?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loanInstance, field: 'loanDate', 'error')} required">
	<label for="loanDate">
		<g:message code="loan.loanDate.label" default="Loan Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="loanDate" precision="day"  value="${loanInstance?.loanDate}"  />
</div>

