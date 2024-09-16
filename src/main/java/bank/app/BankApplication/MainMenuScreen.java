package bank.app.BankApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen extends JFrame {
    private BankService bankService;
    private User user;
    private JLabel balanceLabel; // Add a JLabel to show the balance
    private static final Font FONT_LARGE = new Font("Dialog", Font.PLAIN, 20);

    public MainMenuScreen(BankService bankService, User user) {
        this.bankService = bankService;
        this.user = user;

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); // Adjust size as needed
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Use BorderLayout for better control

        // Create a panel for the center content with GridBagLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin around the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Gap between components

        // Create and configure the welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername(), SwingConstants.CENTER);
        welcomeLabel.setFont(FONT_LARGE); // Set font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        centerPanel.add(welcomeLabel, gbc);

        // Create and configure the balance label
        balanceLabel = new JLabel("Balance: $" + user.getBalance());
        balanceLabel.setFont(FONT_LARGE); // Set font size
        gbc.gridy = 1;
        centerPanel.add(balanceLabel, gbc);

        // Create and configure the deposit button
        JButton depositButton = new JButton("Deposit Money");
        depositButton.setFont(FONT_LARGE); // Set font size
        gbc.gridy = 2;
        centerPanel.add(depositButton, gbc);

        // Create and configure the withdraw button
        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.setFont(FONT_LARGE); // Set font size
        gbc.gridy = 3;
        centerPanel.add(withdrawButton, gbc);

        // Create and configure the logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(FONT_LARGE); // Set font size
        gbc.gridy = 4;
        centerPanel.add(logoutButton, gbc);

        // Add the center panel to the frame
        add(centerPanel, BorderLayout.CENTER);

        // Set up button actions
        depositButton.addActionListener(e -> handleDeposit());
        withdrawButton.addActionListener(e -> handleWithdraw());

        logoutButton.addActionListener(e -> {
            dispose(); // Close this frame
            new LoginScreen().setVisible(true); // Open Login Screen
        });

        setVisible(true);
    }

    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    bankService.deposit(user, amount);
                    JOptionPane.showMessageDialog(this, "Deposited $" + amount);
                    updateBalance(); // Update balance after deposit
                } else {
                    JOptionPane.showMessageDialog(this, "Amount must be positive.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
            }
        }
    }

    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    boolean success = bankService.withdraw(user, amount);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Withdrew $" + amount);
                        updateBalance(); // Update balance after withdrawal
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Amount must be positive.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
            }
        }
    }

    // Method to update the balance label
    private void updateBalance() {
        balanceLabel.setText("Balance: $" + user.getBalance());
    }
}
