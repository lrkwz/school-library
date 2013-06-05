package it.lrkwz.school.library

import org.apache.commons.logging.LogFactory
import org.grails.taggable.Taggable

class Book implements Taggable {
	private static final log = LogFactory.getLog(this)
	
	static constraints = {
		title blank:false
		author blank:false
	}

	String title
	String author

	School library

	static hasMany = [volumes: Volume]

	public String toString(){
		return title + " " + volumes*.toString();
	}

	Volume[] getAvailableVolumes(){
		log.debug "There are " + volumes.size() + " volumes"
		return volumes.findAll { it.isAvailable == true }
	}
}
