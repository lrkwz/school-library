package it.lrkwz.school.library

import school.library.Book;
import school.library.Reader;

/*
 * Loan status:
 * 
 * - active -> returnDate = null
 * - closed -> returnDate != null 
 */
class Loan {

	Book book;
	Reader lender;
	Date loanDate;
	Date expectedReturnDate;
	Date returnDate = null;

	static constraints = {
		returnDate( nullable:true)
		//loanDate( min: new Date())
		expectedReturnDate(editable:false, validator: {val, obj -> val > obj.loanDate})
	}
}
