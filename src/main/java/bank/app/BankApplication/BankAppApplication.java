package bank.app.BankApplication;

import javax.swing.SwingUtilities;

public class BankAppApplication {
    public static void main(String[] args) {
        // Ensure that the Swing UI updates are made on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new LoginScreen().setVisible(true); // Create and show the LoginScreen
        });
    }
}
