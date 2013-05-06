import grails.util.Environment
import groovy.time.TimeCategory
import school.library.Book
import school.library.Loan
import school.library.Reader

class BootStrap {
	def grailsApplication

	def init = { servletContext ->
		switch(Environment.getCurrent()){
			case Environment.DEVELOPMENT:

				def luca = new Reader( firstName: "Luca", lastName:"Orlandi", email: "luca.orlandi@gmail.com" )
				luca.save()

				if( luca.hasErrors()){
					println luca.errors
				}

				def cavallopazzo = new Book(code:"G01", author: "Giovanni Pellegrino", title: "Cavallopazzo", publisher: "Lupetti" )
				cavallopazzo.save()
				if( cavallopazzo.hasErrors()){
					println cavallopazzo.errors
				}

				use( TimeCategory){
					def firstLoan = new Loan(book: cavallopazzo, lender: luca, loanDate: new Date(), expectedReturnDate: new Date() + 1.month );
					firstLoan.save()
					if( firstLoan.hasErrors()){
						println firstLoan.errors
					}
				}

				break

			case Environment.PRODUCTION:
				break
		}
	}
	def destroy = {
	}
}
