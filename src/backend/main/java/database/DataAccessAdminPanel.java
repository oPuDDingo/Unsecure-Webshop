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

    public void lookForCLient(String ipAddess){
        if(this.checkClientExist(ipAddess)){
            this.postClient(ipAddess);
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
        DataAccessShopDatabase dasd = new DataAccessShopDatabase();
        System.out.println(a.login("i", "c"));
    }
}
