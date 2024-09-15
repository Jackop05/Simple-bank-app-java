package bank.app.BankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankService {

    // Register a new user
    public boolean register(String username, String password) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String checkUserQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery);
            checkUserStmt.setString(1, username);
            ResultSet rs = checkUserStmt.executeQuery();

            if (rs.next()) {
                // Username already exists
                return false;
            }

            String insertUserQuery = "INSERT INTO users (username, password, balance) VALUES (?, ?, ?)";
            PreparedStatement insertUserStmt = connection.prepareStatement(insertUserQuery);
            insertUserStmt.setString(1, username);
            insertUserStmt.setString(2, password);
            insertUserStmt.setDouble(3, 0.0);  // Initial balance is 0.0
            insertUserStmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Log in an existing user
    public User login(String username, String password) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get the user's balance
    public double getBalance(User user) {
        return user.getBalance();
    }
}
