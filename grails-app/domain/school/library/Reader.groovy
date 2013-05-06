package school.library

class Reader {

	String firstName
	String lastName
	String email
	Date birthday
	String classRoom

	static constraints = {
		email(email: true)
		birthday(nullable:true)
		classRoom(nullable:true)
	}

	public String toString(){
		return firstName;
	}
}
