/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
 
    public static Connection c;
    private static String db_url = "jdbc:mysql://localhost:3306/quanlysanpham";
    private static String username = "root";
    private static String password = "";//vertrigo

    public static Connection getConnection() throws Exception {
        if (c == null) {
            c = DriverManager.getConnection(db_url, username, password);
        }
        return c;
    }  
}
