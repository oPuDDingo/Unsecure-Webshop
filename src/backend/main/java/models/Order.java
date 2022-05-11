package backend.main.java.models;

import java.util.List;

public class Order {

    private int orderNumber;
    private List<ArticleVersion> articles;
    private List<Coupon> coupons;
    private Address address;
    private Payment payment;
    private String orderDate;
    private double amount;

    public Order(){}

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<ArticleVersion> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVersion> articles) {
        this.articles = articles;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
