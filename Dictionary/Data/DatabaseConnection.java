package Data;

import java.io.File;
import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            String path = new File("").getAbsolutePath();
            path = path.concat("/Dictionary/Data/dict_hh.db");

            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
