/**
 * MoneyStorage defines a type that can store money.
 */
public interface MoneyStorage {
    /**
     * @return the current balance
     */
    double getBalance();

    /**
     * @return a description of the MoneyStorage
     */
    String getDescription();

    /**
     * @param amount money to add to the balance.
     * @return true if the deposit was successful, false if not.
     */
    boolean deposit(double amount);

    /**
     * @param amount money to remove from the balance.
     * @return the amount of money successfully withdrawn.
     * May not be the full parameter amount if rules doesn't allow it.
     */
    double withdraw(double amount);
}
