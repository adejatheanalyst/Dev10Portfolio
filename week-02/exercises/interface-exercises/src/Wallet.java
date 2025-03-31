/**
 * A Wallet stores money.
 * All positive deposits are allowed.
 * Withdraws are allowed up to the balance.
 */
public class Wallet implements MoneyStorage {

    private double balance;
    private String description;

    public Wallet(double startingBalance, String description) {
        this.balance = startingBalance;
        this.description = description;
    }

    public Wallet() {

    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public double withdraw(double amount) {
        // can't withdraw a negative amount
        if (amount <= 0.0) {
            return 0.0;
        }

        double result = Math.min(amount, balance);
        balance -= result;
        return result;
    }
}
