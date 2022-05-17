package backend.main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Article {

	private int articleNumber;
	private String modelName;
	private double amount;
	private int stars;
	private String operatingSystem;
	private String releaseDate;
	private String screen;
	private String resolution;
	private String brand;
	private List<Comment> comments;
	private List<Integer> imageIds;

	public Article(){}

	public Article(int articleNumber, String modelName, double amount, int stars, String operatingSystem,
		String releaseDate, String screen, String resolution, String brand,
		List<Comment> comments, List<Integer> imageIds)
	{
		this.articleNumber = articleNumber;
		this.modelName = modelName;
		this.amount = amount;
		this.stars = stars;
		this.operatingSystem = operatingSystem;
		this.releaseDate = releaseDate;
		this.screen = screen;
		this.resolution = resolution;
		this.brand = brand;
		this.comments = comments;
		this.imageIds=imageIds;
	}

	public Article(int articleNumber, String modelName, double amount, List<String> pictures)
	{
		this.articleNumber = articleNumber;
		this.modelName = modelName;
		this.amount = amount;
	}

	public static Article getRandomArticle()
	{
		return new Article();
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Integer> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<Integer> imageIds) {
		this.imageIds = imageIds;
	}
}