
package edu.iud.ea1.func.iud.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/EA1-Funcionarios-IUD";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
