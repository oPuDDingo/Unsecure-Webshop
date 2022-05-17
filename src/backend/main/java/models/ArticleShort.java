package backend.main.java.models;

import java.util.List;

public class ArticleShort {

	private int articleNumber;
	private String modelName;
	private double amount;
	private List<String> pictures;

	public ArticleShort(){}

	public ArticleShort(int articleNumber, String modelName, double amount)
	{
		this.articleNumber = articleNumber;
		this.modelName = modelName;
		this.amount = amount;
	}

	public static ArticleShort articleToShort(Article article) {
		return new ArticleShort(article.getArticleNumber(), article.getModelName(), article.getAmount());
	}

	public static ArticleShort getRandomArticleShort() {
		return (articleToShort(Article.getRandomArticle()));
	}


	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
}
