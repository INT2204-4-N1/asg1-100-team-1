package data;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:C:\\Github\\asg1-100-team-1\\src\\data\\dict_hh.db");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
