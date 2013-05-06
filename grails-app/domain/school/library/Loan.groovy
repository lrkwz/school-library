package school.library

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
