package backend.main.java.models;

import java.util.List;

public class Wishlist {

	private List<ArticleVersion> articles;

	public Wishlist(){}

	public List<ArticleVersion> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleVersion> articles) {
		this.articles = articles;
	}
}
