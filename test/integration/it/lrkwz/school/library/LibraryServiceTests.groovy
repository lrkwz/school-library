package it.lrkwz.school.library

import static org.junit.Assert.*
import org.junit.*

class LibraryServiceTests {

	@Before
	void setUp() {
		// Setup logic here
	}

	@After
	void tearDown() {
		// Tear down logic here
	}

	def LibraryService libraryService

	@Test
	void testLeanBook() {
		def school = new School( name: "Cavalcanti").save()

		def book = libraryService.saveBook(author:"Giovanni Pellegrino",
		title: "Cavallopazzo",
		libraryName: "Cavalcanti",
		[
			[publisher: "Lupetti", code: "G01", publishedOn: new Date()],
			[publisher: "Einaudi", code: "G02", publishedOn: new Date()]
		])

		def luca = new Student( firstName: "Luca", lastName:"Orlandi", school: school).save()
		def borrowedVolume = libraryService.leanBook(book, luca)
		assert Book.findByTitle( "Cavallopazzo").getAvailableVolume() != null

		def mario = new Student( firstName: "Mario", lastName: "Rossi", school: school).save()
		libraryService.leanBook(book, mario)
		assert Book.findByTitle( "Cavallopazzo").getAvailableVolume() == null

		Loan loan = libraryService.returnBook(borrowedVolume, luca)
		println loan
		assert Book.findByTitle( "Cavallopazzo").getAvailableVolume() != null
	}

	@Test
	void testCreateBookService(){
		def book = libraryService.saveBook(author:"Giovanni Pellegrino",
		title: "Cavallopazzo",
		libraryName: "Cavalcanti",
		[
			[publisher: "Lupetti", code: "G01", publishedOn: new Date()],
			[publisher: "Einaudi", code: "G02", publishedOn: new Date()]
		])

		println book
		assert Book.findByTitle( "Cavallopazzo").count == 1
		assert Book.findByTitle( "Cavallopazzo").getAvailableVolume() != null
	}

	@Test
	void testCreateBook() {
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
	}
}
