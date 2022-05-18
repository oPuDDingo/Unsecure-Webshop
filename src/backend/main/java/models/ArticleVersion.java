package backend.main.java.models;

public class ArticleVersion { //Specifyed Item

	private int id; //Aus m zu n Table
	private int articleNumber; //ID des article
	private int quantity;
	private int gbSize;
	private String color;
	private Article article;
	public ArticleVersion(){}

	public ArticleVersion(int id, int articleNumber, int quantity, int gbSize, String color)
	{
		this.id = id;
		this.articleNumber = articleNumber;
		this.quantity = quantity;
		this.gbSize = gbSize;
		this.color = color;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
