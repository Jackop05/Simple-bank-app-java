package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JFrame {
    BankService bankService = new BankService();

    public LoginScreen() {
        // Set the frame properties
        setTitle("Banking App Login");
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
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(40, 400 + marginAfterUsername + 40 + marginAfterPassword, getWidth() - 80, 50); // Adjust position
        loginButton.setFont(new Font("Dialog", Font.BOLD, 24));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = bankService.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + user.getUsername());
                dispose(); // Close this frame
                new MainMenuScreen(bankService, user).setVisible(true); // Open Main Menu Screen
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
        add(loginButton);

        // Create and configure the registration label
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0, 480 + marginAfterUsername + 50 + marginAfterPassword, getWidth() - 10, 30); // Adjust position
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new RegisterScreen().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
