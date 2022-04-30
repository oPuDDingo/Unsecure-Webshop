package src.backend.main.java;

public class ArticleVersion {

    private int id;
    private int quantity;
    private int GBSize;
    private String color;
    private Article article;
    private double valuation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getGBSize() {
        return GBSize;
    }

    public void setGBSize(int GBSize) {
        this.GBSize = GBSize;
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

    public double getValuation() {
        return valuation;
    }

    public void setValuation(int valuationSum, int number_of_valuation) {
        this.valuation = valuationSum/number_of_valuation;
    }
}
