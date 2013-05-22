package it.lrkwz.school.library

class Volume {

	static constraints = {
		code blank:false, unique:true
	}

	String code
	Date publishedOn
	String publisher
	Boolean isAvailable = true

	public String toString(){
		return code
	}
}
