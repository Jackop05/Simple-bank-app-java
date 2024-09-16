package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterScreen extends JFrame {
    BankService bankService = new BankService();

    public RegisterScreen() {
        // Set the frame properties
        setTitle("Banking App Register");
        setSize(600, 800); // Ensure the frame is large enough
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use absolute positioning
        setLocationRelativeTo(null); // Center the frame on the screen

        // Add GUI components
        addGuiComponents();
    }

    private void addGuiComponents() {
        // Create and configure the banking app label
        JLabel bankingAppLabel = new JLabel("Bank Application");
        bankingAppLabel.setBounds(0, 30, getWidth(), 50); // Centered title
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 36));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);

        // Create and configure the username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 120, getWidth() - 80, 30); // Adjust position
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameLabel);

        // Create and configure the username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(40, 160, getWidth() - 80, 40); // Adjust position
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameField);

        // Add margin after the username field
        int marginAfterUsername = 30;

        // Create and configure the password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 200 + marginAfterUsername, getWidth() - 80, 30); // Adjust position
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordLabel);

        // Create and configure the password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(40, 240 + marginAfterUsername, getWidth() - 80, 40); // Adjust position
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordField);

        // Add margin after the password field
        int marginAfterPassword = 30;

        // Create and configure the login button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(40, 400 + marginAfterUsername + 40 + marginAfterPassword, getWidth() - 80, 50); // Adjust position
        registerButton.setFont(new Font("Dialog", Font.BOLD, 24));
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (bankService.register(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose(); // Close this frame
                new LoginScreen().setVisible(true); // Open Login Screen
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists or registration failed.");
            }
        });
        add(registerButton);

        // Create and configure the registration label
        JLabel loginLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        loginLabel.setBounds(0, 480 + marginAfterUsername + 50 + marginAfterPassword, getWidth() - 10, 30); // Adjust position
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close this frame
                new LoginScreen().setVisible(true);
            }
        });
        add(loginLabel);
    }
}