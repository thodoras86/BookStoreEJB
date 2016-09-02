package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="orders")
public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int ordId;
	@OneToOne
	@JoinColumn(name="id")
	private User customerId;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="booksOnorder", joinColumns=@JoinColumn(name="ordId"), inverseJoinColumns=@JoinColumn(name="id"))
	private ArrayList<Books> books;
	private Date dateBought;
	
	public Order() {}
	
	public Order(User customerId, ArrayList<Books> books, Date dateBought) {
		super();
		this.customerId = customerId;
		this.books = books;
		this.dateBought = dateBought;
	}

	public int getOrdId() {
		return ordId;
	}

	public User getCustomerId() {
		return customerId;
	}


	public Date getDateBought() {
		return dateBought;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public void setCustomerId(User customerId) {
		this.customerId = customerId;
	}


	public void setDateBought(Date dateBought) {
		this.dateBought = dateBought;
	}
	

	public ArrayList<Books> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Books> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Order [ordId=" + ordId + ", customerId=" + customerId + ", books=" + books + ", dateBought="
				+ dateBought + "]";
	} 
	
	
	
}
