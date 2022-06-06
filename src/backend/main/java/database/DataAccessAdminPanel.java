package backend.main.java.database;

import backend.main.java.models.RankingRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessAdminPanel {

    private Connection createConnection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/backend/main/java/database/adminPanel.db");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Can't create Connection!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        }
        return c;
    }

    public void lookForClient(String ipAddress){
        if(this.checkClientExist(ipAddress)){
            this.postClient(ipAddress);
        }
    }

    public List<RankingRow> getRanking(){
        Connection con = this.createConnection();
        Statement stmt = null;
        List<RankingRow> ranking = new ArrayList<>();
        try {
            stmt = con.createStatement();
            String sql="SELECT *, SUM(sql_injection+blind_sql_injection+email_without_at+xss+profile_picture+html_comment_user+price_order_bug+guess_user_login+guess_coupon+delete_user+comment_xss+look_for_email_address+hash_user) as sum FROM ranking GROUP BY ip_address ORDER BY sum DESC;;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                ranking.add( new RankingRow(rs.getString("ip_address"), rs.getBoolean("sql_injection"), rs.getBoolean("blind_sql_injection"), rs.getBoolean("email_without_at"), rs.getBoolean("xss"), rs.getBoolean("profile_picture"),
                        rs.getBoolean("html_comment_user"), rs.getBoolean("price_order_bug"), rs.getBoolean("guess_user_login"), rs.getBoolean("guess_coupon"),
                        rs.getBoolean("delete_user"), rs.getBoolean("comment_xss"), rs.getBoolean("look_for_email_address"), rs.getBoolean("hash_user")));
            }
            rs.close();;
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ranking;
    }

    public boolean login(String username, String password){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="SELECT password FROM admin WHERE username='"+username+"';";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                DataAccessShopDatabase dasd = new DataAccessShopDatabase();
                if(dasd.encryptPasswordRealUser(password).equals(rs.getString("password"))){
                    return true;
                }
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void putSqlInjection(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET sql_injection=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putBlindSqlInjection(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET blind_sql_injection=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emailWithoutAt(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET email_without_at=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putXss(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET xss=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putProfilePicture(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET profile_picture=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putHtmlCommentUser(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET html_comment_user=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putPriceOrderBug(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET price_order_bug=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putGuessUSerLogin(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET guess_user_login=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putGuessCoupon(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET guess_coupon=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putDeleteUSer(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET delete_user=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putCommentXss(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET comment_xss=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putLookForEmail(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET look_for_email_address=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putHashUser(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="UPDATE ranking SET hash_user=1 WHERE ip_address='"+ipAddress+"';";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void postClient(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql="INSERT INTO ranking(ip_address) VALUES('"+ipAddress+"');";
            stmt.execute(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkClientExist(String ipAddress){
        Connection con = this.createConnection();
        Statement stmt = null;
        try {
            stmt=con.createStatement();
            String sql="SELECT ip_address FROM ranking WHERE ip_address='"+ipAddress+"';";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next() && rs.getString("ip_address").equals(ipAddress)){
                return true;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main (String[] args){
        DataAccessAdminPanel a = new DataAccessAdminPanel();
        a.putSqlInjection("128.0.0.1");
    }
}
