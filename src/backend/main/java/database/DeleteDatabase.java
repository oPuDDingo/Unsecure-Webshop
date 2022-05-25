package backend.main.java.database;

import java.util.ArrayList;

public class DeleteDatabase {

    public static String[] deleteDatabase= new String[]{"DROP TABLE address;", "DROP TABLE article;", "DROP TABLE article_version;", "DROP TABLE brand;",
            "DROP TABLE comment;", "DROP TABLE coupon;", "DROP TABLE picture;", "DROP TABLE sales_order;", "DROP TABLE sales_order_article_version", "DROP TABLE session;",
            "DROP TABLE shopping_cart;", "DROP TABLE shopping_cart_article_version;", "DROP TABLE user;", "DROP TABLE wish_list;", "DROP TABLE wish_list_article_version;"};
}
