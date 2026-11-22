import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

public class ATMSimulation {
    public static void processWithdrawal(String accountFile, double balance, String amountStr) {
        try {
            // Simulate checking card-reader file presence
            File file = new File(accountFile);
            if (!file.exists()) {
                throw new FileNotFoundException("Card-reader hardware configuration file missing: " + accountFile);
            }

            // Parse amount (can throw NumberFormatException if letters are entered)
            double amount = Double.parseDouble(amountStr);

            if (amount < 0) {
                throw new InvalidAmountException("Withdrawal amount cannot be negative.");
            }

            if (amount > balance) {
                throw new InsufficientBalanceException("Requested amount exceeds available account balance.");
            }

            balance -= amount;
            System.out.println("Success! Dispensed: $" + amount + " | Remaining Balance: $" + balance);

        } catch (NumberFormatException | InvalidAmountException e) {
            System.out.println("[Catch - Unchecked/Input Error]: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("[Catch - Business Logic Error]: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("[Catch - Checked Exception / System Error]: " + e.getMessage());
        } finally {
            System.out.println("[Finally Block]: Card session logs closed. Session terminated safely.\n");
        }
    }

    public static void main(String[] args) {
        double currentBalance = 5000.00;
        String validFile = "card_config.txt";

        // Test 1: Valid withdrawal
        System.out.println("Test 1: Valid input");
        processWithdrawal(validFile, currentBalance, "1000");

        // Test 2: Letters instead of numbers (NumberFormatException)
        System.out.println("Test 2: Invalid format (Letters)");
        processWithdrawal(validFile, currentBalance, "abc");

        // Test 3: Negative amount
        System.out.println("Test 3: Negative amount");
        processWithdrawal(validFile, currentBalance, "-500");

        // Test 4: Insufficient balance
        System.out.println("Test 4: Exceeds balance");
        processWithdrawal(validFile, currentBalance, "6000");

        // Test 5: Missing card reader file (FileNotFoundException)
        System.out.println("Test 5: Missing system file");
        processWithdrawal("nonexistent_reader.txt", currentBalance, "200");
    }
}
