import grails.util.Environment
import groovy.time.TimeCategory
import it.lrkwz.school.Role
import it.lrkwz.school.User
import it.lrkwz.school.UserRole
import it.lrkwz.school.library.Book;
import it.lrkwz.school.library.Loan
import it.lrkwz.school.library.Reader;
import it.lrkwz.school.library.School

class BootStrap {
	def grailsApplication

	def init = { servletContext ->

		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

		def administrator = new User(username: 'admin', enabled: true, password: 'admin')
		administrator.save(flush: true)

		UserRole.create administrator, adminRole, true

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1

		switch(Environment.getCurrent()){
			case Environment.DEVELOPMENT:
				def cavalcanti = new School( name: "Cavalcanti").save()

				def testUser = new User(username: 'test', enabled: true, password: 'test', school: cavalcanti).save()

				def luca = new Reader( firstName: "Luca", lastName:"Orlandi", email: "luca.orlandi@gmail.com", school: cavalcanti )
				luca.save()

				if( luca.hasErrors()){
					println luca.errors
				}

				def cavallopazzo = new Book(code:"G01", author: "Giovanni Pellegrino", title: "Cavallopazzo", publisher: "Lupetti", library: cavalcanti )
				cavallopazzo.save()

				cavallopazzo.addTag("narrativa")
				cavallopazzo.addTag("italiano")
				cavallopazzo.save()
				if( cavallopazzo.hasErrors()){
					println cavallopazzo.errors
				}

				def rom = new Book(code:"G02", author: "Santino Spinelli", title: "ROM genti libere", publisher: "Dalai Editore", library: cavalcanti )
				rom.save()

				rom.addTag("storia")
				rom.addTag("italiano")
				rom.save()
				if( rom.hasErrors()){
					println rom.errors
				}

				break

			case Environment.PRODUCTION:
				break
		}
	}
	def destroy = {
	}
}
