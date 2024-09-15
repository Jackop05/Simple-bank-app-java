package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends JFrame {

    public MainMenuScreen(BankService bankService, User user) {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername(), SwingConstants.CENTER);
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton logoutButton = new JButton("Logout");

        checkBalanceButton.addActionListener(e -> {
            double balance = bankService.getBalance(user);
            JOptionPane.showMessageDialog(this, "Your current balance is: $" + balance);
        });

        logoutButton.addActionListener(e -> {
            dispose(); // Close this frame
            new LoginScreen(); // Open Login Screen
        });

        add(welcomeLabel);
        add(checkBalanceButton);
        add(logoutButton);

        setVisible(true);
    }
}
