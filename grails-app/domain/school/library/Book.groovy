package school.library


class Book /*implements Taggable*/ {

    static constraints = {
    }
	
	String code
	String title
	String author
	String publisher
	
	//String[] tags;
	
	public String toString(){
		return title;
	}
}
