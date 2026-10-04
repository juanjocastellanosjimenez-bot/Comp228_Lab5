import java.util.Scanner;

// MainDriver: console program that uses Scanner to create 3 accounts and
// run one deposit and one withdrawal on each. The program never stops
// because of a bad input or a banking error.
public class MainDriver {

    private static final int ACCOUNT_COUNT = 3;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== Simple Banking System =====");
        BankAccount[] accounts = new BankAccount[ACCOUNT_COUNT];   // 3 objects in an array

        // Step 1: create the accounts (keeps asking until the data is valid)
        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            System.out.println("\n--- Create account " + (i + 1) + " of " + ACCOUNT_COUNT + " ---");
            accounts[i] = createAccount();
            System.out.println("Account created: " + accounts[i]);
        }

        // Step 2: one deposit and one withdrawal for each account
        for (BankAccount account : accounts) {
            System.out.println("\n===== Transactions for " + account.getAccountHolder() + " =====");
            runTransaction(account, true);    // deposit
            runTransaction(account, false);   // withdrawal
        }

        // Step 3: final summary
        System.out.println("\n===== Final balances =====");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
        System.out.println("\nProgram finished normally.");
        scanner.close();
    }

    // Asks for each field separately, so only the wrong value is asked again.
    // Each loop uses try/catch to handle the IllegalArgumentException.
    private static BankAccount createAccount() {
        String number;
        while (true) {
            try {
                number = readLine("Enter 9-digit account number: ");
                BankAccount.validateAccountNumber(number);   // throws if not 9 digits
                break;                                       // valid -> leave the loop
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            }
        }

        String name;
        while (true) {
            try {
                name = readLine("Enter account holder name: ");
                BankAccount.validateAccountHolder(name);     // throws if blank
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            }
        }

        double balance;
        while (true) {
            try {
                balance = readDouble("Enter opening balance: ");
                BankAccount.validateInitialBalance(balance); // throws if negative
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            }
        }

        return new BankAccount(number, name, balance);
    }

    // One deposit or withdrawal. If it fails the user can retry.
    // try = the risky code, catch = handle each exception type,
    // finally = ALWAYS runs (success or error).
    private static void runTransaction(BankAccount account, boolean isDeposit) {
        String type = isDeposit ? "deposit" : "withdrawal";
        boolean done = false;

        while (!done) {
            try {
                double amount = readDouble("Enter " + type + " amount: ");
                if (isDeposit) {
                    account.deposit(amount);
                } else {
                    performWithdrawal(account, amount);
                }
                System.out.printf("Success: %s of $%.2f completed. New balance: $%,.2f%n",
                        type, amount, account.getBalance());
                done = true;
            } catch (InsufficientFundsException e) {
                // Our custom exception: not enough money in the account
                System.out.println("Banking error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                // Zero or negative amount
                System.out.println("Invalid transaction: " + e.getMessage());
            } finally {
                // Runs after every attempt, no matter what happened above
                System.out.println("[Transaction processed: " + type + " attempt on account "
                        + account.getAccountNumber() + "]");
            }

            if (!done) {
                String again = readLine("Try the " + type + " again? (y/n): ");
                if (!again.equalsIgnoreCase("y")) {
                    System.out.println("Skipping " + type + " for this account.");
                    done = true;
                }
            }
        }
    }

    // 'throws' tells the caller it must catch InsufficientFundsException
    private static void performWithdrawal(BankAccount account, double amount)
            throws InsufficientFundsException {
        account.withdraw(amount);
    }

    // Reads one line of text; ends the program cleanly if there is no more input
    private static String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            System.out.println("\nNo more input available. Exiting.");
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }

    // Keeps asking until the user types a valid number (handles letters / symbols)
    private static double readDouble(String prompt) {
        while (true) {
            String text = readLine(prompt);
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: '" + text + "' is not a number. Please enter digits only (e.g. 250.75).");
            }
        }
    }
}
