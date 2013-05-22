package it.lrkwz.school.library


class Student {

	String firstName
	String lastName
	String email
	Date dateOfBirth
	String classRoom

	School school

	static constraints = {
		email(email: true, nullable:true)
		dateOfBirth(nullable:true)
		classRoom(nullable:true)
	}

	public String toString(){
		return firstName;
	}
}
