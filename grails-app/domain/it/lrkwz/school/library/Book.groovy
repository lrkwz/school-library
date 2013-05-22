package it.lrkwz.school.library

import org.grails.taggable.Taggable

class Book implements Taggable {

	static constraints = {
	}

	String title
	String author

	School library

	static hasMany = [volumes: Volume]

	public String toString(){
		return title + " " + volumes*.toString();
	}
}
