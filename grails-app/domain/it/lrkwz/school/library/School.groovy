package it.lrkwz.school.library

class School {

    static constraints = {
		name blank:false
    }
	
	String name
	
	String toString(){
		return name;
	}
}
