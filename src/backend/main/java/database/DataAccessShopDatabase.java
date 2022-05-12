package backend.main.java.database;

import backend.main.java.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccessShopDatabase {

    private Connection createConnection(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/backend/main/java/database/shopDatabase.db");
            //System.out.println("Verbindung erfolgreich!");
        }
         catch (SQLException ex) {
             System.out.println(ex.getMessage());
             System.out.println("Fehler beim Verbinden mit der Datenbank!");
        } catch (ClassNotFoundException e) {
            System.out.println("Klasse nicht gefunden!");
        }
        return c;
    }

    public boolean createComment(Comment comment, int articleId, int userId){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql= "INSERT INTO comment(comment_text, article_id, user_id) " +
                    "VALUES('"+comment.getCommentText()+"', "+articleId+", "+userId+");";
            stmt.execute(sql);
            stmt.close();;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createUser(User user){
        Connection con = this.createConnection();
        Statement stmt =null;
        int newsletter = user.isNewsletter() ? 1 : 0;
        try {
            stmt = con.createStatement();
            String sql ="INSERT INTO User(e_mail, firstname, lastname, password, newsletter, salutation, title, description, real_user) " +
                    "VALUES('"+user.getEmail()+"', '"+user.getFirstname()+"', '"+user.getLastname()+"', '"+user.getPassword()+"',"+newsletter+", '"+user.getSalutation()+"', '"+
                    user.getTitle()+"', '"+user.getTitle()+"', "+1+");";
            stmt.execute(sql);
            int userId = stmt.getGeneratedKeys().getInt(1);
            user.setId(userId);
            this.createWishList(userId);
            this.createShoppingCart(userId);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createOrder(Order order, int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql= "INSERT INTO sales_order(order_date, amount, iban, bic, account_owner, user_id, address_id) " +
                    "VALUES('"+order.getOrderDate()+"', '"+order.getAmount()+"', '"+order.getPayment().getIban()+"', '"+order.getPayment().getBic()+"', '" +
                    order.getPayment().getAccountHolder()+"', "+userId+", "+order.getAddress().getId()+");";
            stmt.execute(sql);
            int orderId =stmt.getGeneratedKeys().getInt(1);
            order.setOrderNumber(orderId);
            this.createOrderArticleVersion(order.getArticles(), orderId);
            this.createOrderCoupon(order.getCoupons(), orderId);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createAddress(Address address, int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql="INSERT INTO address(street_house_number, postcode, address_suplement, city, country, name, delivery_instruction, user_id)" +
                    "VALUES('"+address.getAddress()+"', '"+address.getZipcode()+"', '"+address.getAddress2()+"', '"+address.getCity()+"', '"+address.getCountry()
                    +"', '"+address.getName()+"', '"+address.getDeliveryInstructions()+"', "+userId+");";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createWishList(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO wish_list(user_id) VALUES("+userId+");";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createShoppingCart(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO shopping_cart(user_id) VALUES("+userId+");";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createOrderArticleVersion(List<ArticleVersion> list, int orderId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            for(ArticleVersion article : list){
                String sql = "INSERT INTO sales_order_article_version(quantity, sales_order_id, article_version_id)" +
                        "VALUES("+article.getQuantity()+", "+orderId+", "+article.getId()+");";
                stmt.execute(sql);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createOrderCoupon(List<Coupon> list, int orderId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            for(Coupon coupon : list){
                String sql = "INSERT INTO sales_order_coupon(sales_order_id, coupon_id) VALUES("+orderId+", "+coupon.getId()+");";
                stmt.execute(sql);
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws SQLException {
        DataAccessShopDatabase s = new DataAccessShopDatabase();
        Address a = new Address();
        a.setCity("Würzburg");
        a.setCountry("Germany");
        s.createAddress(a, 10);
    }
}