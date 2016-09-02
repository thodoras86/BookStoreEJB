package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Books.class)
public class BooksList extends ArrayList<Books>{

	/**
	 * Serves in order to return list of Books from rest service
	 */
	private static final long serialVersionUID = 1L;

	public BooksList() {
		super();
	}
	
	public BooksList(Collection<? extends Books> c) {
		super(c);
	}
	
	public List<Books> getBooks() {
		return this;
	}
	
	public void setBooks(List<Books> books) {
		this.addAll(books);
	}
	
}
