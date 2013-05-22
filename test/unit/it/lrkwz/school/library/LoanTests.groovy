package it.lrkwz.school.library



import grails.test.mixin.*
import groovy.time.TimeCategory

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Loan)
class LoanTests {

	void testLoan(){

		def cavallopazzo = new Book(title: "Cavallopazzo", volumes: [new Volume(code:"G01", isAvailable: true)])
		def Student luca = new Student( firstName: "Luca")

		println "Using " + cavallopazzo
		cavallopazzo.getVolumes().each { println ("Volume: " +  it.toString()  + " " + it.getIsAvailable())}

		use( TimeCategory){
			def firstLoan = new Loan(book: cavallopazzo.getAvailableVolume(), lender: luca, loanDate: new Date(), expectedReturnDate: new Date() + 1.month );
			firstLoan.save()
			if( firstLoan.hasErrors()){
				println firstLoan.errors
			}
		}
	}
}
