package it.lrkwz.school.library



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookTests {

	void testSomething() {
		def cavalcanti = new School( name: "Cavalcanti")
		def cavallopazzo = new Book(author: "Giovanni Pellegrino", title: "Cavallopazzo", library: cavalcanti ).save()
		
		assert Book.count() == 1
	}
}
