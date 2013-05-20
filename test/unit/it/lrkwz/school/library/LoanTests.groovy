package it.lrkwz.school.library



import it.lrkwz.school.library.Loan;
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Loan)
class LoanTests {
	
	void testLoan(){
		
		use( TimeCategory){
			def firstLoan = new Loan(book: cavallopazzo, lender: luca, loanDate: new Date(), expectedReturnDate: new Date() + 1.month );
			firstLoan.save()
			if( firstLoan.hasErrors()){
				println firstLoan.errors
			}
		}

	}

    void testSomething() {
       fail "Implement me"
    }
}
