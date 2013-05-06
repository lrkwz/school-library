package testapp

import grails.converters.JSON

class TestController {

	def index() {
	}

	def states() {

		def states = [
			["name":"Alabama","id":"AL"],
			["name":"Alaska","id":"AK"],
			["name":"Arizona","id":"AZ"],
			["name":"Arkansas","id":"AR"],
			["name":"California","id":"CA"],
			["name":"Colorado","id":"CO"],
			["name":"Connecticut","id":"CT"],
			["name":"Delaware","id":"DE"],
			["name":"District of Columbia","id":"DC"],
			["name":"Florida","id":"FL"],
			["name":"Georgia","id":"GA"],
			["name":"Hawaii","id":"HI"],
			["name":"Idaho","id":"ID"],
			["name":"Illinois","id":"IL"],
			["name":"Indiana","id":"IN"],
			["name":"Iowa","id":"IA"],
			["name":"Kansas","id":"KS"],
			["name":"Kentucky","id":"KY"],
			["name":"Louisiana","id":"LA"],
			["name":"Maine","id":"ME"],
			["name":"Montana","id":"MT"],
			["name":"Nebraska","id":"NE"],
			["name":"Nevada","id":"NV"],
			["name":"New Hampshire","id":"NH"],
			["name":"New Jersey","id":"NJ"],
			["name":"New Mexico","id":"NM"],
			["name":"New York","id":"NY"],
			["name":"North Carolina","id":"NC"],
			["name":"North Dakota","id":"ND"],
			["name":"Ohio","id":"OH"],
			["name":"Oklahoma","id":"OK"],
			["name":"Oregon","id":"OR"],
			["name":"Maryland","id":"MD"],
			["name":"Massachusetts","id":"MA"],
			["name":"Michigan","id":"MI"],
			["name":"Minnesota","id":"MN"],
			["name":"Mississippi","id":"MS"],
			["name":"Missouri","id":"MO"],
			["name":"Pennsylvania","id":"PA"],
			["name":"Rhode Island","id":"RI"],
			["name":"South Carolina","id":"SC"],
			["name":"South Dakota","id":"SD"],
			["name":"Tennessee","id":"TN"],
			["name":"Texas","id":"TX"],
			["name":"Utah","id":"UT"],
			["name":"Vermont","id":"VT"],
			["name":"Virginia","id":"VA"],
			["name":"Washington","id":"WA"],
			["name":"West Virginia","id":"WV"],
			["name":"Wisconsin","id":"WI"],
			["name":"Wyoming","id":"WY"]]
		
		def result = states.findAll {
			it.name.toLowerCase().contains(params.q.toLowerCase())
		}
		// render data as JSONP string to 
		render "${params.callback}(${result as JSON})"  
	}
	
	def save() {
		println params
		render "Done! ${params}"
	}
}
