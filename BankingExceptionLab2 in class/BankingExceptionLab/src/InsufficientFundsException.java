// Custom exception for the banking lab.
// It is a CHECKED exception (extends Exception), so any method that can
// throw it must declare "throws" and the caller must catch it.
public class InsufficientFundsException extends Exception {

    // Keep the numbers so the program can report them later
    private final double balance;
    private final double requestedAmount;

    public InsufficientFundsException(double balance, double requestedAmount) {
        // super(...) sets the message that getMessage() will return
        super(String.format("Insufficient funds: balance $%.2f, requested $%.2f (short by $%.2f).",
                balance, requestedAmount, requestedAmount - balance));
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    public double getBalance() {
        return balance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    // How much money is missing to complete the withdrawal
    public double getShortfall() {
        return requestedAmount - balance;
    }
}
