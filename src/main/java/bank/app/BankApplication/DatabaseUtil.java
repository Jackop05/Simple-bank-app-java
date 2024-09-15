package bank.app.BankApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/bankdb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Malgosia1006";  // Replace with your PostgreSQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
