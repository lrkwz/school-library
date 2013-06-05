 package it.lrkwz.school.library

import groovy.time.TimeCategory

import org.apache.commons.logging.LogFactory
import org.springframework.dao.DataIntegrityViolationException

class LoanController {
	private static final log = LogFactory.getLog(this)

	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

	def index() {
		redirect action: 'list', params: params
	}

	def expiredLoans( Integer max){
		params.max = Math.min(max ?: 10, 100)
		def map =[loanInstanceList: Loan.findByReturnDateIsNullAndExpectedReturnDateLessThan(new Date()), loanInstanceTotal: Loan.count()]
		render( view: "list", model: map)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[loanInstanceList: Loan.list(params), loanInstanceTotal: Loan.count()]
	}

	def dateParser= new java.text.SimpleDateFormat("yy/MM/dd")

	def create() {
		switch (request.method) {
			case 'GET':
				def loan = new Loan(params)
				use( TimeCategory ){
					loan.expectedReturnDate = loan.loanDate + 1.month
				}
				def volumes = []
				if(params?.bookId){
					volumes = Book.findById( params.bookId).getAvailableVolumes()
				}
				log.debug "Lending " + volumes
				[loanInstance: loan, availableVolumes: volumes]
				break
			case 'POST':
				use( TimeCategory ){
					params.expectedReturnDate  = new Date() + 1.month
				}
				log.debug "Form params are" + params
				def loanInstance = new Loan(params)
				if (!loanInstance.save(flush: true)) {
					render view: 'create', model: [loanInstance: loanInstance]
					return
				}

				flash.message = message(code: 'default.created.message', args: [
					message(code: 'loan.label', default: 'Loan'),
					loanInstance.id
				])
				redirect action: 'show', id: loanInstance.id
				break
		}
	}

	def show() {
		def loanInstance = Loan.get(params.id)
		if (!loanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'loan.label', default: 'Loan'),
				params.id
			])
			redirect action: 'list'
			return
		}

		[loanInstance: loanInstance]
	}

	def edit() {
		switch (request.method) {
			case 'GET':
				def loanInstance = Loan.get(params.id)
				if (!loanInstance) {
					flash.message = message(code: 'default.not.found.message', args: [
						message(code: 'loan.label', default: 'Loan'),
						params.id
					])
					redirect action: 'list'
					return
				}

				[loanInstance: loanInstance]
				break
			case 'POST':
				def loanInstance = Loan.get(params.id)
				if (!loanInstance) {
					flash.message = message(code: 'default.not.found.message', args: [
						message(code: 'loan.label', default: 'Loan'),
						params.id
					])
					redirect action: 'list'
					return
				}

				if (params.version) {
					def version = params.version.toLong()
					if (loanInstance.version > version) {
						loanInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
								[
									message(code: 'loan.label', default: 'Loan')] as Object[],
								"Another user has updated this Loan while you were editing")
						render view: 'edit', model: [loanInstance: loanInstance]
						return
					}
				}

				loanInstance.properties = params

				if (!loanInstance.save(flush: true)) {
					render view: 'edit', model: [loanInstance: loanInstance]
					return
				}

				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'loan.label', default: 'Loan'),
					loanInstance.id
				])
				redirect action: 'show', id: loanInstance.id
				break
		}
	}

	def delete() {
		def loanInstance = Loan.get(params.id)
		if (!loanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'loan.label', default: 'Loan'),
				params.id
			])
			redirect action: 'list'
			return
		}

		try {
			loanInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'loan.label', default: 'Loan'),
				params.id
			])
			redirect action: 'list'
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'loan.label', default: 'Loan'),
				params.id
			])
			redirect action: 'show', id: params.id
		}
	}
}
