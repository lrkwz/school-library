 package school.library

import org.springframework.dao.DataIntegrityViolationException

class ReaderController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [readerInstanceList: Reader.list(params), readerInstanceTotal: Reader.count()]
    }

    def create() {
   		switch (request.method) {
		case 'GET':
        	[readerInstance: new Reader(params)]
			break
		case 'POST':
	        def readerInstance = new Reader(params)
	        if (!readerInstance.save(flush: true)) {
	            render view: 'create', model: [readerInstance: readerInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'reader.label', default: 'Reader'), readerInstance.id])
	        redirect action: 'show', id: readerInstance.id
			break
		}
    }

    def show() {
        def readerInstance = Reader.get(params.id)
        if (!readerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), params.id])
            redirect action: 'list'
            return
        }

        [readerInstance: readerInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def readerInstance = Reader.get(params.id)
	        if (!readerInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [readerInstance: readerInstance]
			break
		case 'POST':
	        def readerInstance = Reader.get(params.id)
	        if (!readerInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (readerInstance.version > version) {
	                readerInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'reader.label', default: 'Reader')] as Object[],
	                          "Another user has updated this Reader while you were editing")
	                render view: 'edit', model: [readerInstance: readerInstance]
	                return
	            }
	        }

	        readerInstance.properties = params

	        if (!readerInstance.save(flush: true)) {
	            render view: 'edit', model: [readerInstance: readerInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'reader.label', default: 'Reader'), readerInstance.id])
	        redirect action: 'show', id: readerInstance.id
			break
		}
    }

    def delete() {
        def readerInstance = Reader.get(params.id)
        if (!readerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), params.id])
            redirect action: 'list'
            return
        }

        try {
            readerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'reader.label', default: 'Reader'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reader.label', default: 'Reader'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
