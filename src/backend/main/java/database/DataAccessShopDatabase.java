package backend.main.java.database;

import backend.main.java.models.*;
import com.google.common.hash.Hashing;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
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

    public boolean postComment(Comment comment, int articleId, int userId){
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

    public boolean postUser(User user){
        Connection con = this.createConnection();
        Statement stmt =null;
        int newsletter = user.isNewsletter() ? 1 : 0;
        try {
            stmt = con.createStatement();
            String sql ="INSERT INTO User(e_mail, firstname, lastname, password, newsletter, salutation, title, description, real_user) " +
                    "VALUES('"+user.getEmail()+"', '"+user.getFirstname()+"', '"+user.getLastname()+"', '"+this.encryptPasswordRealUser(user.getPassword())+"',"+0+", '"+user.getSalutation()+"', '"+
                    user.getTitle()+"', '"+user.getTitle()+"', "+1+");";
            stmt.execute(sql);
            int userId = stmt.getGeneratedKeys().getInt(1);
            user.setId(userId);
            this.postWishList(userId);
            this.postShoppingCart(userId);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean postAddress(Address address, int userId){
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

    public void postNewsletter(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt =con.createStatement();
            String sql="UPDATE user SET newsletter=1 WHERE id="+userId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNewsletter(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt =con.createStatement();
            String sql="UPDATE user SET newsletter=0 WHERE id="+userId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(int addressId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE address SET user_id=-1 WHERE id="+addressId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWishList(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int wishListId = this.findWishListId(userId);
        try {
            stmt=con.createStatement();
            String sql="DELETE FROM wish_list_article_version WHERE wish_list_id="+wishListId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWishListItem(int wishListItemId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt =con.createStatement();
            String sql ="DELETE FROM wish_list_article_version WHERE ID="+wishListItemId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShoppingCartItem(int shoppingCartItemId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt =con.createStatement();
            String sql ="DELETE FROM shopping_cart_article_version WHERE ID="+shoppingCartItemId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putUser(User user, int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql ="UPDATE user SET firstname='"+ user.getFirstname()+"', lastname='"+user.getLastname()+"', title='"+user.getTitle()+"', " +
                    "salutation='"+user.getSalutation()+"', e_mail='"+user.getMail()+"'"+"WHERE id="+userId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putShoppingCartItem(int shoppingCartItemId, int quantity){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE shopping_cart_article_version SET quantity="+quantity+" WHERE id="+shoppingCartItemId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putWishListItem(int wishListItemId, int quantity){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE wish_list_article_version SET quantity="+quantity+" WHERE id="+wishListItemId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putAddress(Address address){ //Nicht feritg
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE address SET ";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putPassword(String password, int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        String hash=this.encryptPasswordRealUser(password);
        if(!this.isRealUser(userId)){
            return;
        }
        try {
            stmt = con.createStatement();
            String sql="UPDATE user SET password='"+hash+"' WHERE id="+userId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean postWishList(int userId){
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

    private boolean postShoppingCart(int userId){
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

    private int findWishListId(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int wishListId =-1;
        try {
            stmt = con.createStatement();
            String sql="SELECT id FROM wish_list WHERE user_id="+userId+";";
            ResultSet rs = stmt.executeQuery(sql);
            wishListId = rs.getInt("id");
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishListId;
    }

    private String encryptPasswordRealUser(String password){
        String hash = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
        return hash;
    }

    private String encryptPasswordDummyUser(String password){
        String hash = Hashing.sha1().hashString(password, StandardCharsets.UTF_8).toString();
        return hash;
    }

    private boolean isRealUser(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        boolean real=false;
        try {
            stmt = con.createStatement();
            String sql="SELECT real_user FROM user WHERE id="+userId+";";
            ResultSet rs = stmt.executeQuery(sql);
            real = rs.getBoolean("real_user");
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return real;
    }

    public static void main(String[] args) throws SQLException {
        DataAccessShopDatabase s = new DataAccessShopDatabase();
        User u = new User();
        s.putPassword("987654", 1);
    }
}