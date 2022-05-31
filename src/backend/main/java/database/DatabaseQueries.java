package backend.main.java.database;

import backend.main.java.models.Article;
import backend.main.java.models.User;
import backend.main.java.models.modelsdb.ArticleDB;


public class DatabaseQueries {

	public static String[] deleteDatabase= new String[]{"DROP TABLE address;", "DROP TABLE article_version;",
		"DROP TABLE comment;", "DROP TABLE sales_order;", "DROP TABLE sales_order_article_version", "DROP TABLE session;",
		"DROP TABLE shopping_cart;", "DROP TABLE user;", "DROP TABLE wish_list;"};
	//missing: picture, article, brand, coupon

	public static String[] createDatabase = new String[]{
		"CREATE TABLE IF NOT EXISTS article_version(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
			"    quantity INTEGER,\n" +
			"    gb_size INTEGER,\n" +
			"    color TEXT,\n" +
			"    article_id INTEGER,\n" +
			"    FOREIGN KEY(article_id) REFERENCES article(id)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS user(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
			"    e_mail TEXT UNIQUE,\n" +
			"    firstname TEXT,\n" +
			"    lastname TEXT,\n" +
			"    password TEXT,\n" +
			"    newsletter INTEGER,\n" +
			"    salutation TEXT,\n" +
			"    title TEXT,\n" +
			"    profile_picture TEXT,\n" +
			"    real_user INTEGER, description TEXT\n" +
			");",
		"CREATE TABLE IF NOT EXISTS address(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    street_house_number TEXT,\n" +
			"    postcode TEXT,\n" +
			"    address_suplement TEXT,\n" +
			"    city TEXT,\n" +
			"    country TEXT,\n" +
			"    name TEXT,\n" +
			"    delivery_instruction TEXT,\n" +
			"    user_id INTEGER,\n" +
			"    FOREIGN KEY (user_id) REFERENCES user(id)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS session(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    key Text,\n" +
			"    ip_address Text,\n" +
			"    user_id INTEGER,\n" +
			"    FOREIGN KEY (user_id) REFERENCES user(id)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS wish_list(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    quantity INTEGER,\n" +
			"    user_id INTEGER,\n" +
			"    article_version_id INTEGER,\n" +
			"    FOREIGN KEY (user_id) REFERENCES user(id),\n" +
			"    FOREIGN KEY (article_version_id) REFERENCES article_version(id)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS shopping_cart(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    quantity INTEGER,\n" +
			"    user_id INTEGER,\n" +
			"    article_version_id INTEGER,\n" +
			"    FOREIGN KEY (user_id) REFERENCES user(id),\n" +
			"    FOREIGN KEY (article_version_id) REFERENCES article_version(id)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS comment(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    comment_text TEXT,\n" +
			"    article_id INTEGER,\n" +
			"    user_id INTEGER,\n" +
			"    FOREIGN KEY (article_id) REFERENCES article(id),\n" +
			"    FOREIGN KEY (user_id) REFERENCES user(id)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS sales_order (\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    order_date TEXT,\n" +
			"    amount REAL,\n" +
			"    iban TEXT,\n" +
			"    bic TEXT,\n" +
			"    account_owner TEXT,\n" +
			"    user_id INTEGER,\n" +
			"    address_id INTEGER,\n" +
			"    coupon_code,\n" +
			"    FOREIGN KEY (user_id) REFERENCES user(id),\n" +
			"    FOREIGN KEY (address_id) REFERENCES address(id),\n" +
			"    FOREIGN KEY (coupon_code) REFERENCES coupon(code)\n" +
			");",
		"CREATE TABLE IF NOT EXISTS sales_order_article_version(\n" +
			"    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
			"    quantity INTEGER,\n" +
			"    sales_order_id INTEGER,\n" +
			"    article_version_id INTEGER,\n" +
			"    FOREIGN KEY (sales_order_id) REFERENCES sales_order(id),\n" +
			"    FOREIGN KEY (article_version_id) REFERENCES article_version(id)\n" +
			");"
	};

	public static String[] brands = new String[]{"Samsung", "Apple", "Xiaomi","Sony"};

	public static User[] users = new User[]{new User(1, "Admin@Admin.de", "admin", "admin", false, "", "", "", "Admin User", "123456789"),
		new User(2, "Test@test.de", "test", "test", false, "", "", "", "Test User", "test123456789")};

	public static ArticleDB[] articles = new ArticleDB[]{new ArticleDB("Galaxy Z Fold3", 1393.00, "Android 11.0, ONE UI 3.1.1, KNOX 3.7", "01.01.2022", "Dynamic AMOLED", "2.208x1.768", 100, 25, 1),
		new ArticleDB("Galaxy S22 Ultra", 1449.00, "Android 12, One UI 4.1, Knox 3.8", "05.12.2021", "Dynamic AMOLED, Quad HD+", "3.088x1.440", 90, 30, 1),
		new ArticleDB("Galaxy 21 Ultra", 1279.87, "Android 11.0, OneUI 3.1, KNOX 3.7", "28.02.2019", "Dynamic AMOLED, Quad HD+", "3.200x1.440", 5, 1, 1),
		new ArticleDB("Galaxy Z Flip3", 799.00,"Android 11.0, ONE UI 3.1.1, KNOX 3.7", "17.04.2020", "Super AMOLED", "2.640x1.080", 25, 7, 1),
		new ArticleDB("Galaxy Xcover", 499.00, "Android 10 AOS", "06.08.2017", "LCD-TFT", "2.340x1.080", 2,1,1),
		new ArticleDB("Iphone 13 Pro", 1749.00, "ios 15", "17.09.2021", "Super Retina XDR Display mit ProMotion", "2.778x1.284", 16,4,2),
		new ArticleDB("Iphone 12 Pro", 1379.00, "ios 14", "17.08.2020", "Super Retina XDR, OLED", "1.856x884", 5,1,2),
		new ArticleDB("Iphone 11", 529.99, "ios 13", "08.06.2019", "Liquid Retina HD, IPS, True Tone Display", "1.792x828", 4, 1, 2),
		new ArticleDB("Iphone SE NE", 489.95, "ios 13", "06.06.2018", "Retina HD Display", "1.334x750", 6, 2, 2),
		new ArticleDB("Redmi Note 11", 219.00, "Android 11, MIUI 13", "18.09.2019", "AMOLED FHD+", "2.400x1.080", 2, 1, 3),
		new ArticleDB("Redmi Note 9 Pro", 189.00, "Android 10 + MIUI 11", "04.11.2022", "IPS", "2.400x1.080", 5, 2, 3),
		new ArticleDB("Redmi 9A", 107.99, "Android 10 + MIUI 11", "31.12.2020", "DotDrop-Display", "1.600x720", 4, 1, 3),
		new ArticleDB("Poco X4 Pro", 399.00, "Android 11, MIUI 13", "21.10.2021", "AMOLED, FHD+", "2.400x1.080", 4, 1, 3),
		new ArticleDB("Xperia 10 III", 399.00, "Android 11", "16.06.2018", "CinemaWide Full HD+ OLED Display", "2.520x1.080", 3, 1, 4),
		new ArticleDB("Xperia Pro-I", 199.00, "Android 11", "24.11.2022", "CinemaWide 4K 120Hz HDR OLED-Display", "3.840x1.644", 8,2,4),
		new ArticleDB("Xperia 5 III", 919.00, "Android 12", "05.05.2022", "CinemaWide FHD+ HDR OLED-Display", "2.520x1.080", 5, 1, 4)};

	public static String[] colors = new String[]{"red", "blue", "green", "black", "white"};

	public static String[] gbSizes = new String[]{"128", "256", "512"};
}
