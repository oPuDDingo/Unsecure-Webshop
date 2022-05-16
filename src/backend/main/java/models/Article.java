package models;

import java.util.ArrayList;
import java.util.List;

public class Article {

	private String articleNumber;
	private String modelName;
	private double amount;
	private int stars;
	private String operatingSystem;
	private String releaseDate;
	private String screen;
	private String resolution;
	private String productionYear;
	private String brand;
	private List<String> pictures;
	private List<Comment> comments;

	public Article(){}

	public Article(String articleNumber, String modelName, double amount, int stars, String operatingSystem,
		String releaseDate, String screen, String resolution, String productionYear, String brand,
		List<String> pictures, List<Comment> comments)
	{
		this.articleNumber = articleNumber;
		this.modelName = modelName;
		this.amount = amount;
		this.stars = stars;
		this.operatingSystem = operatingSystem;
		this.releaseDate = releaseDate;
		this.screen = screen;
		this.resolution = resolution;
		this.productionYear = productionYear;
		this.brand = brand;
		this.pictures = pictures;
		this.comments = comments;
	}

	public Article(String articleNumber, String modelName, double amount, List<String> pictures)
	{
		this.articleNumber = articleNumber;
		this.modelName = modelName;
		this.amount = amount;
		this.pictures = pictures;
	}

	public static Article getRandomArticle() {
		return new Article("195438", "Iphone", 2.0, 4, "iOS", "2018",
			"AMOLED", "1024x704", "2017", "Apple", new ArrayList<>(),
			new ArrayList<Comment>());
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

	public int getStars() {
		return stars;
	}

	public void setStars(int numberOfValutation, int sumOfValutation){
		this.stars = Math.round(sumOfValutation/numberOfValutation);
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
