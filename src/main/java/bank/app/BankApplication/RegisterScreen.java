package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JFrame {

    public RegisterScreen() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

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
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose(); // Close this frame
                new LoginScreen(); // Open Login Screen
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists or registration failed.");
            }
        });

        backButton.addActionListener(e -> {
            dispose(); // Close this frame
            new LoginScreen(); // Open Login Screen
        });

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(registerButton);
        add(backButton);

        setVisible(true);
    }
}
