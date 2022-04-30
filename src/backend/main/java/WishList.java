package src.backend.main.java;

public class WishList {

    private int id;
    private int quantity;
    private ArticleVersion articleVersion;
    private User user;

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

    public ArticleVersion getArticleVersion() {
        return articleVersion;
    }

    public void setArticleVersion(ArticleVersion articleVersion) {
        this.articleVersion = articleVersion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
