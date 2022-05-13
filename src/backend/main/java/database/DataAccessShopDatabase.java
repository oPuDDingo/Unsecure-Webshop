package backend.main.java.database;

import backend.main.java.models.*;
import com.google.common.hash.Hashing;
import org.checkerframework.checker.units.qual.A;

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
            String sql ="INSERT INTO User(e_mail, firstname, lastname, password,real_user) " +
                    "VALUES('"+user.getEmail()+"', '"+user.getFirstname()+"', '"+user.getLastname()+"', '"+this.encryptPasswordRealUser(user.getPassword())+"',"+1+");";
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

    public boolean putAddress(Address address, int userId){
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
                    "salutation='"+user.getSalutation()+"', e_mail='"+user.getMail()+"', profile_picture='"+user.getProfilePicturePath()+"', description='"+user.getDescription()+"' "+"WHERE id="+userId+";";
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

    public boolean checkAuthData(int userId, String password){
        Connection con = this.createConnection();
        Statement stmt =null;
        String hash="";
        String rightPassword="";
        if(this.isRealUser(userId)){
            hash=this.encryptPasswordRealUser(password);
        }
        else{
            hash=this.encryptPasswordDummyUser(password);
        }
        try {
            stmt = con.createStatement();
            String sql = "SELECT password FROM user WHERE id="+userId+";";
            ResultSet rs = stmt.executeQuery(sql);
            rightPassword = rs.getString("password");
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(hash.equals(rightPassword)){
            return true;
        }
        else{
            return false;
        }
    }

    public void postWishListItem(ArticleVersion articleVersion, int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int wishListId=this.findWishListId(userId);
        int articleVersionId=this.findArticleVersionId(articleVersion.getArticleNumber(), articleVersion.getGbSize(), articleVersion.getColor());
        try {
            stmt =con.createStatement();
            String sql="INSERT INTO wish_list_article_version(quantity, wish_list_id, article_version_id) " +
                    "VALUES("+articleVersion.getQuantity()+", "+wishListId+", "+articleVersionId+");";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void postShoppingCartItem(ArticleVersion articleVersion, int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int shoppingCartId= this.findShoppingCartId(userId);
        int articleVersionId=this.findArticleVersionId(articleVersion.getArticleNumber(), articleVersion.getGbSize(), articleVersion.getColor());
        try {
            stmt =con.createStatement();
            String sql="INSERT INTO shopping_cart_article_version(quantity, shopping_cart_id, article_version_id) " +
                    "VALUES("+articleVersion.getQuantity()+", "+shoppingCartId+", "+articleVersionId+");";
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
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return real;
    }

    private int findArticleVersionId(int articleId, int gbSize, String color){
        Connection con = this.createConnection();
        Statement stmt =null;
        int articleVersionId=-1;
        try {
            stmt =con.createStatement();
            String sql="SELECT id FROM article_version WHERE article_id="+articleId+" AND gb_size="+gbSize+" AND color='"+color+"';";
            ResultSet rs = stmt.executeQuery(sql);
            articleVersionId = rs.getInt("id");
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleVersionId;
    }

    private int findShoppingCartId(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int shoppingCartId =-1;
        try {
            stmt = con.createStatement();
            String sql="SELECT id FROM shopping_cart WHERE user_id="+userId+";";
            ResultSet rs = stmt.executeQuery(sql);
            shoppingCartId = rs.getInt("id");
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingCartId;
    }

    public static void main(String[] args) throws SQLException {
        DataAccessShopDatabase s = new DataAccessShopDatabase();
        ArticleVersion a = new ArticleVersion();
        a.setQuantity(99);
        a.setColor("red");
        a.setGbSize(522);
        a.setArticleNumber(1);
        s.postShoppingCartItem(a, 2);
    }
}