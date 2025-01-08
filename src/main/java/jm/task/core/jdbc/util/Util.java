package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc_database";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "27011998vS";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось установить подключение");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
