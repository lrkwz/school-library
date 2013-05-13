import grails.util.Environment
import groovy.time.TimeCategory
import it.lrkwz.school.Role
import it.lrkwz.school.User
import it.lrkwz.school.UserRole
import school.library.Book
import school.library.Loan
import school.library.Reader

class BootStrap {
	def grailsApplication

	def init = { servletContext ->

		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

		def testUser = new User(username: 'admin', enabled: true, password: 'admin')
		testUser.save(flush: true)

		UserRole.create testUser, adminRole, true

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1

		switch(Environment.getCurrent()){
			case Environment.DEVELOPMENT:

				def luca = new Reader( firstName: "Luca", lastName:"Orlandi", email: "luca.orlandi@gmail.com" )
				luca.save()

				if( luca.hasErrors()){
					println luca.errors
				}

				def cavallopazzo = new Book(code:"G01", author: "Giovanni Pellegrino", title: "Cavallopazzo", publisher: "Lupetti" )
				cavallopazzo.save()

				cavallopazzo.addTag("narrativa")
				cavallopazzo.addTag("italiano")
				cavallopazzo.save()
				if( cavallopazzo.hasErrors()){
					println cavallopazzo.errors
				}

				def rom = new Book(code:"G02", author: "Santino Spinelli", title: "ROM genti libere", publisher: "Dalai Editore" )
				rom.save()

				rom.addTag("storia")
				rom.addTag("italiano")
				rom.save()
				if( rom.hasErrors()){
					println rom.errors
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
