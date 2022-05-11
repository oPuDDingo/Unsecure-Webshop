package backend.main.java.models;

import java.util.List;

public class WishList {

    private List<ArticleVersion> articles;

    public WishList(){}

    public List<ArticleVersion> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVersion> articles) {
        this.articles = articles;
    }
}
