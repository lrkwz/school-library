 package school.library

import org.springframework.dao.DataIntegrityViolationException

class BookController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
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

			flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])
	        redirect action: 'show', id: bookInstance.id
			break
		}
    }

    def show() {
        def bookInstance = Book.get(params.id)
        if (!bookInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])
            redirect action: 'list'
            return
        }

        [bookInstance: bookInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def bookInstance = Book.get(params.id)
	        if (!bookInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [bookInstance: bookInstance]
			break
		case 'POST':
	        def bookInstance = Book.get(params.id)
	        if (!bookInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (bookInstance.version > version) {
	                bookInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'book.label', default: 'Book')] as Object[],
	                          "Another user has updated this Book while you were editing")
	                render view: 'edit', model: [bookInstance: bookInstance]
	                return
	            }
	        }

	        bookInstance.properties = params

	        if (!bookInstance.save(flush: true)) {
	            render view: 'edit', model: [bookInstance: bookInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])
	        redirect action: 'show', id: bookInstance.id
			break
		}
    }

    def delete() {
        def bookInstance = Book.get(params.id)
        if (!bookInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])
            redirect action: 'list'
            return
        }

        try {
            bookInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'book.label', default: 'Book'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'book.label', default: 'Book'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
