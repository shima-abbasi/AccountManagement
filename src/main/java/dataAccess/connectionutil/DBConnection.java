package dataAccess.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    private static Connection connection;
    private static String username="root";
    private static String password="root";
    private static String url = "jdbc:mysql://localhost:3306/account?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static String dbName="mysql_java_db2";

    private DBConnection() {
    }

    public static Connection getDBConnection(){

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to mysql ");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return connection;

    }

}