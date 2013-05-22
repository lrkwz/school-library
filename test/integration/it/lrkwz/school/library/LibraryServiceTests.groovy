package it.lrkwz.school.library



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(LibraryService)
class LibraryServiceTests {

	def LibraryService libraryService

	void testLeanBook() {
		def cavalcanti = new School( name: "Cavalcanti").save()
		def volg01 = new Volume(publisher: "Lupetti", code: "G01", publishedOn: new Date()).save()
		def volg02 = new Volume(publisher: "Einaudi", code: "G02", publishedOn: new Date()).save()

		def cavallopazzo = new Book(
				author: "Giovanni Pellegrino",
				title: "Cavallopazzo",
				library: cavalcanti,
				volumes: [
					volg01,
					volg02,
					new Volume(publisher: "Feltrinelli", code: "G03", publishedOn: new Date()).save()]
				).save()

		println cavallopazzo
		assert Book.count() == 1
		assert Book.findByTitle("Cavallopazzo").getVolumes().size() == 3

		def luca = new Student( firstName: "Luca", school: cavalcanti)

		def lendedVolume = libraryService.leanBook(cavallopazzo, luca)
	}

	@Test
	void testCreateBook() {
		def cavalcanti = new School( name: "Cavalcanti")
		def volg01 = new Volume(publisher: "Lupetti", code: "G01", publishedOn: new Date())
		def volg02 = new Volume(publisher: "Einaudi", code: "G02", publishedOn: new Date())

		def cavallopazzo = new Book(
				author: "Giovanni Pellegrino",
				title: "Cavallopazzo",
				library: cavalcanti,
				volumes: [
					volg01,
					volg02,
					new Volume(publisher: "Feltrinelli", code: "G03", publishedOn: new Date())]
				)

		println cavallopazzo
		//assert Book.count() == 1
		//assert Book.findByTitle("Cavallopazzo").getVolumes().size() == 3
	}
}
