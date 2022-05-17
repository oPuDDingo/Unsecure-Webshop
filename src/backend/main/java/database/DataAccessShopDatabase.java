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
                    "salutation='"+user.getSalutation()+"', e_mail='"+user.getMail()+"', profile_picture='"+user.getProfilePicture()+"', description='"+user.getDescription()+"' "+"WHERE id="+userId+";";
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

    public void putWishListItem(ArticleVersion articleVersion){
        Connection con = this.createConnection();
        Statement stmt =null;
        int articleVersionId=this.findArticleVersionId(articleVersion.getArticleNumber(), articleVersion.getGbSize(), articleVersion.getColor());
        try {
            stmt = con.createStatement();
            String sql ="UPDATE wish_list_article_version SET quantity="+articleVersion.getQuantity()+", article_version_id="+articleVersionId+" WHERE id="+articleVersion.getId()+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putShoppingCartItem(ArticleVersion articleVersion){
        Connection con = this.createConnection();
        Statement stmt =null;
        int articleVersionId=this.findArticleVersionId(articleVersion.getArticleNumber(), articleVersion.getGbSize(), articleVersion.getColor());
        try {
            stmt = con.createStatement();
            String sql ="UPDATE shopping_cart_article_version SET quantity="+articleVersion.getQuantity()+", article_version_id="+articleVersionId+" WHERE id="+articleVersion.getId()+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserInformation(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        User user=null;
        try {
            stmt =con.createStatement();
            String sql="SELECT id, e_mail, firstname, lastname, newsletter, salutation, title, profile_picture, description FROM user WHERE id="+userId+";";
            System.out.println(sql);
            ResultSet rs= stmt.executeQuery(sql);
            user = new User(rs.getInt("id"), rs.getString("e_mail"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("newsletter"), rs.getString("salutation"),
                    rs.getString("title"), rs.getString("profile_picture"), rs.getString("description"));
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Address getAddress(int addressId){ //nicht fertig
        Connection con = this.createConnection();
        Statement stmt =null;
        Address address =null;
        try {
            stmt =con.createStatement();
            String sql="SELECT name, street_house_number, address_suplement, postcode, city, country, delivery_instruction FROM address WHERE id="+addressId;
            ResultSet rs = stmt.executeQuery(sql);
            address = new Address(addressId, rs.getString("name"), rs.getString("country"), rs.getString("street_house_number"), rs.getString("address_suplement"), rs.getString("postcode"), rs.getString("city"), rs.getString("delivery_instruction"));
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public Payment getPayment(int orderId){
        Connection con = this.createConnection();
        Statement stmt =null;
        Payment payment =null;
        try {
            stmt =con.createStatement();
            String sql="SELECT iban, bic, account_owner FROM sales_order WHERE id="+orderId+";";
            ResultSet rs = stmt.executeQuery(sql);
            payment = new Payment(rs.getString("iban"), rs.getString("bic"), rs.getString("account_owner"));
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public void postOrder(Order order, int userId, boolean clean){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql="INSERT INTO sales_order(order_date, amount, iban, bic, account_owner, user_id, address_id) " +
                    "VALUES('"+order.getOrderDate()+"', "+order.getAmount()+", '"+order.getPayment().getIban()+"', '"+order.getPayment().getBic()+"', '"+order.getPayment().getAccountHolder()+
                    "', "+userId+", "+order.getAddress().getId()+");";
            String sql2="SELECT last_insert_rowid();";
            stmt.execute(sql);
            ResultSet rs = stmt.executeQuery(sql2);
            int orderId =rs.getInt(1);
            stmt.close();
            con.close();
            for(ArticleVersion articleVersion : order.getArticles()){
                this.postOrderItem(articleVersion, orderId);
            }
            this.deleteShoppingCart(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(clean){
            this.deleteShoppingCart(userId);
        }
    }

    public void deleteShoppingCart(int userId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int shoppingCartId = this.findShoppingCartId(userId);
        try {
            stmt=con.createStatement();
            String sql="DELETE FROM shopping_cart_article_version WHERE shopping_Cart_id="+shoppingCartId+";";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Article getArticle(int articleId){
        Connection con = this.createConnection();
        Statement stmt =null;
        Article article = null;
        try {
            stmt = con.createStatement();
            String sql="SELECT article.id, model_name, price, operating_system, release_date, screen, resolution, valuation_sum, number_of_valuation, name FROM article INNER JOIN brand ON brand.id=article.brand_id WHERE article.id="+articleId+";";
            ResultSet rs = stmt.executeQuery(sql);
            article = new Article(rs.getInt("id"), rs.getString("model_name"), rs.getDouble("price"), Math.round(rs.getInt("valuation_sum")/rs.getInt("number_of_valuation")), rs.getString("operating_system"),
                    rs.getString("release_date"), rs.getString("screen"), rs.getString("resolution"), rs.getString("name"),this.getComments(articleId), this.getPictureIds(articleId));
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
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

    private void postOrderItem(ArticleVersion articleVersion, int orderId){
        Connection con = this.createConnection();
        Statement stmt =null;
        int articleVersionId=this.findArticleVersionId(articleVersion.getArticleNumber(), articleVersion.getGbSize(), articleVersion.getColor());
        try {
            stmt =con.createStatement();
            String sql="INSERT INTO sales_order_article_version(quantity, sales_order_id, article_version_id) " +
                    "VALUES("+articleVersion.getQuantity()+", "+orderId+", "+articleVersionId+");";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getPictureIds(int articleId){
        Connection con = this.createConnection();
        Statement stmt =null;
        List<Integer> pictureIds = new ArrayList<>();
        try {
            stmt = con.createStatement();
            String sql="SELECT id FROM picture WHERE article_id="+articleId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                pictureIds.add(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pictureIds;
    }

    private List<Comment> getComments(int articleId){
        Connection con = this.createConnection();
        Statement stmt =null;
        List<Comment> comments = new ArrayList<>();
        try {
            stmt = con.createStatement();
            String sql="SELECT comment.id AS commentId, comment.comment_text, user.firstname, user.lastname, user.id AS userId, user.profile_picture FROM comment INNER JOIN user ON comment.user_id = user.id " +
                    "WHERE article_id="+articleId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                comments.add(new Comment(rs.getInt("commentId"), rs.getString("comment_text"), rs.getInt("userId"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("profile_picture")));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void main(String[] args) throws SQLException {
        DataAccessShopDatabase s = new DataAccessShopDatabase();
        Article a = s.getArticle(1);
        System.out.println(a.getBrand());
        System.out.println(a.getModelName());
    }
}