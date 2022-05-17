package backend.main.java.models;

import java.util.List;

public class ArticleShort {

	private String articleNumber;
	private String modelName;
	private double amount;
	private List<String> pictures;

	public ArticleShort(){}

	public ArticleShort(String articleNumber, String modelName, double amount, List<String> pictures)
	{
		this.articleNumber = articleNumber;
		this.modelName = modelName;
		this.amount = amount;
		this.pictures = pictures;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
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
