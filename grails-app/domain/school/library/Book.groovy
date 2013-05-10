package school.library
import org.grails.taggable.Taggable

class Book implements Taggable {

	static constraints = {
	}

	String code
	String title
	String author
	String publisher

	Integer stock = 1

	//String[] tags;

	public String toString(){
		return title;
	}
}
