package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import model.Books;
import model.Order;
import model.User;

@Stateless(name = "BookStoreService", mappedName = "ejb/BookStoreService")
public class BookStoreService implements BookStoreRemote {

	@PersistenceContext
	private EntityManager em;

	public int login(String mail, String password) {

		Query query = em.createQuery("SELECT u FROM model.User u WHERE u.email = ?1 AND u.password = ?2");
		query.setParameter(1, mail);
		query.setParameter(2, password);
		@SuppressWarnings("unchecked")
		List<User> ids = query.getResultList();

		if (ids.isEmpty()) {
			return 0;
		} else {
			return ids.get(0).getId();
		}

	}

	@Override
	public boolean adminLogin(String mail, String password) {
		if (mail.equals("theo-sot@hotmail.com") && password.equals("theo")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int createUser(String mail, String password) {
		User u = new User(mail, password);

		em.persist(u);
		return u.getId();

	}

	@Override
	public void createOrder(int userId, ArrayList<Books> prodIds) {
		User us = em.find(User.class, userId);

		Order newOrder = new Order(us, prodIds, new Date());

		em.persist(newOrder);

	}

	@Override
	public int insertBook(Books b) {
		if (b != null) {
			em.persist(b);
			return b.getId();
		} else {
			return 0;
		}

	}

	@Override
	public Books getBook(String title) {
		Query query = em.createQuery("SELECT u FROM model.Books u WHERE u.title = ?1");
		query.setParameter(1, title);

		@SuppressWarnings("unchecked")
		List<Books> b = query.getResultList();

		if (b.isEmpty()) {
			return null;
		} else {
			return b.get(0);
		}

	}
	

	@Override
	public Books getBookById(String id) {
		Query query = em.createQuery("SELECT u FROM model.Books u WHERE u.id = ?1");
		query.setParameter(1, Integer.parseInt(id));

		@SuppressWarnings("unchecked")
		List<Books> b = query.getResultList();

		if (b.isEmpty()) {
			return null;
		} else {
			return b.get(0);
		}
	}

	@Override
	public ArrayList<Books> getRelevantBooks(String id, String fam) {
		Query query = em.createQuery("SELECT u FROM model.Books u WHERE u.id != ?1 and u.family = ?2");
		query.setParameter(1, Integer.parseInt(id));
		query.setParameter(2, fam);

		@SuppressWarnings("unchecked")
		List<Books> b = query.getResultList();

		return new ArrayList<Books>(b);
	}

	@Override
	public ArrayList<Order> showUserOrders(int custId) {
		Query query0 = em.createQuery("SELECT u FROM model.User u WHERE u.id = ?1");
		query0.setParameter(1, custId);
		@SuppressWarnings("unchecked")
		List<User> u = query0.getResultList();

		Query query = em.createQuery("SELECT k FROM model.Order k WHERE k.customerId = ?1");
		query.setParameter(1, u.get(0));

		@SuppressWarnings("unchecked")
		List<Order> b = query.getResultList();

		return new ArrayList<Order>(b);
	}

	@Override
	public ArrayList<Books> getProducts() {
		Query query = em.createQuery("SELECT u FROM model.Books u");

		@SuppressWarnings("unchecked")
		List<Books> books = query.getResultList();
		return new ArrayList<Books>(books);
	}

	@Override
	public ArrayList<User> getUsers() {
		Query query = em.createQuery("SELECT u FROM model.User u");

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		return new ArrayList<User>(users);
	}

	@Override
	public ArrayList<Order> showAllOrders() {
		Query query = em.createQuery("SELECT u FROM model.Order u");

		@SuppressWarnings("unchecked")
		List<Order> order = query.getResultList();
		return new ArrayList<Order>(order);
	}

	@Override
	public void delBook(int bId) {
		Books b = em.find(Books.class, bId);
		
		if (b == null) {
			 throw new NotFoundException();
		}
		
		em.remove(b);
		
	}

	@Override
	public void modBook(Books b, int bId) {
		Books book = em.find(Books.class, bId);
		System.out.println("Found: " + book);
		if (book == null) {
			 throw new NotFoundException();
		}
		
		if (!b.getTitle().equals("")) {
			book.setTitle(b.getTitle());
		}
		if (!b.getDescription().equals("")) {
			book.setDescription(b.getDescription());
		}
		if (b.getPrice() != 0) {
			book.setPrice(b.getPrice());
		}
		if (!b.getFamily().equals("")) {
			book.setFamily(b.getFamily());
		}
		if (!b.getIcoName().equals("")) {
			book.setIcoName(b.getIcoName());
		}
		if (!b.getIcoExt().equals("")) {
			book.setIcoExt(b.getIcoExt());
		}
		
		em.merge(book);
	}

}
