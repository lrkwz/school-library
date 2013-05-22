package it.lrkwz.school.library

import org.springframework.dao.DataIntegrityViolationException

class StudentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [readerInstanceList: Student.list(params), readerInstanceTotal: Student.count()]
    }

    def create() {
        [readerInstance: new Student(params)]
    }

    def save() {
        def readerInstance = new Student(params)
        if (!readerInstance.save(flush: true)) {
            render(view: "create", model: [readerInstance: readerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reader.label', default: 'Reader'), readerInstance.id])
        redirect(action: "show", id: readerInstance.id)
    }

    def show(Long id) {
        def readerInstance = Student.get(id)
        if (!readerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), id])
            redirect(action: "list")
            return
        }

        [readerInstance: readerInstance]
    }

    def edit(Long id) {
        def readerInstance = Student.get(id)
        if (!readerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), id])
            redirect(action: "list")
            return
        }

        [readerInstance: readerInstance]
    }

    def update(Long id, Long version) {
        def readerInstance = Student.get(id)
        if (!readerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (readerInstance.version > version) {
                readerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reader.label', default: 'Reader')] as Object[],
                          "Another user has updated this Reader while you were editing")
                render(view: "edit", model: [readerInstance: readerInstance])
                return
            }
        }

        readerInstance.properties = params

        if (!readerInstance.save(flush: true)) {
            render(view: "edit", model: [readerInstance: readerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reader.label', default: 'Reader'), readerInstance.id])
        redirect(action: "show", id: readerInstance.id)
    }

    def delete(Long id) {
        def readerInstance = Student.get(id)
        if (!readerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reader.label', default: 'Reader'), id])
            redirect(action: "list")
            return
        }

        try {
            readerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reader.label', default: 'Reader'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reader.label', default: 'Reader'), id])
            redirect(action: "show", id: id)
        }
    }
}
