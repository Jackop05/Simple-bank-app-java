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

    // Deposit money into the user's account
    public void deposit(User user, double amount) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String updateBalanceQuery = "UPDATE users SET balance = balance + ? WHERE id = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateBalanceQuery);
            updateStmt.setDouble(1, amount);
            updateStmt.setInt(2, user.getId());
            updateStmt.executeUpdate();

            // Update the user's balance in the User object
            double newBalance = getBalance(user) + amount;
            user.setBalance(newBalance);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Withdraw money from the user's account
    public boolean withdraw(User user, double amount) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            // Check if the user has enough balance
            String checkBalanceQuery = "SELECT balance FROM users WHERE id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkBalanceQuery);
            checkStmt.setInt(1, user.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                if (currentBalance >= amount) {
                    // Update balance
                    String updateBalanceQuery = "UPDATE users SET balance = balance - ? WHERE id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateBalanceQuery);
                    updateStmt.setDouble(1, amount);
                    updateStmt.setInt(2, user.getId());
                    updateStmt.executeUpdate();

                    // Update the user's balance in the User object
                    double newBalance = getBalance(user) - amount;
                    user.setBalance(newBalance);

                    return true;
                } else {
                    return false; // Insufficient funds
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Error or user not found
    }
}
