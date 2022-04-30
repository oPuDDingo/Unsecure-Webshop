package src.backend.main.java;

public class ShoppingCart {

    private int id;
    private int quantity;
    private ArticleVersion articelVersion;
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

    public ArticleVersion getArticelVersion() {
        return articelVersion;
    }

    public void setArticelVersion(ArticleVersion articelVersion) {
        this.articelVersion = articelVersion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
