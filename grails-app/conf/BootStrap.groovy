import grails.util.Environment
import it.lrkwz.school.Role
import it.lrkwz.school.User
import it.lrkwz.school.UserRole
import it.lrkwz.school.library.Book
import it.lrkwz.school.library.School
import it.lrkwz.school.library.Student
import it.lrkwz.school.library.Volume

class BootStrap {
	def grailsApplication

	def init = { servletContext ->

		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def librarianRole = new Role(authority: 'ROLE_LIBRARIAN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

		def administrator = new User(username: 'admin', enabled: true, password: 'admin')
		administrator.save(flush: true)

		UserRole.create administrator, adminRole, true

		assert User.count() == 1
		assert Role.count() == 3
		assert UserRole.count() == 1

		switch(Environment.getCurrent()){
			case Environment.DEVELOPMENT:
				def cavalcanti = new School( name: "Cavalcanti").save()

				def librarianUser = new User(username: 'librarian', enabled: true, password: 'password', school: cavalcanti).save()
				UserRole.create librarianUser, librarianRole, true
				def testUser = new User(username: 'test', enabled: true, password: 'password', school: cavalcanti).save()

				def luca = new Student( firstName: "Luca", lastName:"Orlandi", email: "luca.orlandi@gmail.com", school: cavalcanti )
				luca.save()

				if( luca.hasErrors()){
					println luca.errors
				}

				def cavallopazzo = new Book(author: "Giovanni Pellegrino", title: "Cavallopazzo", library: cavalcanti,  volumes: [ new Volume(code:"G01", publisher: "Lupetti", publishedOn: Date.parse("yyyyMMdd", "19991231")).save()] )
				cavallopazzo.save()

				cavallopazzo.addTag("narrativa")
				cavallopazzo.addTag("italiano")
				cavallopazzo.save()
				if( cavallopazzo.hasErrors()){
					println cavallopazzo.errors
				}

				def rom = new Book(author: "Santino Spinelli", title: "ROM genti libere", library: cavalcanti, volumes: [new Volume(code:"G02", publisher: "Dalai Editore", publishedOn: Date.parse("yyyyMMdd", "20031231") )] )
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
