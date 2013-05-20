package it.lrkwz.school.library

import org.grails.taggable.Taggable

class Book implements Taggable {

	static constraints = {
		availableStock min:0
		maxStock min:0
	}

	String code
	String title
	String author
	String publisher

	Integer maxStock = 1
	Integer availableStock = 1
	
	School library

	public String toString(){
		return title;
	}
}
