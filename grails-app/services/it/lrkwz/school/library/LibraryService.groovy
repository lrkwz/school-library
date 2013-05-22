package it.lrkwz.school.library

import groovy.time.TimeCategory

class LibraryService {

	Book saveBook( bookParams, volumesParams ){

		def school = School.findByName(bookParams.libraryName)
		if( school == null ){
			school = new School( name: bookParams.libraryName).save()
		}

		def volumes = []
		volumesParams.each {
			volumes.push( new Volume( code: it.code, publisher:it.publisher, publishedOn: it.publishedOn ).save() )
		}

		def book = new Book(
				author: bookParams.author,
				title: bookParams.title,
				library: school,
				volumes: volumes
				).save()

		return book;
	}

	Volume leanBook( Book book, Student student ) {
		def Volume volume = book?.volumes.find { it.isAvailable == true }
		leanBook( volume, student )
		return volume
	}

	def leanBook( Volume volume, Student student ) {
		if( volume ){
			volume.isAvailable = false
			use( TimeCategory){

				new Loan(book: volume, lender: student, loanDate: new Date(), expectedReturnDate: new Date() + 1.month).save()
			}
			volume.save()
		}
	}

	Loan returnBook( volume, student ){
		def loan = Loan.findByBookAndLender( volume, student)
		if( loan ){
			loan.returnDate = new Date()
			volume.isAvailable = true
			loan.save()
			volume.save()
		}
		return loan
	}
}