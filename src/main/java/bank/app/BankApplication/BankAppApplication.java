package bank.app.BankApplication;

import java.util.Scanner;

public class BankAppApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Bank App!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    // Register a new user
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (bankService.register(username, password)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Username already exists or an error occurred.");
                    }
                    break;

                case 2:
                    // Login for existing users
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    User loggedInUser = bankService.login(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
                        loggedInMenu(scanner, bankService, loggedInUser);
                    } else {
                        System.out.println("Invalid credentials, please try again.");
                    }
                    break;

                case 3:
                    // Exit the application
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void loggedInMenu(Scanner scanner, BankService bankService, User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Logout");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check user balance
                    System.out.println("Your current balance is: $" + bankService.getBalance(user));
                    break;

                case 2:
                    // Logout
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
