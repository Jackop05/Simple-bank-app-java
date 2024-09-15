package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

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
                JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + user.getUsername());
                dispose(); // Close this frame
                new MainMenuScreen(bankService, user); // Open Main Menu Screen
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });

        registerButton.addActionListener(e -> {
            dispose(); // Close this frame
            new RegisterScreen(); // Open Register Screen
        });

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(registerButton);

        setVisible(true);
    }
}
