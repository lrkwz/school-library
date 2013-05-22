package it.lrkwz.school.library

class LibraryService {

	Book saveBook( bookParams, volumesParams ){

		def school = School.findByName(bookParams.libraryName)
		if( school == null ){
			school = new School( name: bookParams.libraryName).save(failOnError: true)
		}
		
		def volumes = []
		volumesParams.each {
			volumes.push( new Volume( code: it.code, publisher:it.publisher, publishedOn: it.publishedOn ).save(failOnError: true) )
		}
				
		def book = new Book(
				author: bookParams.author,
				title: bookParams.title,
				library: school,
				volumes: volumes
				).save(failOnError: true)

		return book;
	}

	Volume leanBook( Book book, Student student ) {
	}
	
	def leanBook( Volume book, Student student ) {
	}
	
	def returnBook( volume, student ){
	}
}
