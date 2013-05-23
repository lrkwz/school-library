package it.lrkwz.school.library

import org.springframework.dao.DataIntegrityViolationException

class VolumeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [volumeInstanceList: Volume.list(params), volumeInstanceTotal: Volume.count()]
    }

    def create() {
        [volumeInstance: new Volume(params)]
    }

    def save() {
        def volumeInstance = new Volume(params)
        if (!volumeInstance.save(flush: true)) {
            render(view: "create", model: [volumeInstance: volumeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'volume.label', default: 'Volume'), volumeInstance.id])
        redirect(action: "show", id: volumeInstance.id)
    }

    def show(Long id) {
        def volumeInstance = Volume.get(id)
        if (!volumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volume.label', default: 'Volume'), id])
            redirect(action: "list")
            return
        }

        [volumeInstance: volumeInstance]
    }

    def edit(Long id) {
        def volumeInstance = Volume.get(id)
        if (!volumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volume.label', default: 'Volume'), id])
            redirect(action: "list")
            return
        }

        [volumeInstance: volumeInstance]
    }

    def update(Long id, Long version) {
        def volumeInstance = Volume.get(id)
        if (!volumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volume.label', default: 'Volume'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (volumeInstance.version > version) {
                volumeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'volume.label', default: 'Volume')] as Object[],
                          "Another user has updated this Volume while you were editing")
                render(view: "edit", model: [volumeInstance: volumeInstance])
                return
            }
        }

        volumeInstance.properties = params

        if (!volumeInstance.save(flush: true)) {
            render(view: "edit", model: [volumeInstance: volumeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'volume.label', default: 'Volume'), volumeInstance.id])
        redirect(action: "show", id: volumeInstance.id)
    }

    def delete(Long id) {
        def volumeInstance = Volume.get(id)
        if (!volumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volume.label', default: 'Volume'), id])
            redirect(action: "list")
            return
        }

        try {
            volumeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'volume.label', default: 'Volume'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'volume.label', default: 'Volume'), id])
            redirect(action: "show", id: id)
        }
    }
}
