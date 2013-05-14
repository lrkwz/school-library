 package it.lrkwz.school.library

import grails.converters.JSON

import org.apache.commons.logging.LogFactory
import org.grails.taggable.Tag
import org.springframework.dao.DataIntegrityViolationException


class BookController {
	private static final log = LogFactory.getLog(this)

	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

	def deleteTag = {
		def bookInstance = Book.get(params.id)
		bookInstance.removeTag(params.tag)
		bookInstance.save()
		render bookInstance as JSON
	}

	def listTags = {
		def bookInstance = Book.get(params.id)
		render bookInstance.getTags() as JSON
	}

	def findTags = {
		render Tag.findAllByNameLike("${params.term}%")*.name as JSON
	}

	def findAllTags = {
		render Tag.findAll() as JSON
	}

	def findByTag() {
		log.debug "Filtering book list by tag ${params}"
		def map = [bookInstanceList: Book.findAllByTag(params.id), bookInstanceTotal: Book.countByTag(params.id)]
		render( view: "list", model: map )
	}

	def index() {
		redirect action: 'list', params: params
	}


	def list() {
		log.debug "Returning book list ${params}"
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[bookInstanceList: Book.list(params), bookInstanceTotal: Book.count()]
	}

	def create() {
		switch (request.method) {
			case 'GET':
				[bookInstance: new Book(params)]
				break
			case 'POST':
				def bookInstance = new Book(params)
				if (!bookInstance.save(flush: true)) {
					render view: 'create', model: [bookInstance: bookInstance]
					return
				}

				flash.message = message(code: 'default.created.message', args: [
					message(code: 'book.label', default: 'Book'),
					bookInstance.id
				])
				redirect action: 'show', id: bookInstance.id
				break
		}
	}

	def show() {
		def bookInstance = Book.get(params.id)
		if (!bookInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'book.label', default: 'Book'),
				params.id
			])
			redirect action: 'list'
			return
		}
		
		def loanList = Loan.findAllByBook(bookInstance)

		[bookInstance: bookInstance, loanList: loanList]
	}

	def edit() {
		switch (request.method) {
			case 'GET':
				def bookInstance = Book.get(params.id)
				if (!bookInstance) {
					flash.message = message(code: 'default.not.found.message', args: [
						message(code: 'book.label', default: 'Book'),
						params.id
					])
					redirect action: 'list'
					return
				}

			//def tags = bookInstance.tags.join(',')
			//log.debug "Current book has tags ${tags}"
				[bookInstance: bookInstance /*, tags: tags*/]
				break
			case 'POST':
				def bookInstance = Book.get(params.id)
				if (!bookInstance) {
					flash.message = message(code: 'default.not.found.message', args: [
						message(code: 'book.label', default: 'Book'),
						params.id
					])
					redirect action: 'list'
					return
				}

				if (params.version) {
					def version = params.version.toLong()
					if (bookInstance.version > version) {
						bookInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
								[
									message(code: 'book.label', default: 'Book')] as Object[],
								"Another user has updated this Book while you were editing")
						render view: 'edit', model: [bookInstance: bookInstance]
						return
					}
				}

				log.debug "User defined tags are ${params.tags}"
				bookInstance.properties = params
				bookInstance.setTags(params.list('tags'))

				if (!bookInstance.save(flush: true)) {
					render view: 'edit', model: [bookInstance: bookInstance]
					return
				}

				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'book.label', default: 'Book'),
					bookInstance.id
				])
				redirect action: 'show', id: bookInstance.id
				break
		}
	}

	def delete() {
		def bookInstance = Book.get(params.id)
		if (!bookInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'book.label', default: 'Book'),
				params.id
			])
			redirect action: 'list'
			return
		}

		try {
			bookInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'book.label', default: 'Book'),
				params.id
			])
			redirect action: 'list'
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'book.label', default: 'Book'),
				params.id
			])
			redirect action: 'show', id: params.id
		}
	}
}
