package it.lrkwz.school.library

class Volume {

	static constraints = {
	}

	String code
	Date publishedOn
	String publisher
	Boolean isAvailable = true

	public String toString(){
		return code
	}
}
