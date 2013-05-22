package it.lrkwz.school.library

import static org.junit.Assert.*
import org.junit.*

class LibraryTests {

	@Before
	void setUp() {
		// Setup logic here
	}

	@After
	void tearDown() {
		// Tear down logic here
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
		volumes: [volg01, volg02, new Volume(publisher: "Feltrinelli", code: "G03", publishedOn: new Date()).save()] ).save()

		println cavallopazzo
		assert Book.count() == 1
		assert Book.findByTitle("Cavallopazzo").getVolumes().size() == 3
	}
}
