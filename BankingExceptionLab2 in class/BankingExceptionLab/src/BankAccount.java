// BankAccount: stores the account data and validates every operation.
// Rules: 9-digit number, non-blank name, balance never negative (2 decimals).
public class BankAccount {

    private final String accountNumber;
    private final String accountHolder;
    private double balance;

    // Constructor: validates ALL the data before the object is created.
    // If something is wrong it throws IllegalArgumentException (unchecked).
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        validateAccountNumber(accountNumber);
        validateAccountHolder(accountHolder);
        validateInitialBalance(initialBalance);
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder.trim();
        this.balance = round2(initialBalance);   // keep only 2 decimal places
    }

    // Account number must be exactly 9 digits (regex \d{9})
    public static void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || !accountNumber.matches("\\d{9}")) {
            throw new IllegalArgumentException("Account number must be exactly 9 digits.");
        }
    }

    // Name cannot be null, empty, or only spaces
    public static void validateAccountHolder(String accountHolder) {
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be blank.");
        }
    }

    // Opening balance must be a real number and not negative
    public static void validateInitialBalance(double balance) {
        validateNumber(balance);
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
    }

    // Deposit: adds money. 'throw' is used when the amount is zero or negative.
    public void deposit(double amount) {
        validateNumber(amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance = round2(balance + amount);
    }

    // Withdraw: removes money.
    // 'throws InsufficientFundsException' warns the caller that it must handle it.
    public void withdraw(double amount) throws InsufficientFundsException {
        validateNumber(amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        // Cannot take out more than the available balance -> custom exception
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance = round2(balance - amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account %s | %-12s | Balance: $%,.2f", accountNumber, accountHolder, balance);
    }

    // Rounds to 2 decimals so the balance always has cent precision
    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Rejects NaN and Infinity (they are valid doubles but not valid money)
    private static void validateNumber(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Amount must be a valid number.");
        }
    }
}
