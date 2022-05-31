package backend.main.java;

import backend.main.java.database.DataAccessShopDatabase;
import backend.main.java.models.*;

import java.util.List;

public class DataHandler
{
	static DataAccessShopDatabase Database = new DataAccessShopDatabase();

	public static List<Article> getArticles(int page, String brand, String name, boolean specifications)
	{
		List<Article> list = Database.getArticles(page, brand, name); //search name also and return ArticleVersion
		return list;
	}

	public static Article getArticle(int id)
	{
		return Database.getArticle(id);
	}

	public static List<ArticleVersion> getCartItems(String session)
	{
		return Database.getShoppingCart(Database.getUserId(session));
	}

	public static void createCartItem(ArticleVersion articleVersion, String session)
	{
		Database.postShoppingCartItem(articleVersion, Database.getUserId(session));  //get user id from
	}

	public static ArticleVersion modifyCartItem(int id, ArticleVersion articleVersion)
	{
		Database.putShoppingCartItem(articleVersion);
		return articleVersion;
	}

	public static void deleteCartItem(int id)
	{
		Database.deleteShoppingCartItem(id);
	}

	public static void createContact(Contact contact)
	{
		//TODO detect attack
	}

	public static Coupon getCoupon(String name)
	{
		return Database.getCoupon(name);
	}

	public static String getPicture(int id)
	{
		return Database.getPicture(id);
	}

	public static List<Order> getOrders(String session)
	{
		return Database.getOrders(Database.getUserId(session));
	}

	public static Order getOrder(int id)
	{
		return Database.getOrder(id);
	}

	public static int createOrder(Order order, String session, boolean cleanup)
	{
		Database.postOrder(order, Database.getUserId(session), cleanup);  //TODO return object
		return 1;
	}

	public static User getUser(String session)
	{
		return Database.getUserInformation(Database.getUserId(session));
	}

	public static User getUserById(int id)
	{
		return Database.getUserInformation(id);
	}

	public static int createUser(User user)
	{
		Database.postUser(user);
		return 1;
	}

	public static void modifyUser(String session, User user)
	{
		Database.putUser(user, Database.getUserId(session));
	}

	public static void deleteUser(String session)
	{
		// Database.(Database.getUserId(session));
	}

	public static Payment getUserPayment(String session)
	{
		return Database.getPayment(Database.getUserId(session));
	}

	public static int createUserPayment(String session, Payment payment)
	{
		Database.postPayment(Database.getUserId(session), payment);
		return 1;
	}

	public static Address getUserAddress(String session, int addressID)
	{
		return Database.getAddress(addressID);
	}

	public static List<Address> getAllUserAddresses(String session)
	{
		return Database.getAddresses(Database.getUserId(session));
	}

	public static int createAddress(String session, Address address)
	{
		// return Database.addr(id, address);
		return 1;
	}

	public static void modifyAddress(String session, int addressID, Address address)
	{
		// Database.putAddress(id, addressID, address);
	}

	public static void deleteAddress(String session, int addressID)
	{
		//Database.deleteAddress(id, addressID);
	}

	public static String getUserMail(String session)
	{
		return Database.getUserInformation(Database.getUserId(session)).getMail();
	}

	public static void createUserMail(String session, String mail)
	{
		// Database.createUserMail(id);
	}

	public static void turnOnNewsletter(String session)
	{
		Database.postNewsletter(Database.getUserId(session));
	}

	public static List<ArticleVersion> getWishlist(String session)
	{
		return Database.getWishlist(Database.getUserId(session));
	}

	public static void createWishlistItem(ArticleVersion articleVersion, String session)
	{
		Database.postWishListItem(articleVersion, Database.getUserId(session));
	}

	public static void modifyWhishlistItem(ArticleVersion articleVersion, String session, int id)
	{
		Database.putWishListItem(articleVersion);
	}

	public static void deleteWishlist(String session)
	{
		Database.deleteShoppingCart(Database.getUserId(session));
	}

	public static void deleteWishlistItem(int id)
	{
		Database.deleteWishListItem(id);
	}
}
