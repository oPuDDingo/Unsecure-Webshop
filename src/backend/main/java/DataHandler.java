package backend.main.java;

import backend.main.java.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataHandler
{
	public static List<Object> getArticles(int page, String brand, String name, boolean specifications)
	{
		// List<Object> list = Database.getArticles(page, brand, name)
		List<Object> list = Article.getExampleArticleList(9);
		if (specifications) {
			return list;
		}
		return list;
	}

	public static Article getArticle (int id) {
		// return Database.getArticle(id)
		return Article.getExampleArticle();
	}

	public static List<Object> getCartItems() {
		return Article.getExampleArticleList(3);
	}

	public static int createCartItem(ArticleVersion articleVersion) {
		// return Database.createCartItem(articleVersion)
		return 1;
	}

	public static ArticleVersion modifyCartItem(int id, ArticleVersion articleVersion) {
		// return Database.modifyCartItem(id, articleVersion)
		return articleVersion;
	}

	public static void deleteCartItem(int id) {
		// Database.deleteCartItem(id)
	}

	public static int createContact(Contact contact) {
		// int id = Database.createContact(contact);
		return 1;
	}

	public static Coupon getCoupon(String name)	{
		// return Database.getCoupon(name)
		return Coupon.getExampleCoupon(name);
	}

	public static String getPicture(int id) {
		// return Database.getPicture(id)
		return Logic.getByteArrayFromPictureURL("https://www.kindpng.com/picc/m/6-67785_broken-phone-png-broken-iphone-transparent-png-download.png");
	}

	public static List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.getExampleOrder(1));
		return orders;
	}

	public static Order getOrder(int id) {
		return Order.getExampleOrder(id);
	}

	public static int createOrder(Order order, boolean cleanup) {
		// int id = Database.createOrder(order);
		if (cleanup) {
			// Database.deleteWishlist
		}
		// return id;
		return 1;
	}

	public static User getUser(String session) {
		// User user = Database.getUser(session);
		return User.getExampleUser();
	}

	public static User getUserById(int id) {
		User user = User.getExampleUser();
		return User.getExampleElseUser();
	}

	public static int createUser(User user) {
		// return Database.createUser(user);
		return 1;
	}

	public static void modifyUser(String session, User user) {
		int id = Logic.getUserIdFromSession(session);
		// Database.modifyUser(id, user)
	}

	public static void deleteUser(String session) {
		int id = Logic.getUserIdFromSession(session);
		// Database.deleteUser(id);
	}

	public static Payment getUserPayment(String session) {
		int id = Logic.getUserIdFromSession(session);
		// return Database.getUserPayment(id);
		return Payment.getExamplePayment();
	}

	public static int createUserPayment(String session, Payment payment) {
		int id = Logic.getUserIdFromSession(session);
		// return Database.createUserPayment(id, payment);
		return 1;
	}

	public static void modifyUserPayment(String session, Payment payment) {
		int id = Logic.getUserIdFromSession(session);
		// Database.moidfyUserPayment(id, payment)
	}

	public static void deleteUserPayment(String session) {
		int id = Logic.getUserIdFromSession(session);
		// Database.deleteUserPayment(id);
	}

	public static Address getUserAddress(String session, int addressID) {
		// return Database.getUserAddress(session, addressID);
		return Address.getExampleAddress();
	}

	public static List<Address> getAllUserAddresses(String session) {
		// return Database.getAllUserAddress(session, addressID);
		return Collections.nCopies( 4, Address.getExampleAddress() );
	}

	public static int createUserAddress(String session, Address address) {
		int id = Logic.getUserIdFromSession(session);
		// return Database.createUserAddress(id, address);
		return 1;
	}

	public static void modifyUserAddress(String session, int addressID, Address address) {
		int id = Logic.getUserIdFromSession(session);
		// Database.modifyUserAddress(id, addressID, address);
	}

	public static void deleteUserAddress(String session, int addressID) {
		int id = Logic.getUserIdFromSession(session);
		// Database.deleteUserAddress(id, addressID)
	}

	public static String getUserMail(String session) {
		int id = Logic.getUserIdFromSession(session);
		// return Database.getUserMail(id);
		return "mail@mail.com";
	}

	public static void createUserMail(String session, String mail) {
		int id = Logic.getUserIdFromSession(session);
		// Database.createUserMail(id);
	}

	public static void turnOnNewsletter() {

	}
}
