package backend.main.java.database;

import java.sql.*;

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
        System.out.println(a.checkClientExist("127.0.0.1"));
    }
}
