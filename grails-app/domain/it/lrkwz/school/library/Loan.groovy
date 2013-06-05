package it.lrkwz.school.library


/*
 * Loan status:
 * 
 * - active -> returnDate = null
 * - closed -> returnDate != null 
 */
class Loan {

	Volume book;
	Student lender;
	Date loanDate =  new Date();
	Date expectedReturnDate;
	Date returnDate = null;

	static constraints = {
		returnDate( nullable:true)
		//loanDate( min: new Date())
		expectedReturnDate(editable:false, validator: {val, obj -> val > obj.loanDate})
	}
}
