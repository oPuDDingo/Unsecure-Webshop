package backend.main.java.models;

public class ArticleVersion { //Specifyed Item

	private int id; //Aus m zu n Table
	private int articleNumber; //ID des article
	private String name;
	private int quantity;
	private int gbSize;
	private String color;
	private double amount;
	private String picture;

	public ArticleVersion(){}

	public ArticleVersion(int id, int articleNumber, String name, int quantity, int gbSize, String color, double amount,
		String picture)
	{
		this.id = id;
		this.articleNumber = articleNumber;
		this.name = name;
		this.quantity = quantity;
		this.gbSize = gbSize;
		this.color = color;
		this.amount = amount;
		this.picture = picture;
	}

	public static ArticleVersion getRandomArticleVersion() {
		return new ArticleVersion(24, 2846, "Iphone", 1, 64, "Red", 4.0, "picture");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getGbSize() {
		return gbSize;
	}

	public void setGbSize(int gbSize) {
		this.gbSize = gbSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
