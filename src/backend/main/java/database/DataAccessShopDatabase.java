package src.backend.main.java.database;

import backend.main.java.models.Comment;
import backend.main.java.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void createComment(Comment comment) throws SQLException { //Probe Methode
        Connection con = this.createConnection();
        Statement stmt = con.createStatement();
        String sql= "INSERT INTO comment(comment_text) VALUES('"+comment.getCommentText()+"');";
        stmt.execute(sql);
        con.close();
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

            user.setId(stmt.getGeneratedKeys().getInt(1));
            this.createWishList(user);
            this.createShoppingCart(user);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createWishList(User user){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO wish_list(user_id) VALUES("+user.getId()+");";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createShoppingCart(User user){
        Connection con = this.createConnection();
        Statement stmt =null;
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO shopping_cart(user_id) VALUES("+user.getId()+");";
            stmt.execute(sql);
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
        User u = new User();
        u.setFirstname("Test");
        boolean erfolgreich = s.createUser(u);
        System.out.println(erfolgreich);


    }
}
