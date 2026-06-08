package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    private static Connection conn;

    private DatabaseService() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        if (conn == null || conn.isClosed()) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/training",
                    "root",
                    "root"
            );

            System.out.println("Database connected successfully.");
        }

        return conn;
    }
}