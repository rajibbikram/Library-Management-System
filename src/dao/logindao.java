package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import userDao.User;

public class logindao {
    public String dologin(Connection conn,String userName, String password) throws SQLException {
        String query = "select * from login where username = ? and password = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()){
                 if(rs.next()) {
                     return rs.getString("userType");
                 }
            }
        }
        return null;
    }

        public void registerUser(Connection conn, User user) throws SQLException {

            String query = "INSERT INTO login(username, password, userType) VALUES(?,?,?)";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getUserType());

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("User Registered Successfully.");
                } else {
                    System.out.println("Failed to Register User.");
                }
            }
        }

    public void getAllUsers(Connection conn) throws SQLException {

        String sql = "SELECT id, username, userType FROM login";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println("===== REGISTERED USERS =====");

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String userType = rs.getString("userType");

            System.out.println(id + " | " + username + " | " + userType);
        }

        System.out.println("============================");
    }
}
