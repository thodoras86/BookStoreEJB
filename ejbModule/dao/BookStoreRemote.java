package dao;

import java.util.ArrayList;

import javax.ejb.Remote;

import model.Order;
import model.Books;
import model.User;

@Remote
public interface BookStoreRemote {
	public int login(String mail, String password);
	public boolean adminLogin(String mail, String password);
	public int createUser(String mail, String password);
	public void createOrder(int userId, ArrayList<Books> prodIds);
	public int insertBook(Books b);
	public Books getBook(String title);
	public Books getBookById(String id);
	public ArrayList<Books> getRelevantBooks(String id, String fam);
	public ArrayList<Order> showUserOrders(int custId);
	public ArrayList<Books> getProducts();
	public ArrayList<User> getUsers();
	public ArrayList<Order> showAllOrders();
	public void delBook(int bId);
	public void modBook(Books b, int bId);
}
