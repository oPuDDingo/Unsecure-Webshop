package backend.main.java.models;

import java.util.List;

public class ShoppingCart {

    public ShoppingCart(){}

    private List<ArticleVersion> articles;

    public List<ArticleVersion> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVersion> articles) {
        this.articles = articles;
    }
}
