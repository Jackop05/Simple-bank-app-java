package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;

public class BankAppApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAppApplication().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Bank Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        showLoginScreen(frame);

        frame.setVisible(true);
    }

    private void showLoginScreen(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        BankService bankService = new BankService();

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            User user = bankService.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + user.getUsername());
                showMainMenu(frame, bankService, user);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });

        registerButton.addActionListener(e -> showRegisterScreen(frame));

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(registerButton);

        frame.revalidate();
        frame.repaint();
    }

    private void showRegisterScreen(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        BankService bankService = new BankService();

        registerButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (bankService.register(username, password)) {
                JOptionPane.showMessageDialog(frame, "Registration successful!");
                showLoginScreen(frame);
            } else {
                JOptionPane.showMessageDialog(frame, "Username already exists or registration failed.");
            }
        });

        backButton.addActionListener(e -> showLoginScreen(frame));

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(registerButton);
        frame.add(backButton);

        frame.revalidate();
        frame.repaint();
    }

    private void showMainMenu(JFrame frame, BankService bankService, User user) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername());
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton logoutButton = new JButton("Logout");

        checkBalanceButton.addActionListener(e -> {
            double balance = bankService.getBalance(user);
            JOptionPane.showMessageDialog(frame, "Your current balance is: $" + balance);
        });

        logoutButton.addActionListener(e -> showLoginScreen(frame));

        frame.add(welcomeLabel);
        frame.add(checkBalanceButton);
        frame.add(logoutButton);

        frame.revalidate();
        frame.repaint();
    }
}
