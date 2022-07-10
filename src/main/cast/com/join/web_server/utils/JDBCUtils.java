package com.join.web_server.utils;
import java.sql.*;

public class JDBCUtils {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/mmm";
        String user = "root";
        String pswd = "root";
        Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pswd);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("conn"+e);
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement prst,Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        closeAll(prst, conn);
    }
    public static void close(PreparedStatement prst,Connection conn) {
        closeAll(prst, conn);
    }

    private static void closeAll(PreparedStatement prst, Connection conn) {
        if (prst != null) {
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}