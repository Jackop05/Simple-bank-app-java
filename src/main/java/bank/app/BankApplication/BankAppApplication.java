package bank.app.BankApplication;

import javax.swing.SwingUtilities;

public class BankAppApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);  // Start with Login Screen
    }
}
